package by.babanin.ext.component;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class TextAreaPanel extends JPanel {

    private static final int MINIMUM_HEIGHT = 150;

    private JTextArea textArea;
    private final boolean scrollable;

    public TextAreaPanel() {
        this(true);
    }

    public TextAreaPanel(boolean scrollable) {
        super(new BorderLayout());
        this.scrollable = scrollable;
        createUiComponents();
        placeComponents();
    }

    private void createUiComponents() {
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMinimumSize(new Dimension(textArea.getPreferredSize().width, MINIMUM_HEIGHT));
        textArea.setBorder(BorderFactory.createEmptyBorder());
        if(textArea.getCaret() instanceof DefaultCaret caret) {
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        }
    }

    private void placeComponents() {
        if(scrollable) {
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setMinimumSize(textArea.getMinimumSize());
            add(scrollPane, BorderLayout.CENTER);
        }
        else {
            add(textArea, BorderLayout.CENTER);
        }
    }

    public void setText(String text) {
        textArea.setText(text);
        textArea.setCaretPosition(0);
    }

    public void setEditable(boolean editable) {
        textArea.setEditable(editable);
    }
}
