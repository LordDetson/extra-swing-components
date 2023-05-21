package by.babanin.ext.settings.style.theme;

import java.io.FileInputStream;
import java.nio.file.Path;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;

import by.babanin.ext.component.exception.UIException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ThemeManager {

    public static void applyAndUpdateUI(ThemeSource source) {
        FlatAnimatedLafChange.showSnapshot();
        apply(source);
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public static void apply(ThemeSource source) {
        if(source instanceof ClassThemeSource classThemeSource) {
            String className = classThemeSource.clazz().getName();
            if(!className.equals(UIManager.getLookAndFeel().getClass().getName())) {
                try {
                    UIManager.setLookAndFeel(className);
                }
                catch(Exception e) {
                    throw new UIException("Unexpected exception when applying theme " + className, e);
                }
            }
        }
        else if(source instanceof FileThemeSource fileThemeSource) {
            Path filePath = fileThemeSource.filePath();
            try {
                if(filePath.endsWith(".properties")) {
                    FlatLaf.setup(new FlatPropertiesLaf(filePath.getFileName().toString(), filePath.toFile()));
                }
                else {
                    FlatLaf.setup(IntelliJTheme.createLaf(new FileInputStream(filePath.toFile())));
                }
            }
            catch(Exception e) {
                throw new UIException("Unexpected exception when applying theme from file " + filePath, e);
            }
        }
        else {
            throw new UIException("Unsupported theme source " + source.getClass().getSimpleName());
        }
    }
}
