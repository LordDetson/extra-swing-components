package by.babanin.ext.component.util;

import javax.swing.Icon;

@FunctionalInterface
public interface IconProvider {

    Icon get(String name, int iconSize);
}
