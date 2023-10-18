package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// deleteMany
public class MongoEx08_deleteMany {

	public static void main(String[] args) {
		try {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
		
			MongoCollection<Document> collection = database.getCollection("member");
			
			Document filter = new Document();
			
			//filter.append("pw", "1234");
			// 위 코드를 주석처리하면 모두 삭제
			
			collection.deleteMany(filter);
			
			System.out.println("deleteMany 성공");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}