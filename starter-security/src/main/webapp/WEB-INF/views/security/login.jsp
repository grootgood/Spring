<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>Login Page</h1>

<div style="width: 500px" class="mx-auto">
	<c:if test="${param.error == 'true' }">
		<div class="error">사용자 ID 또는 비밀번호가 일치하지 않습니다.</div>
	</c:if>

	<c:if test="${param.error == 'true' }">
		<div class="error">사용자 ID 또는 비밀번호가 일치하지 않습니다.</div>
	</c:if>
	<c:if test="${param.error == 'login_required' }">
		<div class="error">로그인이 필요한 서비스 입니다.</div>
	</c:if>

	<form action="/security/login" method="post">
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	사용자 ID : 
	<input type="text" name="username"/><br> <!-- name의 username은 정해져있어서 우리가 변경하지 못한다. -->
	
	비밀번호 : 
	<input type="password" name="password"/><br> <!-- name의 password 또한 정해져있어서 우리가 변경하지 못한다. -->

	<label>
		<input type="checkbox" name="remember-me"> 로그인 유지
	</label>

	<input type="submit" value="로그인">
	<input type="submit" value="회원가입">
</form>
</div>