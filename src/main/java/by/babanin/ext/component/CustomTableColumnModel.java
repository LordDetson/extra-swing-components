package by.babanin.ext.component;

import java.util.List;

import javax.swing.table.DefaultTableColumnModel;

import by.babanin.ext.component.util.GUIUtils;
import by.babanin.ext.representation.ReportField;

public class CustomTableColumnModel extends DefaultTableColumnModel {

    public CustomTableColumnModel(List<ReportField> fields) {
        int modelIndex = 0;
        for(ReportField field : fields) {
            addColumn(GUIUtils.createTableColumn(field, modelIndex++));
        }
    }
}
