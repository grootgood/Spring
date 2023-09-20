<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp" %>

<h1 style="text-align:center">회원 가입</h1>
<div>
	<form:form modelAttribute="member">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
		
		<div class="form-group mx-auto" style="width:300px">
			<form:errors path="username" cssClass="error" /><br>
			<form:label path="username"> 사용자 ID:</form:label>
			<form:input path="username" cssClass="form-control" />
		</div>
		
		<div class="form-group mx-auto" style="width:300px">
			<form:errors path="password" cssClass="error" /><br> 
			<form:label path="password"> 비밀번호:</form:label>
			<form:password path="password" cssClass="form-control" />
		</div>
		
		<div class="form-group mx-auto" style="width:300px">
			<form:errors path="password2" cssClass="error" /><br>
			<form:label path="password2"> 비밀번호 확인:</form:label>
			<form:password path="password2" cssClass="form-control" />
		</div>
		
		<div class="form-group mx-auto" style="width:300px">
			<form:errors path="email" cssClass="error" /><br>
			<form:label path="email"> email:</form:label>
			<form:input path="email" cssClass="form-control" />
		</div>
		
		<div class="text-center mx-auto" style="width:300px">
			<button type="submit" class="btn btn-primary">
				<i class="fa-solid fa-user-plus"></i> 회원가입
			</button>
		</div>
	</form:form>
</div>

<%@ include file="../layouts/footer.jsp" %>