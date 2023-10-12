package org.galapagos.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.galapagos.domain.BoardAttachmentVO;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;

public interface BoardMapper {
	//@Select("select * from tbl_board")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board); // insert 후에 bno를 모름
	
	public void insertSelectKey(BoardVO board); // insert 후에 bno를 추출
	
	public BoardVO read(Long bno); // pk : where절 구성
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
	
	public void insertAttachment(BoardAttachmentVO attach);
	
	// 목록 보여줄때 사용
	public List<BoardAttachmentVO> getAttachmentList(Long bno);
	
	// Attachment 1개 얻기 (다운로드)
	public BoardAttachmentVO getAttachment(Long no);
	
	// 삭제
	public int removeAttachment(Long no);
}
