package myshop.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import yihun.myshop.model.*;

public class AllProduct extends AbstractController {

	private ProductDAO pdao = new ProductDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// === Ajax(JSON)를 사용하여 HIT 상품목록 "스크롤" 방식으로 페이징 처리해서 보여주겠다. === //
		String cname = request.getParameter("cname");
		// System.out.println("카테고리 확인: " + cname); // "1"이 출력됨
		
		int totalHITCount = pdao.totalPspecCount(cname); // HIT 상품의 전체개수를 알아온다.
		
    	//   System.out.println("~~~~ 확인용 totalHITCount : " + totalHITCount);
	    // ~~~~ 확인용 totalHITCount : 36
		
		request.setAttribute("totalHITCount", totalHITCount);
		request.setAttribute("cname", cname);
		
	      
	    super.setRedirect(false);
		super.setViewPage("/WEB-INF/myshop/AllProduct.jsp");

	}

}
