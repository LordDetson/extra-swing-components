package by.babanin.ext.component.action;

import static javax.swing.JComponent.WHEN_FOCUSED;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.apache.commons.lang3.StringUtils;

import by.babanin.ext.component.exception.UIException;

public class BindingAction extends Action {

    public BindingAction(JComponent owner) {
        this(owner, WHEN_FOCUSED);
    }

    public BindingAction(JComponent owner, int condition) {
        addPropertyChangeListener(event -> {
            if(event.getPropertyName().equals(javax.swing.Action.ACCELERATOR_KEY)) {
                String id = getId();
                KeyStroke accelerator = getAccelerator();
                if(StringUtils.isBlank(id)) {
                    throw new UIException("Id is blank");
                }
                if(accelerator == null) {
                    throw new UIException("Accelerator is null");
                }
                owner.getInputMap(condition).put(accelerator, id);
                owner.getActionMap().put(id, BindingAction.this);
            }
        });
    }
}
