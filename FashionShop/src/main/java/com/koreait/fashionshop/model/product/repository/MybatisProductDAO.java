package com.koreait.fashionshop.model.product.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.fashionshop.exception.ProductRegistException;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.domain.TopCategory;

@Repository // component-scan의 대상이 될 수 있으려면 Repository선언
public class MybatisProductDAO implements ProductDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List selectAll() {
		return sqlSessionTemplate.selectList("Product.selectAll");
	}

	public List selectById(int subcategory_id) {
		return sqlSessionTemplate.selectList("Product.selectBySubCategoryID",subcategory_id);
	}

	public Product select(int product_id) {
		return sqlSessionTemplate.selectOne("Product.select", product_id);
	}

	public void insert(Product product) throws ProductRegistException{
		int result=sqlSessionTemplate.insert("Product.insert", product);
		if(result==0) {
			throw new ProductRegistException("상품테이블에 입력실패");
		}
	}

	public void update(Product product) throws ProductRegistException{
		
	}

	public void delete(int product_id) throws ProductRegistException{
		
	}





}
