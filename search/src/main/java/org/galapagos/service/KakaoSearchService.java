package org.galapagos.service;

import org.galapagos.domain.book.BookResult;
import org.galapagos.domain.local.LocalResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface KakaoSearchService {
	
	String BASE_URL = "https://dapi.kakao.com/"; // 멤버변수 X, 상수O. 그리고 반드시 /로 끝나야 함
	
	@GET("v3/search/book") // 위에 /가 있으므로 /를 붙이면 안됨
	@Headers({ "Authorization: KakaoAK 6751caa5ca4993e748e46566d0379210"}) // { }-> 배열
	Call<BookResult> searchBook(@Query("query") String query, @Query("size") int size, @Query("page") int page);
	
	@GET("v2/local/search/keyword") // 위에 /가 있으므로 /를 붙이면 안됨
	@Headers({ "Authorization: KakaoAK 6751caa5ca4993e748e46566d0379210"}) 
	Call<LocalResult> searchLocal(@Query("query") String query, @Query("size") int size, @Query("page") int page);
	
	// static 키워드를 붙이지 않으면 Error
	public static KakaoSearchService getService() {
		Retrofit retrofit = new Retrofit.Builder()
										.baseUrl(BASE_URL)
										.addConverterFactory(GsonConverterFactory.create())
										.build();
		return retrofit.create(KakaoSearchService.class); // 인터페이스 구현체 생성하여 return
	}
}
