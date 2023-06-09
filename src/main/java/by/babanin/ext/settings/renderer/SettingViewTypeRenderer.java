package by.babanin.ext.settings.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import by.babanin.ext.settings.view.SettingViewType;

public class SettingViewTypeRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText(((SettingViewType) value).getTitle());
        return this;
    }
}
