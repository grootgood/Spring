<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp" %>

<h1><i class="fa-solid fa-plane-departure"></i>100대 관광지</h1>

<%@ include file="../common/search_bar.jsp" %>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th style="width: 60px">No</th>
			<th style="width: 100px">권역</th>
			<th>제목</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="travel" items="${list}">
			<tr>
				<td>${travel.no}</td>
				<td>${travel.region}</td>
				<td>
					<a href="${cri.getLink('get')}&no=${travel.no}">
						${travel.title}</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="text-right">
	<a href="register" class="btn btn-primary"> <i class="far fa-edit"></i>
		추가
	</a>
</div>

<%@include file="../common/pagination.jsp" %>

<%@ include file="../layouts/footer.jsp" %>