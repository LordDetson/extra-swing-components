package by.babanin.ext.component;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import by.babanin.ext.component.exception.UIException;
import by.babanin.ext.component.form.FormRowFactory;
import by.babanin.ext.representation.Representation;
import by.babanin.ext.representation.Indexable;
import by.babanin.ext.representation.ReportField;
import by.babanin.ext.task.Task;

public abstract class MovableCrudTablePanel<C extends Indexable> extends CrudTablePanel<C> {

    protected MovableCrudTablePanel(Class<C> componentClass, FormRowFactory formRowFactory, CrudStyle crudStyle) {
        super(componentClass, formRowFactory, crudStyle);
    }

    @Override
    protected void createUiComponents() {
        super.createUiComponents();
        CrudStyle crudStyle = getCrudStyle();
        crudStyle.setMoveUpActionImpl(actionEvent -> moveUp());
        crudStyle.setMoveDownActionImpl(actionEvent -> moveDown());
    }

    @Override
    protected IndexableTableModel<C> createTableModel(Representation<C> representation, List<ReportField> fields) {
        return new IndexableTableModel<>(representation, fields);
    }

    @Override
    protected JPopupMenu createTablePopupMenu() {
        JPopupMenu tablePopupMenu = super.createTablePopupMenu();
        CrudStyle crudStyle = getCrudStyle();
        tablePopupMenu.add(crudStyle.getMoveUpAction());
        tablePopupMenu.add(crudStyle.getMoveDownAction());
        return tablePopupMenu;
    }

    @Override
    protected void placeComponents() {
        super.placeComponents();
        CrudStyle crudStyle = getCrudStyle();
        addToolBarComponent(new JButton(crudStyle.getMoveUpAction()));
        addToolBarComponent(new JButton(crudStyle.getMoveDownAction()));
    }

    @Override
    protected void actionEnabling() {
        super.actionEnabling();
        CrudStyle crudStyle = getCrudStyle();
        JTable table = getTable();
        int selectionCount = table.getSelectionModel().getSelectedItemsCount();
        crudStyle.getMoveUpAction().setEnabled(selectionCount == 1 && table.getSelectedRow() != 0);
        crudStyle.getMoveDownAction().setEnabled(selectionCount == 1 && table.getSelectedRow() != table.getRowCount() - 1);
    }

    @Override
    public IndexableTableModel<C> getModel() {
        return (IndexableTableModel<C>) super.getModel();
    }

    private void moveUp() {
        moveComponent(Direction.UP);
    }

    private void moveDown() {
        moveComponent(Direction.DOWN);
    }

    private void moveComponent(Direction direction) {
        IndexableTableModel<C> model = getModel();
        C selectedComponent = getSelectedComponent()
                .orElseThrow(() -> new UIException("Can't move component because component is not selected"));
        int selectedIndex = model.indexOf(selectedComponent);
        int directionCount = direction == Direction.UP ? -1 : 1;
        int nextIndex = selectedIndex + directionCount;
        Task<Void> task = createSwapTask(selectedIndex, nextIndex);
        task.addFinishListener(unused -> {
            model.swap(selectedIndex, nextIndex);
            selectRow(nextIndex);
        });
        task.execute();
    }

    protected abstract Task<Void> createSwapTask(int selectedIndex, int nextIndex);
}
