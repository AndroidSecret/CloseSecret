package com.secret.util;

import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class DataBaseHelper {

	private DataBaseConnection dbConnection = null;
	private MongoCollection<Document> col = null;
	
	//构造函数，打开数据库连接
	public DataBaseHelper(){
		this.dbConnection = new DataBaseConnection();//打开连接
	}	
	
	//关闭连接
	public void close(){
		this.dbConnection.close();
		
	}
	
	//统计集合中的文档总数目
	public int getCount(String collection){
		int count = 0;
		try {
			col = dbConnection.getCollection(collection);
			count = (int) col.count();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//向指定集合插入一条数据
	public void insert(Document doc,String collection){
		//		String collection = "user";
		try {
			col = dbConnection.getCollection(collection);
			col.insertOne(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//批量插入数据
	public void insertAll(List<Document> all,String collection){
		try {
			col = dbConnection.getCollection(collection);
			col.insertMany(all);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//根据指定条件查询集合数据，返回查询结果的游标集合
	public MongoCursor<Document> find(BasicDBObject obj,String collection){
		MongoCursor<Document> cursor = null;
		try {
			col = dbConnection.getCollection(collection);
			cursor = col.find(obj).iterator();
			while (cursor.hasNext()) {
				System.out.println("found item : "+cursor.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}
	
	//查询集合中的全部数据，返回查询结果的游标集合
	public MongoCursor<Document> findAll(String collection){
		MongoCursor<Document> cursor = null;
		try {
			col = dbConnection.getCollection(collection);
			cursor = col.find().iterator();
			while (cursor.hasNext()) {
				System.out.println("found item of all : "+cursor.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cursor;
	}
	
	//修改指定集合中的数据，返回被修改的文档数
	public int update(BasicDBObject where,BasicDBObject set,String collection){
		int modifiedCount = 0;
		try {
			col = dbConnection.getCollection(collection);
			UpdateResult result = col.updateMany(where, set);
			modifiedCount = (int) result.getModifiedCount();
			System.out.println("modifiedCount : "+modifiedCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modifiedCount;
	}
	
	//删除指定集合中符合删除条件的一条数据，返回被删除的文档数
	public int deleteOne(BasicDBObject cond,String collection){
		int deletedCount = 0;
		try {
			col = dbConnection.getCollection(collection);
			DeleteResult result = col.deleteOne(cond);
			deletedCount = (int) result.getDeletedCount();
			System.out.println("deletedCount : "+deletedCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deletedCount;
	}
	
	//删除指定集合中符合删除条件的所有数据，返回被删除的文档数
	public int deleteMany(BasicDBObject cond,String collection){
		int deletedCount = 0;
		try {
			col = dbConnection.getCollection(collection);
			DeleteResult result = col.deleteMany(cond);
			deletedCount = (int) result.getDeletedCount();
			System.out.println("deletedCount : "+deletedCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deletedCount;
	}
	
	//删除集合中的所有数据（谨慎操作）
	public void dropCollection(String collection){
		try {
			col = dbConnection.getCollection(collection);
			col.drop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	
}
