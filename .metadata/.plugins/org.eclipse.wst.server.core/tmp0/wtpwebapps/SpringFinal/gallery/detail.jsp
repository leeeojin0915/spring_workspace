<%@page import="com.study.springfinal.domain.Gallery"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//정말로 포워딩이 요청을 했는지 테스트
Gallery gallery = (Gallery) request.getAttribute("gallery");
out.print("현재 이 jsp페이지가 사용하는 요청 객체는" + gallery);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

.reply-box {
	background-color: yellow;
}

.reply-list {
	overflow: hidden;
}

.reply-list p {
	float: left;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.15.1/standard/ckeditor.js"></script>
<script>
$(function() {
	CKEDITOR.replace("content");
	
	$($("input[type='button']")[0]).click(function() {
		edit();//동기방식으로 요청
	});
	$($("input[type='button']")[1]).click(function() {
		del();//동기방식으로 요청
	});
	getCommentList();//댓글 목록 비동기로 가져오기
});
//글등록 요청
function edit() {
	if(confirm("수정하시겠어요?")){
		$("form").attr({
			action:"/gallery/edit",
			method:"post"
		});
		$("form").submit();		
	}
}
function del() {
	if(confirm("삭제하시겠어요?")){
		$("form").attr({
			action:"/gallery/delete",
			method:"post"//form태그 안에 있는 히든값이 board_id가 전송됨
		});
		$("form").submit();		
	}
}
//댓글 목록 가져오기
function getCommentList(){
	$.ajax({
		url:"/comment/list.do", 
		type:"get", 
		data:{
			gallery_id : <%=gallery.getGallery_id()%>
		}, 
		success:function(result){
			//서버에서 전송되어온 데이터는 json 객체가 아닌, 문자열일 뿐이다.. 
			// json={name:"ddd"}
			console.log(result.list.length);
			
			//result 에는 서버에서 전송한 json 배열이 들어있다..이 배열을이용하여 아래의 컨텐츠를
			//완성하기!! 4시 10분까지...
			$("#list-area").html(""); //초기화 시킨 후
			
			var tag="";
			for(var i=0;i<result.list.length;i++){
				var json = result.list[i];
				tag+="<div class=\"reply-list\">";
				tag+="<p style=\"width:75%\">"+json.msg+"</p>"; 
				tag+="<p style=\"width:15%\">"+json.author+"</p>";
				tag+="<p style=\"width:10%\">"+json.cdate+"</p>"; 
				tag+="</div>";
			}
			$("#list-area").html(tag);  //innerHTML과 동일
		}
	});	
}
//현재페이지가 새로고침 (reloading)되지 않게 비동기 방식으로 등록요청을 시도
//순수 js의 ajax를 사용하면 처리가 복잡하므로, jquery-ajax로 처리해보자
function registComment() {
	$.ajax({
		url:"/comment/regist.do",
		type:"post",
		data:{
			msg:$("input[name='msg']").val(),
			author:$("input[name='author']").val(),
			gallery_id:<%=gallery.getGallery_id()%>
		},
		//피드백은 success 즉,서버에서 에러없이 데이터가 결과값이 전송되면
		//success 우측에 명시된 익명함수가 동작
		success:function(result){
			//alert("서버로부터 받은결과데이터"+result);
			if(result==1){
				getCommentList();
			}else{
				alert("등록시래");
			}
		}
	});
}
</script>

</head>
<body>

	<h3>상세내용</h3>

	<div class="container">
		<form>
			<input type="hidden" name="gallery_id" value="<%=gallery.getGallery_id()%>">
			<input type="hidden" name="filename" value="<%=gallery.getFilename()%>">
			
			<input type="text" name="title" value="<%=gallery.getTitle()%>">
			<input type="text" name="writer" value="<%=gallery.getWriter()%>">
			<textarea id="content" name="content" style="height: 200px"><%=gallery.getContent()%></textarea>
			<%-- <input type="file" name="photo" value="<%=gallery.getPhoto() %>"> --%>
			
			<input type="button" value="글수정">
			<input type="button" value="글삭제">
			<input type="button" value="목록보기" onClick="location.href='/gallery/list'">
				<div class="reply-box">
					<input type="text" name="msg" placeholder="댓글입력" style="width: 75%">
					<input type="text" name="author" placeholder="작성자입력" style="width: 15%">
					<button type="button" onClick="registComment()">댓글등록</button>
				</div>
				<div id="list-area"></div>
		</form>
	</div>

</body>
</html>