<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<%
	String ctxPath = request.getContextPath();
    //    /MyMVC
%>

 
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css" > 


<!-- Font Awesome 6 Icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css">

<!-- 직접 만든 CSS -->
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/qna/qna.css" />

<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 




<script type="text/javascript">

function QnaAnswerUpload(){

	const frm = document.qnaAnswerFrm;
	frm.action = "/CLARETe/qna/qnaAnswerOneDetail.cl";
	frm.method = "post";
	frm.submit();
	
}</script>

<div id="container">
						
	   					<table class="table1">      
	                          <tr>
                                 <td>등록일자</td>
                                 <td>${qvo.q_register}</td>                               	
                              </tr>
                              <tr>
                                 <td>문의유형</td>
                                 <td>${qvo.q_category}</td>                               	
                              </tr>
	                          <tr>
	                         	 <td>작성자</td>
	                         	 <td>${qvo.fk_m_id}</td>
	                          </tr>                                                        
                              <tr>
                                  <td>제목</td>
                                  <td>${qvo.q_title}</td>                               	
                              </tr>
	                          <tr>
                                  <td>내용</td>
                                  <td>${qvo.q_ask}</td>                               	
                              </tr>     
                              
                                	                         
	   					 </table>
	      		
	        <form name="qnaAnswerFrm">        
	                         <table class="table2">    
                                
	                          <tr>
                                  <td>답변</td>
                                  <td><textarea name="q_answer" id="q_answer" placeholder="내용을 입력하세요"></textarea></td>                               	
                              </tr>   
	                          </table> 
	                             
	                            <div id=btn>                     
		                            <span><input type="button" id="btnSubmit" value="등록" class="btn btn-primary " onclick="QnaAnswerUpload()" /></span>
	                 				<span><input type="reset"  class="btn" value="취소"/></span>
                 				</div>   
								<input type="text" style="display:none;" />
								<input type="text" name="q_num" value="${qvo.q_num}"/>
									                        
	       </form>
	    </div>

