/**

 */
 
const boardListTable = document.querySelector('.board-list-table');

let path = window.location.pathname;
 
 load();
 
 function load(){
	let boardCode = path.substring(path.lastIndexOf("/")+1);
	$.ajax({
		type: "get",
		url: `/board/${boardCode}`,
		dataType: "text",
		success: function(data){
			let boardObject = JSON.parse(data);
			getBoardDtl(boardObject.data);
		},
		error: function(){
			alert("비동기처리 오류");
		}
	});
}


function getBoardDtl(data){
	boardListTable.innerHTML = `
		<tr>
			<th>제목</th>
			<td>${data.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${data.username}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${data.boardCount}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><pre>${data.content}</pre></td>
		</tr>
	`;
}