<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../layouts/header.jsp" %>

<script 
	src="https://cdn.jsdelivr.net/npm/@fancyapps/ui@5.0/dist/fancybox/fancybox.umd.js">
</script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@fancyapps/ui@5.0/dist/fancybox/fancybox.css"/>

<script>
$(document).ready(function() {
	
	$('.remove').click(function(){ // remove 클래스가 클릭 됐을 때 함수 호출 
		// 클릭 이벤트 핸들러 함수
		if(!confirm('정말 삭제할까요?')) return; // 취소 버튼 누르면 바로 return
		
		// form을 얻어서 submit() 호출
		// console.log(document.forms); // document에 있는 form 목록 확인
		document.forms.removeForm.submit();
	}); 
	
	// a 태그 5개(사진이 5장이므로)가 선택이 되고, 그것들을 FancyBox가 관리하도록 지정
	Fancybox.bind('[data-fancybox="gallery"]', {
		// options
	});
	
	$('.sm-images > img').mouseenter(function() {
		let src = $(this).attr('src');
		$('.image-panel > img').attr('src', src);
	});
});
</script>

<style>
	.thumb-images>a {
	width: 20%;
	}

	.thumb-images img {
	width: 100%;
	height: 150px;
	padding: 2px;
	object-fit: cover;
	}

	.image-panel {
	width: 300px;
	height: 270px;
	}
	
	.image-panel > img {
	width: 300px;
	height: 200px;
	}
	
	.image-panel > .sm-images > img {
	width: 20%;
	height: 60px;
	object-fit: cover;
	cursor: pointer;
	}
</style>

<h1 class="page-header"><i class="far fa-file-alt"></i> ${travel.title }</h1>

<div class="d-flex justify-content-between">
	<div>
		${travel.region }
	</div>
	<div>
		${travel.phone}
	</div>
</div>

<div class="clearfix">
	<div class="image-panel float-left mr-3">
		<img src="${travel.image }"> 
		<div class="sm-images mt-1 d-flex">
			<c:forEach var="image" items="${travel.images }">
				<img src="${image }">
			</c:forEach>
		</div>
	</div>
	${travel.description}
</div>

<div class="thumb-images my-5 d-flex">
	<c:forEach var="image" items="${travel.images }" >
		<a href ="${image}" data-fancybox="gallery">
			<img src="${image}">
		</a>
	</c:forEach>
</div>

<div class="my-5">
	<h4>주변 볼거리</h4>
	<c:forEach var="local" items="${travel.locals}">
		<div>
			${local.placeName } / ${local.roadAddressName } / ${local.phone}
		</div>
	</c:forEach>
</div>

<div class="mt-4">
	주소: ${travel.address}
</div>

<div id="map" style="width:100%; height:400px; background:gray"></div>

<div class="mt-4">
	<a href="${cri.getLink('list') }" class="btn btn-primary list">
		<i class="fas fa-list"></i>목록</a>
	<sec:authorize access="hasAnyRole('ROLE_MANAGER')">
		<a href="${cri.getLink('modify')}&no=${travel.no}" class="btn btn-primary modify"> <!-- 수정 사항 있음 주의 -->
			<i class="far fa-edit"></i>수정</a>
		<a href="#" class="btn btn-danger remove">
			<i class="fas fa-trash-alt"></i>삭제</a>	
	</sec:authorize>
</div>

<form action="remove" method="post" name="removeForm">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	<input type="hidden" name="no" value="${travel.no}"/> <!-- 수정 사항 있음 주의 -->
	<input type="hidden" name="pageNum" value="${cri.pageNum }"/>
	<input type="hidden" name="amount" value="${cri.amount }"/>	
	<input type="hidden" name="type" value="${cri.type }"/>
	<input type="hidden" name="keyword" value="${cri.keyword }"/>
</form>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=10a509a3d6cc9eaf04fd6c9addbbce61&libraries=services"></script>
<script>
// GeoCode
	
	let geocoder = new kakao.maps.services.Geocoder();
	
	let locals = [
		<c:forEach var="local" items="${travel.locals}" >
			{
				name: '${local.placeName}', coords: new kakao.maps.LatLng(${local.y}, ${local.x})
			},
		</c:forEach>
	]
	
	let address = '${travel.address}';
	
	// 비동기 함수. 동기 함수의 실행이 끝나고 나서 callback 함수 호출
	geocoder.addressSearch(address, function(result, status) {
		if(status == kakao.maps.services.Status.OK) {
			// 배열의 첫번째 위치로 이동
			let coords = new kakao.maps.LatLng(result[0].y, result[0].x); // 지도의 중심좌표와 마커의 위치
			
			let mapContainer = document.getElementById('map'); // map은 id 태그
		  	//  지도 제어 코딩
		  	let mapOption= {
				  center: coords, // 위도 경도
				  level: 3 //지도 확대 레벨
		  	};
		  
		  	let map = new kakao.maps.Map(mapContainer, mapOption); // 두번째 인자는 지도의 위치를 어디에 둘 것인지 설정
			
/* 			let marker = new kakao.maps.Marker({
				map: map,
				position: coords
			}); 
*/			
			for(let local of locals) {
				let marker = new kakao.maps.Marker({
					map: map,
					position: local.coords
				});
			}
			
			// 지도의 중심을 결고값으로 받은 위치로 이동
			 map.setCenter(coords);
		} else {
			alert('잘못된 주소입니다.');
		}
	});

</script>

<%@ include file="../layouts/footer.jsp" %>
