package member.controller;

import java.util.HashMap;
import java.util.Map;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Idle extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" Ǵ "POST"

		String loc = "";
		String message = "";
		request.setAttribute("method", method);

		if ("POST".equalsIgnoreCase(method)) {

			String m_name = request.getParameter("m_name");
			String m_mobile = request.getParameter("m_mobile");
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("m_name", m_name);
			paraMap.put("m_mobile", m_mobile);
			System.out.println("uuuuuuuuuuuuuuuuuuuu" + m_name); // ok
			System.out.println("uuuuuuuuuuuuuuuuuuuu" + m_mobile); // ok

			boolean bool = mdao.idlecheck(paraMap);
			System.out.println("m_name 확인" + m_name); // null
			System.out.println("m_mobile 확인" + m_mobile); // 잘뜸
			System.out.println("bool 확인" + bool);
			if (bool) {

				message = "인증번호가 전송되었습니다.";
		        loc = request.getContextPath()+"/member/idleEnd.cl";
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		        
		        HttpSession session =  request.getSession();
		        session.setAttribute("auth", "true");
		        
		        super.setViewPage("/WEB-INF/msg.jsp");
		        return;
				
			} else {
				message = "[경고] 성명과 전화번호가 일치하지 않습니다.\n 다시 입력해주세요!!";
		        loc = "javascript:history.back()";
		        
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		        
		        super.setViewPage("/WEB-INF/msg.jsp");
		        return;
			}
		}

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/member/idle.jsp");
	}

}
