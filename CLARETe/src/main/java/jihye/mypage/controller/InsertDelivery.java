package jihye.mypage.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import delivery.model.*;
import delivery.domain.*;

public class InsertDelivery extends AbstractController {
	
	DeliveryDAO ddao = new DeliveryDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	String method = request.getMethod(); // "GET" 또는 "POST"
		
		if("GET".equals(method)) {
		
		// super.setRedirect(false);
		   super.setViewPage("/WEB-INF/member/mypage.jsp");
		}
		
		
		else {
			String fk_m_id = request.getParameter("fk_m_id");
			String d_name = request.getParameter("d_name");
			String d_address = request.getParameter("d_address");			
			String d_detail_adress = request.getParameter("d_detail_address");
			String d_postcode = request.getParameter("d_postcode");
			String d_extra = request.getParameter("d_extra");
			String d_mobile = request.getParameter("d_mobile");
			
			DeliveryVO delivery = new DeliveryVO();
			
			//delivery.setD_num(d_num);
			delivery.setFk_m_id(fk_m_id);
			delivery.setD_name(d_name);
			delivery.setD_address(d_address);
			delivery.setD_detail_address(d_detail_adress);
			delivery.setD_postcode(d_postcode);
			delivery.setD_extra(d_extra);
			delivery.setD_mobile(d_mobile);
			
			
			int n = ddao.InsertDelivery(delivery);
			
			if(n==1) {// 넣은 값이 있다면 
				request.setAttribute("fk_m_id", fk_m_id);
				request.setAttribute("d_name", d_name);
				request.setAttribute("d_address", d_address);
				request.setAttribute("d_detail_address", d_address);
				request.setAttribute("d_postcode", d_postcode);
				request.setAttribute("d_extra", d_extra);
				request.setAttribute("d_mobile", d_mobile);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/mypage/mypage_main.jsp");
			}
			
	
		}
	}

}
