package by.babanin.ext.representation;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import by.babanin.ext.representation.exception.RepresentationException;

public class Representation<C> {

    private final Class<C> componentClass;
    private final String componentName;
    private final List<ReportField> fields;

    public Representation(Class<C> componentClass) {
        this.componentClass = componentClass;
        this.componentName = getNameAttribute(componentClass);
        this.fields = collectReportFields();
    }

    private String getNameAttribute(Class<C> componentClass) {
        String name = componentClass.getAnnotation(ReportableComponent.class).name();
        if(StringUtils.isBlank(name)) {
            throw new RepresentationException("Name attribute of @ReportableComponent is blank");
        }
        return name;
    }

    private List<ReportField> collectReportFields() {
        List<ReportField> reportFields = Stream.of(componentClass.getDeclaredFields())
                .filter(field -> field.getAnnotation(ReportableField.class) != null)
                .map(ReportField::new)
                .sorted(Comparator.comparingLong(ReportField::getIndex))
                .toList();
        Set<Integer> indexes = new HashSet<>();
        for(ReportField field : reportFields) {
            if(!indexes.add(field.getIndex())) {
                throw new RepresentationException("Index of " + field.getName() + " field is duplicated");
            }
        }
        return reportFields;
    }

    public Class<C> getComponentClass() {
        return componentClass;
    }

    public String getComponentName() {
        return componentName;
    }

    public List<ReportField> getFields() {
        return fields;
    }

    public ReportField getField(String name) {
        return fields.stream()
                .filter(field -> field.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RepresentationException(name + " doesn't exist"));
    }

    public Object getValueAt(C component, ReportField field) {
        return field.getValue(component);
    }
}
