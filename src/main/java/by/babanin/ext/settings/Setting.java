package by.babanin.ext.settings;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import by.babanin.ext.component.table.adjustment.TableColumnAdjustment;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @Type(value = TableColumnAdjustment.class, name = TableColumnAdjustment.ID)
})
public interface Setting extends Cloneable {

    void update(Setting setting);

    Setting clone();
}
