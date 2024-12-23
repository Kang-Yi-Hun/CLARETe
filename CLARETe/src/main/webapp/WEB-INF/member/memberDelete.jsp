<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String ctxPath = request.getContextPath();
    //    /MyMVC
%>

<%-- Required meta tags --%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%-- css--%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/member/find.css" > 

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<script type="text/javascript">

$(document).ready(function(){
	
	const method = "${requestScope.method}";
	
	
	
	console.log(method);
	
    $("div.find_go").click(function(){
    	goFind();
     });// end of $("button.btn-success").click(function(){})----
    
}); // end of $(document).ready(function(){})


function goFind() {
	
	const m_pwd = '${sessionScope.loginuser.m_pwd}';
	console.log(m_pwd);
	if(m_pwd == $("input:password[name='m_pwd']").val()){
		alert("회원이 정상적으로 탈퇴되었습니다. \n 감사합니다.");
	}
	else {
		alert("비밀번호가 일치하지 않습니다.");
		return;
	}
 		
	const frm = document.memberDelete;
    frm.action = "<%= ctxPath%>/mypage/memberDelete.cl";
    frm.method = "post";
    frm.submit();
} // end of function goFind()

</script>


	<form name="memberDelete">
	    <div class="find_check_container">
	        <div class="find_logo">
	            LOGO
	        </div>
	        <div class="find_title">
	            회원탈퇴하기
	        </div>
	        <div class="input_box">
	
	            <div class="input_container">
	                <input type="password" name="m_pwd" placeholder="탈퇴하실려면 비밀번호를 입력해주세요" />
	            </div>
			
			<input type="hidden" name="m_id" value="${sessionScope.loginuser.m_id}" />

	            <div class="find_go">
	            	<span class="delete">탈퇴하기</span>
	            </div>
	        </div>
	    </div>
	</form>
