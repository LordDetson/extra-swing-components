package by.babanin.ext.settings.view;

import java.util.List;

import by.babanin.ext.settings.Setting;
import by.babanin.ext.settings.SettingChangeListener;

public interface SettingView<T extends Setting> {

    T getOriginal();

    T getAccumulator();

    SettingViewType getType();

    void addListener(SettingChangeListener<T> listener);

    List<SettingChangeListener<T>> getListeners();

    default void fireChange() {
        T accumulator = getAccumulator();
        getListeners().forEach(listener -> listener.settingChange(accumulator));
    }

    default boolean shouldApply() {
        return !getOriginal().equals(getAccumulator());
    }

    default T apply() {
        T original = getOriginal();
        original.update(getAccumulator());
        return original;
    }
}
