package mongoex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// inserMany (test 데이터 추가)
public class MongoEx09 {

	public static void main(String[] args) {
		
		try {
			MongoClient mongoClient = new MongoClient(
					new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2")
			);
			MongoDatabase database = mongoClient.getDatabase("shop2");
		
			MongoCollection<Document> collection = database.getCollection("member");
			
			collection.drop(); // 컬렉션 제거
			List<Document> list = new ArrayList<Document>();
			for(int i=0 ; i<10; i++) {
				Document doc = new Document();
				doc.append("id", "apple" + i);
				doc.append("pw", "1234");
				doc.append("name", "apple" + i);
				doc.append("tel", "00" + i);
				
				list.add(doc);
			}
			
			collection.insertMany(list);
			
			System.out.println("insertMany 성공");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}