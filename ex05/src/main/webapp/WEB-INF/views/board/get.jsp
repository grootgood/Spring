<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../layouts/header.jsp" %>

<script src="/resources/js/rest.js"></script>

<script>
$(document).ready(async function() {
	
	$('.remove').click(function(){ // remove 클래스가 클릭 됐을 때 함수 호출 
		// 클릭 이벤트 핸들러 함수
		if(!confirm('정말 삭제할까요?')) return; // 취소 버튼 누르면 바로 return
		
		// form을 얻어서 submit() 호출
		// console.log(document.forms); // document에 있는 form 목록 확인
		document.forms.removeForm.submit();
	}); 
	
	const bno = ${board.bno};
	
	const url = '/api/board/' + bno + '/comment';
	let data = await rest_get(url);
	console.log(data);
	
});
</script>

<h1 class="page-header"><i class="far fa-file-alt"></i> ${board.title }</h1>

<div class="d-flex justify-content-between">
	<div><i class="fas fa-user"></i>${board.writer }</div>
	<div>
		<i class="fas fa-clock"></i>
		<fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate }"/>
	</div>
</div>

<hr>

<div>
	${board.content }
</div>

결과: ${result }

<div class="mt-4">
	<a href="${cri.getLink('list') }" class="btn btn-primary list">
		<i class="fas fa-list"></i>목록</a>
		
	<c:if test="${username == board.writer}">
		<a href="${cri.getLinkWithBno('modify', board.bno) }" class="btn btn-primary modify">
			<i class="far fa-edit"></i>수정</a>
		<a href="#" class="btn btn-danger remove">
			<i class="fas fa-trash-alt"></i>삭제</a>
	</c:if>	
</div>

<form action="remove" method="post" name="removeForm">
    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	<input type="hidden" name="bno" value="${board.bno}"/>
	<input type="hidden" name="pageNum" value="${cri.pageNum }"/>
	<input type="hidden" name="amount" value="${cri.amount }"/>	
	<input type="hidden" name="type" value="${cri.type }"/>
	<input type="hidden" name="keyword" value="${cri.keyword }"/>
</form>

<%@ include file="../layouts/footer.jsp" %>
