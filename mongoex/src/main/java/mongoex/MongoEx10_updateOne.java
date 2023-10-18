package mongoex;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

// updateOne
public class MongoEx10_updateOne {

	public static void main(String[] args) {
		
		try {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
		
			MongoCollection<Document> collection = database.getCollection("member");
			// 업데이트 할 조건
			Bson filter = new Document("id", "apple2");
			
			// 업데이트 할 내용
			Bson update = Updates.set("name", "winwinwin");
			
			collection.updateOne(filter, update);
			mongoClient.close();
			
			System.out.println("updateOne 성공");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}