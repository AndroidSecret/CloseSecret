package com.secret.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBAddress;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.bulk.DeleteRequest;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
 
public class DataBaseConnection {
 
    public static String USERNAME = "secret";
    public static String PASSWORD = "secret";
    public static String HOST = "localhost";
    public static int PORT = 27017;
    public static String DATABASE_NAME = "secretDB";  
//    public static String DB_URI = "mongodb://secret:secret@localhost:27017/secretDB";
    public static String DB_URI = "mongodb://"+USERNAME+":"+PASSWORD+"@"+HOST+":"+PORT+"/"+DATABASE_NAME;
    
//    private static String collection = "user";
    
	private MongoClientURI uri = null;
	private MongoClient client = null;
	private MongoDatabase db = null;
	
	public DataBaseConnection(){
		openConnection();
	}
	
	//打开数据库连接
    public void openConnection(){
    	uri = new MongoClientURI(DB_URI);
    	client = new MongoClient(uri);
    	db = client.getDatabase(DATABASE_NAME);
    }
    
    //获得数据库的指定集合对象
    public MongoCollection<Document> getCollection(String collection){
    	MongoCollection<Document> col = db.getCollection(collection);
    	return col;
    }
    
    //关闭数据库连接
    public void close(){
    	client.close();
    }
    
    /*
    public static void main(String[] args) {
    	String dbUri = "mongodb://secret:secret@localhost:27017/secretDB";
    	String dbName = "secretDB";
    	String collection = "user";
    	
    	//连接数据库
    	MongoClientURI uri = new MongoClientURI(dbUri);
    	MongoClient client = new MongoClient(uri);
    	MongoDatabase db = client.getDatabase(dbName);	
    	MongoCollection col = db.getCollection(collection);
    	
    	//插入数据
//    	List<Document> all = new ArrayList<Document>();
//    	for(int x = 0; x < 100 ; x ++){
//    		Document doc = new Document();
//    		doc.append("uid", x);
//    		doc.append("name", "姓名 - " + x);
//    		doc.append("sex", "男");
//    		all.add(doc);
//    	}
//    	col.insertMany(all);
//    	client.close();
    	
    	//查询数据
//    	MongoCursor<Document> cursor = col.find().iterator();
//    	while (cursor.hasNext()) {
//    		System.out.println(cursor.next());
//    	}
//    	client.close();
    	
    	//范围查询
//    	BasicDBObject cond = new BasicDBObject();
//    	cond.put("uid", new BasicDBObject("$gt",5).append("$lte", 10));
//    	MongoCursor<Document> cursor = col.find(cond).iterator();
//    	while (cursor.hasNext()) {
//    		System.out.println(cursor.next());
//    	}
//    	client.close();
    	
    	//模糊查询
//    	Pattern pattern = Pattern.compile("1");
//    	BasicDBObject cond = new BasicDBObject();
//    	cond.put("name", new BasicDBObject("$regex",pattern).append("$options", "i"));
//    	MongoCursor<Document> cursor = col.find(cond).iterator();
//    	while (cursor.hasNext()) {
//    		System.out.println(cursor.next());
//    	}
//    	client.close();
    	
    	//分页显示
//    	Pattern pattern = Pattern.compile("1");
//    	BasicDBObject cond = new BasicDBObject();
//    	cond.put("name", new BasicDBObject("$regex",pattern).append("$options", "i"));
//    	MongoCursor<Document> cursor = col.find(cond).skip(5).limit(5).iterator();
//    	while (cursor.hasNext()) {
//    		System.out.println(cursor.next());
//    	}
//    	client.close();
    	
    	//数据修改
//    	BasicDBObject condA = new BasicDBObject("uid",0);
//    	BasicDBObject condB = new BasicDBObject("$set",new BasicDBObject("name","Superman"));
//    	UpdateResult result = col.updateMany(condA, condB);
//    	System.out.println(result.getMatchedCount());
//    	client.close();
    	
    	//数据删除
//     	BasicDBObject condA = new BasicDBObject("uid",0);
//     	DeleteResult result = col.deleteOne(condA);
//     	System.out.println(result.getDeletedCount());
//     	client.close();
    	
    	//统计查询
//    	List<BasicDBObject> all = new ArrayList<BasicDBObject>();
//    	BasicDBObject cond = new BasicDBObject("$group",new BasicDBObject("_id","$sex").append("count", new BasicDBObject("$sum",1)));
//    	all.add(cond);
//    	MongoCursor<Document> cursor = col.aggregate(all).iterator();
//    	while(cursor.hasNext()){
//    		Document doc = cursor.next();
//    		System.out.println(doc.getString("_id") + " " + doc.getInteger("count"));;
//    	}
//    	client.close();
    	
    	//统计集合中的文档总数目
//    	System.out.println(col.count());
//    	client.close();
	}*/
    
}
