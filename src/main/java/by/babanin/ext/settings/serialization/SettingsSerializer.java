package by.babanin.ext.settings.serialization;

import java.io.IOException;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import by.babanin.ext.settings.Setting;
import by.babanin.ext.settings.Settings;

public class SettingsSerializer extends StdSerializer<Settings> {

    public SettingsSerializer() {
        super(Settings.class);
    }

    @Override
    public void serialize(Settings settings, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        for(Entry<String, Setting> entry : settings) {
            gen.writeObjectField(entry.getKey(), entry.getValue());
        }
        gen.writeEndObject();
    }
}
