<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/auth/signin" method="post">
		<input type="text" name="username" placeholder="아이디"><br>
		<input type="password" name="password" placeholder="비밀번호"><br>
		<button>로그인</button>
	</form>
</body>
</html>