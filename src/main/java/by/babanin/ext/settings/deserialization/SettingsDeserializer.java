package by.babanin.ext.settings.deserialization;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import by.babanin.ext.settings.Setting;
import by.babanin.ext.settings.Settings;

public class SettingsDeserializer extends StdDeserializer<Settings> {

    public SettingsDeserializer() {
        super(Settings.class);
    }

    @Override
    public Settings deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        Settings instance = Settings.getInstance();
        TreeNode treeNode = p.readValueAsTree();
        ObjectCodec codec = p.getCodec();
        for(Iterator<String> it = treeNode.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            ObjectNode next = (ObjectNode) treeNode.get(key);
            instance.put(key, next.traverse(codec).readValueAs(Setting.class));
        }
        return instance;
    }
}
