package org.galapagos.service;

import java.util.List;

import org.galapagos.domain.BoardAttachmentVO;
import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {

	public void register(BoardVO board, List<MultipartFile> files) throws Exception;
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board, List<MultipartFile> files) throws Exception;
	
	public boolean remove(Long bno);
	
//	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);
	
	// 다운로드 할 때 필요
	public BoardAttachmentVO getAttachment(Long no);
	
	// 수정 페이지에서 삭제하는 기능
	public boolean removeAttachment(Long no);
}
