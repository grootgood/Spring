package org.galapagos.service;

import java.util.List;

import org.galapagos.domain.BoardVO;
import org.galapagos.domain.Criteria;
import org.galapagos.domain.TravelVO;

public interface TravelService {
	
	// read 기능 먼저 제시 -> update -> delete 순
	public int getTotal(Criteria cri);
	
	public List<TravelVO> getList(Criteria cri);
	
	public List<TravelVO> getRandom(int count);
	
	public TravelVO get(Long no);
	
	public void register(TravelVO travel);
	
	public boolean modify(TravelVO travel);
	
	public boolean remove(Long no);
}
