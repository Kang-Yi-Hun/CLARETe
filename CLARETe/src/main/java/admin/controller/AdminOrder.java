package admin.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import minkyu.admin.model.AdminDAO;
import minkyu.admin.model.AdminDAO_imple;
import order.domain.OrderVO;

public class AdminOrder extends AbstractController {

    private AdminDAO adao = new AdminDAO_imple();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
        String method = request.getMethod(); // 요청 메서드 확인
        
        if ("GET".equalsIgnoreCase(method)) {

            String message="";
            String loc="";

            try {
            	List<OrderVO> orderList =  adao.SelectOrderMember();
  //              System.out.println("컨트롤러에서 받은 주문 데이터 수: " + orderList.size());
                if (orderList.size() != 0) {
                    message = "주문 회원 조회 성공.";
                    
                    request.setAttribute("orderList", orderList);
                    
                } else {
                    message = "주문 회원 정보가 없습니다.";
                }
                loc = request.getContextPath() + "/admin/adminOrder.cl";
            } catch (SQLException e) {
                e.printStackTrace();
                message = "주문 회원 조회 중 오류가 발생했습니다.";
                loc = "javascript:history.back();";
            }
            
//            request.setAttribute("message", message);
//            request.setAttribute("loc", loc);
//
//            super.setViewPage("/WEB-INF/msg.jsp");

            super.setViewPage("/WEB-INF/admin/adminOrder.jsp");
        }
    }
}
