package by.babanin.ext.component.validation;

import org.apache.commons.lang3.StringUtils;

import by.babanin.ext.component.logger.LogMessageType;
import by.babanin.ext.component.validation.exception.ValidationException;
import by.babanin.ext.message.TranslateCode;
import by.babanin.ext.message.Translator;

public class LengthLimitValidation implements Validator {

    private static final int DEFAULT_MAX_LENGTH = 255;

    private final int maxLength;
    private final String fieldCaption;

    public LengthLimitValidation(String fieldCaption) {
        this(fieldCaption, DEFAULT_MAX_LENGTH);
    }

    public LengthLimitValidation(String fieldCaption, int maxLength) {
        if(StringUtils.isBlank(fieldCaption)) {
            throw new ValidationException("fieldCaption can't be blank");
        }
        if(maxLength <= 0) {
            throw new ValidationException("maxLength should be more 0");
        }
        this.fieldCaption = fieldCaption;
        this.maxLength = maxLength;
    }

    @Override
    public ValidationResult validate(Object currentValue, Object newValue) {
        ValidationResult result = new ValidationResult();
        if(newValue != null) {
            if(newValue instanceof String name) {
                if(name.length() > maxLength) {
                    String lengthLimitMessage = Translator.toLocale(TranslateCode.LENGTH_LIMIT).formatted(fieldCaption, maxLength);
                    result.put(LogMessageType.ERROR, lengthLimitMessage);
                }
            }
            else {
                throw new ValidationException(newValue.getClass().getSimpleName() + " value type should be String");
            }
        }
        return result;
    }
}
