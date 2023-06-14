package org.com.serializer;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.bson.types.ObjectId;

public class ObjectIdConverter extends StdConverter<ObjectId, String> {
    @Override
    public String convert(ObjectId id) {
        return id.toHexString();
    }
}
