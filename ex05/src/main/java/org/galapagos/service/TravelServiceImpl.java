package org.galapagos.service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.galapagos.domain.Criteria;
import org.galapagos.domain.TravelVO;
import org.galapagos.domain.kakao.local.LocalResult;
import org.galapagos.mapper.TravelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import retrofit2.Call;
import retrofit2.Response;

@Log4j
@Service
public class TravelServiceImpl implements TravelService {
	
	@Autowired
	private TravelMapper mapper;
	
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}

	@Override
	public List<TravelVO> getList(Criteria cri, Principal principal) {
		List<TravelVO> list = mapper.getList(cri);
		if(principal != null) {
			List<Long> hearts = mapper.getHeartsList(principal.getName()); // username 추출
			for(TravelVO travel: list) {
				travel.setMyHeart(hearts.contains(travel.getNo()));
			}
		}
		return list;
	}

	@Override
	public TravelVO get(Long no, Principal principal) { 
		TravelVO travel = mapper.read(no);
		if(principal != null) {
			List<Long> hearts = mapper.getHeartsList(principal.getName());
			travel.setMyHeart(hearts.contains(travel.getNo()));
		}
		
		// 주변 검색
		String query = travel.getTitle();
		KakaoSearchService service = KakaoSearchService.getService();
		Call<LocalResult> call = service.searchLocal(query, 10, 1);
		Response<LocalResult> res;
		
		try {
			res=call.execute();
			if(res.isSuccessful()) {
				LocalResult result = res.body();
				log.info("===> " + result);
				travel.setLocals(result.getLocals());
			} else {
				log.info("호출 실패ㅐ ===> " + res);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return travel;
	}

	@Override
	public void register(TravelVO travel) {
		mapper.insert(travel);
		
		// register에서 보통 첨부파일 업로드를 구현한다. insert 이후에
		// 구현은 다음주(9.18~) 진행
	}

	@Override
	public boolean modify(TravelVO travel) {
		return mapper.update(travel) == 1;
	}

	@Override
	public boolean remove(Long no) {
		return mapper.delete(no) == 1;
	}
	
	@Override
	public List<TravelVO> getRandom(int count) {
		return mapper.getRandom(count);
	}
}
