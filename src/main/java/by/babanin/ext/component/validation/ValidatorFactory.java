package by.babanin.ext.component.validation;

import java.util.List;

import by.babanin.ext.representation.ReportField;

@FunctionalInterface
public interface ValidatorFactory {

    List<Validator> factor(ReportField field);
}
