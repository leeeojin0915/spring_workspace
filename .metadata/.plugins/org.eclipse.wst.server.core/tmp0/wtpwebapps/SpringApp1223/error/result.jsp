<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script>
	alert("<%=request.getAttribute("msg")%>");
	history.back();
</script>
</head>
<body bgcolor="yellow">
	<%-- <%
	out.print(request.getAttribute("board_id"));
%>
 --%>
</body>
</html>