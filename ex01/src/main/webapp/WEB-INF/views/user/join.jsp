<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="/user/join" method="POST">
		회원 ID: <input type="text" name="userid" value="hong"><br>
		비밀번호: <input type="password" name="password" value="1234"><br>
		비밀번호: <input type="password" name="password2" value="1234"><br>
		email: <input type="email" name="email" value="hong@naver.com"><br>
		취미:
			<input type="checkbox" name="hobbies" value="축구" checked> 축구
			<input type="checkbox" name="hobbies" value="농구"> 농구
			<input type="checkbox" name="hobbies" value="배구"> 배구
			<br>
		생일: <input type="text" name="birthDate" 
				placeholder="yyyy-MM-dd" value="2000-12-12"><br>
		<input type="submit">
	</form>
</body>
</html>