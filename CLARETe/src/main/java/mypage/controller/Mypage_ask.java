package mypage.controller;


import java.sql.SQLException;
import java.util.List;

import cart.domain.CartVO;
import chaeeun.cart.model.CartDAO_imple;
import common.controller.AbstractController;
import faq.domain.FaqVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;
import qna.domain.QnaVO;
import youjin.qna.model.QnaDAO;
import youjin.qna.model.QnaDAO_imple;

public class Mypage_ask extends AbstractController {

	private QnaDAO qdao = new QnaDAO_imple();
	CartDAO_imple cdao = new CartDAO_imple();	// 채은 코드 추가
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		
		String m_id = loginuser.getM_id();
		
		String method = request.getMethod(); // "GET" 또는 "POST"
		System.out.println(method);
		
		if("GET".equalsIgnoreCase(method)) {			
			// GET 방식이면 페이지 이동 안되게
					
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/mypage/mypage_ask.jsp");
		
		} 
		

		try {
			List<QnaVO> myQnaList = qdao.myQnaList(m_id);
			request.setAttribute("myQnaList", myQnaList);
			
			super.setRedirect(false);		
			super.setViewPage("/WEB-INF/mypage/mypage_ask.jsp");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			// 채은 코드 추가
			List<CartVO> cartList = cdao.cartListCount(m_id);
			
			//System.out.println("cartList: " + cartList);
			
			request.setAttribute("cartList",cartList.size());
			// 채은 코드 끝
			
		} catch(SQLException e) {
			e.printStackTrace();
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"index.cl"); ///error.up 은 없어서 일단 메인
		}
	}

}
