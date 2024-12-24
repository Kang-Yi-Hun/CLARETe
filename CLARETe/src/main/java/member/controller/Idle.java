package member.controller;

import java.util.HashMap;
import java.util.Map;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Idle extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		
		request.setAttribute("method", method);
		
		if("get".equalsIgnoreCase(method)) {
			super.setRedirect(false);
		    super.setViewPage("/WEB-INF/member/idle.jsp");
		    
		}
		else {
			String m_name = request.getParameter("m_name");
			String m_mobile = request.getParameter("m_mobile");
			
			Map<String, String> paraMap = new HashMap<>();
			
			paraMap.get("m_name");
			paraMap.get("m_mobile");
			
			// 유저가 존재하는지 찾기
			boolean idleuser = mdao.checkMobileName(paraMap);
			
		}
		
		
	    
	}

}
