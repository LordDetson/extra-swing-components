package by.babanin.ext.settings;

@FunctionalInterface
public interface SettingsSubscriber {

    void handleSettingsUpdateEvent(SettingsUpdateEvent event);
}
