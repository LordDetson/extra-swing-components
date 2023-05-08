package by.babanin.ext.preference.deserialization;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import by.babanin.ext.preference.Preference;
import by.babanin.ext.preference.PreferencesGroup;

public class PreferencesGroupDeserializer extends StdDeserializer<PreferencesGroup> {

    public PreferencesGroupDeserializer() {
        super(PreferencesGroup.class);
    }

    @Override
    public PreferencesGroup deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        PreferencesGroup preferencesGroup = new PreferencesGroup();
        TreeNode treeNode = p.readValueAsTree();
        ObjectCodec codec = p.getCodec();
        for(Iterator<String> it = treeNode.fieldNames(); it.hasNext(); ) {
            String key = it.next();
            ObjectNode next = (ObjectNode) treeNode.get(key);
            preferencesGroup.put(key, next.traverse(codec).readValueAs(Preference.class));
        }
        return preferencesGroup;
    }
}
