package by.babanin.ext.preference;

import java.awt.Point;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PointPreference implements Preference {

    @JsonProperty("point")
    private Point point;
}
