<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
   
<%
	String ctxPath = request.getContextPath();
%>

<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css" > 

<!-- Font Awesome 6 Icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css">

<!-- 직접 만든 CSS -->
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/qna/qna_board.css" />

<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

<%-- 직접 만든 JS --%>    
<script type="text/javascript" src="<%= ctxPath%>/js/faq/faq_qna_board.js"></script>


<div id="container">
	 <div id="title">BOARD</div>
	 <div id="menu">
		<span><a href="/CLARETe/faq/faq_board.cl" id="a_faq">FAQ</a></span>
		<!-- &nbsp;&nbsp; -->
		<span id="span2"><a href="/CLARETe/qna/qna_board.cl" id="a_qna">1:1문의</a></span>
	 </div>
	
	

	
     <div class="container-fluid">
     <div id="searchbar">
     <input id="search" type="search" placeholder="키워드를 입력해주세요" /><input id="searchbtn" type="button" value="검색"/>
     </div>
      <div class="panel-group" id="accParent" role="tablist">
        <hr id="first">
        <span id="qnaSpan1">문의제목</span>
        <span id="qnaSpan2">문의날짜</span>
        <span id="qnaSpan3">문의유형</span> <%-- 답변상태 X --%>
         <hr id="second">
       
            
       <%-- 시작 --%>    
        <div class="panel-group" id="accParent" role="tablist">
        
		<c:if test="${not empty requestScope.qnaList}">
			<c:forEach var="qvo" items="${requestScope.qnaList}" varStatus="status">
			
				
					 <div class="panel panel-default">
         			 	<div class="panel-heading" role="tab">
           				    <a role="button" data-toggle="collapse" data-parent="#accParent" data-target="#aa${status.count}" aria-constrols="addEx2">
				              
				     		
				      
				      		<%-- <span>${status.count}</span> --%>				    
								<span id="qnaSpan1">${qvo.q_title}</span><span id="qnaSpan2">${qvo.q_register}</span>          		                       
				   	            
				            </a>
				        </div>
      						
					     <div id="aa${status.count}" class="panel-collapse collapse" role="tabpanel">
					      
							  <div class="panel-body">  
							  		<div id="qnaAsk">${qvo.q_ask}</div>           
							  		<hr>				       		
							    	<span id="qnaSpan1">${qvo.q_answer}</span>
							  </div>      		
						 </div>
					 </div>
							  <hr>		 					 
			  </c:forEach>            
		</c:if>
			                  
		</div> <!-- id=accParent --> 
		<%-- 끝 --%>
       
       
       
	
	
	
	
	
</div>	                    

	                    