package by.babanin.ext.component;

import java.util.Collections;
import java.util.List;

import by.babanin.ext.component.exception.UIException;
import by.babanin.ext.representation.Representation;
import by.babanin.ext.representation.Indexable;
import by.babanin.ext.representation.ReportField;

public class IndexableTableModel<C extends Indexable> extends TableModel<C> {

    public IndexableTableModel(Representation<C> representation, List<ReportField> fields) {
        super(representation, fields);
    }

    public void swap(int index1, int index2) {
        if(index1 < 0 || index2 < 0) {
            throw new UIException("Indices shouldn't be less 0");
        }
        Collections.swap(getList(), index1, index2);
        fireTableRowsUpdated(Integer.min(index1, index2), Integer.max(index1, index2));
    }
}
