package member.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class VerifyCertification extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		
		String method = request.getMethod();
		
		if("post".equalsIgnoreCase(method)) {
			
			String userCertificationCode = request.getParameter("userCertificationCode");
			String m_id = request.getParameter("m_id");
			
			HttpSession session = request.getSession(); // 세션불러오기
			String certification_code = (String)(session.getAttribute("certification_code"));
			
			String message = "";
			String loc = "";
			
			if(certification_code.equals(userCertificationCode)) {
				message = "인증성공 되었습니다.";
				loc = request.getContextPath()+"/member/pwd_find_3.cl="+m_id;
			}
			else {
				message = "발급된 인증코드가 아닙니다.\\n인증코드를 다시 발급받으세요!!";
				loc = request.getContextPath()+"/member/pwd_find_1.cl";
			}
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
			// !!!! 중요 !!!! //
	        // !!!! 세션에 저장된 인증코드 삭제하기 !!!! //
			session.removeAttribute("certification_code");
			
		} // end of if(post)---------
		
		
	}

}
