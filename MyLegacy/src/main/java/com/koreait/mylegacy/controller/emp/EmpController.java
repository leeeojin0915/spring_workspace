package com.koreait.mylegacy.controller.emp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mylegacy.model.domain.Dept;
import com.koreait.mylegacy.model.domain.Emp;
import com.koreait.mylegacy.model.service.JdbcEmpService;
import com.koreait.mylegacy.model.service.MybatisEmpService;

//component-scan대상이 되려면 어노테이션을 지정해야한다.
@Controller
public class EmpController {
	private static final Logger logger=LoggerFactory.getLogger(EmpController.class);
	@Autowired
	private JdbcEmpService empService;
	@Autowired
	private MybatisEmpService mybatisEmpService; 
	
	
	//사원 등로 폼 요청
	@RequestMapping("/emp/registform")
	public String registForm() {
		//저장할 것이 없고 그냥 뷰만 반환하고 싶을때 String도 가능
		
		return "emp/regist_form";
	}
	
	//사원등록 요청 처리
	@RequestMapping(value="/emp/regist", method=RequestMethod.POST)
	public String regist(Dept dept, Emp emp) {
		//log4j라는 라이브러리는 우리가 개발시 디버그 목적으로 사용하는 콘솔  출력보다도 훨씬 다양하며
		//복잡한 기능을 지원하는 로그 라이브러리이다.
		//특히 출력 로그레벨이란 기능을 둬서 개발자가 원하는 레벨을 선택하여 출력을 제어할 수 있도록 지원
		//ALL<DEBUG(확인)<INFO(강조)<WARN(경고)<ERROR(오류)<FATAL(심각한오류)<OFF(로깅해제)
		logger.debug(""+dept.getDeptno());
		logger.debug(dept.getDname());
		logger.debug(dept.getLoc());
		
		logger.debug(""+emp.getEmpno());
		logger.debug(emp.getEname());
		logger.debug(""+emp.getSal());
		//System.out.println(emp.getDept().getDeptno());
		
		
		//부서등록과 사원등록이라는 두 개의 업무가 모두 성공되어야 전체를 성공으로 간주하는 트랜잭션 상황
//		int result=jdbcDeptDAO.insert(dept);
//		System.out.println("result: "+result);
//		
//		int result2=JdbcEmpDAO.insert(emp);
//		System.out.println("result2:"+result2);
		
		
		//서비스에게 사원등록 요청
		emp.setDept(dept);//emp와 부서를 합체
		
		int result=empService.regist(emp);
		logger.debug("등록결과 "+result);
		
		return "redirect:/emp/list";
	}
	
	//사원목록
	@RequestMapping(value="/emp/list", method=RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav=new ModelAndView();
		
		//3단계
		List empList=mybatisEmpService.selectAll();
		
		//4단계
		mav.addObject("empList",empList);
		mav.setViewName("emp/list");
		return mav;
	}
}




















