package com.springmvc.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;
import com.model2.domain.Board;

public class DeleteController implements Controller {
	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int board_id = boardDAO.delete(Integer.parseInt(request.getParameter("board_id")));
		
		ModelAndView mav = new ModelAndView();
		
		if(board_id==1) {
			mav.setViewName("redirect:/board/list");//������ ��� url			
		}else {
			mav.addObject("msg","��������");
			mav.setViewName("error/result");//������ ��� url			
		}
		return mav;
	}

}
