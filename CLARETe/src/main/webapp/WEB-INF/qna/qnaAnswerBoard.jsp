<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
 String ctxPath = request.getContextPath();
%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>금쪽이</title>

	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/mapage.css">
	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/media_nav.css">   
    <link rel="stylesheet" href="<%= ctxPath %>/css/mypage/delivery_detail.css">
	<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/member/memberAsk.css" />
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    
    <!-- Optional JavaScript -->
    <script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script>
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css" >
	
	<%-- jQueryUI CSS 및 JS --%>
	<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
	<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 
	
	<!-- Font Awesome 6 Icons -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css">

	<%-- 해더 include 받아옴 --%>
	<%-- <jsp:include page="<%= ctxPath %>/WEB-INF/header.jsp"> --%>
    <jsp:include page="../header.jsp"></jsp:include>
	<%-- 해더 include 받아옴 --%>
	
	<%-- 마이페이지해더 include 받아옴 --%>
    <jsp:include page=".././mypage/mypageheader.jsp"></jsp:include>    
	<%-- 마이페이지해더 include 받아옴 --%>
	<script type="text/javascript" src="<%= ctxPath %>/js/mypage/insertDelivery.js"></script> 
	 <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 


	<%-- =============================================== --%>
<style type="text/css">


</style>
	
		

<script type="text/javascript">

$(document).ready(function(){

	// **** 특정 글을 클릭하면 상세정보를 보여주도록 한다. **** //
	$("td.title").click( e => {
		<%--alert("확인용"); --%>
		 	    
		 const q_num = $(e.target).parent().find(".q_num").text();
		 // 또는
		 //  const userid = $(e.target).parent().children(".q_num").text();
		 <%-- alert(q_num); --%>
		 
		   const frm = document.qnaAnswerOneDetail_frm;
		   frm.q_num.value = q_num;
		   
		   frm.action = "<%= ctxPath%>/qna/qnaAnswerOneDetail.cl";
		   <%-- frm.action = "${pageContext.request.contextPath}/qna/qnaAnswerOneDetail.cl"; --%>
		   frm.method = "post";
		   frm.submit(); 
	});
	
	
	

});// end of $(document).ready(function(){})------------
</script>




<form name="qnaAnswerOneDetail_frm">
	<input type="hidden" name="q_num" />
	<input type="hidden" name="goBackURL"  value="${requestScope.currentURL}"/>
</form>
	
	
	<%-- ****** 문의답변하기 Modal 시작 ****** --%>
<%-- 
<div class="modal fade" id="askAnswerUpload" data-backdrop="static">
<div class="modal-dialog">
      <div class="modal-content" style="width: 600px !important;">
      
        <!-- Modal header -->
        <div class="modal-header">
          <h4 class="modal-title">1:1문의등록</h4>
          <button type="button" class="close askUploadClose" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div id="askUpload">
             <iframe id="iframe_askUpload" src="<%= ctxPath%>/qna/qnaAnswerOneDetail.cl"> 
             </iframe>
          </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger askUploadClose" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
--%>

<%-- ****** 문의답변하기 Modal 끝 ****** --%>

	
	
	
	
	
	
	
	
<div id="container">


	<div id="qna_table">
	<table>
	    <tr>
	      <th class="num">번호</th>
	      <th class="title">문의제목</th>
	      <th class="ask">문의내용</th>
	      <th class="fk_m_id">회원아이디</th>
	      <th class="registerday">등록일자</th>
	      <th class="category">문의유형</th>
	      <th class="q_num">Q&A넘버</th>
	      
	    </tr>
	   
	    <c:if test="${not empty requestScope.qnaList}">
			<c:forEach var="qvo" items="${requestScope.qnaList}" varStatus="status">
			 <tr>
				  <td class="num">${status.count}</td>
			      <%-- <td class="title" id="askModal"><a style="cursor: pointer;" data-toggle="modal" data-target="#askAnswerUpload" data-dismiss="modal">${qvo.q_title}</a></td> --%>
			     <%--  <td class="ask"><a href="<%= ctxPath%>/qna/qnaAnswerOneDetail.cl">${qvo.q_title}</a></td> --%>
			     
			      <td class="title">${qvo.q_title}</td>
			      <td class="ask">${qvo.q_ask}</td>
			      <td class="fk_m_id">${qvo.fk_m_id}</td>
			      <td class="registerday">${qvo.q_register}</td>
			      <td class="category">${qvo.q_category}</td>
			      <td class="q_num">${qvo.q_num}</td>
			</tr>
			 </c:forEach>            
			</c:if>
	         
	</table>
	
	</div>



</div>

	<%-- =============================================== --%>
	
	<%-- 마이페이지푸터 include 받아옴 --%>
	<jsp:include page=".././mypage/mypagefooter.jsp"></jsp:include>
	<%-- 마이페이지푸터 include 받아옴 --%> 
	
	<%-- 푸터 include 받아옴 --%>
	<jsp:include page="../footer.jsp"></jsp:include>
	<%-- 푸터 include 받아옴 --%>
	    
    

