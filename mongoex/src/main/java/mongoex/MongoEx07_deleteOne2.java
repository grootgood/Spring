package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// deleteOne (AND 조건)
public class MongoEx07_deleteOne2 {

	public static void main(String[] args) {
		try {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
		
			MongoCollection<Document> collection = database.getCollection("member");
			
			Document filter = new Document();
			// AND 조건으로 삭제
			filter.append("id", "apple3");
			filter.append("pw", "1234");
			
			collection.deleteOne(filter);
			mongoClient.close();
			
			System.out.println("deleteOne 성공");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}