package com.koreait.fashionshop.client.aop;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.koreait.fashionshop.exception.LoginRequiredException;

/*
 * 앞으로 로그인이 필요한 서비스 여부를 처리하기 위한 코드는 컨트롤러에 두지 않고
 * 지금 이 객체로 공통화시켜 AOP를 적용
 * */
public class MemberSessionCheckAspect {
	private static final Logger logger = LoggerFactory.getLogger(MemberSessionCheckAspect.class);

	public Object sessionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		Object target = joinPoint.getTarget();
		logger.debug("원래 호출하려했던 객체는 " + target);
		String methodName = joinPoint.getSignature().getName();
		logger.debug("원래 호출하려했던 메서드는 " + methodName);
		Object[] args=joinPoint.getArgs();//원래 호출하려했던 메서드의 매개변수
		
		//현재의 요청이 세션을 가지고 있ㄴ느지를 판단하여 적절한 제어
		
		//세션을 얻기 위해서는 HttpServletRequest가 필요
		 HttpServletRequest request=null;
		for(Object arg:args) {
			logger.debug("매개변수명은 "+arg);
			if(arg instanceof HttpServletRequest) {//request객체라면
				request=(HttpServletRequest)arg;				
			}
		}
		//1.세션이없다면?예외발생처리->exceptionhandler거쳐서 클라이언트에게 적절한 응답 처리
		//2.세션이 있다면?그대로 원래 호출하려 했던 메서드 진행
		HttpSession session=null;	
		session=request.getSession();
		Object result=null;
		
		if(session.getAttribute("member")==null) {
			throw new LoginRequiredException("로그인이 필요한 서비스 입니다.");
		}else {
			// 원래요청의 흐름을 그대로 진행
			result=joinPoint.proceed();
			
		}
		return result;
	}
}
