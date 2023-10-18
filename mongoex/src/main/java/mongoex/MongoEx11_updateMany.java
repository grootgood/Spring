package mongoex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

// updateMany
public class MongoEx11_updateMany {

	public static void main(String[] args) {
		
		try {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
		
			MongoCollection<Document> collection = database.getCollection("member");
			// 업데이트 할 조건
			Document filter = new Document();
			filter.append("id", "apple4");
			
			// 업데이트 할 내용이 많은 경우
			// 1) Bson 객체로 각각 만든다
			Bson update = Updates.set("name", "happy");
			Bson update2 = Updates.set("tel", "5555");
			
			// 2) Bson 객체의 list를 만든다
			List<Bson> list = new ArrayList<Bson>();
			list.add(update);
			list.add(update2);
			
			// 가변 매개변수를 이용한 combine
			// 3) list를 Bson으로 만든다
			Bson all = Updates.combine(list); // Updates는 일종의 헬퍼 클래스
			
			collection.updateOne(filter, all);
			mongoClient.close();
			
			System.out.println("updateMany 성공");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}