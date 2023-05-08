package by.babanin.ext.settings.view;

import java.util.function.Consumer;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import by.babanin.ext.component.action.Action;
import by.babanin.ext.component.table.adjustment.TableColumnAdjustment;
import by.babanin.ext.message.TranslateCode;
import by.babanin.ext.message.Translator;

public class TableColumnAdjustmentView extends AbstractSettingView<TableColumnAdjustment> {

    public TableColumnAdjustmentView(TableColumnAdjustment tableColumnAdjustment, SettingViewType settingViewType) {
        super(tableColumnAdjustment, settingViewType);
    }

    @Override
    protected JPanel createContentPanel(TableColumnAdjustment setting, TableColumnAdjustment accumulator, SettingViewType settingViewType) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(createCheckBox(
                TranslateCode.SETTINGS_COLUMNS_HEADER_INCLUDED,
                setting.isColumnHeaderIncluded(),
                accumulator::setColumnHeaderIncluded));
        panel.add(createCheckBox(
                TranslateCode.SETTINGS_COLUMNS_CONTENT_INCLUDED,
                setting.isColumnContentIncluded(),
                accumulator::setColumnContentIncluded));
        panel.add(createCheckBox(
                TranslateCode.SETTINGS_ONLY_ADJUST_LARGER,
                setting.isOnlyAdjustLarger(),
                accumulator::setOnlyAdjustLarger));
        panel.add(createCheckBox(
                TranslateCode.SETTINGS_DYNAMIC_ADJUSTMENT,
                setting.isDynamicAdjustment(),
                accumulator::setDynamicAdjustment));
        return panel;
    }

    private JCheckBox createCheckBox(String translateCode, boolean selected, Consumer<Boolean> setter) {
        Action action = Action.builder()
                .name(Translator.toLocale(translateCode))
                .selected(selected)
                .action(actionEvent -> {
                    setter.accept(((AbstractButton) actionEvent.getSource()).isSelected());
                    fireChange();
                })
                .build();
        return new JCheckBox(action);
    }
}
