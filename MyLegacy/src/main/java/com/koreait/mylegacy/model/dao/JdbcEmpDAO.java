package com.koreait.mylegacy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;

//Dept 테이블에 대한 CRUD를 수행하되 jdbc기반으로 코드를 작성
@Repository
public class JdbcEmpDAO {
	private Connection con;
	public void setCon(Connection con) {
		this.con = con;
	}
	public List selectAll() {
		List list=new ArrayList();	
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuilder sb=new StringBuilder();
		sb.append("select d.deptno as deptno,dname,loc,empno,ename,sal");
		sb.append(" from dept d,emp e");
		sb.append(" where d.deptno=e.deptno");
	
		try {
			pstmt=con.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Emp emp=new Emp();
				Dept dept=new Dept();
				//부서
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				//사원
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setSal(rs.getInt("sal"));
				emp.setDept(dept);
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	public Emp select(int empno) {
		Emp emp=null;
		return emp;
	}
	public int insert(Emp emp) throws SQLException{
		int result=0;
		PreparedStatement pstmt=null;
		String sql="insert into emp(empno,ename,sal,deptno) values(?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setInt(3, emp.getSal());
			pstmt.setInt(4, emp.getDept().getDeptno());
			
			result=pstmt.executeUpdate();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public int update(Emp Emp) {
		int result=0;
		return result;
	}
	public int delete(int empno) {
		int result=0;
		return result;
	}
}
