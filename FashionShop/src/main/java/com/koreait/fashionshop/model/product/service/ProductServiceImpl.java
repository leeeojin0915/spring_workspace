package com.koreait.fashionshop.model.product.service;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.fashionshop.common.FileManager;
import com.koreait.fashionshop.model.domain.Color;
import com.koreait.fashionshop.model.domain.Image;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.Psize;
import com.koreait.fashionshop.model.product.repository.ColorDAO;
import com.koreait.fashionshop.model.product.repository.ImageDAO;
import com.koreait.fashionshop.model.product.repository.ProductDAO;
import com.koreait.fashionshop.model.product.repository.PsizeDAO;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ImageDAO imageDAO;
	@Autowired
	private PsizeDAO psizeDAO;
	@Autowired
	private ColorDAO colorDAO;

	public List selectAll() {
		return null;
	}

	public List selectById(int subcategory_id) {
		return null;
	}

	public Product select(int product_id) {
		return null;
	}

	public void regist(FileManager fileManager, Product product) {
		String ext = fileManager.getExtend(product.getRepImg().getOriginalFilename());
		product.setFilename(ext);// 확장자넣기
		// db에 넣는 일은 DAO에게 시키고
		productDAO.insert(product);

		// 파일업로드는 FileManager에게 시킨다
		// 대표이미지
		// 상품의 product_id를 이용하여 대표 이미지명을 결정짓자
		String basicImg = product.getProduct_id() + "." + ext;
		fileManager.saveFile(fileManager.getSaveBasicDir() + File.separator + basicImg, product.getRepImg());

		// 추가이미지 업로드(FileManager에게 추가이미지 갯수만큼 업로드 업무를 시키면 됨)
		for (int i = 0; i < product.getAddImg().length; i++) {

			MultipartFile file = product.getAddImg()[i];
			ext = fileManager.getExtend(file.getOriginalFilename());
			// image table에 넣기
			Image image = new Image();
			image.setProduct_id(product.getProduct_id());
			image.setFilename(ext);// 확장자넣기
			imageDAO.insert(image);

			// 매니저 저장 호출
			String addImg = image.getImage_id() + "." + ext;
			fileManager.saveFile(fileManager.getSaveAddonDir() + File.separator + addImg, file);
		}

		// 기타 옵션 중, 색상 사이즈 넣기(반복문으로..)
		// 사이즈
		for (Psize psize : product.getPsize()) {
			logger.debug("당신이 선택한 사이즈는 " + psize.getFit());
			//psize.setProduct_id(product.getProduct_id());// fk대입
			//psizeDAO.insert(psize);
		}

		// 색상
		for (Color color : product.getColor()) {
			logger.debug("넘겨받은 색상은 " + color.getPicker());
			//color.setProduct_id(product.getProduct_id());
			//colorDAO.insert(color);

		}
	}

	public void update(Product product) {

	}

	public void delete(int product_id) {

	}

}
