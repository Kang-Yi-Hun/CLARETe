package myshop.controller;

import java.util.List;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;
import option.domain.OptionVO;
import yihun.option.model.*;
import product.domain.ProductVO;
import yihun.myshop.model.*;

public class ProdView extends AbstractController {

	ProductDAO pdao = new ProductDAO_imple();
	OptionDAO odao = new OptionDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("get".equalsIgnoreCase(method)) { // get 으로 들어온 경우
			
			String m_id;
			
			HttpSession session = request.getSession();
			MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
			
			if (loginuser == null) {
		        // 로그인하지 않은 경우
		        m_id = "0";
		    }
			else {
				m_id = loginuser.getM_id(); // 세션값 아이디를 알아온다
			}

			///////////////////////////////////////////////////////////
			
			
			int p_num = Integer.parseInt(request.getParameter("p_num"));
			
			System.out.println(p_num); 
			
			ProductVO pvo = pdao.selectProductOne(p_num); // 상품 상세페이지의 정보를 알아온다
			List<OptionVO> ovo = odao.selectOption(p_num); // 상품 옵션 정보를 알아온다
			
			// System.out.println("리스트 사이즈 : "+ovo.size());
			
			request.setAttribute("pvo", pvo);
			request.setAttribute("ovo", ovo);
			request.setAttribute("m_id", m_id);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/myshop/prodView.jsp");
			
		}
		else { // post 로 들어온 경우
			
			// 해당 로직에서 즉시 구매하기의 경우가 발생하면 됩니다! 다른 서블릿 받아도 되긴함
			
			
		}
		
		

	}

}
