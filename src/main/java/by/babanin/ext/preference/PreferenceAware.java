package by.babanin.ext.preference;

public interface PreferenceAware<T extends Preference> {

    void apply(T preference);

    T createCurrentPreference();

    T createDefaultPreference();

    default void resetToDefault() {
        apply(createDefaultPreference());
    }

    String getKey();
}
