package by.babanin.ext.settings.style.theme;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.apache.commons.collections4.map.PredicatedMap;
import org.apache.commons.lang3.StringUtils;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class ThemesRegister implements Map<String, ThemeInfo>, Iterable<Map.Entry<String, ThemeInfo>> {

    private static ThemesRegister instance;

    public static ThemesRegister getInstance() {
        ThemesRegister result = instance;
        if(result != null) {
            return result;
        }
        synchronized(ThemesRegister.class) {
            if(instance == null) {
                instance = new ThemesRegister();
            }
            return instance;
        }
    }

    private final Map<String, ThemeInfo> themeMap;

    public ThemesRegister() {
        Predicate<String> blankCheck = StringUtils::isNotBlank;
        Predicate<Object> nullCheck = PredicateUtils.notNullPredicate();
        this.themeMap = PredicatedMap.predicatedMap(new LinkedHashMap<>(), blankCheck, nullCheck);
        initDefaults();
    }

    private void initDefaults() {
        put("flatLightLaf", "FlatLaf Light", FlatLightLaf.class);
        put("flatDarkLaf", "FlatLaf Dark", FlatDarkLaf.class);
        put("flatIntelliJLaf", "FlatLaf IntelliJ", FlatIntelliJLaf.class);
        put("flatDarculaLaf", "FlatLaf Darcula", FlatDarculaLaf.class);
        put("flatMacLightLaf", "FlatLaf MacOS Light", FlatMacLightLaf.class);
        put("flatMacDarkLaf", "FlatLaf MacOS Dark", FlatMacDarkLaf.class);
    }

    @Override
    public int size() {
        return themeMap.size();
    }

    @Override
    public boolean isEmpty() {
        return themeMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return themeMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return themeMap.containsValue(value);
    }

    @Override
    public ThemeInfo get(Object key) {
        return Optional.ofNullable(themeMap.get(key))
                .orElseThrow(() -> new IllegalArgumentException("Theme with id '" + key + "' not found"));
    }

    public void put(String id, String name, Class<?> clazz) {
        put(id, name, new ClassThemeSource(clazz));
    }

    public void put(String id, String name, Path filePath) {
        put(id, name, new FileThemeSource(filePath));
    }

    public void put(String id, String name, ThemeSource themeSource) {
        put(id, new ThemeInfo(id, name, themeSource));
    }

    @Override
    public ThemeInfo put(String key, ThemeInfo value) {
        return themeMap.put(key, value);
    }

    @Override
    public ThemeInfo remove(Object key) {
        return themeMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends ThemeInfo> m) {
        themeMap.putAll(m);
    }

    @Override
    public void clear() {
        themeMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return themeMap.keySet();
    }

    @Override
    public Collection<ThemeInfo> values() {
        return themeMap.values();
    }

    @Override
    public Set<Entry<String, ThemeInfo>> entrySet() {
        return themeMap.entrySet();
    }

    @Override
    public Iterator<Entry<String, ThemeInfo>> iterator() {
        return entrySet().iterator();
    }
}
