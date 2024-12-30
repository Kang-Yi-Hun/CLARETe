package myshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import yihun.myshop.model.*;
import product.domain.*;
public class MallDisplayJSON extends AbstractController {

	private ProductDAO pdao = new ProductDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		
		String selectVal = request.getParameter("selectVal"); // 정렬값
		String cname = request.getParameter("cname"); // 카테고리
	    String start = request.getParameter("start");
	    String len = request.getParameter("len");
	    // ajax로 넘긴 값들을 받음
		
	    Map<String, String> paraMap = new HashMap<>();
	    paraMap.put("selectVal", selectVal);
	    paraMap.put("cname", cname);  //        "1"  "2"  "3"  "4"
	    paraMap.put("start", start);  // start  "1"    "7"    "14"
	    // ajax를 통해 받은 값들을 imple로 조회하기 위해 map에 담음
	    
	    String end = String.valueOf(Integer.parseInt(start) + Integer.parseInt(len) - 1);
	    paraMap.put("end", end); // end => start + len -1							  
	    						 // end    "8"    "16"   "24"    "32"    "40"
	    
	    // ajax로 받아온 값을 조회하는 메소드
	    List<ProductVO> productList = pdao.selectProduct(paraMap);
	    
	    
	    JSONArray jsonArr = new JSONArray(); // []
	    
	    if(productList.size() > 0) {
	    	// DB에서 조회해온 결과물이 있을 경우
	    	
	    	for(ProductVO pvo : productList) {
	    		
	    		JSONObject jsonObj = new JSONObject(); // {}
	    		
	    		jsonObj.put("p_num", pvo.getP_num());			// p_num
	    		jsonObj.put("p_name", pvo.getP_name()); 		// p_name
	    		// jsonObj.put("p_season", pvo.getP_season());		// p_season
	    		jsonObj.put("p_image", pvo.getP_image());		// p_image
	            // jsonObj.put("p_sale", pvo.getP_sale());			// p_sale
	            jsonObj.put("p_price", pvo.getP_price());		// p_price
	            // jsonObj.put("p_register", pvo.getP_register());	// p_register
	            
	            // jsonObj.put("DiscountPercent", isRedirect()); 
	            
	            jsonArr.put(jsonObj); 
	            
	    	} // end of for-----------
            
	    } // end of if(productList.size() > 0)---------
	    
	    String json = jsonArr.toString(); // 문자열 변환
        
        
        request.setAttribute("json", json);
        
        super.setRedirect(false);
        super.setViewPage("/WEB-INF/jsonview.jsp");
	    
	}

}
