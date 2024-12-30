package faq.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import common.controller.AbstractController;
import faq.domain.FaqVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import youjin.faq.model.FaqDAO;
import youjin.faq.model.FaqDAO_imple;

public class FAQUpload extends AbstractController {

	private FaqDAO fdao = new FaqDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		String method = request.getMethod(); // "GET" 또는 "POST"
		System.out.println(method);
		
		if("GET".equalsIgnoreCase(method)) {			
			// GET 방식이면 페이지 이동 안되게
			System.out.println("겟으로 들어옴");
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/faq/faq.jsp");
		
		} else {
			System.out.println("포스트로 들어옴");

			String ask = request.getParameter("ask");
			String answer = request.getParameter("answer");
			
			
			FaqVO faq = new FaqVO();
			faq.setAsk(ask);
			faq.setAnswer(answer);
			
			String message = "";
			String loc = "";
			
			try {
				int n = fdao.faqUpload(faq);
				
				if (n == 1) {	
					message = "FAQ 업로드 성공";
					loc = request.getContextPath()  + "/faq/faq_board.cl";			   		    
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				message = "FAQ 업로드 실패";
				loc = "javascript:history.back()";		// JS를 이용한 이전페이지로 이동하는 것
			}
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
//			super.setRedirect(true);
			super.setViewPage("/WEB-INF/msg.jsp");
		
		}   
			  
			
			
	    
		
	}//end of public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception


	
}
	 