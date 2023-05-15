package by.babanin.ext.representation;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import by.babanin.ext.representation.exception.RepresentationException;

public class ReportField {

    private final Field field;
    private final int index;
    private final boolean mandatory;

    public ReportField(Field field) {
        this.field = field;
        this.index = getIndexAttribute();
        this.mandatory = getMandatoryAttribute();
    }

    private int getIndexAttribute() {
        int result = getReportableField().index();
        if(result < 0) {
            throw new RepresentationException("Index of " + getName() + " field must be positive");
        }
        return result;
    }

    private boolean getMandatoryAttribute() {
        return getReportableField().mandatory();
    }

    private ReportableField getReportableField() {
        return field.getAnnotation(ReportableField.class);
    }

    public int getIndex() {
        return index;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public String getName() {
        return field.getName();
    }

    public Class<?> getDeclaringClass() {
        return field.getDeclaringClass();
    }

    public Representation<?> getRepresentation() {
        return RepresentationRegister.get(getDeclaringClass());
    }

    public Class<?> getType() {
        return field.getType();
    }

    public Object getValue(Object o) {
        try {
            return getReadMethod().invoke(o);
        }
        catch(IllegalAccessException | InvocationTargetException e) {
            throw new RepresentationException(e);
        }
    }

    private Method getReadMethod() {
        try {
            String name = getName();
            for (PropertyDescriptor pd : Introspector.getBeanInfo(getDeclaringClass()).getPropertyDescriptors()) {
                if(pd.getReadMethod() != null && name.equals(pd.getName())) {
                    return pd.getReadMethod();
                }
            }
            throw new RepresentationException(name + " field getter isn't exist");
        }
        catch(IntrospectionException e) {
            throw new RepresentationException(e);
        }
    }
}
