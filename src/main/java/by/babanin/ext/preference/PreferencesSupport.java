package by.babanin.ext.preference;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.map.PredicatedMap;
import org.apache.commons.lang3.StringUtils;

public final class PreferencesSupport {

    private final Map<String, PreferenceAware<?>> preferenceAwareMap;

    public PreferencesSupport() {
        Predicate<String> blankCheck = StringUtils::isNotBlank;
        Predicate<Object> nullCheck = PredicateUtils.notNullPredicate();
        this.preferenceAwareMap = PredicatedMap.predicatedMap(new LinkedHashMap<>(), blankCheck, nullCheck);
    }

    public void put(PreferenceAware<?> preferenceAware) {
        String key = preferenceAware.getKey();
        preferenceAwareMap.put(key, preferenceAware);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void apply() {
        PreferencesStore preferencesStore = PreferencesStore.getInstance();
        preferenceAwareMap.forEach(
                (key, preferenceAware) -> preferencesStore.getOpt(key)
                        .ifPresentOrElse(
                                preference -> ((PreferenceAware) preferenceAware).apply(preference),
                                preferenceAware::resetToDefault
                        ));
    }

    public void save() {
        PreferencesStore preferencesStore = PreferencesStore.getInstance();
        preferenceAwareMap.forEach((key, preferenceAware) -> preferencesStore.put(key, preferenceAware.createCurrentPreference()));
    }
}
