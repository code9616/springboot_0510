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
		<div class="board-list">
			<table class="board-list-table">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
				
			</table>
		</div>
		<button onclick="location.href='/board'">작성</button>
		<div class="board-page-buttons">
			<div class="pre-next-btn">&lt;</div>
			<div class="board-list-page">
			</div>
			<div class="pre-next-btn">&gt;</div>
		</div>
	</div>
	
	<script type="text/javascript" src="/js/board-list.js"></script>
</body>
</html>