package by.babanin.ext.preference;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class StringsPreference implements Preference {

    private final List<String> values = new ArrayList<>();
}
