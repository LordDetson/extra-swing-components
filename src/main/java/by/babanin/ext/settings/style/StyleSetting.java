package by.babanin.ext.settings.style;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import by.babanin.ext.component.exception.UIException;
import by.babanin.ext.settings.Setting;
import by.babanin.ext.settings.style.color.AccentColor;
import by.babanin.ext.settings.style.color.AccentColorManager;
import by.babanin.ext.settings.style.theme.ThemeInfo;
import by.babanin.ext.settings.style.theme.ThemesRegister;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StyleSetting implements Setting {

    public static final String ID = "style";

    @Builder.Default
    private String theme = "flatLightLaf";

    @Builder.Default
    private String fontFamily = "Arial";

    @Builder.Default
    private Integer fontSize = 14;

    @Builder.Default
    private String accentColor = "color.accent.default";

    @Override
    public void update(Setting setting) {
        StyleSetting styleSetting = (StyleSetting) setting;
        this.theme = styleSetting.theme;
        this.fontFamily = styleSetting.fontFamily;
        this.fontSize = styleSetting.fontSize;
        this.accentColor = styleSetting.accentColor;
    }

    @JsonIgnore
    public ThemeInfo getThemeInfo() {
        return ThemesRegister.getInstance().get(theme);
    }

    @JsonIgnore
    public AccentColor getAccentColorInfo() {
        return AccentColorManager.get(accentColor);
    }

    @Override
    public StyleSetting clone() {
        JsonMapper mapper = new JsonMapper();
        try {
            return mapper.readValue(mapper.writeValueAsString(this), StyleSetting.class);
        }
        catch(JsonProcessingException e) {
            throw new UIException("Unexpected exception when cloning TableColumnAdjustment", e);
        }
    }
}
