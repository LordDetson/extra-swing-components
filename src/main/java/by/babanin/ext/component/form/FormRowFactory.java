package by.babanin.ext.component.form;

import by.babanin.ext.representation.ReportField;

@FunctionalInterface
public interface FormRowFactory {

    FormRow<Object> factor(ReportField field);
}
