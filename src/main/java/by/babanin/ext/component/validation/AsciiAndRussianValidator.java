package by.babanin.ext.component.validation;

import org.apache.commons.lang3.CharUtils;

import by.babanin.ext.component.logger.LogMessageType;
import by.babanin.ext.component.validation.exception.ValidationException;
import by.babanin.ext.message.TranslateCode;
import by.babanin.ext.message.Translator;

public class AsciiAndRussianValidator implements Validator {

    private final String fieldCaption;

    public AsciiAndRussianValidator(String fieldCaption) {
        this.fieldCaption = fieldCaption;
    }

    @Override
    public ValidationResult validate(Object currentValue, Object newValue) {
        ValidationResult result = new ValidationResult();
        if(newValue != null) {
            if(newValue instanceof String str) {
                if(!containsAsciiAndRussian(str)) {
                    String message = Translator.toLocale(TranslateCode.NON_ASCII_PRINTABLE).formatted(fieldCaption);
                    result.put(LogMessageType.WARNING, message);
                }
            }
            else {
                throw new ValidationException(newValue.getClass().getSimpleName() + " value type should be String");
            }
        }
        return result;
    }

    public boolean containsAsciiAndRussian(final CharSequence cs) {
        if (cs == null) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            char c = cs.charAt(i);
            if (!CharUtils.isAscii(c) && (c < 'А' || c > 'я')) {
                return false;
            }
        }
        return true;
    }
}
