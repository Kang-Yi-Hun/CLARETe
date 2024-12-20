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

<%-- CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/member/find.css" > 

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<script type="text/javascript">
  $(document).ready(function(){
	  const method = "${requestScope.method}";
	  
	  console.log("~~~확인용 method : "+ method);	
	  
	  if(method == "GET"){
		  $("div.find_go2").hide();
	  }
	  
	  if(method == "POST"){
		  $("div.find_go2").show();
		  $("div.find_go").hide();
		  $('input:text[name="m_id"]').val("${requestScope.m_id}");
		  $('input:text[name="m_email"]').val("${requestScope.m_email}");
		  
		  if(${requestScope.isUserExist == true && requestScope.sendMailSuccess == true}) {
	           $("div.find_go2").click(function(){
	           	 alert("인증번호를 발송했습니다.");
	            });
	        }
		  else {
			 alert("사용자 정보가 없습니다!");
			 $("div.find_go").show();
			 $("div.find_go2").hide();
			 
		  }
	  }
	  
	  
	  $("div.find_go").click(function(){
           goFind(); 
      });// end of $("button.btn-success").click(function(){})-----
     
      $("input:text[name='m_email']").bind("keyup", function(e){
          if(e.keyCode == 13) {
             goFind();
          }
      });// end of $("input:text[name='email']").bind("keyup", function(e){})-------

      
	  
  }); // end of $(document).ready(function(){})--------------- 
  
  
  // Function Declaration
  function goFind() {
    
     const m_id = $("input:text[name='m_id']").val().trim();
     
     if(m_id == "") {
        alert("아이디를 입력하세요!!");
        return; // 종료
     }
     
     const m_email = $("input:text[name='m_email']").val();
     
   // const regExp_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;  
   // 또는
      const regExp_m_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
     // 이메일 정규표현식 객체 생성 
         
      const bool = regExp_m_email.test(m_email);
 
      if(!bool) {
         // 이메일이 정규표현식에 위배된 경우
         alert("e메일을 올바르게 입력하세요!!");
       	return; // 종료
      }    
       
      const frm = document.pwdFindFrm;
      frm.action = "<%= ctxPath%>/member/pwd_find_1.cl";
      frm.method = "post";
      frm.submit();
      $("div.find_go").hide();
      $("div.find_go2").show();
      
      $("div.find_go2").click(function(){
    	 
    	  const frm = document.pwdFindFrm;
          frm.action = "<%= ctxPath%>/member/pwd_find_2.cl";
          frm.method = "post";
          frm.submit();
    	  
      });
      
  }// end of function goFind(){}-----------------------
  
  
</script>

<form name="pwdFindFrm">
    <div class="find_check_container">
        <div class="find_logo">
            LOGO
        </div>
        <div class="find_title">
            비밀번호 찾기
        </div>
        <div class="input_box">

            <div class="input_container">
                <input type="text" name="m_id" placeholder="아이디를 입력해주세요" />
                <input type="text" name="m_email" placeholder="이메일을 입력해주세요" />
            </div>
            
            <div class="find_btn">
                <a href="<%= ctxPath%>/member/id_find.cl">아이디 찾기</a>
            </div>
            <div class="find_go">
                <span class="check">확인</span>
            </div>
            <div class="find_go2">
            	<span style="color: white;" class="close">인증번호 입력하기</span>
            </div>
        </div>
    </div>
</form>

