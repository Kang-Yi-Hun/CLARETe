package faq.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.controller.AbstractController;
import faq.domain.FaqVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import youjin.faq.model.FaqDAO;
import youjin.faq.model.FaqDAO_imple;

public class Faq_board extends AbstractController {
	
	private FaqDAO fdao = new FaqDAO_imple();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod(); // "GET" 또는 "POST"
		System.out.println(method);
		
		
		if("GET".equalsIgnoreCase(method)) {			
			// GET 방식이면 페이지 이동 안되게
					
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/faq/faq_board.jsp");
		
		} 
		
		/*
		String searchType = request.getParameter("searchType"); 
		String searchWord = request.getParameter("searchWord"); 
		
		String sizePerPage = request.getParameter("sizePerPage");
		String currentShowPageNo = request.getParameter("currentShowPageNo");
		int totalPage = 1;
		

		if(searchType == null || 
		  (!"name".equals(searchType)&&
		   !"userid".equals(searchType))&&
		   !"email".equals(searchType)) {
		   searchType = "";
		}
		if(searchWord == null) {
			searchWord = "";
		}
		
		if(sizePerPage == null ||
		   !"10".equals(sizePerPage)&&
		   !"5".equals(sizePerPage)&&
		   !"3".equals(sizePerPage)	) {
			sizePerPage = "10";	
		}
		if(currentShowPageNo == null) {
			currentShowPageNo = "1";
		}
		

		
		  Map<String, String> paraMap = new HashMap<>(); 
		  paraMap.put("searchType", searchType);
		  paraMap.put("searchWord", searchWord);
		  paraMap.put("currentShowPageNo", currentShowPageNo);
		  paraMap.put("sizePerPage", sizePerPage); // 한페이지당 보여줄 행의 개수
		
		  
		  
		
		try {
			if(Integer.parseInt(currentShowPageNo) > totalPage ||
			   Integer.parseInt(currentShowPageNo) <= 0) {
			   currentShowPageNo = "1";
			   paraMap.put("currentShowPageNo", currentShowPageNo);
			};
			
		}catch(NumberFormatException e) {
			currentShowPageNo = "1";
			paraMap.put("currentShowPageNo", currentShowPageNo);
		}
		
		
		
		
		
		  
		
		// *** ===== 페이지바 만들기 시작 ===== *** //
			
			
		    String pageBar = "";
			
			int blockSize = 5;
	        // blockSize 는 블럭(토막)당 보여지는 페이지 번호의 개수이다.
			
			int loop = 1;
	        // loop 는 1 부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다.
			
			// ==== !!! 다음은 pageNo 구하는 공식이다. !!! ==== // 
	        int pageNo  = ( (Integer.parseInt(currentShowPageNo) - 1)/blockSize ) * blockSize + 1; 
	        // pageNo 는 페이지바에서 보여지는 첫번째 번호이다.  
		  

	        // *** [맨처음][이전] 만들기 *** //
	           pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType=&"+searchType+"searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo=1&'>[맨처음]</a></li>";
	           
	           if (pageNo > blockSize) {
	              pageBar += "<li class='page-item '><a class='page-link' href='memberList.up?searchType=&"+searchType+"searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+(pageNo-1)+"&'>[이전]</a></li>";
	           }
	        
	        
	        while(!(loop > blockSize || pageNo > totalPage)) { // 루프가 10보다 적게
	        	
	        	if(pageNo == Integer.parseInt(currentShowPageNo)) {
	        	pageBar += "<li class='page-item active '><a class='page-link' href='#'>"+pageNo+"</a></li>";
	        	}
	        	else {
	        	pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
	        	}
	        	
	        	loop++;   // 1 2 3 4 5 6 7 8 9 10
	        	
	        	pageNo++; // 1  2  3  4  5  6  7  8  9  10 
	        	          // 11 12 13 14 15 16 17 18 19 20
	        	          // 21 22 23 24 25 26 27 28 29 30
	        			  // 31 32 33 34 35 36 37 38 39 40
	        	          // 41 42  < 42페이지가 마지막이라 끊어줘야함 // pageNo > totalPage 참이되니까 42까지만 찍고 빠져나옴
	        	
	        }// end of wile()-------------------------------
	        
	        
	        
	        // *** [다음][마지막] 만들기 *** //
	        // pageNo ==> 11
	        
	        if(pageNo <= totalPage) {
	        pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>";
	        }
	        
	        pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>";
        	
	        
	        // *** ===== 페이지바 만들기 끝 ===== *** //
	        
	      */  
	
		
		
		try {
			List<FaqVO> faqList = fdao.faqList();
			request.setAttribute("faqList", faqList);
		/*	
			System.out.println(faqList.size());
			
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchWord", searchWord);			
			request.setAttribute("sizePerPage", sizePerPage);			
			request.setAttribute("pageBar", pageBar);
			
			// >>> 뷰단(memberList.jsp)에서 "페이징 처리시 보여주는 순번 공식" 에서 사용하기 위해 
            // 검색이 있는 또는 검색이 없는 회원의 총개수 알아오기 시작 <<< 
			//int totalMemberCount = mdao.getTotalMemberCount(paraMap);

			//request.setAttribute("totalMemberCount", totalMemberCount); //조회하고자하는 행이 몇개인지
			request.setAttribute("currentShowPageNo", currentShowPageNo);
			
		*/	
			
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/faq/faq_board.jsp");
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			super.setRedirect(true);
			super.setViewPage(request.getContextPath()+"index.cl"); ///error.up 은 없어서 일단 메인
		}
		
		
	}

}
