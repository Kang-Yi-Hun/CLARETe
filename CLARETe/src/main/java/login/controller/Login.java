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
			
			System.out.println("id : " + id);
			System.out.println("pwd : " + pwd);
			
			String clientip = request.getRemoteAddr();
			
			request.setAttribute("id", id); // 濡�洹몄�� �� ���대�� 媛� �����ㅺ린 ���� 異�媛�
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("id", id);
			paraMap.put("pwd", pwd);
			paraMap.put("clientip", clientip);
			
			MemberVO loginuser = mdao.login(paraMap); 
			
			if(loginuser != null) {
				System.out.println(id + "濡�洹몄�� �깃났");
				System.out.println("Logged-in User: " + loginuser.getM_id());
				
				if (loginuser.getM_idle() == 0) {
					// �대㈃怨����대㈃
					
					String message = "濡�洹몄�몄�� ��吏� 1���� 吏����� �대㈃����濡� �����듬����.\\n�대㈃�� ���댁＜�� ���댁�濡� �대���⑸����!!";
					String loc = request.getContextPath()+"/member/idle.cl";		// �대㈃���댁＜�� ���댁� 留��ㅼ�댁�� �댁��
				
					request.setAttribute("message", message);
					request.setAttribute("loc", loc);
					
					super.setRedirect(false); 
					super.setViewPage("/WEB-INF/msg.jsp");
					
					return;
				}
				
				HttpSession session = request.getSession();	
				session.setAttribute("loginuser", loginuser);
				session.setAttribute("id", id);
				session.setAttribute("m_mobile", loginuser.getM_mobile());
				session.setAttribute("m_postcode", loginuser.getM_postcode());
				session.setAttribute("m_address", loginuser.getM_address());
				session.setAttribute("m_detail_address", loginuser.getM_detail_address());
				session.setAttribute("m_extra", loginuser.getM_extra());
				
				System.out.println(loginuser.getM_id());
				
				if(loginuser.isRequirePwdChange() ) {
					 // 鍮�諛�踰��몃�� 蹂�寃쏀��吏� 3媛��� �댁���� 寃쎌��
					
					String message = "鍮�諛�踰��몃�� 蹂�寃쏀����吏� 3媛����� 吏��ъ�듬����.\\n���몃�� 蹂�寃쏀���� ���댁�濡� �대���⑸����!!"; 
					String loc = request.getContextPath()+"/index.up";
					// ������ ����媛��� index.up �� ������ ���몃�� 蹂�寃쏀���� ���댁�濡� URL�� �≪��二쇱�댁�� ����.!!
					
					request.setAttribute("message", message);
					request.setAttribute("loc", loc);
					
					super.setRedirect(false); 
					super.setViewPage("/WEB-INF/msg.jsp");
					
					return; // 硫����� 醫�猷� 
				} else { // 鍮�諛�踰��몃�� 蹂�寃쏀��吏� 3媛��� 誘몃��� 寃쎌��
				
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
				String message = "���댄���� ����������.";
		        String loc = "javascript:history.back()";
		         
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		         
		        super.setRedirect(false); 
		        super.setViewPage("/WEB-INF/msg.jsp");
				
			}
			
			
		}
	    
	}

}
