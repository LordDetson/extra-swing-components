package by.babanin.ext.message;

import java.util.Locale;

@FunctionalInterface
public interface MessageProvider {

    String getMessage(String code, Locale locale);
}
