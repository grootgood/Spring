package mongoex;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx01 {

	public static void main(String[] args) {
		// Unchecked Exception
				// ex) 0으로 나누기, 배열/리스트의 인덱스 오류
				
				// Checked Exception -> 반드시 try 처리
		try {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
		
			MongoCollection<Document> collection = database.getCollection("member");
			
			Document doc = new Document();
			doc.append("id", "apple");
			doc.append("pw", "1234");
			doc.append("name", "apple");
			doc.append("tel", "011");
			
			collection.insertOne(doc);
			System.out.println(doc); // insert
			
			System.out.println("insertOne 성공");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
