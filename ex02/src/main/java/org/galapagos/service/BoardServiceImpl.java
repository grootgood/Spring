package org.galapagos.service;

import java.util.List;

import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.galapagos.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service // 비즈니스 영역을 담당하는 객체임을 표시하기 위해 사용. Componentscan을 통해서 빈으로 등록.
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	// @Autowired -> 새로운 방법으로 생성자를 이용하는 방법이 있다. 가독성이 높다.
	private BoardMapper mapper; // 생성자의 매개변수로 주입이 되어 설정된다.

	@Override
	public void register(BoardVO board) {
		
		log.info("register......"+board);
		
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		
		log.info("get......." + bno);
		
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		
		log.info("modify........" + board);
		
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		
		log.info("remove....." + bno);
		
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("getList with criteria: "+cri);
		
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		
		return mapper.getTotalCount(cri);
	}
}
