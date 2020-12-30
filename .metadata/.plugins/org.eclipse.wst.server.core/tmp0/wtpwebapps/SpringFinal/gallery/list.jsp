<%@page import="com.study.springfinal.common.Pager"%>
<%@page import="com.study.springfinal.domain.Gallery"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Gallery> galleryList=(List)request.getAttribute("galleryList");
	Pager pager=new Pager();
	pager.init(request, galleryList);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<table>
		<tr>
			<th>NO</th>
			<th>이미지</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%
		int num=pager.getNum();
		int curPos=pager.getCurPos();
		for(int i=1;i<pager.getPageSize();i++){
			if(num<1)break;
			Gallery gallery=galleryList.get(curPos++);
		%>
		<tr>
			<td><%=num-- %></td>
			<td><img src="/data/<%=gallery.getFilename()%>" width="50px"></td>
			<td><a href="/gallery/detail?gallery_id=<%=gallery.getGallery_id() %>"><%=gallery.getTitle() %></a></td>
			<td><%=gallery.getWriter() %></td>
			<td><%=gallery.getRegdate().substring(0,10)%></td>
			<td><%=gallery.getHit() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="6" style="text-align:center">
				[1][2][3]
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<button onClick="location.href='regist_form.jsp'">글등록</button>
			</td>
		</tr>
	</table>

</body>
</html>