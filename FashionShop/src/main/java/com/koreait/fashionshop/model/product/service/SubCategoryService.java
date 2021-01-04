package com.koreait.fashionshop.model.product.service;

import java.util.List;

import com.koreait.fashionshop.model.domain.SubCategory;

public interface SubCategoryService {
	public List selectAll();//모든 레코드 가져오기
	public List selectAllById(int topcategory_id);//선택한 상위 카테고리에 소속된 하위 카테고리 목록가져옥;
	public SubCategory select(int subcategory_id);
	public void insert(SubCategory subCategory);
	public void update(SubCategory subCategory);
	public void delete(int subcategory_id);
}
