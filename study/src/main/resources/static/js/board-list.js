const boardListTable = document.querySelector('.board-list-table');
const boardListPage = document.querySelector('.board-list-page');

 
 let nowPage = 1;
 
 load(nowPage);
 
 function load(page){
	let url = "/api/board/list?page=" + page; //`/board/list?page=${page}`
	
	fetch(url)
	.then(response => {
		if(response.ok){
			return response.json();
		}else{
			throw new Error("비동기 처리 오류");
		}
	})
	.then(result => {
		getBoardList(result.data);
		console.log(result.data);
		createPageNumber(result.data[0].boardCountAll);
		getBoardItems();
	})
	.catch(error => {console.log(error);});

	/*요청해서 게시글목록데이터를 가지고 오기 위해(서버에 요청날릴려고)*/
	/*요청받기위해서는 type url 필수*/
	/*$.ajax({
		type: "get",
		url: "/board/list",
		data: {
			"page" : page
		},
		dataType: "text",
		success: function(data){
			console.log(data);*/
			/*json 전에는 xml썼었다
			 키 벨류로 이루어져서 원하는 데이터를 텍스트형식으로 적은 데이터량(자바객체)으로 주고받을수
			 */
			/*let boardList = JSON.parse(data);
			getBoardList(boardList.data);
			createPageNumber(boardList.data[0].boardCountAll);
			getBoardItems();
		},
		error: function(){
			alert("비동기처리 오류");
		}
	});*/
}

function createPageNumber(data){
	const boardListPage = document.querySelector('.board-list-page');
	const preNextBtn = document.querySelectorAll('.pre-next-btn');
	
	const totalBoardCount = data;
	const totalPageCount = data % 5 == 0 ? data / 5 : (data / 5) + 1;
		
	const startIndex = nowPage % 5 == 0 ? nowPage - 4 :  nowPage - (nowPage % 5) + 1;
	const endIndex = startIndex + 4 <= totalPageCount ? startIndex + 4 : totalPageCount;
	
		preNextBtn[0].onclick = () => {
			nowPage = startIndex != 1 ? startIndex - 1 : 1;
			load(nowPage);
		}
		preNextBtn[1].onclick = () => {
			nowPage = endIndex != totalPageCount ? endIndex + 1 : totalPageCount;
			load(nowPage)
		}
	
	//1 => 1,2,3,4,5
	//6 => 6,7,8,9,10
	
	let pageStr = ``;
	
	for(let i = startIndex; i <= endIndex; i++){
		pageStr += `<div>${i}</div>`;
	}
	
	
	boardListPage.innerHTML = pageStr;
	
	const pageButton = boardListPage.querySelectorAll('div');
	for(let i = 0; i < pageButton.length; i++){
	pageButton[i].onclick = () => {
		nowPage = pageButton[i].textContent;
		load(nowPage);
		}
	}
}

function getBoardList(data){
	while(boardListTable.hasChildNodes()){
		boardListTable.removeChild(boardListTable.firstChild);
	}
	let tableStr = `
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>조회수</th>
	</tr>
	`
	
	for(let i = 0; i < data.length; i++){
		tableStr +=`
		<tr class="board-items">
			<td>${data[i].boardCode}</td>
			<td>${data[i].title}</td>
			<td>${data[i].username}</td>
			<td>${data[i].boardCount}</td>
		</tr>
		`;
	}
	/*innerHTML HTML형식으로 가져와서 사용함
	innertext text형식으로 해서 텍스트형식으로 써도됨*/
	boardListTable.innerHTML = tableStr;
}



function getBoardItems(){
	const boardItems = document.querySelectorAll('.board-items');
	for(let i = 0; i < boardItems.length; i++){
		boardItems[i].onclick = () => {
			location.href = "/board-info/" + boardItems[i].querySelectorAll('td')[0].textContent;
		}
	}
}
