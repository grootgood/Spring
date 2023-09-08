<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- enctype이 Header의 Content-Type에 적용이 됨 -->
	<!-- Part 구분이 여러개 있다고해서 멀티파트라고 부름 aaa=bbb -->
	<form action="/sample/exUploadPost" method="post"
		enctype="multipart/form-data"> 
		<div>
			<input type="file" name="files" multiple="multiple"/>
		</div>

		<div>
			<input type="submit" />
		</div>
	</form>
</body>
</html>
