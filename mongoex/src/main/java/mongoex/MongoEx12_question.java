package mongoex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx12_question {

	public static void main(String[] args) {
		try {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://user:1234@localhost:27017/shop")
			);
			MongoDatabase database = mongoClient.getDatabase("shop");
			
			MongoCollection<Document> collection = database.getCollection("memo");
			
			// 1번 문제 - insertOne
/*			Document doc = new Document();
			doc.append("age", "500");
			doc.append("name", "song");
			doc.append("office", "busan");
			doc.append("phone", "011");
			
			collection.insertOne(doc);
			
			System.out.println("insertOne 성공");
*/			
			
/*			// 2번 문제 - insertMany
			Document doc = new Document();
			doc.append("age", "501");
			doc.append("name", "song2");
			doc.append("office", "busan");
			doc.append("phone", "011");
			
			Document doc2 = new Document();
			doc2.append("age", "502");
			doc2.append("name", "song3");
			doc2.append("office", "seoul");
			doc2.append("phone", "011");
			
			Document doc3 = new Document();
			doc3.append("age", "503");
			doc3.append("name", "song4");
			doc3.append("office", "busan");
			doc3.append("phone", "011");
			
			List<Document> list = new ArrayList<Document>();
			list.add(doc);
			list.add(doc2);
			list.add(doc3);
			
			collection.insertMany(list);
			
			System.out.println("insertMany 성공");
*/			
		
			// 3번 문제 - deleteOne
			Document doc = new Document("age", "500");
			
			collection.deleteOne(doc);
			
			System.out.println("deleteOne 성공");
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
