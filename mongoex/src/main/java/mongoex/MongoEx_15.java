package mongoex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx_15 {

	public static void main(String[] args) {
		try {
			
			Bson filter = new Document("name", 1);
		
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
			MongoCollection<Document> collection = database.getCollection("memo");
			
			Bson filter2 = new Document("name", 1);
			FindIterable<Document> result = collection.find().sort(filter2);
			
			List<MemoVO> list = new ArrayList<MemoVO>();
			for(Document document : result) {
				MemoVO memo = new MemoVO();
				
				list.add(new MemoVO(document));
			}
			System.out.println(list);
			System.out.println(result.first());
			System.out.println(result.first().containsKey("job"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

