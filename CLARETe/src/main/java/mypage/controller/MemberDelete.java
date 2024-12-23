package mypage.controller;

import java.util.HashMap;
import java.util.Map;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;

public class MemberDelete extends AbstractController {
	
	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" ���� "POST"
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		
		if("POST".equalsIgnoreCase(method)) {
			String m_status = request.getParameter("m_status");
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("m_status", m_status);
			paraMap.put("m_id", loginuser.getM_id());
			paraMap.put("m_pwd", loginuser.getM_pwd());
			
			int memberDelete = mdao.memberDelete(paraMap);
			
			if(memberDelete == 1) {
				
				request.setAttribute("memberDelete", memberDelete);
				request.setAttribute("m_status", m_status);
		        
			}
			else {
				String message = "sql오류로 회원탈퇴 실패!!";
		        String loc = "javascript:history.back()";
		         
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		         
		        super.setRedirect(false); 
		        super.setViewPage("/WEB-INF/msg.jsp");
			}
		}
		
		
		
	//	request.setAttribute("m_id", m_id);
	//	request.setAttribute("method", method);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/member/memberDelete.jsp");

	}
}
