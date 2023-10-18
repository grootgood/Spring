package mongoex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx04 {

	public static void main(String[] args) {
		
		try {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
		
			MongoCollection<Document> collection = database.getCollection("member");
			
			Document doc = new Document();
			doc.append("id", "apple4");
			doc.append("pw", "1234");
			doc.append("name", "apple4");
			doc.append("tel", "014");
			
			Document doc2 = new Document();
			doc2.append("id", "apple5");
			doc2.append("pw", "1234");
			doc2.append("name", "apple5");
			doc2.append("tel", "015");
			
			List<Document> list = new ArrayList<Document>();
			list.add(doc);
			list.add(doc2);
			
			collection.insertMany(list);
			
			System.out.println("insertOne 성공");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

