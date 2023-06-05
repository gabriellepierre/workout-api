package org.com.mongo;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.com.model.Program;

public class ProgramMongoClient extends BaseMongoClient {

    private final MongoCollection<Document> entityCollection;

    public ProgramMongoClient() {
        super();
        this.entityCollection = this.mongoClient.getDatabase("workout").getCollection("program");
    }
}
