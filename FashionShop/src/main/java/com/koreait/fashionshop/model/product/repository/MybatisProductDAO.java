package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.domain.TopCategory;

@Repository // component-scan의 대상이 될 수 있으려면 Repository선언
public class MybatisProductDAO implements ProductDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List selectAll() {
		return null;
	}

	public List selectById(int subcategory_id) {
		return null;
	}

	public Product select(int product_id) {
		return null;
	}

	public void insert(Product product) {
		sqlSessionTemplate.insert("Product.insert", product);
	}

	public void update(Product product) {
		
	}

	public void delete(int product_id) {
		
	}





}
