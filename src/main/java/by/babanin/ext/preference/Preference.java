package by.babanin.ext.preference;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @Type(value = PreferencesGroup.class, name = "group"),
        @Type(value = StringPreference.class, name = "string"),
        @Type(value = StringsPreference.class, name = "strings"),
        @Type(value = BooleanPreference.class, name = "boolean"),
        @Type(value = IntegerPreference.class, name = "integer"),
        @Type(value = DimensionPreference.class, name = "dimension"),
        @Type(value = PointPreference.class, name = "point"),
        @Type(value = SplitPanePreference.class, name = "splitPane"),
        @Type(value = TableColumnsPreference.class, name = "tableColumns"),
        @Type(value = TableColumnPreference.class, name = "tableColumn"),
        @Type(value = TableColumnAdjustmentPreference.class, name = "tableColumnAdjustment")
})
public interface Preference {

}
