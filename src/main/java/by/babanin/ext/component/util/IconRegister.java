package by.babanin.ext.component.util;

import java.util.EnumMap;
import java.util.Map;

import javax.swing.Icon;

import by.babanin.ext.component.logger.LogMessageType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IconRegister {

    public static final int LOG_MESSAGE_TYPE_ICON_SIZE = 16;

    private static final Map<LogMessageType, Icon> LOG_MESSAGE_TYPE_ICON_MAP = new EnumMap<>(LogMessageType.class);
    private static IconProvider iconProvider;

    public static void setIconProvider(IconProvider iconProvider) {
        IconRegister.iconProvider = iconProvider;
    }

    public static void registerLogMessageTypeIcon(LogMessageType type, Icon icon) {
        LOG_MESSAGE_TYPE_ICON_MAP.put(type, icon);
    }

    public static Icon get(LogMessageType logMessageType) {
        return LOG_MESSAGE_TYPE_ICON_MAP.get(logMessageType);
    }

    public static Icon get(String name, int iconSize) {
        return iconProvider.get(name, iconSize);
    }
}
