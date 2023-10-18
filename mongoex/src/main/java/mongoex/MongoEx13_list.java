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

public class MongoEx13_list {

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
			System.out.println("-------------");
			
			List<UserVO> list = new ArrayList<UserVO>();
			for(Document document : result) {
				UserVO user = new UserVO();
//				user.setId(document.getString("id"));
//				user.setId(document.getString("pw"));
//				user.setId(document.getString("name"));
//				user.setId(document.getString("tel"));
//				list.add(user);
				
				list.add(new UserVO(document));
			}
			System.out.println(list);
			
			} catch(Exception e) {
				e.printStackTrace();
			}
	}
}
