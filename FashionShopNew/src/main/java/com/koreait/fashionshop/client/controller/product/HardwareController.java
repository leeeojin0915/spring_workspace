package com.koreait.fashionshop.client.controller.product;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.fashionshop.model.product.service.HardwareService;

@Controller
public class HardwareController {
	@Autowired
	private HardwareService hardwareService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list() {
		List hardwareList=hardwareService.selectAll();
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("hardwareList",hardwareList);
		mav.setViewName("/list");
		return mav;
	}
}

