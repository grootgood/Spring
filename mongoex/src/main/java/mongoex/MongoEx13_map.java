package mongoex;

import java.util.HashMap;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx13_map {
	public static void main(String[] args) {
		try {
			
			Bson filter = new Document("name", 1);
		
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
			MongoCollection<Document> collection = database.getCollection("member");
			FindIterable<Document> result = collection.find().sort(filter);
			
			System.out.println("-------------");
			System.out.println(result.first());
			System.out.println(result.first().containsKey("name"));
			System.out.println(result.first().containsValue("Peter John"));
			System.out.println("-------------");
			
			for(Document document : result) {
				System.out.print(document.get("id") + "\t");
				System.out.print(document.get("pw") + "\t");
				System.out.print(document.get("name") + "\t");
				System.out.print(document.get("tel") + "\t");
				System.out.println();
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("id", result.first().get("id"));
			map.put("pw", result.first().get("pw"));
			map.put("name", result.first().get("name"));
			map.put("tel", result.first().get("tel"));
			System.out.println("---------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

