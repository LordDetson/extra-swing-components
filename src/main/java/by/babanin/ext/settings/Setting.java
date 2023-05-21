package by.babanin.ext.settings;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import by.babanin.ext.component.table.adjustment.TableColumnAdjustment;
import by.babanin.ext.settings.style.StyleSetting;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @Type(value = TableColumnAdjustment.class, name = TableColumnAdjustment.ID),
        @Type(value = StyleSetting.class, name = StyleSetting.ID)
})
public interface Setting extends Cloneable {

    void update(Setting setting);

    Setting clone();
}
