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
			
			String message = "비정상적인 경로로 들어왔습니다.";
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			super.setViewPage("/WEB-INF/msg.jsp");
			
			return; // execute(HttpServletRequest request, HttpServletResponse respone) 메소드 종료함.  
		} 
	    
	    else {	// "POST"
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			
			String clientip = request.getRemoteAddr();
			
			request.setAttribute("id", id);
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("id", id);
			paraMap.put("pwd", pwd);
			paraMap.put("clientip", clientip);
			
			MemberVO loginuser = mdao.login(paraMap); 
			
			if(loginuser != null) {
				
				if (loginuser.getM_idle() == 0) {
					// 휴면계정이면
					
					String message = "로그인을 한지 1년이 지나서 휴면상태로 되었습니다.\\n휴면을 풀어주는 페이지로 이동합니다!!";
					String loc = request.getContextPath()+"/member/idle.cl";
				
					request.setAttribute("message", message);
					request.setAttribute("loc", loc);
					
					super.setRedirect(false); 
					super.setViewPage("/WEB-INF/msg.jsp");
					
					return;
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("loginuser", loginuser);
				session.setAttribute("id", id); // 로그인 한 아이디 값 알아오기 위해 추가
				session.setAttribute("m_mobile", loginuser.getM_mobile());
				session.setAttribute("m_postcode", loginuser.getM_postcode());
				session.setAttribute("m_address", loginuser.getM_address());
				session.setAttribute("m_detail_address", loginuser.getM_detail_address());
				session.setAttribute("m_extra", loginuser.getM_extra());
				
				
				if(loginuser.isRequirePwdChange() ) {
					 // 비밀번호를 변경한지 3개월 이상된 경우
					
					String message = "비밀번호를 변경하신지 3개월이 지났습니다.\\n암호를 변경하는 페이지로 이동합니다!!"; 
					String loc = request.getContextPath()+"/index.cl";
					// 원래는 위와같이 index.up 이 아니라 암호를 변경하는 페이지로 URL을 잡아주어야 한다.!!
					
					request.setAttribute("message", message);
					request.setAttribute("loc", loc);
					
					super.setRedirect(false); 
					super.setViewPage("/WEB-INF/msg.jsp");
					
					return; // 메소드 종료 
				} else { // 비밀번호를 변경한지 3개월 미만인 경우
				
					super.setRedirect(true);
					super.setViewPage(request.getContextPath()+"/index.cl");
				}
				
				
				
				
				if(!("admin".equals(loginuser.getM_id())) ) {
					super.setRedirect(true);
					super.setViewPage(request.getContextPath()+"/index.cl");
				}
				else {
					super.setRedirect(true);
					super.setViewPage(request.getContextPath()+"/admin/adminMain.cl");
				}
			} // end of if(loginuser != null) {}
			
			else {
				String message = "아이디 또는 비밀번호가 일치하지 않습니다!!";
		        String loc = "javascript:history.back()";
		         
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		        
		        
		        super.setRedirect(false); 
		        super.setViewPage("/WEB-INF/msg.jsp");
				
			}
			
			
		}
	    
	}

}
