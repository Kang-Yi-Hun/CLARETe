package login.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;

import java.util.HashMap;
import java.util.Map;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;

public class Login extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    
	    String method = request.getMethod();
	    
	    if(!"POST".equalsIgnoreCase(method)) {	// "GET"
			
			String message = "鍮��������� 寃쎈�濡� �ㅼ�댁���듬����.";
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			super.setViewPage("/WEB-INF/msg.jsp");
			
			return; // execute(HttpServletRequest request, HttpServletResponse respone) 硫����� 醫�猷���.  
		} 
	    
	    else {	// "POST"
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String clientip = request.getRemoteAddr();
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("id", id);
			paraMap.put("pwd", pwd);
			paraMap.put("clientip", clientip);
			
			MemberVO loginuser = mdao.login(paraMap); 
			
			if(loginuser != null) {
				System.out.println(id + "濡�洹몄�� �깃났");
				
				/*
				 	�ш린�� �대㈃ 肄��� 異�媛�
				 */
				
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", loginuser);
				
				/*
			 		�ш린��鍮�諛�踰��� 蹂�寃� 3媛��� 肄��� 異�媛�
				 */
				
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.cl");
		        
			} // end of if(loginuser != null) {}
			
			else {
				String message = "로그인실패!!";
		        String loc = "javascript:history.back()";
		         
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		         
		        super.setRedirect(false); 
		        super.setViewPage("/WEB-INF/msg.jsp");
				
			}
			
			
		}
	    
	}

}
