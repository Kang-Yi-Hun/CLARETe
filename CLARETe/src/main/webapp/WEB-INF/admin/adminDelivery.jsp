<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_section.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin/admin_header.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>


<jsp:include page="adminheader.jsp" />



<style type="text/css">

div#pageBar {
	border: solid 0px red;
	width: 80%;
	margin: 3% auto 0 auto;
	display: flex;
}

div#pageBar>nav {
	margin: auto;
}

 button.btn.btn-primary {
	width: 60px; /* 너비를 줄임 */
	height: 30px; /* 높이를 조정 */
	font-size: 12px; /* 텍스트 크기 조정 */
	padding: 3px; /* 내부 여백 조정 */
}

button.btn.btn-secondary {
        width: 60px; /* 너비 조정 */
        height: 30px; /* 높이 조정 */
        font-size: 12px; /* 텍스트 크기 */
        padding: 5px; /* 내부 여백 */
} 

.aclass {
	color: gray;
	text-decoration: none;
}



.top-nav{
margin-bottom: 10px;
}

.nav-title {
	margin-bottom: 15px;
}

.blackbtn {  
	background-color: white;
	color:black;
	border: 2px solid gray;
}

.blackbtn:hover {
	background-color: white;
	color:black;
	border: 3px solid black;
	
}

.search {
	background-color: gray;
	border:black;
}

.search:hover {
	background-color: gray;
	border:black;
}


.home {
	border: black 1px solid;
	color:black;
	text-decoration: none;
}

.first-div {
	border: 1px solid black;
}

.end {
	background-color: black;
}

.end:hover {
	background-color: gray;
}

.adeco {
	text-decoration: none;
}

.modalclose {
	background-color: black;
	color: white;
	border: black 1px solid;
}

.modalclose:hover {
	background-color: white;
	color: black;
	text-decoration: none;
	border: gray 1px solid;
}

.modalupdate {
	background-color: black;
	border: white 1px solid;
	color:white;
	text-decoration: none;
}

.modalupdate:hover {
	background-color: gray;
}


.modalclose {
	background-color: white;
	color:black;
	border: 1px solid black;
}


th {
    text-align: center; /* 텍스트를 가운데로 정렬 */
    vertical-align: middle; /* 수직 가운데 정렬 */
}

table td {
    text-align: center;
    vertical-align: middle;
}


</style>

<body class="allbody" style="background-color: #F1F5F9 !important; width: 100% !important;">

<script type="text/javascript">

$(document).ready(function() {
    
    $("select[name='searchType']").val("${requestScope.searchSelect}");
    $("input:text[name='searchWord']").val("${requestScope.searchWord}");
    
    $("select[name='sizePerPage']").val("${requestScope.sizePerPage}");
    
    $("input:text[name='searchWord']").bind("keydown", function(e){
       
       if(e.keyCode == 13) {
          goSearch();
       }
    });
    
    // **** select 태그에 대한 이벤트는 click 이 아니라 change 이다. **** // 
    $("select[name='sizePerPage']").bind("change", function(){
       const frm = document.order_search_frm;
       // frm.action = "memberList.up"; // form 태그에 action 이 명기되지 않았으면 현재보이는 URL 경로로 submit 되어진다.
       // frm.method="get"; // form 태그에 method 를 명기하지 않으면 "get" 방식이다.
          frm.submit();
    });
});
</script>




<title>주문배송관리</title>

	<header class="side-header" style="padding-top: 30px;">
        <nav class="header-nav" >
            <ul>
                <li>
                    <div>회원관리</div>
                    <ul>
                      <li><a class="aclass" href="<%= request.getContextPath() %>/admin/admin.cl">회원조회</a></li>
                        <li><a class="aclass" href="<%= request.getContextPath() %>/admin/adminMemberStatus.cl">탈퇴회원조회</a></li>
                    </ul>
                </li>
                <li>
                    <div class="nav-title">상품관리</div>
                    <ul>
						<li><a class="aclass" href="<%= request.getContextPath() %>/admin/adminProduct.cl">상품조회</a></li>
						<li><a class="aclass" href="<%= request.getContextPath() %>/admin/adminProductInsertGo.cl">상품등록</li>
                    </ul>
                </li>
                <li style="margin-top:20px;">
                    <div style="color:black; font-weight: bold;" class="nav-title" >주문관리</div>
                    <ul>
                        <li><a class="aclass"
						href="<%=request.getContextPath()%>/admin/adminOrder.cl">주문회원조회</a></li>
                        <li><a class="aclass"
						href="<%=request.getContextPath()%>/admin/adminDelivery.cl">주문배송관리</a></li>
                    </ul>
                </li>
                <li>
                <div>문의관리</div>
                <ul>
                    <li><a class="aclass" href="<%=request.getContextPath()%>/faq/faq.cl">FAQ등록</a></li>
                    <li><a class="aclass" href="<%=request.getContextPath()%>/admin/adminBoard.cl">Q&A답변</a></li>
                </ul>
            </li>
                
            </ul>
        </nav>
    </header>

<nav class="top-nav">
    <div class="nav-logo">
        <div>LOGO</div>
    </div>
    <div class="nav-btn">
        <a class="adeco" href="<%=request.getContextPath()%>/index.cl"><div class="home-btn home" >홈으로</div></a>
        <a class="adeco" href="<%=request.getContextPath()%>/login/logout.cl"><div class="end-btn end ">종료</div></a>
    </div>
</nav>

<section>
	<div style="display: flex; flex-wrap: wrap;">
		<div class="first-div">
			<div style="margin: 30px 0px 0px 30px;">
				<form name="delivery_search_frm">
					<select name="searchType">
						<!-- required 속성이 있으면, 사용자가 값을 선택하지 않을 경우 브라우저가 기본적으로 유효성 검사를 수행 -->
						<option value="">검색대상</option>
						<option value="m_id">회원아이디</option>
						<option value="d_name">받는사람 이름</option>
					</select> <input type="text" name="searchWord" placeholder="검색어 입력" required />
					<button type="submit" class="btn btn-secondary search" onclick="goSearch()">검색</button>
				
   				   <span style="font-size: 12pt; font-weight: bold;">배송상태&nbsp;-&nbsp;</span>
					<select name="status" onchange="this.form.submit()">
					    <option value="" ${empty param.status ? 'selected' : ''}>배송상태 선택</option>
					    <option value="0" ${param.status == '0' ? 'selected' : ''}>배송전</option>
					    <option value="1" ${param.status == '1' ? 'selected' : ''}>배송중</option>
					    <option value="2" ${param.status == '2' ? 'selected' : ''}>배송완료</option>
					</select>
				</form>

				<script>
						function goSearch() {
					        const searchType = document.querySelector("select[name='searchType']").value;
					
					        if (searchType === "") {
					            alert("검색대상을 선택하세요 !!");
					            return; // goSearch() 함수를 종료
					        }

							const frm = document.delivery_search_frm;
							frm.action ="adminDelivery.cl";
							frm.submit();
						}
					</script>
			</div>
		</div>
	</div>
	<%-- first --%>



	<!-- 주문배송 테이블 -->
	<div class="second-div">
		<h4
			style="font-weight: bold; text-align: center; margin-top: 50px; padding: 2% 0;">주문 배송 관리</h4>
			<div class="total-count" style="text-align: left; margin: 10px; font-weight: bold; font-size: 13pt;">
				    * 총 주문배송 수: ${requestScope.totalDeliveryCount}명
			</div>
		<div class="table-container">
			<table style="width: 100% !important;" class="table table-hover table-bordered align-middle table-responsive" >
				<thead class="table-dark align-center">
					<tr>
						<th>번호</th>
						<th>주문번호</th>
						<th>회원아이디</th>
						<th>받는사람 이름</th>
						<th>받는사람 우편번호</th>
						<th>받는사람 주소</th>
						<th>받는사람 상세주소</th>
						<th>받는사람 전화번호</th>
						<th>배송현황</th>
						<th>상세보기</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty deliveryList}">
    					<c:forEach var="delivery" items="${deliveryList}" varStatus="status">
							<c:set var="mobile" value="${d_mobile}" />
							<%-- member.m_mobile 만 선언해서 하이픈 넣을 수 있게함 --%>

							<tr class="orderInfo">

								<fmt:parseNumber var="currentShowPageNo"
									value="${requestScope.currentShowPageNo}" />
								<fmt:parseNumber var="sizePerPage"
									value="${requestScope.sizePerPage}" />
								<%-- fmt:parseNumber 은 문자열을 숫자형식으로 형변환 시키는 것이다. --%>

								<td>${(currentShowPageNo - 1) * sizePerPage + (status.index + 1)}</td>
								<%-- >>> 페이징 처리시 보여주는 순번 공식 <<<
	                           			 데이터개수 - (페이지번호 - 1) * 1페이지당보여줄개수 - 인덱스번호 => 순번 --%>
								<td class="d_num">${delivery.ordervo.o_num}</td>
								<td>${delivery.fk_m_id}</td>
								<td>${delivery.d_name}</td>
								<td>${delivery.d_postcode}</td>
								<td>${delivery.d_address}</td>
								<td>${delivery.d_detail_address}</td>
								<td>
								    ${fn:substring(delivery.d_mobile, 0, 3)}-
								    ${fn:substring(delivery.d_mobile, 3, 7)}-
								    ${fn:substring(delivery.d_mobile, 7, 11)}
								</td>
								<td>
								    <div id="delivery-status" data-status="${delivery.ordervo.status}">
								    <c:choose>
								        <c:when test="${delivery.ordervo.status == 0}">배송전</c:when>	
								        <c:when test="${delivery.ordervo.status == 1}">배송중</c:when>
								        <c:when test="${delivery.ordervo.status == 2}">배송완료</c:when>
								        <c:otherwise>배송취소</c:otherwise>
								    </c:choose>
							        </div>
								</td>

								<td><button 
								    type="button"
								    class="btn btn-primary blackbtn"
								    data-toggle="modal"
								    data-target="#exampleModal_centered"
								    data-num="${delivery.d_num}"
								    data-pname="${delivery.productvo.p_name}"
								    data-odcount="${delivery.orderdetailvo.od_count}"
								    data-id="${delivery.fk_m_id}"
								    data-mmobile="${fn:substring(delivery.membervo.m_mobile, 0, 3)}-${fn:substring(delivery.membervo.m_mobile, 3, 7)}-${fn:substring(delivery.membervo.m_mobile, 7, 11)}"
								    data-dname="${delivery.d_name}"
								    data-postcode="${delivery.d_postcode}"
								    data-address="${delivery.d_address}"
								    data-detailaddress="${delivery.d_detail_address}"
								    data-extra="${delivery.d_extra}"
								    data-mobile="${fn:substring(delivery.d_mobile, 0, 3)}-${fn:substring(delivery.d_mobile, 3, 7)}-${fn:substring(delivery.d_mobile, 7, 11)}"
								    data-status="${delivery.ordervo.status}"
								    data-onum="${delivery.ordervo.o_num}">
								    상세보기
								</button>
								</td>
							</tr>
						</c:forEach>
					</c:if>

					<c:if test="${empty deliveryList}">
						<tr>
							<td colspan="13" style="text-align: center; font-weight: bold;">
								주문 배송 정보가 없습니다.</td>
						</tr>
					</c:if>
				</tbody>
			</table>

			<%-- 페이징 네비게이션 --%>
			<div id="pageBar">
				<nav>
					<ul class="pagination">${requestScope.pageBar}</ul>
				</nav>
			</div>
		</div>
	</div>   <!-- 세컨 -->
</section>



<!-- Modal -->
<div class="modal fade" id="exampleModal_centered" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
        
       		<!-- Modal header -->
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">주문배송조회 상세보기</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            
            <!-- Modal body -->
            <div class="modal-body">
                <p><strong>배송지번호:</strong> <span id="modal-num"></span></p>
                <p><strong>상품명:</strong> <span id="modal-pname"></span></p>
                <p><strong>주문수:</strong> <span id="modal-odcount"></span></p>
				<p><strong>회원아이디:</strong> <span id="modal-id"></span></p>
                <p><strong>회원전화번호:</strong> <span id="modal-mmobile"></span></p>
                <p><strong>받는사람 이름:</strong> <span id="modal-dname"></span></p>
                <p><strong>받는사람 우편번호:</strong> <span id="modal-postcode"></span></p>
                <p><strong>받는사람 주소:</strong> <span id="modal-address"></span></p>
                <p><strong>받는사람 상세주소:</strong> <span id="modal-detailaddress"></span></p>
                <p><strong>받는사람 참고항목:</strong> <span id="modal-extra"></span></p>
                <p><strong>받는사람 전화번호:</strong> <span id="modal-mobile"></span></p>
                <p>
				    <strong>배송현황:</strong> 
				    <select id="modal-status">
				        <option value="0">배송전</option>
				        <option value="1">배송중</option>
				        <option value="2">배송완료</option>
				    </select>
				</p>
				<p><strong>주문번호:</strong> <span id="modal-onum"></span></p>
            </div>
            
            <!-- Modal footer -->
            <div class="modal-footer">
            	<button id="update-status-btn" class="btn btn-primary modalupdate">수정</button>
                <button type="button" class="btn btn-danger modalclose" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">

document.querySelector("select[name='status']").addEventListener("change", function () {
    const selectedStatus = this.value; // 선택한 상태값
    const rows = document.querySelectorAll(".orderInfo"); // 모든 데이터 행

    rows.forEach(row => {
        const rowStatus = row.getAttribute("data-status");
        if (selectedStatus === "" || rowStatus === selectedStatus) {
            row.style.display = ""; // 상태가 맞으면 표시
        } else {
            row.style.display = "none"; // 상태가 맞지 않으면 숨김
        }
    });
});



document.addEventListener("DOMContentLoaded", function () {
    const detailButtons = document.querySelectorAll('.btn[data-toggle="modal"]');
    
    detailButtons.forEach(button => {
    	button.addEventListener("click", function () {
    	    const num = this.getAttribute("data-num");
    	    const pname = this.getAttribute("data-pname");
    	    const odcount = this.getAttribute("data-odcount");
    	    const id = this.getAttribute("data-id");
    	    const mmobile = this.getAttribute("data-mmobile");
    	    const dname = this.getAttribute("data-dname");
    	    const postcode = this.getAttribute("data-postcode");
    	    const address = this.getAttribute("data-address");
    	    const detailaddress = this.getAttribute("data-detailaddress");
    	    const extra = this.getAttribute("data-extra");
    	    const mobile = this.getAttribute("data-mobile");
    	    const status = this.getAttribute("data-status");
    	    const onum = this.getAttribute("data-onum");

    	    // 모달창 데이터 설정
    	    document.getElementById("modal-num").textContent = num;
    	    document.getElementById("modal-pname").textContent = pname;
    	    document.getElementById("modal-odcount").textContent = odcount;
    	    document.getElementById("modal-id").textContent = id;
    	    document.getElementById("modal-mmobile").textContent = mmobile;
    	    document.getElementById("modal-dname").textContent = dname;
    	    document.getElementById("modal-postcode").textContent = postcode;
    	    document.getElementById("modal-address").textContent = address;
    	    document.getElementById("modal-detailaddress").textContent = detailaddress;
    	    document.getElementById("modal-extra").textContent = extra;
    	    document.getElementById("modal-mobile").textContent = mobile;
    	    document.getElementById("modal-onum").textContent = onum;

    	    // 배송 상태는 select의 value로 설정
    	    document.getElementById("modal-status").value = status;
    	    
    	    $('input[name="o_numm"]').val(onum);
    	    
    	});
    });
});



	 $("#update-status-btn").click(function (e) {
//	    const updateStatus = document.getElementById("modal-status").value;  // 배송 상태
//	    const dnum = document.getElementById("modal-dnum").textContent; // 배송 번호
	    
	    const status = $("select#modal-status").val();
//	    alert(status);   // 배송상태를 바꾸는 값 
	    
	    const d_num = $("span#modal-onum").text();
	    	
	    	//$(this).next().val();
	     //alert(d_num);

	     const method = "${requestScope.method}";
	     
	     console.log(method);
	    $.ajax({
	    	url: "<%= request.getContextPath() %>/admin/adminUpdateDeliveryStatus.cl", // 서버 요청 URL
	        type: "post",
	        data: {
	            "d_num": d_num,    // 주문 번호
	            "status": status  // 변경된 상태
	        },
	        dataType:"json",
	        success: function (json) {
	            console.log("~~~~확인용 : " + JSON.stringify(json));
	            if (json.isUpdated == 1) {
	                alert("배송 상태가 성공적으로 변경되었습니다.");
	                // 새로고침
	                location.href = "<%= request.getContextPath() %>/admin/adminDelivery.cl";
	            } else {
	                alert("배송 상태 변경에 실패하였습니다.");
	            }
	        },
	        error: function (request, status, error) {
	            console.error("Ajax 요청 실패:", status, error);
	            alert("서버 요청 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
	        }
	    });
	}); 


</script>
