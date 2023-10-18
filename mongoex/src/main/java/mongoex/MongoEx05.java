package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx05 {

	public static void main(String[] args) {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
		
			MongoCollection<Document> collection = database.getCollection("member");
			
			Document filter = new Document("id", "apple"); 
			// filter.append("id", "apple") 한 것과 동일하다.
//			Document proj = new Document("id", 1);
//			proj.append("name", 1);
			
			FindIterable<Document> result = collection.find(filter);
			
			for(Document doc: result) {
				System.out.print("ID: " + doc.getString("id") + ", ");
				System.out.print("PW: " + doc.getString("pw") + ", ");
				System.out.print("NAME: " + doc.getString("name") + ", ");
				System.out.print("TEL: " + doc.getString("tel") + ", ");
			}
		}
}
