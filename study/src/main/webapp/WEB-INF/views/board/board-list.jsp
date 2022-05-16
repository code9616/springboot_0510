<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="/css/board/board-list.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div id="container">
		<div class = "board-list">
			<table class="board-list-table">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
				</tr>
				<tr class="border-items">
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
				</tr>
			</table>
		</div>
		<div class = "board-list-page">
			<a href="/board?page = 1">1</a>
			<a href="/board?page = 2">2</a>
			<a href="/board?page = 3">3</a>
			<a href="/board?page = 4">4</a>
			<a href="/board?page = 5">5</a>
		</div>
	</div>
	<script type="text/javascript" src="/js/board-list.js"></script>
</body>
</html>