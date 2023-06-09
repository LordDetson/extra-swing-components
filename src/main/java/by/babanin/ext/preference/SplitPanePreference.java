package by.babanin.ext.preference;

import javax.swing.JSplitPane;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SplitPanePreference implements Preference {

    @JsonProperty("proportion")
    private double proportion;

    public void storeProportion(JSplitPane splitPane) {
        double size;
        if(splitPane.getOrientation() == JSplitPane.VERTICAL_SPLIT) {
            size = splitPane.getHeight();
        }
        else {
            size = splitPane.getWidth();
        }
        setProportion(roundToSecondDecimalPlace(splitPane.getDividerLocation() / size));
    }

    private static double roundToSecondDecimalPlace(double number) {
        return Math.round(number * 100) / 100.0;
    }
}
