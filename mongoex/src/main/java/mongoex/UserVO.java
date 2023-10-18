package mongoex;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	
	private String id;
	private String pw;
	private String name;
	private String tel;
	
	public UserVO(Document document) {
		id = document.getString("id");
		pw = document.getString("pw");
		name = document.getString("name");
		tel = document.getString("tel");
	}
}
