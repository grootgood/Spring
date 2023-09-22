package org.galapagos.mapper;

import java.util.List;

import org.galapagos.domain.CommentVO;

public interface CommentMapper {
	List<CommentVO> readAll (Long bno); // 목록보기 할 때 foreign key인 글 번호를 주어야 한다.
	CommentVO get(Long no);
	
	void create(CommentVO vo);
	void update(CommentVO vo);
	void delete(Long no);
}
