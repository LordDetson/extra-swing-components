package by.babanin.ext.settings;

@FunctionalInterface
public interface SettingChangeListener<T extends Setting> {

    void settingChange(T setting);
}
