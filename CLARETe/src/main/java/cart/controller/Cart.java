package cart.controller;

import java.util.List;

import cart.domain.CartVO;
import chaeeun.cart.model.CartDAO;
import chaeeun.cart.model.CartDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;

public class Cart extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberVO login_id = (MemberVO) session.getAttribute("loginuser");
		
		System.out.println("ddddddddddddddddddd" + login_id.getM_id());
		System.out.println("ddddddddddddddddddd" + login_id.getM_mobile());
		
	    if (login_id.getM_id() == null) {
	    	
	    	super.setRedirect(true);
            super.setViewPage("/login/login.jsp");
            return;
            
	    } else {
	    	
	    	CartDAO cdao = new CartDAO_imple();
	    	List<CartVO> cartList = cdao.selectCart(login_id.getM_id());
	    	
	    	System.out.println("우하하하하하하하하" + cartList.size());	// 장바구니 개수 잘 받아옴
	    	
	    	request.setAttribute("cartList", cartList);
	    	
	    	super.setRedirect(false);
		    super.setViewPage("/WEB-INF/cart/cart.jsp");
		    
	    }

	}

}
