package by.babanin.ext.preference.until;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import by.babanin.ext.preference.Preference;
import by.babanin.ext.preference.PreferenceAware;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PreferenceUtils {

    /**
     * Some components depend on the size of the component.
     * Unfortunately the component size isn't applied immediately.
     * So apply preferences for the component after component size is applied.
     * @param component is an object to which the preferences will be applied
     * @param preference is an object with preference parameters
     * @param <C> is the type of the component
     * @param <T> is the type of the preference
     */
    public static <C extends Component & PreferenceAware<T>, T extends Preference> void applyLater(C component, T preference) {
        component.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                component.apply(preference);
                component.removeComponentListener(this);
            }
        });
    }
}
