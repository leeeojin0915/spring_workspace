package com.koreait.fashionshop.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.common.FileManager;
import com.koreait.fashionshop.model.domain.Product;
import com.koreait.fashionshop.model.domain.SubCategory;
import com.koreait.fashionshop.model.product.service.ProductService;
import com.koreait.fashionshop.model.product.service.SubCategoryService;
import com.koreait.fashionshop.model.product.service.TopCategoryService;

//관리자 모드에서의 상품에 대한 요청 처리
@Controller
public class ProductController implements ServletContextAware{
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private TopCategoryService topCategoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private FileManager fileManager;
	
	//우리가 왜 ServletContext를 써야하는가? getRealPath()하려고
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
	//이 타이밍을 놓치지 말고 실제 물리적 경로를 FileManager에 대입해놓자
		fileManager.setSaveBasicDir(servletContext.getRealPath(fileManager.getSaveBasicDir()));
		fileManager.setSaveAddonDir(servletContext.getRealPath(fileManager.getSaveAddonDir()));
		
		logger.debug(fileManager.getSaveBasicDir());
	}

	// 상위 카테고리 가져오기
	@RequestMapping(value = "/admin/product/registform", method = RequestMethod.GET)
	public ModelAndView getTopList() {
		List topList = topCategoryService.selectAll();// 3단계:로직 객체에 일시키기

		// 4단계:저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList", topList);
		mav.setViewName("admin/product/regist_form");
		return mav;
	}

	// 하위 카테고리 가져오기
	// 스프링에서는 java객체와 json간 변환을 자동으로 처리해주는 라이브러리를 지원한다
	@RequestMapping(value = "/admin/product/sublist", method = RequestMethod.GET)
	@ResponseBody
	public List getSubList(int topcategory_id) {
		List<SubCategory> subList = subCategoryService.selectAllById(topcategory_id);
		return subList;
	}

	/*
	 * @RequestMapping(value="/admin/product/sublist",method=RequestMethod.GET,
	 * produces = "application/json;charset=utf8")
	 * 
	 * @ResponseBody //jsp와 같은 뷰페이지가 아닌, 단순 데이터만 전송시 public String getSubList(int
	 * topcategory_id) { logger.debug("topcategory_id:" +topcategory_id);
	 * 
	 * List<SubCategory> subList=subCategoryService.selectAllById(topcategory_id);
	 * 
	 * //리스트를 jsop으로 변형해야 한다. StringBuilder sb = new StringBuilder();
	 * sb.append("{"); sb.append("\"subList\" : ["); for(int
	 * i=0;i<subList.size();i++) { SubCategory subCategory=subList.get(i);
	 * sb.append("{");
	 * sb.append("\"subcategory_id\":"+subCategory.getSubcategory_id()+",");
	 * sb.append("\"topcategory_id\":"+subCategory.getTopcategory_id()+",");
	 * sb.append("\"name\":\""+subCategory.getName()+"\""); if(i<subList.size()-1) {
	 * sb.append("},"); }else { sb.append("}"); } }
	 * 
	 * sb.append("]"); sb.append("}");
	 * 
	 * return sb.toString(); }
	 */

	// 상품 목록
	@RequestMapping(value = "/admin/product/list", method = RequestMethod.GET)
	public ModelAndView getProductList() {
		ModelAndView mav = new ModelAndView("admin/product/product_list");
		return mav;
	}

	// 상품등록 폼
	@RequestMapping(value = "/admin/product/registform")
	public String registForm() {
		return "admin/product/regist_form";
	}
	// 상품 상세

	// 상품 등록
	@RequestMapping(value = "/admin/product/regist", method = RequestMethod.POST)
	@ResponseBody
	public String registProduct(Product product, HttpServletRequest request) {
		logger.debug("하위카테고리 " + product.getSubcategory_id());
		logger.debug("상품명 " + product.getProduct_name());
		logger.debug("가격 " + product.getPrice());
		logger.debug("브랜드 " + product.getBrand());
		logger.debug("상세내용 " + product.getDetail());
		//logger.debug("업로드 이미지명 " + product.getRepImg().getOriginalFilename());

//		for (int i = 0; i < product.getAddImg().length; i++) {
//			logger.debug(product.getAddImg()[i].getOriginalFilename());
//		}
		
		logger.debug("insert하기 전 상품의 product_id "+product.getProduct_id());
		productService.regist(fileManager,product);//상품등록
		logger.debug("방금 insert된 상품의 Product_id "+product.getProduct_id());
		
		
		/*
		for (int i = 0; i < product.getFit().length; i++) {
			String fit = product.getFit()[i];
			logger.debug("지원 사이즈는  " + fit);
		}*/

		return "hahahahhah";
	}
	// 상품 수정


	// 상품 삭제
	
	//예외처리
	//위의 메서드 중에서 하나라도 예외가 발생하면,
}
