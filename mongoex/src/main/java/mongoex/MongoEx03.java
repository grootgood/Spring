package mongoex;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx03 {

	public static void main(String[] args) {
	
		try {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
		
			MongoCollection<Document> collection = database.getCollection("member");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("id 입력 : ");
			String id = sc.next();
			System.out.print("pw 입력 : ");
			String pw = sc.next();
			System.out.print("name 입력 : ");
			String name = sc.next();
			System.out.print("tel 입력 : ");
			String tel = sc.next();
			sc.close();
		
			Document doc = new Document();
			doc.append("id", id);
			doc.append("pw", pw);
			doc.append("name", name);
			doc.append("tel", tel);
			
			System.out.println(doc); // insert 하기 전
			collection.insertOne(doc);
			System.out.println(doc); // insert 한 후
			
			System.out.println("insertOne 성공");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}