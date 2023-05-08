package by.babanin.ext.message;

import java.util.Locale;
import java.util.function.Supplier;

import by.babanin.ext.settings.view.SettingViewType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Translator {

    private static Supplier<MessageProvider> messageProviderSupplier;
    private static Supplier<Locale> localeSupplier;

    public static void setMessageProvider(Supplier<MessageProvider> messageProviderSupplier) {
        Translator.messageProviderSupplier = messageProviderSupplier;
    }

    public static void setLocaleSupplier(Supplier<Locale> localeSupplier) {
        Translator.localeSupplier = localeSupplier;
    }

    public static String toLocale(String code) {
        return messageProviderSupplier.get().getMessage(code, localeSupplier.get());
    }

    public static <T> String getComponentCaption(Class<T> componentClass) {
        return Translator.toLocale(String.format(TranslateCode.COMPONENT_CAPTION, componentClass.getSimpleName()));
    }

    public static <T> String getComponentPluralCaption(Class<T> componentClass) {
        return Translator.toLocale(String.format(TranslateCode.COMPONENT_PLURAL_CAPTION, componentClass.getSimpleName()));
    }

    public static String getSettingViewTitle(SettingViewType type) {
        return Translator.toLocale(String.format(TranslateCode.SETTINGS_VIEW_TITLE, type.getId()));
    }

    public static String getSettingViewDescription(SettingViewType type) {
        return Translator.toLocale(String.format(TranslateCode.SETTINGS_VIEW_DESCRIPTION, type.getId()));
    }
}
