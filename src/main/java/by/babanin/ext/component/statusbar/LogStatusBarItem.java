package by.babanin.ext.component.statusbar;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.StringJoiner;

import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import by.babanin.ext.component.action.Action;
import by.babanin.ext.component.exception.UIException;
import by.babanin.ext.component.logger.CombinedLogPanel;
import by.babanin.ext.component.logger.CombinedLogger;
import by.babanin.ext.component.logger.LogMessageType;
import by.babanin.ext.component.logger.LogUpdateListener;
import by.babanin.ext.component.logger.Logger;
import by.babanin.ext.component.util.GUIUtils;
import by.babanin.ext.component.util.IconRegister;
import by.babanin.ext.message.TranslateCode;
import by.babanin.ext.message.Translator;

public class LogStatusBarItem extends StatusBarItem {

    private static final String LOG_SHOWING_ACTION_KEY = "showLog";
    private static final String LOG_CLOSING_ACTION_KEY = "closeLog";
    private final Map<LogOwner, Logger> loggerMap = new LinkedHashMap<>();
    private final transient CombinedLogger combinedLogger = new CombinedLogger();

    private final transient Action showLogAction;

    public LogStatusBarItem() {
        showLogAction = createShowLogAction();
        getLabel().addMouseListener(createClickActionListener());
    }

    private Action createShowLogAction() {
        return Action.builder()
                .id(LOG_SHOWING_ACTION_KEY)
                .accelerator(KeyStroke.getKeyStroke("control L"))
                .action(actionEvent -> {
                    Window window = GUIUtils.getWindowOwner((Component) actionEvent.getSource());
                    JDialog dialog = new JDialog(window, ModalityType.APPLICATION_MODAL);
                    dialog.setContentPane(new CombinedLogPanel(combinedLogger));
                    dialog.setTitle(Translator.toLocale(TranslateCode.LOG_MESSAGES));
                    Dimension smallFrameSize = GUIUtils.getSmallFrameSize();
                    dialog.setMinimumSize(smallFrameSize);
                    dialog.setSize(smallFrameSize);
                    dialog.setLocationRelativeTo(window);
                    GUIUtils.addCloseActionOnEscape(dialog, LOG_CLOSING_ACTION_KEY);
                    dialog.setVisible(true);
                })
                .build();
    }

    private MouseListener createClickActionListener() {
        return new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent event) {
                if(!event.isConsumed() && event.getClickCount() == 1 && event.getButton() == MouseEvent.BUTTON1
                        && showLogAction.isEnabled()) {
                    showLogAction.actionPerformed(new ActionEvent(LogStatusBarItem.this, ActionEvent.ACTION_PERFORMED,
                            showLogAction.getId()));
                    event.consume();
                }
            }
        };
    }

    public void addLogger(Component owner, String title, Logger log) {
        LogOwner logOwner = new LogOwner(owner, title);
        if(loggerMap.containsKey(logOwner)) {
            throw new UIException("A log supplier is already registered for given owner with same title: " + title);
        }
        log.addLogChangeListener(createStatusBarItemUpdater());
        loggerMap.put(logOwner, log);
        updateStatusBarInfo();
    }

    private LogUpdateListener createStatusBarItemUpdater() {
        return new LogUpdateListener() {

            @Override
            public void logChanged() {
                reload();
            }

            @Override
            public void logCleared() {
                reload();
            }
        };
    }

    private void reload() {
        if(!loggerMap.isEmpty()) {
            clear();
            load();
        }
    }

    private void load() {
        for(Entry<LogOwner, Logger> entry : loggerMap.entrySet()) {
            LogOwner key = entry.getKey();
            Logger logger = entry.getValue();
            for(LogMessageType type : LogMessageType.values()) {
                if(logger.hasMessages(type)) {
                    logger.getMessages(type).forEach(message -> combinedLogger.logMessage(key.title(), type, message));
                }
            }
        }
        updateStatusBarInfo();
    }

    private void clear() {
        combinedLogger.clear();
        updateStatusBarInfo();
    }

    public void updateStatusBarInfo() {
        int count = combinedLogger.getMessageCount();
        if(count == 0) {
            setIcon(null);
            setText("");
            setToolTipText("");
            showLogAction.setEnabled(false);
        }
        else {
            Collection<Logger> loggers = combinedLogger.getLoggers();
            Logger firstLogger = loggers.iterator().next();
            LogMessageType firstType = firstLogger.getMessageTypes().iterator().next();
            String message = firstLogger.getMessages(firstType).iterator().next();
            StringJoiner joiner = new StringJoiner("\n");
            loggers.forEach(logger -> logger.getMessageTypes().forEach(type -> logger.getMessages(type).forEach(joiner::add)));

            setIcon(IconRegister.get(firstType));
            setToolTipText(joiner.toString());
            if(count == 1) {
                setText(message);
            }
            else {
                setText(Translator.toLocale(TranslateCode.ERROR_AND_X_MORE_CLICK).formatted(message, count - 1));
            }
            showLogAction.setEnabled(true);
        }
    }

    public void addLogShowingAction(JDialog dialog) {
        JRootPane rootPane = dialog.getRootPane();
        rootPane.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(showLogAction.getAccelerator(), showLogAction.getId());
        rootPane.getActionMap().put(showLogAction.getId(), showLogAction);
    }

    private record LogOwner(Component component, String title) {

        @Override
        public boolean equals(Object o) {
            if(this == o) {
                return true;
            }
            if(o == null || getClass() != o.getClass()) {
                return false;
            }
            LogOwner that = (LogOwner) o;
            return Objects.equals(component, that.component) && Objects.equals(title, that.title);
        }

        @Override
        public int hashCode() {
            return Objects.hash(component, title);
        }
    }
}
