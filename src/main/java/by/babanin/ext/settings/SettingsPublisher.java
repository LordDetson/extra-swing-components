package by.babanin.ext.settings;

import java.util.ArrayList;
import java.util.List;

public class SettingsPublisher {

    private final List<SettingsSubscriber> subscribers = new ArrayList<>();

    public void subscribe(SettingsSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void publish(Setting setting) {
        SettingsUpdateEvent event = new SettingsUpdateEvent(setting);
        subscribers.forEach(settingsSubscriber -> settingsSubscriber.handleSettingsUpdateEvent(event));
    }
}
