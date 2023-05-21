package by.babanin.ext.settings.style;

import java.awt.Font;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.util.FontUtils;

import by.babanin.ext.message.TranslateCode;
import by.babanin.ext.message.Translator;
import by.babanin.ext.settings.style.color.AccentColor;
import by.babanin.ext.settings.style.color.AccentColorsToolBar;
import by.babanin.ext.settings.style.font.FontManager;
import by.babanin.ext.settings.style.theme.ClassThemeSource;
import by.babanin.ext.settings.style.theme.ThemeInfo;
import by.babanin.ext.settings.style.theme.ThemeInfoRenderer;
import by.babanin.ext.settings.style.theme.ThemesRegister;
import by.babanin.ext.settings.view.AbstractSettingView;
import by.babanin.ext.settings.view.SettingViewType;
import net.miginfocom.swing.MigLayout;

public class StyleView extends AbstractSettingView<StyleSetting> {

    private AccentColorsToolBar accentColorsToolBar;

    public StyleView(StyleSetting setting, SettingViewType type) {
        super(setting, type);
    }

    @Override
    protected JPanel createContentPanel(StyleSetting setting, StyleSetting accumulator, SettingViewType settingViewType) {
        JPanel panel = new JPanel(new MigLayout("insets 5 0 5 0"));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.add(createLabel(TranslateCode.SETTINGS_STYLE_THEME));
        panel.add(createThemeComboBox(setting, accumulator), "wrap");
        panel.add(createLabel(TranslateCode.SETTINGS_STYLE_FONT));
        panel.add(createFontComboBox(setting, accumulator), "wrap");
        panel.add(createLabel(TranslateCode.SETTINGS_STYLE_FONT_SIZE));
        panel.add(createFontSizeComboBox(setting, accumulator), "wrap");
        panel.add(createLabel(TranslateCode.SETTINGS_STYLE_ACCENT_COLOR));
        accentColorsToolBar = createAccentColorsToolBar(setting, accumulator);
        panel.add(accentColorsToolBar);
        accentColorsToolBar.setVisible(AccentColor.isSupported());
        UIManager.addPropertyChangeListener(e -> {
            if("lookAndFeel".equals(e.getPropertyName())) {
                accentColorsToolBar.setVisible(AccentColor.isSupported());
            }
        });
        return panel;
    }

    private static JLabel createLabel(String captionCode) {
        return new JLabel(Translator.toLocale(captionCode) + ":");
    }

    private JComboBox<ThemeInfo> createThemeComboBox(StyleSetting setting, StyleSetting accumulator) {
        ThemesRegister themesRegister = ThemesRegister.getInstance();
        DefaultComboBoxModel<ThemeInfo> themeComboBoxModel = new DefaultComboBoxModel<>();
        themeComboBoxModel.addAll(themesRegister.values());
        JComboBox<ThemeInfo> themeComboBox = new JComboBox<>(themeComboBoxModel);
        themeComboBox.setRenderer(new ThemeInfoRenderer());
        themeComboBox.setSelectedItem(setting.getThemeInfo());
        themeComboBox.addItemListener(e -> {
            ThemeInfo themeInfo = (ThemeInfo) e.getItem();
            accumulator.setTheme(themeInfo.id());
            if(themeInfo.source() instanceof ClassThemeSource classThemeSource) {
                accentColorsToolBar.setVisible(AccentColor.isSupported(classThemeSource.clazz()));
            }
            fireChange();
        });
        return themeComboBox;
    }

    private JComboBox<String> createFontComboBox(StyleSetting setting, StyleSetting accumulator) {
        Set<String> fontFamilies = Arrays.stream(FontUtils.getAllFonts())
                .map(Font::getFamily)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
        DefaultComboBoxModel<String> fontComboBoxModel = new DefaultComboBoxModel<>();
        fontComboBoxModel.addAll(fontFamilies);
        JComboBox<String> fontComboBox = new JComboBox<>(fontComboBoxModel);
        fontComboBox.setSelectedItem(setting.getFontFamily());
        fontComboBox.addItemListener(e -> {
            String fontFamily = (String) e.getItem();
            accumulator.setFontFamily(fontFamily);
            fireChange();
        });
        return fontComboBox;
    }

    private JComboBox<Integer> createFontSizeComboBox(StyleSetting setting, StyleSetting accumulator) {
        DefaultComboBoxModel<Integer> fontSizeComboBoxModel = new DefaultComboBoxModel<>();
        fontSizeComboBoxModel.addAll(FontManager.getAvailableFontSizes());
        JComboBox<Integer> fontSizeComboBox = new JComboBox<>(fontSizeComboBoxModel);
        fontSizeComboBox.setSelectedItem(setting.getFontSize());
        fontSizeComboBox.addItemListener(e -> {
            Integer fontSize = (Integer) e.getItem();
            accumulator.setFontSize(fontSize);
            fireChange();
        });
        return fontSizeComboBox;
    }

    private AccentColorsToolBar createAccentColorsToolBar(StyleSetting setting, StyleSetting accumulator) {
        return new AccentColorsToolBar(setting, accumulator, this::fireChange);
    }
}
