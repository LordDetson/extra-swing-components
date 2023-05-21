package by.babanin.ext.settings;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.map.PredicatedMap;
import org.apache.commons.lang3.StringUtils;

import by.babanin.ext.settings.view.SettingViewType;

public class SettingsViewRegister implements Map<String, SettingViewType>, Iterable<Map.Entry<String, SettingViewType>> {

    private static SettingsViewRegister instance;

    public static SettingsViewRegister getInstance() {
        SettingsViewRegister result = instance;
        if(result != null) {
            return result;
        }
        synchronized(SettingsViewRegister.class) {
            if(instance == null) {
                instance = new SettingsViewRegister();
            }
            return instance;
        }
    }

    private final Map<String, SettingViewType> settingMap;

    public SettingsViewRegister() {
        Predicate<String> blankCheck = StringUtils::isNotBlank;
        Predicate<Object> nullCheck = PredicateUtils.notNullPredicate();
        this.settingMap = PredicatedMap.predicatedMap(new LinkedHashMap<>(), blankCheck, nullCheck);
    }

    @Override
    public int size() {
        return settingMap.size();
    }

    @Override
    public boolean isEmpty() {
        return settingMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return settingMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return settingMap.containsValue(value);
    }

    @Override
    public SettingViewType get(Object key) {
        return settingMap.get(key);
    }

    @Override
    public SettingViewType put(String key, SettingViewType value) {
        return settingMap.put(key, value);
    }

    @Override
    public SettingViewType remove(Object key) {
        return settingMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends SettingViewType> m) {
        settingMap.putAll(m);
    }

    @Override
    public void clear() {
        settingMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return settingMap.keySet();
    }

    @Override
    public Collection<SettingViewType> values() {
        return settingMap.values();
    }

    @Override
    public Set<Entry<String, SettingViewType>> entrySet() {
        return settingMap.entrySet();
    }

    @Override
    public Iterator<Entry<String, SettingViewType>> iterator() {
        return entrySet().iterator();
    }
}
