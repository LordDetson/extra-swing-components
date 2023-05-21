package by.babanin.ext.settings.style.color;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.util.ColorFunctions;

import by.babanin.ext.settings.style.StyleSetting;

public class AccentColorsToolBar extends JToolBar {

    public AccentColorsToolBar(StyleSetting setting, StyleSetting accumulator, Runnable fireChange) {
        ButtonGroup group = new ButtonGroup();
        for(AccentColor accentColor : AccentColorManager.values()) {
            JToggleButton button = new JToggleButton(new AccentColorIcon(accentColor));
            button.setToolTipText(accentColor.name());
            button.addActionListener(e -> {
                accumulator.setAccentColor(accentColor.id());
                fireChange.run();
            });
            group.add(button);
            add(button);
            if(accentColor.id().equals(setting.getAccentColor())) {
                button.setSelected(true);
            }
        }
    }

    private static class AccentColorIcon extends FlatAbstractIcon {

        private final AccentColor accentColor;

        AccentColorIcon(AccentColor accentColor) {
            super(16, 16, null);
            this.accentColor = accentColor;
        }

        @Override
        protected void paintIcon(Component c, Graphics2D g) {
            Color color = accentColor.get();
            if(color == null) {
                color = Color.lightGray;
            }
            else if(!c.isEnabled()) {
                color = FlatLaf.isLafDark()
                        ? ColorFunctions.shade(color, 0.5f)
                        : ColorFunctions.tint(color, 0.6f);
            }

            g.setColor(color);
            g.fillRoundRect(1, 1, width - 2, height - 2, 5, 5);
        }
    }
}
