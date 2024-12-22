package mypage.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import member.controller.GoogleMail;

public class MemberDelete extends AbstractController {
	
	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" ���� "POST"
		String m_id = request.getParameter("m_id");
		String m_pwd = request.getParameter("m_pwd");
		
		if("POST".equalsIgnoreCase(method)) {
		
			String m_status = request.getParameter("m_status");
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("m_id", m_id);
			paraMap.put("m_pwd", m_pwd);
			paraMap.put("m_status", m_status);
			
			int memberDelete = mdao.memberDelete(paraMap);
			
				
			
			request.setAttribute("memberDelete", memberDelete);
			request.setAttribute("m_status", m_status);
		}
		
		HttpSession session = request.getSession();
		session.getAttribute(m_id);
		session.getAttribute(m_pwd);
		
		request.setAttribute("m_id", m_id);
		request.setAttribute("method", method);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/member/memberDelete.jsp");

	}
}
