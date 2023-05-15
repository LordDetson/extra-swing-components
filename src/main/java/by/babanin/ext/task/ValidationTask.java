package by.babanin.ext.task;

import java.util.ArrayList;
import java.util.List;

import by.babanin.ext.component.validation.ValidationResult;
import by.babanin.ext.component.validation.Validator;

public class ValidationTask extends AbstractTask<List<ValidationResult>> {

    private final Object currentValue;
    private final Object newValue;
    private final List<Validator> validators = new ArrayList<>();

    public ValidationTask(Object currentValue, Object newValue, List<Validator> validators) {
        this.currentValue = currentValue;
        this.newValue = newValue;
        this.validators.addAll(validators);
    }

    @Override
    protected List<ValidationResult> doInBackground() {
        return validators.stream()
                .map(validator -> validator.validate(currentValue, newValue))
                .filter(ValidationResult::isNotEmpty)
                .toList();
    }
}
