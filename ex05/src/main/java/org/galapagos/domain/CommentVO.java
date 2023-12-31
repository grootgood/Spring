package org.galapagos.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CommentVO {
	
	private Long no;
	private Long bno;
	
	private String writer;
	private String content;
	private Date regDate;
	private Date updateDate;
	
	List<ReplyVO> replyList; // 이것으로 1:N 관계가 추가된다.
}
