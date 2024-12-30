package myshop.controller;

import org.json.JSONObject;


import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import yihun.cart.model.*;

public class InsertCartJSON extends AbstractController {

	private CartDAO cdao = new CartDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if ("POST".equalsIgnoreCase(method)) {
			
			JSONObject jsonObj = new JSONObject();		// {}
			int result;
			
			String p_num = request.getParameter("p_num");	// 상품번호
			String op_num = request.getParameter("op_num"); // 옵션번호
			String m_id = request.getParameter("m_id");		// 회원아이디
			
			// 회원이 장바구니에 넣었었는지 상태 체크
			boolean isExists = cdao.cartCheckInsert(m_id, op_num, p_num);	
			
			if(isExists) {
				result = cdao.cartDelete(m_id, op_num, p_num);
                jsonObj.put("action", "delete");
			}
			else {
				result = cdao.cartInsert(m_id, op_num, p_num);
                jsonObj.put("action", "insert");
			}
			
			
			jsonObj.put("result", result);	
			
			String json = jsonObj.toString();		// 문자열 형태인 "{"isExists": true}" 또는 "{"isExists": false}" 로 만들어준다.

			request.setAttribute("json", json);
			response.setContentType("application/json; charset=UTF-8");
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
		}

	}

}
