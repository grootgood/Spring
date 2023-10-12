package org.galapagos.mapper;

import java.util.List;

import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.galapagos.domain.HeartVO;
import org.galapagos.domain.TravelVO;

public interface TravelMapper {
	public int getTotalCount(Criteria cri);
	
	//// read 기능 먼저 제시 -> update -> delete 순
	
	// 페이지 목록 추출
	public List<TravelVO> getList(Criteria cri);
	
	public List<TravelVO> getRandom(int count);
	
	public TravelVO read(Long no);
	
	// pk 추출 포함
	public void insert(TravelVO travel);
	
	public int update(TravelVO travel);
	
	public int delete(Long no);
	
	// -- 좋아요 처리 --
	// tno 목록
	public List<Long> getHeartsList(String username);
	
	// ajax를 통해서 처리
	public int addHeart(HeartVO heart);
	
	public int deleteHeart(HeartVO heart);
}
