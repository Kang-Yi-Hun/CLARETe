package qna.controller;

import java.sql.SQLException;
import java.util.List;

import common.controller.AbstractController;
import faq.domain.FaqVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import qna.domain.QnaVO;
import youjin.qna.model.*;


public class Qna_board extends AbstractController {
	
	private QnaDAO qdao = new QnaDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// admin만 볼수있게
		
		String method = request.getMethod(); // "GET" 또는 "POST"
		System.out.println(method);
		
		if("GET".equalsIgnoreCase(method)) {			
			// GET 방식이면 페이지 이동 안되게
					
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/qna/qna_board.jsp");
		
		} 
			

		try {
			List<QnaVO> qnaList = qdao.qnaList();
			request.setAttribute("qnaList", qnaList);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/qna/qna_board.jsp");
			
		} catch(SQLException e) {
			e.printStackTrace();
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"index.cl"); ///error.up 은 없어서 일단 메인
		}
	}

}
