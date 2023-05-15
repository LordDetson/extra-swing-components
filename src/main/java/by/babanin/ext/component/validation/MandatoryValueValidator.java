package by.babanin.ext.component.validation;

import org.apache.commons.lang3.StringUtils;

import by.babanin.ext.component.logger.LogMessageType;
import by.babanin.ext.component.validation.exception.ValidationException;

public class MandatoryValueValidator implements Validator {

    private final String fieldCaption;

    public MandatoryValueValidator(String fieldCaption) {
        if(StringUtils.isBlank(fieldCaption)) {
            throw new ValidationException("fieldCaption can't be blank");
        }
        this.fieldCaption = fieldCaption;
    }

    @Override
    public ValidationResult validate(Object currentValue, Object newValue) {
        ValidationResult result = new ValidationResult();
        if(newValue == null) {
            result.put(LogMessageType.ERROR, fieldCaption + " can't be empty");
        }
        return result;
    }
}
