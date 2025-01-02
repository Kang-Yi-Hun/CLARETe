package mypage.controller;

import java.sql.SQLException;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;

public class Mypage_memberUpdateEnd extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" 또는 "POST" 
		
		if("post".equalsIgnoreCase(method)) {
			// **** POST 諛⑹���쇰� ���댁�� 寃��대�쇰㈃ **** //
			
			String m_id = request.getParameter("m_id");  
			String m_name = request.getParameter("m_name");
			String m_mobile = request.getParameter("m_mobile");
			String m_postcode = request.getParameter("m_postcode");
			String m_address = request.getParameter("m_address");
			String m_detail_address = request.getParameter("m_detail_address");
			String m_extra = request.getParameter("m_extra");
			
			MemberVO member = new MemberVO();
			member.setM_id(m_id);
			member.setM_name(m_name);
			member.setM_mobile(m_mobile);
			member.setM_postcode(m_postcode);
			member.setM_address(m_address);
			member.setM_detail_address(m_detail_address);
			member.setM_extra(m_extra);
		
	        try {
	        	
	        	// 회원의 정보를 수정하는 메소드
				int n = mdao.updateMember(member);
		        
		        if(n==1) {
	                HttpSession session = request.getSession();
	                MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
	                
	                loginuser.setM_name(m_name);
	                loginuser.setM_mobile(m_mobile);
	                loginuser.setM_postcode(m_postcode);
	                loginuser.setM_address(m_address);
	                loginuser.setM_detail_address(m_detail_address);
	                loginuser.setM_extra(m_extra);
		        	
		        	String message = "변경이 완료되었습니다.";
		        	String loc = request.getContextPath()+"/mypage/mypage_memberUpdate.cl"; // �������댁�濡� �대������.
		            
		            request.setAttribute("message", message);
		            request.setAttribute("loc", loc);
		            
		            super.setRedirect(false); 
		            super.setViewPage("/WEB-INF/msg.jsp");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				super.setRedirect(true);
				super.setViewPage(request.getContextPath() + "/error.cl");
			}
	        
		}
		
	}

}
