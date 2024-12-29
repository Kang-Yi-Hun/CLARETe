package cart.controller;

import java.util.List;

import chaeeun.cart.model.CartDAO;
import chaeeun.cart.model.CartDAO_imple;
import common.controller.AbstractController;
import delivery.domain.DeliveryVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;

public class CartOrder extends AbstractController {

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
			MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");

			if (loginuser != null) {
				
				String message = "선택된 상품이 없습니다. 상품을 선택해주세요.";
				String loc = "javascript:history.back()";

				request.setAttribute("message", message);
				request.setAttribute("loc", loc);

				super.setViewPage("/WEB-INF/msg.jsp");			
				
			}
			
			// ---------------------------------------------------------------------------------
			// 로그인 사용자 정보를 JSP에 전달
			request.setAttribute("m_name", loginuser.getM_name());
			request.setAttribute("m_mobile", loginuser.getM_mobile());
			request.setAttribute("m_email", loginuser.getM_email());
			request.setAttribute("m_point", loginuser.getM_point());

			// ---------------------------------------------------------------------------------
			// input 태그 값 받아오기 (향수 정보들)
			String[] index = request.getParameterValues("index");
			String[] perfumeName = request.getParameterValues("perfumeName");
			String[] quantity = request.getParameterValues("quantity");
			String[] price = request.getParameterValues("price");
			String[] priceQuantity = request.getParameterValues("priceQuantity");
			String[] option = request.getParameterValues("option");

			// input 태그 값 받아오기 (가격)
			String totalProduct = request.getParameter("totalProduct");
			String totalShipping = request.getParameter("totalShipping");
			String totalTotal = request.getParameter("totalTotal");
			
			// ---------------------------------------------------------------------------------
			// 배송 정보 조회하기
			CartDAO cdao = new CartDAO_imple();
			List<DeliveryVO> deliveryList = cdao.selectDeliveryList(loginuser.getM_id());
			request.setAttribute("deliveryList", deliveryList);
			
			
			if (index == null || index.length == 0) {
				// 상품 장바구니 없는 경우 !!

				String message = "선택된 상품이 없습니다. 상품을 선택해주세요.";
				String loc = "javascript:history.back()";

				request.setAttribute("message", message);
				request.setAttribute("loc", loc);

				super.setViewPage("/WEB-INF/msg.jsp");

				return;
				
			} else {
				
				request.setAttribute("index", index);
                request.setAttribute("perfumeName", perfumeName);
                request.setAttribute("quantity", quantity);
                request.setAttribute("price", price);
                request.setAttribute("priceQuantity", priceQuantity);
                request.setAttribute("option", option);
                
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("totalShipping", totalShipping);
                request.setAttribute("totalTotal", totalTotal);
                
			}

			super.setRedirect(false);
			super.setViewPage("/WEB-INF/cart/cart_order.jsp");
		}

	}

}
