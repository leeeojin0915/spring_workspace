package com.koreait.mylegacy.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.mylegacy.exception.RegistException;
import com.koreait.mylegacy.model.dao.MybatisDeptDAO;
import com.koreait.mylegacy.model.dao.MybatisEmpDAO;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.mybatis.config.MybatisConfigManager;


@Service
public class MybatisEmpService {
	@Autowired
	private MybatisConfigManager manager;
	@Autowired
	private MybatisEmpDAO mybatisEmpDAO;
	@Autowired
	private MybatisDeptDAO mybatisDeptDAO;
	
	//모든 레코드 가져오기
	public List selectAll() {
		List list=null;
		SqlSession sqlSession=manager.getSqlSession();
		mybatisEmpDAO.setSqlSession(sqlSession);
		list=mybatisEmpDAO.selectAll();
		manager.close(sqlSession);
		return list;
	}
	
	//사원등록(부서+사원=트랜잭션)
	public int regist(Emp emp) {
		int result=0;
		//일시키기전에 SqlSession배분
		SqlSession sqlSession=manager.getSqlSession();//default false
		
		//아래의 두 DML 메서드를 대상으로 커밋해야 할 코드 라인
		mybatisEmpDAO.setSqlSession(sqlSession);
		mybatisDeptDAO.setSqlSession(sqlSession);
		
		try {
			mybatisEmpDAO.insert(emp);
			mybatisDeptDAO.insert(emp.getDept());
			sqlSession.commit();
			result=1;
		} catch (RegistException e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		
		manager.close(sqlSession);
		return result;
	}
}
