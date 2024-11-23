package com.example.mongodb;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

public class MongoDBExample {

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

        MongoDatabase database = mongoClient.getDatabase("testDatabase");
        MongoCollection<Document> collection = database.getCollection("testCollection");

        Document newDocument = new Document("_id", new ObjectId())
                .append("name", "Timo")
                .append("age", 23)
                .append("city", "Espoo");

        collection.insertOne(newDocument);
        System.out.println("Dokumentti lisätty: " + newDocument.toJson());

        Document query = new Document("name", "Timo");
        FindIterable<Document> result = collection.find(query);
        for (Document doc : result) {
            System.out.println("Löydetty dokumentti: " + doc.toJson());
        }

        Document updateQuery = new Document("name", "Timo");
        Document updateDocument = new Document("$set", new Document("city", "Helsinki"));
        collection.updateOne(updateQuery, updateDocument);

        collection.deleteOne(new Document("name", "Timo"));
        System.out.println("Dokumentti poistettu.");

        mongoClient.close();
    }
}
