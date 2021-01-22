package com.koreait.fashionshop.model.qna.repository;

import java.util.List;

import com.koreait.fashionshop.model.domain.Member;
import com.koreait.fashionshop.model.domain.Qna;

public interface QnaDAO {
	public List selectAll();
	public List selectAll(Member member);//회원이 목록만
	public Qna select(int qna_id);
	public void insert(Qna qna);
	public void reply(Qna qna);//답변
	public void update(Qna qna);
	public void delete(int qna_id);
}
