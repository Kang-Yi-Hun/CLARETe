package cart.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import chaeeun.cart.model.*;

public class CartUpdate extends AbstractController {

	private CartDAO cdao = new CartDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" 또는 "POST" 
		
		if("POST".equalsIgnoreCase(method)) {
			
			String cartNum = request.getParameter("cartNum");
			//String quantity = request.getParameter("quantity");
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("cartNum", cartNum);
			//paraMap.put("quantity", quantity);

			// 장바구니 수량 감소
			int decrease = cdao.decreaseQuantity(paraMap);
			
			System.out.println("decrease" + decrease);
			
			JSONObject jsonOBJ = new JSONObject();
			jsonOBJ.put("decrease", decrease); 	// 임플결과 put 
			
			String json = jsonOBJ.toString();
			request.setAttribute("json", json);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
		
		}
		
	}

}
