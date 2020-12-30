<%@page import="com.practice.finalpractice.domain.Emp"%>
<%@page import="com.practice.finalpractice.mybatis.config.MybatisConfigManager"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	MybatisConfigManager manager=MybatisConfigManager.getInstance();
%>
<%
	//servoice메서드 영역
	SqlSession sqlSession=manager.getSqlSession();
	List<Emp> list=sqlSession.selectList("Emp.selectAll");
	out.print("검색된 총 사원수는"+list.size());
%>

