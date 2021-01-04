package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.TopCategory;

@Repository //component-scan의 대상이 될 수 있으려면 Repository선언
public class MybatisTopCategoryDAO implements TopCategoryDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List selectAll() {		
		return sqlSessionTemplate.selectList("TopCategory.selectAll");
	}

	public TopCategory select(int topcategory_id) {
		return null;
	}

	public void insert(TopCategory topCategory) {
		
	}

	public void update(TopCategory topCategory) {
		
	}

	public void delete(int topcategory_id) {
		
	}

}
