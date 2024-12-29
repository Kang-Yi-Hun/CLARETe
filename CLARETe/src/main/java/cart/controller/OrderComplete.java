package cart.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;

import java.util.HashMap;
import java.util.Map;

import chaeeun.order.model.*;

public class OrderComplete extends AbstractController {

	private OrderDAO odao = new OrderDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		
		if (!"POST".equals(method)) { // "GET"인 경우

			String message = "비정상적인 경로로 들어왔습니다.";
			String loc = "javascript:history.back()";

			request.setAttribute("message", message);
			request.setAttribute("loc", loc);

			super.setViewPage("/WEB-INF/msg.jsp");

			return; // execute(HttpServletRequest request, HttpServletResponse respone) 메소드 종료함.

		} else {
			
			HttpSession session = request.getSession();
			
			
			//fk_op_num, o_date, status, o_price
			
			// 주문 insert 하기
			Map<String, String> paraMap = new HashMap<>();
			MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
			paraMap.put("fk_m_id", loginuser.getM_id());
			
			String fk_d_num = request.getParameter("selectedDNum");
			paraMap.put("fk_d_num", fk_d_num);
			
			
			int n = odao.insertOrder(paraMap);
			
			super.setRedirect(false);
		    super.setViewPage("/WEB-INF/cart/orderComplete.jsp");
		}
 
	}

}
