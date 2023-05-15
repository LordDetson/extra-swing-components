package by.babanin.ext.component.form;

import java.util.Map;
import java.util.function.Consumer;

import by.babanin.ext.representation.ReportField;

@FunctionalInterface
public interface ApplyListener extends Consumer<Map<ReportField, ?>> {

}
