package com.koreait.fashionshop.model.member.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberService {
	public List selectAll();//모든회원
	public Member select(Member member);//회원1명
	public void regist(Member member);//회원등록 및 기타필요사항 처리
	public void update(Member member);//회원수정
	public void delete(Member member);//회원삭제
}
