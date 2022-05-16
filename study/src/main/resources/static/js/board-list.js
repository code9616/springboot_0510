/**

 */
 
 const boardListTable = document.querySelector('.board-list-table');
 
 let page = 1;
 
 load();
 
 function load(){
	$.ajax({
		type: "get",
		url: "/board/list",
		data: {
			"page" : page
		},
		dataType: "text",
		success: function(data){
			console.log(data);
			let boardList = JSON.parse(data);
		},
		error: function(){
			alert("비동기처리 오류");
		}
	});
}