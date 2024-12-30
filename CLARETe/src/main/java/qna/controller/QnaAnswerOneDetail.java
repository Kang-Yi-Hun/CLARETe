package qna.controller;

import java.sql.SQLException;
import java.util.List;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;
import qna.domain.QnaVO;
import youjin.qna.model.QnaDAO;
import youjin.qna.model.QnaDAO_imple;

public class QnaAnswerOneDetail extends AbstractController {

	private QnaDAO qdao = new QnaDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ==== 관리자(admin)로 로그인 했을때만 조회가 가능하도록 한다. ==== //
		HttpSession session = request.getSession();		
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser"); 

		/*if(loginuser != null && "admin".equals(loginuser.getM_id())) {*/
			// 관리자(admin)로 로그인 했을 경우 
			String method = request.getMethod();
		

			if("POST".equalsIgnoreCase(method)) {
				// POST 방식일때
				//int q_num = Integer.parseInt(request.getParameter("q_num")); //게시글에 q_num넣기 받아와서 임플에서 selectOneQna sql작성 , jsp에 리퀘스트스코프.qvo.겟다음에 네임
				
			    String q_answer = request.getParameter("q_answer");
				String q_num = request.getParameter("q_num");
				/*
				  System.out.println("q_num : "+q_num);
				  System.out.println("q_answer : "+q_answer);
				 */
				String goBackURL = request.getParameter("goBackURL");
				
				QnaVO qvo = qdao.selectOneQna(Integer.parseInt(q_num));
				qvo.setQ_answer(q_answer);				
					
				request.setAttribute("qvo", qvo);
				request.setAttribute("goBackURL", goBackURL);
				
				super.setRedirect(false);			
				super.setViewPage("/WEB-INF/qna/qnaAnswerOneDetail.jsp");
				
				String message = "";
				String loc = "";
				try { 				  
					  
					System.out.println("문의 등록 테스트!");
					  int n = qdao.qnaAnswerUpload(qvo);
				  
					  if (n == 1) {	
							message = "1:1 문의 답변이 등록되었습니다.";
							loc = request.getContextPath()  + "/index.cl";			   		    
						}
				  super.setRedirect(false);
				  super.setViewPage("/WEB-INF/qna/qnaAnswerOneDetail.jsp");
				  
				  } catch(SQLException e) { 
					  e.printStackTrace(); 
					  message = "1:1 문의 답변 등록에 실패했습니다.";
					 loc = "javascript:history.back()";		// JS를 이용한 이전페이지로 이동하는 것
					 
				  super.setRedirect(true);
				  super.setViewPage(request.getContextPath()+"index.cl");
				  }			
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
				
			}
			/*
			 * }
			 * 
			 * else { // 로그인을 안하거나 또는 관리자(admin)가 아닌 사용자로 로그인 했을 경우 String message =
			 * "관리자만 접근이 가능합니다."; String loc = "javascript:history.back()";
			 * 
			 * request.setAttribute("message", message); request.setAttribute("loc", loc);
			 * 
			 * super.setRedirect(false); super.setViewPage("/WEB-INF/msg.jsp"); }
			 */
		
			
			  
			 
		

	}

}
