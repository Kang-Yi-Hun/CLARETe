<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	  $("div.find_go > span.check").html("확인");
	  const method = "${requestScope.method}";
	  
	  console.log("~~~확인용 method : "+method);
	  /*
	  	~~~확인용 method : GET
	  	~~~확인용 method : POST
	  */
	  
	  if(method == "GET"){
		  $('div.find_result_contants').hide();
		  $("a.close").hide();
		  $("div.find_go > span.close").hide();
	  }
	  
	  if(method == "POST"){
		  $("div.find_go > span.check").html("");
		  $('input:text[name="m_name"]').val("${requestScope.m_name}");
		  $('input:text[name="m_email"]').val("${requestScope.m_email}");
		  
	  }
	  
	  
	  $('div.find_go').click(function(){
		  goFind();
		  
		  
	  }); // end of $('button.btn-success').click(function(){})----------- 
	  
	  $('input:text[name="m_email"]').on('keyup', function(e){
		  if(e.keyCode == 13){
			  goFind();  
		  }
	  }); // end of $('input:text[name="m_email"]').on('keyup', function(e){})----------
	  
	  
  }); // end of $(document).ready(function(){})--------------- 
  
  // Function Declaration
  
  function goFind() {
	  const m_name = $("input:text[name='m_name']").val().trim();
	     
      if(m_name == "") {
         alert("성명을 입력하세요!!");
         return; // 종료
      }
      const m_email = $("input:text[name='m_email']").val();
      
      // const regExp_m_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;  
      // 또는
      const regExp_m_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
      // 이메일 정규표현식 객체 생성 
         
      const bool = regExp_m_email.test(m_email);
 
      if(!bool) {
         // 이메일이 정규표현식에 위배된 경우
         alert("e메일을 올바르게 입력하세요!!");
       return; // 종료
      }    
      
      const frm = document.idFindFrm;
      frm.action = "<%= ctxPath%>/member/id_find.cl";
      frm.method = "post";
      frm.submit();
      $("div.find_go > span.check").html("");
      $("div.find_go > a.close").show();
  } // end of function goFind() {})---------------- 
  
</script>

<form name="idFindFrm">
	<div class="find_check_container">
	        <div class="find_logo">
	            LOGO
	        </div>
	        <div class="find_title">
	            아이디 찾기
	        </div>
	        <div class="input_box">
	
	            <div class="input_container">
	                <input type="text" name="m_name" placeholder="이름을 입력해주세요" />
	                <input type="text" name="m_email" placeholder="이메일을 입력해주세요" />
	            </div>
	
	            <div class="find_result_contants">
	                <span >${requestScope.m_name}</span><span> 님의 아이디는 </span><br>
	                <span style="color: red;">${requestScope.m_id}</span>
	            </div>
	
	            <div class="find_btn">
	                <a href="<%= ctxPath%>/member/pwd_find_1.cl">비밀번호 찾기</a>
	            </div>
	            <div class="find_go">
	                <span class="check"></span><a style="color: white;" class="close" href="<%= ctxPath%>/login/loginView.cl">닫기</a>
	            </div>
	        </div>
	    </div>
</form>







