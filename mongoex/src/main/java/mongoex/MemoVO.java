package mongoex;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoVO {
	private String job;
	private String id;
	private String pw;
	private String name;
	private String tel;
	
	public MemoVO(Document document) {
		job = document.getString("job");
		id = document.getString("id");
		pw = document.getString("pw");
		name = document.getString("name");
		tel = document.getString("tel");
	}
}
