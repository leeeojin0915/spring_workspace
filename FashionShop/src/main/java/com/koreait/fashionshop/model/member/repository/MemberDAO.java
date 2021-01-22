package com.koreait.fashionshop.model.member.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;

public interface MemberDAO {
	public List selectAll();//모든회원
	public Member select(Member member);//회원1명
	public void insert(Member member);//회원등록
	public void update(Member member);//회원수정
	public void delete(Member member);//회원삭제
}
