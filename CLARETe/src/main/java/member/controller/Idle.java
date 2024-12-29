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


		String method = request.getMethod(); // "GET" �Ǵ� "POST"
		
		System.out.println("�޸� ���� ������");
		
		request.setAttribute("method", method);
		
		
		if("POST".equalsIgnoreCase(method)) {
			
			System.out.println("post��~~~~");
			
			String m_name = request.getParameter("m_name");
			String m_mobile = request.getParameter("m_mobile");
			String m_idle = request.getParameter("m_idle");
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("m_name", m_name);
			paraMap.put("m_mobile", m_mobile);
			
			int n = mdao.checkMobileName(paraMap);
			
			if(n == 1) {
							
				paraMap.put("m_idle", m_idle);
				request.setAttribute("m_idle", m_idle);
				request.setAttribute("m_name", m_name);
				request.setAttribute("m_mobile", m_mobile);
			//	String message = "������ȣ�� �߼۵Ǿ����ϴ�.";
		    //  request.setAttribute("message", message);
		        System.out.println("�޴��ȣ" + m_mobile);
			}
			else {
				String message = "�������� �ʴ� ȸ���Դϴ�.";
		        String loc = "javascript:history.back()";
		         
		        request.setAttribute("message", message);
		        request.setAttribute("loc", loc);
		         
		        super.setRedirect(false); 
		        super.setViewPage("/WEB-INF/msg.jsp");
			}
		}
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/member/idle.jsp");
	}

}