/*
 * 版權宣告: FDC all rights reserved.
 */
package cht.connection;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

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
    
    DBCollection table;
    
    public void connect() throws UnknownHostException {
     // Since 2.10.0, uses MongoClient
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        DB db = mongo.getDB("us-cert");
        table = db.getCollection("bulletin");
    }
    
    public List<String> getList() throws UnknownHostException {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("vendor", "bmc");
        
        connect();
        DBCursor cursor = table.find(); // find all

        List<String> results = new ArrayList<String>();
        while (cursor.hasNext()) {
            results.add(cursor.next().toString());
        }
        
        return results;
    }
    
//    public static void main(String[] args) {
//        try {
//            new MongoDB().getList();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//    }

}
