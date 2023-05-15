package by.babanin.ext.component.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

import by.babanin.ext.component.logger.LogMessageType;

public class ReservedWordsValidator implements Validator {

    private final List<String> reservedWords = new ArrayList<>();

    public ReservedWordsValidator(Collection<String> reservedWords) {
        this.reservedWords.addAll(reservedWords);
    }

    @Override
    public ValidationResult validate(Object currentValue, Object newValue) {
        ValidationResult result = new ValidationResult();
        if(newValue instanceof String name && reservedWords.contains(name)) {
            StringJoiner joiner = new StringJoiner(", ");
            reservedWords.forEach(joiner::add);
            result.put(LogMessageType.ERROR, "\"" + name + "\" is forbidden to use. Reserved words: " + joiner);
        }
        return result;
    }
}
