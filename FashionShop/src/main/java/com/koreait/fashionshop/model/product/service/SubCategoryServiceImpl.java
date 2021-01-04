package com.koreait.fashionshop.model.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.product.repository.SubCategoryDAO;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
	@Autowired
	private SubCategoryDAO subCategoryDAO;
	
	public List selectAll() {
		return null;
	}

	public List selectAllById(int topcategory_id) {
		return subCategoryDAO.selectAllById(topcategory_id);
	}

	public SubCategory select(int subcategory_id) {
		return null;
	}

	public void insert(SubCategory subCategory) {
		
	}

	public void update(SubCategory subCategory) {
		
	}

	public void delete(int subcategory_id) {
		
	}

}
