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
		String loc = "";
		String message = "";
		if("POST".equalsIgnoreCase(method)) {
			
			String m_pwd = request.getParameter("m_pwd");
			
			
			Map<String, String> paraMap = new HashMap<>();

			paraMap.put("m_id", loginuser.getM_id());
			paraMap.put("m_pwd", m_pwd);
			
			int memberDelete = mdao.memberDelete(paraMap);
			
			if(memberDelete == 1) {
				request.setAttribute("m_pwd", m_pwd);
				
				message = "회원탈퇴가 완료되었습니다.";
		        loc = request.getContextPath()+"/index.cl";
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		         
		        super.setViewPage("/WEB-INF/msg.jsp");
		        session.removeAttribute("loginuser");
		        return;
			}
			else {
				message = "비밀번호가 일치하지 않습니다.";
		        loc = "javascript:history.back()";
		         
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		        
		        
		        super.setViewPage("/WEB-INF/msg.jsp");
		        return;
			}
		}
			
		request.setAttribute("method", method);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/member/memberDelete.jsp");

	}
}
