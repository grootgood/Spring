<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../layouts/header.jsp" %>

<%-- 개별 페이지 --%>
<h1 class="page-header"><i class="fas fa-list"></i> Board List</h1>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th style="width: 60px">No</th>
			<th>제목</th>
			<th style="width: 100px">작성자</th>
			<th style="width: 130px">등록일</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="board" items="${list }">
		<tr>
			<td>${board.bno }</td>
			<td>
				<a href="get?bno=${board.bno }">${board.title}</a>
				<!-- 상대경로이기 때문에 실제 클릭시 요청되는 주소는 /board/get?bno=xxx 이다.-->
			</td>
			<td>${board.writer}</td>
			<td>
				<fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate }"/>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<div class="text-right">
	<a href="register" class="btn btn-primary">
	<!-- 상대경로이기 때문에 실제 클릭시 요청되는 주소는 /board/register이다.  -->
		<i class="far fa-edit"></i>
		글쓰기
	</a>
</div>

<%@ include file="../layouts/footer.jsp" %>
