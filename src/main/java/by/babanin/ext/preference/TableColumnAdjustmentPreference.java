package by.babanin.ext.preference;

import by.babanin.ext.component.table.adjustment.TableColumnAdjustment;
import lombok.Data;

@Data
public class TableColumnAdjustmentPreference implements Preference {

    private TableColumnAdjustment tableColumnAdjustment;
}
