package by.babanin.ext.settings.view;

import java.util.function.BiFunction;

import by.babanin.ext.message.Translator;
import by.babanin.ext.settings.Setting;
import by.babanin.ext.settings.Settings;

public class SettingViewType {

    private final String id;
    private final BiFunction<Settings, SettingViewType, SettingView<?>> viewFactory;

    public SettingViewType(String id, BiFunction<Settings, SettingViewType, SettingView<?>> viewFactory) {
        this.id = id;
        this.viewFactory = viewFactory;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return Translator.getSettingViewTitle(this);
    }

    public String getDescription() {
        return Translator.getSettingViewDescription(this);
    }

    @SuppressWarnings("unchecked")
    public SettingView<Setting> createView(Settings settings) {
        SettingView<?> view = viewFactory.apply(settings, this);
        ((java.awt.Component) view).setVisible(false);
        return (SettingView<Setting>) view;
    }
}
