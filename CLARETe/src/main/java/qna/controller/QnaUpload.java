package qna.controller;

import java.sql.SQLException;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import qna.domain.QnaVO;
import youjin.qna.model.*;

	

public class QnaUpload extends AbstractController {

	private QnaDAO qdao = new QnaDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod(); // "GET" 또는 "POST"
		System.out.println(method);
		
		if("GET".equalsIgnoreCase(method)) {			
			// GET 방식이면 페이지 이동 안되게
			System.out.println("겟으로 들어옴");
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/qna/qna.jsp");
		
		} else {		
		
			System.out.println("포스트로 들어옴");
			
			
			
			String fk_m_id = request.getParameter("fk_m_id");
			/* int p_num = Integer.parseInt(request.getParameter("p_num")); */
			String q_title = request.getParameter("q_title");
			String q_ask = request.getParameter("q_ask");			
			int q_category = Integer.parseInt(request.getParameter("q_category"));
			
			
			QnaVO qvo = new QnaVO();
			qvo.setFk_m_id(fk_m_id);
			/* qvo.setQ_num(p_num); */
			qvo.setQ_title(q_title);			
			qvo.setQ_ask(q_ask);			
			qvo.setQ_category(q_category);
			
			
			String message = "";
			String loc = "";
			
			try {
				int n = qdao.qnaUpload(qvo);
				
				if (n == 1) {	
					message = "1:1 문의가 등록되었습니다.";
					loc = request.getContextPath()  + "/index.cl";			   		    
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				message = "1:1 문의 등록에 실패했습니다.";
				loc = "javascript:history.back()";		// JS를 이용한 이전페이지로 이동하는 것
			}
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			

//			super.setRedirect(true);
			super.setViewPage("/WEB-INF/msg.jsp");
		}

	}

}
