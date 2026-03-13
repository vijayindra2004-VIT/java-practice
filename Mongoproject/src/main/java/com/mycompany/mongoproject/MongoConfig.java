/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mongoproject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class MongoConfig {
    private static final String URI="mongodb://localhost:27017";
    public static MongoDatabase getDatabase(){
        MongoClient mongoClient = MongoClients.create(URI);
        return mongoClient.getDatabase("students");
    }
    
}
