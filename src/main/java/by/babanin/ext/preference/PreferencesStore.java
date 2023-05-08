package by.babanin.ext.preference;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.map.PredicatedMap;
import org.apache.commons.lang3.StringUtils;

public final class PreferencesStore implements Map<String, Preference>, Iterable<Entry<String, Preference>> {

    private static PreferencesStore instance;

    public static PreferencesStore getInstance() {
        PreferencesStore result = instance;
        if(result != null) {
            return result;
        }
        synchronized(PreferencesStore.class) {
            if(instance == null) {
                instance = new PreferencesStore();
            }
            return instance;
        }
    }

    private final Map<String, Preference> preferenceMap;

    public PreferencesStore() {
        Predicate<String> blankCheck = StringUtils::isNotBlank;
        Predicate<Object> nullCheck = PredicateUtils.notNullPredicate();
        this.preferenceMap = PredicatedMap.predicatedMap(new HashMap<>(), blankCheck, nullCheck);
    }

    @Override
    public int size() {
        return preferenceMap.size();
    }

    @Override
    public boolean isEmpty() {
        return preferenceMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return preferenceMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return preferenceMap.containsValue(value);
    }

    @Override
    public Preference get(Object key) {
        return preferenceMap.get(key);
    }

    public Optional<Preference> getOpt(Object key) {
        return Optional.ofNullable(get(key));
    }

    @Override
    public Preference put(String key, Preference value) {
        return preferenceMap.put(key, value);
    }

    @Override
    public Preference remove(Object key) {
        return preferenceMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Preference> m) {
        preferenceMap.putAll(m);
    }

    @Override
    public void clear() {
        preferenceMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return preferenceMap.keySet();
    }

    @Override
    public Collection<Preference> values() {
        return preferenceMap.values();
    }

    @Override
    public Set<Entry<String, Preference>> entrySet() {
        return preferenceMap.entrySet();
    }

    @Override
    public Iterator<Entry<String, Preference>> iterator() {
        return entrySet().iterator();
    }
}
