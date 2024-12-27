package member.controller;

import java.util.HashMap;
import java.util.Map;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;

public class Idle extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		String method = request.getMethod(); // "GET" 또는 "POST"
		
		System.out.println("휴면 해제 페이지");
		
		request.setAttribute("method", method);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/member/idle.jsp");
		
		if("POST".equalsIgnoreCase(method)) {
			
			System.out.println("post임~~~~");
			
			String m_name = request.getParameter("m_name");
			String m_mobile = request.getParameter("m_mobile");
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("m_name", m_name);
			paraMap.put("m_mobile", m_mobile);
			
			int n = mdao.checkMobileName(paraMap);
			
			if(n == 1) {
							
				request.setAttribute("n", n);
				request.setAttribute("m_name", m_name);
				request.setAttribute("m_mobile", m_mobile);
				
			//	String message = "인증번호가 발송되었습니다.";
		    //    request.setAttribute("message", message);
		        
			}
			else {
				String message = "존재하지 않는 회원입니다.";
		        String loc = "javascript:history.back()";
		         
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		         
		        super.setRedirect(false); 
		        super.setViewPage("/WEB-INF/msg.jsp");
			}
		}
		

		
		

		
		
	    
	}

}
