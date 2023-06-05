package org.com.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class BaseMongoClient {
    MongoClient mongoClient;
    MongoCollection<Document> entityCollection;

    public BaseMongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://root:root@localhost:27017/workout?authSource=admin");
        this.mongoClient = MongoClients.create(connectionString);
    }
}
