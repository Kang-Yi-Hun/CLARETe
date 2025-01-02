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

		String method = request.getMethod(); // "GET" 풔 "POST"

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
			System.out.println("m_name ����" + m_name); // null
			System.out.println("m_mobile ����" + m_mobile); // ����
			System.out.println("bool ����" + bool);
			if (bool) {

				message = "�몄�踰��멸� ���〓�����듬����.";
		        loc = request.getContextPath()+"/member/idleEnd.cl";
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		        
		        HttpSession session =  request.getSession();
		        session.setAttribute("auth", "true");
		        session.setAttribute("m_name", m_name);
		        session.setAttribute("m_mobile", m_mobile);
		        super.setViewPage("/WEB-INF/msg.jsp");
		        return;
				
			} else {
				message = "[寃쎄�] �깅�怨� ����踰��멸� �쇱���吏� ���듬����.\n �ㅼ�� ���ν�댁＜�몄��!!";
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
