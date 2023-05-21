package by.babanin.ext.settings.style.font;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.util.FontUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FontManager {

    private static final List<Integer> availableFontSizes = new ArrayList<>(List.of(11, 12, 13, 14, 15, 16, 18, 20, 22));

    public static List<Integer> getAvailableFontSizes() {
        return availableFontSizes;
    }

    public static void addAvailableFontSize(int size) {
        availableFontSizes.add(size);
        availableFontSizes.sort(Integer::compareTo);
    }

    public static void removeAvailableFontSize(int size) {
        availableFontSizes.remove(Integer.valueOf(size));
    }

    public static Font getDefaultFont() {
        return UIManager.getFont("defaultFont");
    }

    public static void setDefaultFont(Font font) {
        UIManager.put("defaultFont", font);
    }

    public static void applyAndUpdateUI(String fontFamily, int style, int size) {
        FlatAnimatedLafChange.showSnapshot();
        apply(fontFamily, style, size);
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void apply(String fontFamily, int style, int size) {
        setDefaultFont(FontUtils.getCompositeFont(fontFamily, style, size));
    }
}
