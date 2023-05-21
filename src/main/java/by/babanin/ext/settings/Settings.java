package by.babanin.ext.settings;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.map.PredicatedMap;
import org.apache.commons.lang3.StringUtils;

import by.babanin.ext.component.table.adjustment.TableColumnAdjustment;
import by.babanin.ext.settings.style.StyleSetting;

public class Settings implements Map<String, Setting>, Iterable<Entry<String, Setting>> {

    private static Settings instance;

    public static Settings getInstance() {
        Settings result = instance;
        if(result != null) {
            return result;
        }
        synchronized(Settings.class) {
            if(instance == null) {
                instance = new Settings();
            }
            return instance;
        }
    }

    private final Map<String, Setting> settingMap;

    public Settings() {
        Predicate<String> blankCheck = StringUtils::isNotBlank;
        Predicate<Object> nullCheck = PredicateUtils.notNullPredicate();
        this.settingMap = PredicatedMap.predicatedMap(new HashMap<>(), blankCheck, nullCheck);
        init();
    }

    private void init() {
        settingMap.put(TableColumnAdjustment.ID, new TableColumnAdjustment());
        settingMap.put(StyleSetting.ID, new StyleSetting());
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
    public Setting get(Object key) {
        return settingMap.get(key);
    }

    @Override
    public Setting put(String key, Setting value) {
        return settingMap.put(key, value);
    }

    @Override
    public Setting remove(Object key) {
        return settingMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Setting> m) {
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
    public Collection<Setting> values() {
        return settingMap.values();
    }

    @Override
    public Set<Entry<String, Setting>> entrySet() {
        return settingMap.entrySet();
    }

    @Override
    public Iterator<Entry<String, Setting>> iterator() {
        return entrySet().iterator();
    }
}
