package admin.controller;

import java.sql.SQLException;
import java.util.List;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.domain.MemberVO;
import minkyu.admin.model.AdminDAO;
import minkyu.admin.model.AdminDAO_imple;
import product.domain.ProductVO;

public class AdminProduct extends AbstractController {
	
	private AdminDAO adao = new AdminDAO_imple();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	     String method = request.getMethod();
	      
	      System.out.println(method);
	      
	      if("GET".equalsIgnoreCase(method)) {
	         
	         String message = "";
	         String loc= "";
	         
	         try {
	            
	            List<ProductVO> productAll_list = adao.ProductAll_member();
	            
	            
	            if(productAll_list.size() != 0) {
//	               System.out.println(productAll_list.size());
	               request.setAttribute("productAll_list", productAll_list);
	               
	               message = "모든 상품 조회입니다.";
	               loc = request.getContextPath() + "admin.cl";
	            }
	            
	         } catch (SQLException e) {
	             e.printStackTrace();
	             message = "조회된 회원이 없습니다.";
	             loc = "javascript:history.back";
	             
	          }
	         request.setAttribute("message", message);
	         request.setAttribute("loc", loc);

	         // super.setRedirect(true);
	         // super.setViewPage("/WEB-INF/msg.jsp");
	         
	         // super.setRedirect(false);
	         super.setViewPage("/WEB-INF/admin/adminProduct.jsp");
	      }

	      
	   }


	}
