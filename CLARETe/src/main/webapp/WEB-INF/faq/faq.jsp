<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%
	String ctxPath = request.getContextPath();
    //    /MyMVC
%>




<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css" > 

<!-- Font Awesome 6 Icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css">



<!-- 직접 만든 CSS -->
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/faq/faq.css" />

<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

<%-- 직접 만든 JS --%>    
<script type="text/javascript" src="<%= ctxPath%>/js/faq/faq.js"></script>


<div id="container">
	
	    
	    
	        <form name="faqFrm">        
	               <table style="padding-top:50px" align=center width=auto border=0 cellpadding=2>
	                    <tr>
	                        <td>
	                            <p><b>FAQ 등록</b></p>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td>
	                            <table class="table2">                            
	                                <tr>
	                                    <td>질문</td>
	                                    <td><input type="text" name="ask" id="title" placeholder="질문을 입력하세요" maxlength=20 /></td>
	                                </tr>
	                                <tr>
	                                    <td>답변</td>
	                                    <td><textarea name="answer" id="content" placeholder="내용을 입력하세요"></textarea></td>
	                                </tr>
	                            </table>           
	                            
	                            <div id=btn>                 
	                            <span><input type="button" value="등록" class="btn btn-primary btn-round" onclick="FAQupload()" /></span>	                      
	                            <span><input type="reset" class="btn " value="취소"/></span>
	                            </div>
	                            
	                        </td>
	                    </tr>
	                </table>         
	        </form>
	    </div>

