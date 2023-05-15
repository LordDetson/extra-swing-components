package by.babanin.ext.message;

import java.util.Locale;
import java.util.function.Supplier;

import by.babanin.ext.representation.RepresentationRegister;
import by.babanin.ext.representation.ReportField;
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
        String componentName = RepresentationRegister.get(componentClass).getComponentName();
        return getComponentCaption(componentName);
    }

    public static String getComponentCaption(String componentName) {
        return getComponentCaption(TranslateCode.COMPONENT_CAPTION, componentName);
    }

    public static <T> String getComponentPluralCaption(Class<T> componentClass) {
        String componentName = RepresentationRegister.get(componentClass).getComponentName();
        return getComponentPluralCaption(componentName);
    }

    public static String getComponentPluralCaption(String componentName) {
        return getComponentCaption(TranslateCode.COMPONENT_PLURAL_CAPTION, componentName);
    }

    private static String getComponentCaption(String code, String componentName) {
        return Translator.toLocale(String.format(code, componentName));
    }

    public static String getSettingViewTitle(SettingViewType type) {
        return Translator.toLocale(String.format(TranslateCode.SETTINGS_VIEW_TITLE, type.getId()));
    }

    public static String getSettingViewDescription(SettingViewType type) {
        return Translator.toLocale(String.format(TranslateCode.SETTINGS_VIEW_DESCRIPTION, type.getId()));
    }

    public static String getFieldCaption(ReportField field) {
        return Translator.toLocale(String.format(TranslateCode.FIELD_CAPTION, field.getRepresentation().getComponentName(), field.getName()));
    }
}
