package by.babanin.ext.preference;

import java.awt.Dimension;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DimensionPreference implements Preference {

    @JsonProperty("dimension")
    private Dimension dimension;
}
