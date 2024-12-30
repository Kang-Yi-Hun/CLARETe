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
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/qna/correctDeleteAsk.css" />

<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

<%-- 직접 만든 JS --%>    
<script type="text/javascript" src="<%= ctxPath%>/js/qna/qna.js"></script>



<div id="container">
	    
	        <form name="faqFrm">        
	               <table style="padding-top:50px" align=center border=0 cellpadding=2>
	                    <%--<tr>
	                        <td>
	                            <p><b>1:1 문의등록</b></p>
	                        </td>
	                    </tr>	                    	                  
	                    <tr> --%>
	                        <td>
                
	                            <table class="table2">    
	                            
	                          <tr>
	                         	<td>문의유형</td>
	                         	 <td>
			                            <select>
			                            	<option id="1">결제/교환/환불</option>
			                            	<option id="2">상품문의</option>
			                            	<option id="3">매장문의</option>
			                            	<option id="4">배송문의</option>
			                            	<!-- <option id="5">기타</option> -->
			                            </select>
		                            </td>
	                          </tr>
	                          
	                               <c:if test="${not empty requestScope.qnaList}">
	                                <tr>
	                                    <td>문의 제목</td>
	                                    <td><input type="text" name="q_title" id="q_title" placeholder="질문을 입력하세요" maxlength=20 ${qvo.q_ask} /></td>
	                                </tr>
	                                <tr>
	                                    <td>문의 내용</td>
	                                    <td><textarea name="q_ask" id="q_ask" placeholder="내용을 입력하세요">${qvo.q_ask}</textarea></td>
	                                	
	                                </tr>
	                                </c:if>
	                                <tr>
	                                	<td>이미지 파일 첨부</td>
	                                </tr>
	                                <tr>
	                                	<td> 답변 수신 여부 (선택)	</td>	                                   
				                            <td>  
					                           <ul>
					                           <li>     			                           				                         		
					                         		<input id="SMS" class="form-check-input" type="checkbox" value=""/>
					                         		<label for="SMS">SMS</label>
					                         		<input type="text" name="hp1" id="hp1" size="6 maxlength="3" value="010" readonly/>	&nbsp;-&nbsp;
					                         		<input type="text" name="hp2" id="hp2" size="6 maxlength="4" />	&nbsp;-&nbsp;
					                         		<input type="text" name="hp3" id="hp3" size="6 maxlength="4" />	
					                         					                       
					                           </li>
					                           
					                           <li>       			                           			                         		
					                         		<input id="E-mail" class="form-check-input" type="checkbox" value=""/>
					                         		<label for="E-mail">E-mail</label>
					                         		<input type="text" name="email" id="email1" size="6 maxlength="4"/>
					                         		&nbsp;@&nbsp;
					                         		<input type="text" name="email" id="email2" />
					                         		 <select>
					                         		    <option>직접입력</option>	
						                            	<option name="email2" value="gmail">gmail.com</option>
						                            	<option name="email2" value="naver">naver.com</option>
						                            	<option name="email2" value="daum">daum.net</option>					                            						                            	
						                            </select>
					                           </li>
			                                   </ul>
		                                   </td>                           	                                   
	                                </tr>
	                                
	                            </table>
	                             
	                            <div id=btn>                     
		                            <span><input type="button" id="btnSubmit" value="수정" class="btn btn-primary " onclick="correctDeleteAsk()" /></span>
	                 				<span><input type="reset"  class="btn" value="삭제"/></span>
                 				</div>   
								<input type="text" style="display:none;" />
								
						       
					          
	                        </td>
	                    </tr>
	                </table>         
	        </form>
	    </div>

