package cart.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;

public class CartOrder extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		System.out.println(method);		// post 라고 출력됨
		
		if (!"POST".equals(method)) {	// "GET"인 경우
			
			String message = "비정상적인 경로로 들어왔습니다.";
			String loc = "javascript:history.back()";
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			super.setViewPage("/WEB-INF/msg.jsp");
			
			return; // execute(HttpServletRequest request, HttpServletResponse respone) 메소드 종료함.  
			
		} else {
			
			String[] productNames = request.getParameterValues("productIds");
			
			for (String name : productNames) {
                System.out.println("Product Name: " + name);
            }
			
			
			super.setRedirect(false);
		    super.setViewPage("/WEB-INF/cart/cart_order.jsp");
		}
		
	    
	}

}
