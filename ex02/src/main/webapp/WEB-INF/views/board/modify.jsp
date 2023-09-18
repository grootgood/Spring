<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="../layouts/header.jsp"%>

<link rel="stylesheet" href="/resources/css/summernote/summernote-lite.min.css">
<script src="/resources/js/summernote/summernote-lite.min.js"></script> 
<script src="/resources/js/summernote/lang/summernote-ko-KR.min.js"></script> 

<script>
$(document).ready(function() {
	$('#content').summernote({
		height: 300,                 // 에디터 높이
		lang: "ko-KR",					// 한글 설정
	});
/* 
	$('.get').click(function(){
		document.forms.getForm.submit();
	});	 
*/

});
</script>

<h1 class="page-header"><i class="far fa-edit"></i> 글 수정</h1>

<div class="panel panel-default">
	<div class="panel-body">
		<form:form modelAttribute="board" role="form">
			<form:hidden path="bno"/>
			
			<div class="form-group">
				<form:label path="title">제목</form:label>
				<form:input path="title" cssClass="form-control" />
				<form:errors path="title" cssClass="error"/>
			</div>		
			<div class="form-group">
				<form:label path="writer">작성자</form:label>
				<form:input path="writer" cssClass="form-control" />
				<form:errors path="writer" cssClass="error"/>				
			</div>			
			
			<div class="form-group">
				<form:label path="content">내용</form:label>
				<form:textarea path="content" class="form-control"></form:textarea>
				<form:errors path="content" cssClass="error"/>
			</div>

			<button type="submit" class="btn btn-primary">
             <i class="fas fa-check"></i> 확인</button>
			<button type="reset" class="btn btn-primary">
             <i class="fas fa-undo"></i> 취소</button>
			<a href="${cri.getLinkWithBno('get', board.bno)}" class="btn btn-primary get">
             <i class="fas fa-file-alt"></i> 돌아가기</a>
		</form:form>
	</div>
</div>
<%-- 
<form id="getForm" action="/board/get" method="get" >
	<input type="hidden" id="bno" name="bno" value="${board.bno}"/>
	<input type="hidden" name="pageNum" value="${cri.pageNum}"/>
	<input type="hidden" name="amount" value="${cri.amount}"/>
	<input type="hidden" name="type" value="${cri.type}"/>
	<input type="hidden" name="keyword" value="${cri.keyword}"/>
</form>
 --%>

<%@include file="../layouts/footer.jsp"%>