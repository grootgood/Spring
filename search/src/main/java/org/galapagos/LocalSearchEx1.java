package org.galapagos;

import java.io.IOException;
import java.util.Scanner;

import org.galapagos.domain.naver.Local;
import org.galapagos.domain.naver.LocalResult;
import org.galapagos.service.NaverLocalSearch;

import retrofit2.Call;
import retrofit2.Response;

public class LocalSearchEx1 {

	public static void main(String[] args) {
		NaverLocalSearch api = NaverLocalSearch.getService();
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("검색어: ");
			String query = sc.nextLine();
			
			Call<LocalResult> call = api.searchLocal(query, 5, 1); // 요청을 할 수 있는 Call 객체 리턴
			System.out.println(call.request()); // 요청 URL 확인
			System.out.println(call.request().headers()); // 헤더 확인 - 인증 키
			
			Response<LocalResult> res = call.execute(); // 서버에 요청 전송 (동기식:execute() / 비동기식:enqueue(callback) )
			if(res.isSuccessful()) { // 200이라면
				
				LocalResult result = res.body();
				System.out.println(result);
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
