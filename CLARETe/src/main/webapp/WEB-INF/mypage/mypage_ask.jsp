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
	<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> -->
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css" >  
	
	<%-- jQueryUI CSS 및 JS 
	<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
	<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>  --%>
	
	<!-- Font Awesome 6 Icons 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css"> -->

	<%-- 해더 include 받아옴 --%>
    <jsp:include page="../header.jsp"></jsp:include>
	<%-- 해더 include 받아옴 --%>
	
	<%-- 마이페이지해더 include 받아옴 --%>
    <jsp:include page="mypageheader.jsp"></jsp:include>    
	<%-- 마이페이지해더 include 받아옴 --%>
	


	<%-- =============================================== --%>

<script type="text/javascript">


var txt = "aaaaaaaaaaaaaaaaaaaaaazzzzzzaaaaaaaaaaaaaaa";//$('td.ask').val($(this).attr('id'));
console.log(txt);

var len = 5;
var lastTxt = "...";


function textLengthOverCut(txt, len, lastTxt) {
	     /*    if (txt.length <= len) { // 기본값
	            return txt;
	        }
	        else {
	            return txt.slice(0,len) + (lastTxt || "");		       
	        } */
	
	 if (len == "" || len == null) { // 기본값
		            len = 20;
		        }
		        if (lastTxt == "" || lastTxt == null) { // 기본값
		            lastTxt = "...";
		        }
		        if (txt.length > len) {
		            txt = txt.substr(0, len) + lastTxt;
		        }
		        return txt;
			
	    }
//alert(txt);




});

/* 
modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal")) {
        modal.style.display = "none"
}
 */


function closeModal() {
    var modal = document.getElementById("myModal");
    modal.style.display = "none";
}

// 자식 창에서 메시지를 받을 이벤트 리스너 추가
window.addEventListener("message", function(event) {
    if (event.data === "closeModal") {
        closeModal();
    }
});



</script>	
	
	    
	
<div id="">

<span id="title">문의목록</span> <span id="askModal"><a style="cursor: pointer;" data-toggle="modal" data-target="#askUpload" data-dismiss="modal">문의작성하기▶</a></span>


<%-- ****** 문의작성하기 Modal 시작 ****** --%>
<div class="modal fade" id="askUpload" data-backdrop="static">
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
             <iframe id="iframe_askUpload" src="<%= ctxPath%>/qna/qna.cl"> 
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

<%-- ****** 문의작성하기 Modal 끝 ****** --%>





<%-- ****** 문의 수정/삭제하기 Modal 시작 ****** --%>
<div class="modal fade" id="correctDeleteAsk" data-backdrop="static">
<div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal header -->
        <div class="modal-header">
          <h4 class="modal-title">1:1문의수정/삭제</h4>
          <button type="button" class="close correctDeleteAsk" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div id="askUpload">
             <iframe id="iframe_correctDeleteAsk" src="<%= ctxPath%>/qna/correctDeleteAsk.cl"> 
             </iframe>
          </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger correctDeleteAsk" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

<%-- ****** 문의 수정/삭제하기 Modal 끝 ****** --%>




	<div id="table">
	<table>
	    <tr>
	      <th class="num">번호</th>
	      <th class="title">문의제목</th>
	      <th class="ask">문의내용</th>
	      <th class="registerday">등록일자</th>
	      <th class="category">문의유형</th>
	      
	    </tr>
	   
	    <c:if test="${not empty requestScope.myQnaList}">
			<c:forEach var="qvo" items="${requestScope.myQnaList}" varStatus="status">
			 <tr>
				  <td class="num">${status.count}</td>
			      <td class="title" id="askModal"><a style="cursor: pointer;" data-toggle="modal" data-target="#correctDeleteAsk" data-dismiss="modal">${qvo.q_title}</a></td>
			      <td class="ask" style="overflow: hidden; text-overflow=ellipsis;">${qvo.q_ask}</td>
			      <td class="registerday">${qvo.q_register}</td>
			      
			      <c:choose>
			      <c:when test="${qvo.q_category == 1}"><td class="category">결제/교환/환불</td></c:when>
			      <c:when test="${qvo.q_category == 2}"><td class="category">상품문의</td></c:when>
			      <c:when test="${qvo.q_category == 3}"><td class="category">매장문의</td></c:when>
			      <c:when test="${qvo.q_category == 4}"><td class="category">배송문의</td></c:when>      
			      </c:choose>
			</tr>
			 </c:forEach>            
			</c:if>
	     
	    
	</table>
	
	</div>



</div>

	<%-- =============================================== --%>
	
	
	
<%-- 마이페이지푸터 include 받아옴 --%>
<jsp:include page="mypagefooter.jsp"></jsp:include>
<%-- 마이페이지푸터 include 받아옴 --%> 

<%-- 푸터 include 받아옴 --%>
<jsp:include page="../footer.jsp"></jsp:include>
<%-- 푸터 include 받아옴 --%>
    

