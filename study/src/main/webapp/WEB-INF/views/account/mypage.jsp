<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/account/mypage.css">
</head>
<body>
	<div id="container">
		<form action="" enctype="mulitpart/form-data">
			<div class="user-info">
				<div class="profile-img">
					<img class="profile-img-url" src="https://lh3.googleusercontent.com/a-/AOh14GhVawD6MQftcH6JQgHW6siUFAN6BtEAG-Q6qwfO=s96-c">
				</div>
				<div class="username-text">username</div><br>
				<input type="file" class="file-input" name="file">
			</div>
		</form>
		
		<div class="input-items">
			<input type="text" class="text-inputs" placeholder="username">
		</div>
		<div class="input-items">
			<input type="text" class="text-inputs" placeholder="email">
		</div>
		<div class="input-items">
			<input type="text" class="text-inputs" placeholder="name">
		</div>
		<button>수정하기</button>
	</div>
	<script type="text/javascript" src="/js/authentication/principal.js"></script>
	<script type="text/javascript" src="/js/account/mypage.js"></script>
</body>
</html>