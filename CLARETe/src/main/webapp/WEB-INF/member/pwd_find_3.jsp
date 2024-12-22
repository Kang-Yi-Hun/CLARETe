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
	
	const pwd  = $("input:password[name='pwd']").val();
    const pwd2 = $("input:password[name='pwd2']").val();
    
    if(pwd != pwd2) {
       alert("암호가 일치하지 않습니다.");
       $("input:password[name='pwd']").val("");
       $("input:password[name='pwd2']").val("");
       return;  // 종료
    }
    else {
       const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g);  
        // 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성 
        
        const bool = regExp_pwd.test(pwd);   
       
        if(!bool) {
          // 암호가 정규표현식에 위배된 경우
          alert("암호는 8글자 이상 15글자 이하에 영문자,숫자,특수기호가 혼합되어야만 합니다.");
          $("input:password[name='pwd']").val("");
          $("input:password[name='pwd2']").val("");
          return; // 종료
        }
        else {
           // 암호가 정규표현식에 맞는 경우
           alert("사용자 ID ${requestScope.m_id}님의 비밀번호가 새로이 변경되었습니다.");
        }
    }
    
    const frm = document.pwdUpdateEndFrm;
    frm.action = "<%= ctxPath%>/login/loginView.cl";
    frm.method = "post";
    frm.submit();
    
} // end of function goFind(){}-----------------------


</script>


<c:if test="${requestScope.method == 'POST'}">
	<form name="pwdUpdateEndFrm">
	    <div class="find_check_container">
	        <div class="find_logo">
	            LOGO
	        </div>
	        <div class="find_title">
	            비밀번호 찾기
	        </div>
	        <div class="input_box">
	
	            <div class="input_container">
	                <input type="password" name="pwd" placeholder="새로운 비밀번호를 입력해주세요" />
	                <input type="password" name="pwd2" placeholder="비밀번호를 한번 더 입력해주세요" />
	            </div>
			
			<input type="hidden" name="m_id" value="${requestScope.m_id}" />
	
	            <div class="find_go">
	            	<span class="check">변경완료</span>
	            </div>
	        </div>
	    </div>
	</form>
</c:if>

         
    <%--      <c:if test="${requestScope.n == 0}">
            SQL구문 오류가 발생되어 비밀번호 변경을 할 수 없습니다.
         </c:if> --%>
