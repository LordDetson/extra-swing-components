package by.babanin.ext.settings.style.color;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;

import by.babanin.ext.component.exception.UIException;
import by.babanin.ext.settings.Settings;
import by.babanin.ext.settings.style.StyleSetting;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccentColorManager {

    private static final Map<String, AccentColor> ACCENT_COLORS = new LinkedHashMap<>();

    static {
        ACCENT_COLORS.put("color.accent.default", new AccentColor("color.accent.default", "Default"));
        ACCENT_COLORS.put("color.accent.blue", new AccentColor("color.accent.blue", "Blue"));
        ACCENT_COLORS.put("color.accent.purple", new AccentColor("color.accent.purple", "Purple"));
        ACCENT_COLORS.put("color.accent.red", new AccentColor("color.accent.red", "Red"));
        ACCENT_COLORS.put("color.accent.orange", new AccentColor("color.accent.orange", "Orange"));
        ACCENT_COLORS.put("color.accent.yellow", new AccentColor("color.accent.yellow", "Yellow"));
        ACCENT_COLORS.put("color.accent.green", new AccentColor("color.accent.green", "Green"));

        FlatLaf.setSystemColorGetter(name -> {
            StyleSetting styleSetting = (StyleSetting) Settings.getInstance().get(StyleSetting.ID);
            return "accent".equals(name) ? styleSetting.getAccentColorInfo().get() : null;
        });
    }

    public static Collection<AccentColor> values() {
        return ACCENT_COLORS.values();
    }

    public static AccentColor get(String id) {
        return ACCENT_COLORS.get(id);
    }

    public static void applyAndUpdateUI() {
        FlatAnimatedLafChange.showSnapshot();
        apply();
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void apply() {
        Class<? extends LookAndFeel> lafClass = UIManager.getLookAndFeel().getClass();
        try {
            FlatLaf.setup(lafClass.getDeclaredConstructor().newInstance());
        }
        catch(Exception e) {
            throw new UIException(e);
        }
    }
}
