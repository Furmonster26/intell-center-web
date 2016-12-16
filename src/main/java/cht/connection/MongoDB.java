/*
 * 版權宣告: FDC all rights reserved.
 */
package cht.connection;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

/**
 * 程式資訊摘要：<P>
 * 類別名稱　　：MongoDB.java<P>
 * 程式內容說明：<P>
 * 程式修改記錄：<P>
 * XXXX-XX-XX：<P>
 *@author su
 *@version 1.0
 *@since 1.0
 */
public class MongoDB {
    
    MongoClient mongo;
    MongoCollection<Document> table;
    
    public void connect() throws UnknownHostException {
     // Since 2.10.0, uses MongoClient
        mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase db = mongo.getDatabase("us-cert");
        table = db.getCollection("bulletin");
    }
    
    public List<String> getList() throws UnknownHostException {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("vendor", "bmc");
        
        connect();
        FindIterable<Document> find = table.find(); // find all

        List<String> results = new ArrayList<String>();
        
        try(MongoCursor<Document> cursor = find.iterator()) {
            while (cursor.hasNext()) {
//                System.out.println(cursor.next().toJson());
                results.add(cursor.next().toJson());
            }
        }
        
        
        mongo.close();
        return results;
    }
    
    public void update(String id, String chi) throws UnknownHostException {
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("chi", chi));
        
        BasicDBObject q = new BasicDBObject().append("_id", new ObjectId(id));
        
        connect();
        UpdateResult r = table.updateOne(q, newDocument);
        System.out.println("Update " + r.getModifiedCount() + " doc");
        mongo.close();
    }
    
    public void update(String id, String chi, boolean submit) throws UnknownHostException {
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("chi", chi).append("submit", true));
        
        BasicDBObject q = new BasicDBObject().append("_id", new ObjectId(id));
        
        connect();
        UpdateResult r = table.updateOne(q, newDocument);
        System.out.println("Submit " + r.getModifiedCount() + " doc");
        mongo.close();
    }
    
    public static void main(String[] args) {
        try {
            new MongoDB().update("584a66bec720b221d4eb5717", "11111");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
