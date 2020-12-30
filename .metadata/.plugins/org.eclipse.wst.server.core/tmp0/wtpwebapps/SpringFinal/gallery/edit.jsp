<%@page import="com.study.springfinal.domain.Gallery"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	int result=(Integer)request.getAttribute("result");//수정결과
	Gallery gallery=(Gallery)request.getAttribute("gallery");
	
	StringBuilder sb=new StringBuilder();
	sb.append("<script>");
	if(result==0){
		sb.append("alert('수정실패');");
		sb.append("history.back();");
	}else{
		sb.append("alert('수정성공');");
		sb.append("location.href='/gallery/detail?gallery_id="+gallery.getGallery_id()+"';");
	}
	sb.append("</script>");
	out.print(sb.toString());

%>