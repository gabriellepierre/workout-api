package org.com.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListObjectIdDeserializer extends JsonDeserializer<List<ObjectId>> {

    @Override
    public List<ObjectId> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        ArrayNode node = mapper.readTree(p);
        List<ObjectId> objectIdList = new ArrayList<>();

        for (JsonNode jsonNode : node) {
            JsonNode oid = jsonNode.get("$oid");
            objectIdList.add(new ObjectId(oid.asText()));
        }

        return objectIdList;
    }

}
