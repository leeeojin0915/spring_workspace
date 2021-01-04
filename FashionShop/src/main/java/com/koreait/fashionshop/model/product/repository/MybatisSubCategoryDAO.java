package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.domain.TopCategory;

@Repository // component-scan의 대상이 될 수 있으려면 Repository선언
public class MybatisSubCategoryDAO implements SubCategoryDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List selectAll() {
		return null;
	}

	public List selectAllById(int topcategory_id) {
		return sqlSessionTemplate.selectList("SubCategory.selectAllById", topcategory_id);
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
