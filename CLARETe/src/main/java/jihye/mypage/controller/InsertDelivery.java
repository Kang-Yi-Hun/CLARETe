package jihye.mypage.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cart.domain.CartVO;
import chaeeun.cart.model.CartDAO;
import chaeeun.cart.model.CartDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;
import delivery.model.*;
import delivery.domain.*;
import minkyu.product.model.ProductDAO;
import minkyu.product.model.ProductDAO_imple;
import product.domain.ProductVO;

public class InsertDelivery extends AbstractController {
	
	DeliveryDAO ddao = new DeliveryDAO_imple();
	CartDAO cdao = new CartDAO_imple();
	private ProductVO pvo = new ProductVO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	String method = request.getMethod(); // "GET" 또는 "POST"
		
		if("GET".equalsIgnoreCase(method)) {
			/* System.out.println("get 도착~"); */
			
			HttpSession session = request.getSession();
			MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
			
			String m_id = loginuser.getM_id();
			
			List<DeliveryVO> deliveryList = ddao.selectDeliveryList(m_id);
			request.setAttribute("deliveryList", deliveryList);
	
			List<CartVO> cartList = cdao.cartListCount(m_id);
			request.setAttribute("cartList",cartList.size());
			//System.out.println("배송지cartList: " + cartList.size()); 
			
	        //System.out.println(deliveryList.size()); 
	        
	    	
			List<Integer> pnumList = (List<Integer>) session.getAttribute("pnumList");
			ProductDAO pdao = new ProductDAO_imple();
			List<ProductVO> pvoList = pdao.selectProduct(pnumList);
			Set<ProductVO> pvoSet = new HashSet<>(pvoList);
			pvoList = new ArrayList<>(pvoSet);
			request.setAttribute("pvoListCount", pvoList.size());
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/mypage/mypage_delivery_detail.jsp");
			
		}
		
		
		else {
			
			
			HttpSession session = request.getSession();
			MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
			
			String fk_m_id = request.getParameter("fk_m_id");
			String d_name = request.getParameter("d_name");
			String d_address = request.getParameter("d_address");			
			String d_detail_address = request.getParameter("d_detail_address");
			String d_postcode = request.getParameter("d_postcode");
			String d_extra = request.getParameter("d_extra");
			String d_mobile = request.getParameter("d_mobile");
			String d_num = request.getParameter("d_num");
			
			DeliveryVO delivery = new DeliveryVO();
			
			//delivery.setD_num(d_num);
			delivery.setFk_m_id(fk_m_id);
			delivery.setD_name(d_name);
			delivery.setD_address(d_address);
			delivery.setD_detail_address(d_detail_address);
			delivery.setD_postcode(d_postcode);
			delivery.setD_extra(d_extra);
			delivery.setD_mobile(d_mobile);

		
			
		
			int n = ddao.InsertDelivery(delivery);
			
			if(n==1) {// 넣은 값이 있다면 
				
				
				/*
					request.setAttribute("fk_m_id", fk_m_id);
					request.setAttribute("d_name", d_name);
					request.setAttribute("d_address", d_address);
					request.setAttribute("d_detail_address", d_detail_address);
					request.setAttribute("d_postcode", d_postcode);
					request.setAttribute("d_extra", d_extra);
					request.setAttribute("d_mobile", d_mobile);
				*/
				
				
				
			}
			
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"/mypage/insertDelivery.cl");
		}
		
	
		
	}

}
