package member.controller;

import java.util.HashMap;
import java.util.Map;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class IdleEnd extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" Ǵ "POST"

		request.setAttribute("method", method);

		System.out.println("method 메소드 확인"+ method);
		HttpSession session = request.getSession();
		
		if("get".equalsIgnoreCase(method)) {
			String auth =  (String) session.getAttribute("auth");

			if(auth != null) {
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/member/idleEnd.jsp");
				session.removeAttribute("auth");
			} else {
				super.setRedirect(true);
				super.setViewPage(request.getContextPath()+"/index.cl");
			}
			
		}
		if ("POST".equalsIgnoreCase(method)) {

			System.out.println("실ㅇ행ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");

			String m_name = request.getParameter("m_name");
			String m_mobile = request.getParameter("m_mobile");
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("m_name", (String) session.getAttribute("m_name"));
			paraMap.put("m_mobile", (String) session.getAttribute("m_mobile"));
			System.out.println("uuuuuuuuuuuuuuuuuuuu" + m_name); // ok
			System.out.println("uuuuuuuuuuuuuuuuuuuu" + m_mobile); // ok

			String certification = request.getParameter("certification"); // ? 값 자체가 없음
			System.out.println("uuuuuuuuuuuuuuuuuuuu" + certification);

			
			String certification_code = (String) session.getAttribute("certification_code");
			
			System.out.println("certification_code          " + certification_code);

			

			if (certification.equals(certification_code)) {
				
				int n = mdao.idleUpdate(paraMap);
				
				if(n==1) {
					String message = "휴면이 해제되었습니다.";
			        String loc = request.getContextPath()+"/login/loginView.cl";
			        
			        request.setAttribute("message", message);
			        request.setAttribute("loc", loc);
			        
			        super.setViewPage("/WEB-INF/msg.jsp");
				}
				System.out.println("n값 확인" + n);
				//		if (n == 1) {
				
				//		}
			} // end of if(certification == certification_code)

		} // end of if("POST".equalsIgnoreCase(method))
		
		
	}

}