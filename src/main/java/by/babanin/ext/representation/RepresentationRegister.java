package by.babanin.ext.representation;

import java.util.HashMap;
import java.util.Map;

import by.babanin.ext.representation.exception.RepresentationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RepresentationRegister {

    private static final Map<Class<?>, Representation<?>> COMPONENT_REPRESENTATION_MAP = new HashMap<>();

    public static <T> void register(Class<T> componentClass) {
        ReportableComponent annotation = componentClass.getAnnotation(ReportableComponent.class);
        if(annotation == null) {
            throw new RepresentationException("Component " + componentClass.getName() + " isn't annotated by @ReportableComponent");
        }
        COMPONENT_REPRESENTATION_MAP.put(componentClass, new Representation<>(componentClass));
    }

    @SuppressWarnings("unchecked")
    public static <T> Representation<T> get(Class<T> componentClass) {
        Representation<T> representation = (Representation<T>) COMPONENT_REPRESENTATION_MAP.get(componentClass);
        if(representation == null) {
            throw new RepresentationException("Component representation wasn't registered for " + componentClass.getName());
        }
        return representation;
    }
}
