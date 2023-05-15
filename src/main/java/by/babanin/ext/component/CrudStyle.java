package by.babanin.ext.component;

import static by.babanin.ext.message.TranslateCode.*;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.Icon;
import javax.swing.KeyStroke;

import by.babanin.ext.component.action.Action;
import by.babanin.ext.component.exception.UIException;
import by.babanin.ext.component.util.IconRegister;
import by.babanin.ext.component.validation.ValidatorFactory;
import by.babanin.ext.message.Translator;

public class CrudStyle implements Serializable {

    private static final int DEFAULT_SMALL_ICON_SIZE = 12;
    private static final int DEFAULT_LARGE_ICON_SIZE = 48;
    private static final String SHOW_CREATION_DIALOG_ACTION_ID = "showCreationDialog";
    private static final String SHOW_EDIT_DIALOG_ACTION_ID = "showEditDialog";
    private static final String SHOW_DELETE_CONFIRM_DIALOG_ACTION_ID = "showDeleteConfirmDialog";
    private static final String MOVE_UP_ACTION_ID = "moveUp";
    private static final String MOVE_DOWN_ACTION_ID = "moveDown";

    private String createButtonIconName = "plus";
    private String editButtonIconName = "pen_write";
    private String deleteButtonIconName = "minus";
    private String moveUpButtonIconName = "chevron_up";
    private String moveDownButtonIconName = "chevron_down";
    private String createButtonToolTipCode = TOOLTIP_BUTTON_CREATE;
    private String editButtonToolTipCode = TOOLTIP_BUTTON_EDIT;
    private String deleteButtonToolTipCode = TOOLTIP_BUTTON_DELETE;
    private String moveUpButtonToolTipCode = TOOLTIP_BUTTON_MOVE_UP;
    private String moveDownButtonToolTipCode = TOOLTIP_BUTTON_MOVE_DOWN;
    private int smallIconSize = DEFAULT_SMALL_ICON_SIZE;
    private int largeIconSize = DEFAULT_LARGE_ICON_SIZE;
    private transient ValidatorFactory validatorFactory;
    private final List<String> excludedFieldFromCreationForm = new ArrayList<>();
    private final List<String> excludedFieldFromEditForm = new ArrayList<>();
    private final List<String> excludedFieldFromTable = new ArrayList<>();

    private final Map<String, Action> crudActionMap = new HashMap<>();
    private final Map<String, Consumer<ActionEvent>> crudActionImplMap = new HashMap<>();
    private Supplier<Action> actionFactory = Action::new;

    private Icon getCreateButtonIcon(int iconSize) {
        return IconRegister.get(createButtonIconName, iconSize);
    }

    public CrudStyle setCreateButtonIconName(String createButtonIconName) {
        this.createButtonIconName = createButtonIconName;
        return this;
    }

    private Icon getEditButtonIcon(int iconSize) {
        return IconRegister.get(editButtonIconName, iconSize);
    }

    public CrudStyle setEditButtonIconName(String editButtonIconName) {
        this.editButtonIconName = editButtonIconName;
        return this;
    }

    private Icon getDeleteButtonIcon(int iconSize) {
        return IconRegister.get(deleteButtonIconName, iconSize);
    }

    public CrudStyle setDeleteButtonIconName(String deleteButtonIconName) {
        this.deleteButtonIconName = deleteButtonIconName;
        return this;
    }

    private Icon getMoveUpButtonIcon(int iconSize) {
        return IconRegister.get(moveUpButtonIconName, iconSize);
    }

    public CrudStyle setMoveUpButtonIconName(String moveUpButtonIconName) {
        this.moveUpButtonIconName = moveUpButtonIconName;
        return this;
    }

    private Icon getMoveDownButtonIcon(int iconSize) {
        return IconRegister.get(moveDownButtonIconName, iconSize);
    }

    public String getCreateButtonToolTip() {
        return Translator.toLocale(createButtonToolTipCode);
    }

    public CrudStyle setCreateButtonToolTipCode(String createButtonToolTipCode) {
        this.createButtonToolTipCode = createButtonToolTipCode;
        return this;
    }

    public String getEditButtonToolTip() {
        return Translator.toLocale(editButtonToolTipCode);
    }

    public CrudStyle setEditButtonToolTipCode(String editButtonToolTipCode) {
        this.editButtonToolTipCode = editButtonToolTipCode;
        return this;
    }

    public String getDeleteButtonToolTip() {
        return Translator.toLocale(deleteButtonToolTipCode);
    }

    public CrudStyle setDeleteButtonToolTipCode(String deleteButtonToolTipCode) {
        this.deleteButtonToolTipCode = deleteButtonToolTipCode;
        return this;
    }

    public String getMoveUpButtonToolTip() {
        return Translator.toLocale(moveUpButtonToolTipCode);
    }

    public CrudStyle setMoveUpButtonToolTipCode(String moveUpButtonToolTipCode) {
        this.moveUpButtonToolTipCode = moveUpButtonToolTipCode;
        return this;
    }

    public String getMoveDownButtonToolTip() {
        return Translator.toLocale(moveDownButtonToolTipCode);
    }

    public CrudStyle setMoveDownButtonToolTipCode(String moveDownButtonToolTipCode) {
        this.moveDownButtonToolTipCode = moveDownButtonToolTipCode;
        return this;
    }

    public CrudStyle setMoveDownButtonIconName(String moveDownButtonIconName) {
        this.moveDownButtonIconName = moveDownButtonIconName;
        return this;
    }

    public int getSmallIconSize() {
        return smallIconSize;
    }

    public void setSmallIconSize(int smallIconSize) {
        this.smallIconSize = smallIconSize;
    }

    public int getLargeIconSize() {
        return largeIconSize;
    }

    public CrudStyle setLargeIconSize(int largeIconSize) {
        this.largeIconSize = largeIconSize;
        return this;
    }

    public Optional<ValidatorFactory> getValidatorFactory() {
        return Optional.ofNullable(validatorFactory);
    }

    public CrudStyle setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
        return this;
    }

    public List<String> getExcludedFieldFromCreationForm() {
        return Collections.unmodifiableList(excludedFieldFromCreationForm);
    }

    public CrudStyle excludeFieldFromCreationForm(String... fields) {
        this.excludedFieldFromCreationForm.addAll(List.of(fields));
        return this;
    }

    public List<String> getExcludedFieldFromEditForm() {
        return Collections.unmodifiableList(excludedFieldFromEditForm);
    }

    public CrudStyle excludeFieldFromEditForm(String... fields) {
        this.excludedFieldFromEditForm.addAll(List.of(fields));
        return this;
    }

    public List<String> getExcludedFieldFromTable() {
        return Collections.unmodifiableList(excludedFieldFromTable);
    }

    public CrudStyle excludeFieldFromTable(String... fields) {
        this.excludedFieldFromTable.addAll(List.of(fields));
        return this;
    }

    @SuppressWarnings("unchecked")
    public void setActionFactory(Supplier<? extends Action> actionFactory) {
        this.actionFactory = (Supplier<Action>) actionFactory;
    }

    public void setShowCreationDialogActionImpl(Consumer<ActionEvent> actionImpl) {
        crudActionImplMap.put(SHOW_CREATION_DIALOG_ACTION_ID, actionImpl);
    }

    public Action getShowCreationDialogAction() {
        return crudActionMap.computeIfAbsent(SHOW_CREATION_DIALOG_ACTION_ID,
                id -> Action.builder(actionFactory)
                        .id(id)
                        .name(getCreateButtonToolTip())
                        .toolTip(getCreateButtonToolTip())
                        .smallIcon(getCreateButtonIcon(smallIconSize))
                        .largeIcon(getCreateButtonIcon(largeIconSize))
                        .accelerator(KeyStroke.getKeyStroke("control SPACE"))
                        .action(getActionImpl(id))
                        .build());
    }

    public void setShowEditDialogActionImpl(Consumer<ActionEvent> actionImpl) {
        crudActionImplMap.put(SHOW_EDIT_DIALOG_ACTION_ID, actionImpl);
    }

    public Action getShowEditDialogAction() {
        return crudActionMap.computeIfAbsent(SHOW_EDIT_DIALOG_ACTION_ID,
                id -> Action.builder(actionFactory)
                        .id(id)
                        .name(getEditButtonToolTip())
                        .toolTip(getEditButtonToolTip())
                        .smallIcon(getEditButtonIcon(smallIconSize))
                        .largeIcon(getEditButtonIcon(largeIconSize))
                        .accelerator(KeyStroke.getKeyStroke("control E"))
                        .action(getActionImpl(id))
                        .build());
    }

    public void setShowDeleteConfirmDialogActionImpl(Consumer<ActionEvent> actionImpl) {
        crudActionImplMap.put(SHOW_DELETE_CONFIRM_DIALOG_ACTION_ID, actionImpl);
    }

    public Action getShowDeleteConfirmDialogAction() {
        return crudActionMap.computeIfAbsent(SHOW_DELETE_CONFIRM_DIALOG_ACTION_ID,
                id -> Action.builder(actionFactory)
                        .id(id)
                        .name(getDeleteButtonToolTip())
                        .toolTip(getDeleteButtonToolTip())
                        .smallIcon(getDeleteButtonIcon(smallIconSize))
                        .largeIcon(getDeleteButtonIcon(largeIconSize))
                        .accelerator(KeyStroke.getKeyStroke("DELETE"))
                        .action(getActionImpl(id))
                        .build());
    }

    public void setMoveUpActionImpl(Consumer<ActionEvent> actionImpl) {
        crudActionImplMap.put(MOVE_UP_ACTION_ID, actionImpl);
    }

    public Action getMoveUpAction() {
        return crudActionMap.computeIfAbsent(MOVE_UP_ACTION_ID,
                id -> Action.builder(actionFactory)
                        .id(id)
                        .name(getMoveUpButtonToolTip())
                        .toolTip(getMoveUpButtonToolTip())
                        .smallIcon(getMoveUpButtonIcon(smallIconSize))
                        .largeIcon(getMoveUpButtonIcon(largeIconSize))
                        .accelerator(KeyStroke.getKeyStroke("control UP"))
                        .action(getActionImpl(id))
                        .build());
    }

    public void setMoveDownActionImpl(Consumer<ActionEvent> actionImpl) {
        crudActionImplMap.put(MOVE_DOWN_ACTION_ID, actionImpl);
    }

    public Action getMoveDownAction() {
        return crudActionMap.computeIfAbsent(MOVE_DOWN_ACTION_ID,
                id -> Action.builder(actionFactory)
                        .id(id)
                        .name(getMoveDownButtonToolTip())
                        .toolTip(getMoveDownButtonToolTip())
                        .smallIcon(getMoveDownButtonIcon(smallIconSize))
                        .largeIcon(getMoveDownButtonIcon(largeIconSize))
                        .accelerator(KeyStroke.getKeyStroke("control DOWN"))
                        .action(getActionImpl(id))
                        .build());
    }

    private Consumer<ActionEvent> getActionImpl(String id) {
        Consumer<ActionEvent> action = crudActionImplMap.get(id);
        if(action == null) {
            throw new UIException("No action implementation found for " + id);
        }
        return action;
    }
}
