package admin.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.controller.AbstractController;
import delivery.domain.DeliveryVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import minkyu.admin.model.AdminDAO;
import minkyu.admin.model.AdminDAO_imple;
import my.util.MyUtil;
import product.domain.ProductVO;

public class AdminDelivery extends AbstractController {
	
	private AdminDAO adao = new AdminDAO_imple();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();

		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String currentShowPageNo = request.getParameter("currentShowPageNo");

		String sizePerPage = "10"; // 페이지에서 보여줄 상품 수
	      
		if (searchType == null ||
		        (!"d_name".equals(searchType) &&
		        !"m_id".equals(searchType))) {
		    searchType = "";
		}

		// "m_id"를 "fk_m_id"로 매핑
		if ("m_id".equals(searchType)) {
		    searchType = "fk_m_id";
		}

        if (searchWord == null) {
            searchWord = "";
        }
        
        if(currentShowPageNo == null) {
            currentShowPageNo = "1";
         }

        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("searchType", searchType);
        paraMap.put("searchWord", searchWord);
        paraMap.put("currentShowPageNo", currentShowPageNo);
        paraMap.put("sizePerPage", sizePerPage); // 한페이지당 보여줄 행의 개수
	         
        
//        System.out.println("searchType: " + paraMap.get("searchType"));
//        System.out.println("searchWord: " + paraMap.get("searchWord"));

        
        // **** 페이징 처리를 한 모든 회원목록 또는 검색되어진 회원목록 보여주기 **** //
        // 페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총페이지수 알아오기 //
        int totalPage = adao.getDeliveryPage(paraMap);
        
        try {
            if(Integer.parseInt(currentShowPageNo) > totalPage || 
              Integer.parseInt(currentShowPageNo)   <= 0 ) {
               currentShowPageNo = "1";
                paraMap.put("currentShowPageNo", currentShowPageNo);
            }
         } catch(NumberFormatException e) {
            currentShowPageNo = "1";
            paraMap.put("currentShowPageNo", currentShowPageNo);
         }
        
        
        String pageBar = "";
        
        int blockSize = 10;   /////////////데이터 많아지면 여기서 바꾸기!!!
         // blockSize 는 블럭(토막)당 보여지는 페이지 번호의 개수이다.
        
        int loop = 1;
         // loop 는 1 부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다.
        
        // ==== !!! 다음은 pageNo 구하는 공식이다. !!! ==== // 
         int pageNo  = ( (Integer.parseInt(currentShowPageNo) - 1)/blockSize ) * blockSize + 1; 
         // pageNo 는 페이지바에서 보여지는 첫번째 번호이다.
         
         
      // *** [맨처음][이전] 만들기 *** //
	        pageBar += "<li class='page-item'><a class='page-link' href='adminDelivery.cl?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo=1'>[맨처음]</a></li>";
	        
	        if(pageNo != 1) {
	            pageBar += "<li class='page-item'><a class='page-link' href='adminDelivery.cl?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
	        }
	        
	         while( !(loop > blockSize || pageNo > totalPage) ) { 
	            
//	        	 System.out.println(totalPage);
	        	 
	            if(pageNo == Integer.parseInt(currentShowPageNo)) {
	                pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";
	                
	            }
	            else {
	                pageBar += "<li class='page-item'><a class='page-link' href='adminDelivery.cl?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
	            }
	            
	            loop++;    // 1 2 3 4 5 6 7 8 9 10 11 12 13 ...
	            
	            pageNo++;   //  1  2  3  4  5  6  7  8  9 10 ... 
	                     // 11 12 13 14 15 16 17 18 19 20 ...
	                     // 21 22 23 24 25 26 27 28 29 30 ...
	                     // 31 32 
	         }// end of while()-----------------------------
	         
	         
	      // *** [다음][마지막] 만들기 *** //
	         // pageNo ==> 11 
	         if(pageNo <= totalPage) {
	            pageBar += "<li class='page-item'><a class='page-link' href='adminDelivery.cl?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>";

	         }
	        pageBar += "<li class='page-item'><a class='page-link' href='adminDelivery.cl?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>";
	        // ==== 페이지바 만들기 끝 ==== // 
	        
	        
	        // *** ====== 현재 페이지를 돌아갈 페이지(goBackURL)로 주소 지정하기 ======= *** //
	        String currentURL = MyUtil.getCurrentURL(request);
	        // 회원조회를 했을시 현재 그 페이지로 그대로 되돌아가길 위한 용도로 쓰임.
	        
	        //   System.out.println("currentURL : => " + currentURL);
	         
	         
	         
	         
        
	        try {
	            List<DeliveryVO> deliveryList = adao.searchDelivery(paraMap); 
	            request.setAttribute("deliveryList", deliveryList);
	            
	            // 기타 데이터 전달
	            request.setAttribute("searchType", searchType);
	            request.setAttribute("searchWord", searchWord);
	            request.setAttribute("sizePerPage", sizePerPage);
	            request.setAttribute("pageBar", pageBar);
	            request.setAttribute("currentURL", currentURL);

	            int totalDeliveryCount = adao.getDeliveryCount(paraMap);
	            request.setAttribute("totalDeliveryCount", totalDeliveryCount);
	            request.setAttribute("currentShowPageNo", currentShowPageNo);

	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/admin/adminDelivery.jsp");

	        } catch (SQLException e) {
	            e.printStackTrace();

	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/admin/error.jsp");
	        }

		   
	    }
	}