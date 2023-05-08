package by.babanin.ext.component.table.adjustment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import by.babanin.ext.component.exception.UIException;
import by.babanin.ext.settings.Setting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableColumnAdjustment implements Setting {

    public static final String ID = "tableColumnAdjustment";

    @Builder.Default
    private boolean columnHeaderIncluded = true;

    @Builder.Default
    private boolean columnContentIncluded = true;

    @Builder.Default
    private boolean onlyAdjustLarger = false;

    @Builder.Default
    private boolean dynamicAdjustment = true;

    @Override
    public void update(Setting setting) {
        TableColumnAdjustment adjustment = (TableColumnAdjustment) setting;
        this.columnHeaderIncluded = adjustment.columnHeaderIncluded;
        this.columnContentIncluded = adjustment.columnContentIncluded;
        this.onlyAdjustLarger = adjustment.onlyAdjustLarger;
        this.dynamicAdjustment = adjustment.dynamicAdjustment;
    }

    @Override
    public TableColumnAdjustment clone() {
        JsonMapper mapper = new JsonMapper();
        try {
            return mapper.readValue(mapper.writeValueAsString(this), TableColumnAdjustment.class);
        }
        catch(JsonProcessingException e) {
            throw new UIException("Unexpected exception when cloning TableColumnAdjustment", e);
        }
    }
}
