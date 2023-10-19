package org.galapagos;

import java.io.IOException;
import java.util.Scanner;

import org.galapagos.domain.local.Local;
import org.galapagos.domain.local.LocalResult;
import org.galapagos.service.KakaoSearchService;

import retrofit2.Call;
import retrofit2.Response;

public class KakaoLocalSearchEx {

	public static void main(String[] args) {
		KakaoSearchService api = KakaoSearchService.getService();
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("검색어: ");
			String query = sc.nextLine();
			
			Call<org.galapagos.domain.local.LocalResult> call = api.searchLocal(query, 10, 1); // 요청을 할 수 있는 Call 객체 리턴
			
			Response<LocalResult> res = call.execute(); // 서버에 요청 전송 (동기식:execute() / 비동기식:enqueue(callback) )
			if(res.isSuccessful()) { // 200이라면
				
				LocalResult result = res.body();
				System.out.println(result.getMeta());
				for(Local local: result.getLocals()) {
					System.out.println(local);
				}
			} else {
				System.out.println("호출 실패");
				System.out.println(res); // 실패 시 응답 객체 출력
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
