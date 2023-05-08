package by.babanin.ext.settings;

public class SettingsUpdateEvent {

    private final Setting setting;

    public SettingsUpdateEvent(Setting setting) {
        this.setting = setting;
    }

    public Setting getSetting() {
        return setting;
    }
}
