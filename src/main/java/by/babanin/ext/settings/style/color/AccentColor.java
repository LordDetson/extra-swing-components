package by.babanin.ext.settings.style.color;

import java.awt.Color;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public record AccentColor(String id, String name) {

    public Color get() {
        return UIManager.getColor(id);
    }

    public static boolean isSupported() {
        Class<? extends LookAndFeel> lafClass = UIManager.getLookAndFeel().getClass();
        return isSupported(lafClass);
    }

    public static boolean isSupported(Class<?> lafClass) {
        return lafClass == FlatLightLaf.class ||
                lafClass == FlatDarkLaf.class ||
                lafClass == FlatIntelliJLaf.class ||
                lafClass == FlatDarculaLaf.class ||
                lafClass == FlatMacLightLaf.class ||
                lafClass == FlatMacDarkLaf.class;
    }
}
