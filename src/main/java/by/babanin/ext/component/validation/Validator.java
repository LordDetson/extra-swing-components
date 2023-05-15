package by.babanin.ext.component.validation;

@FunctionalInterface
public interface Validator {

    ValidationResult validate(Object currentValue, Object newValue);
}
