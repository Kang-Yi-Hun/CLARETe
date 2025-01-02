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
     const method = "${requestScope.method}";
     
     console.log("~~~확인용 method : "+method);
     /*
        ~~~확인용 method : GET
        ~~~확인용 method : POST
     */
     
     if(method == "GET"){
     }
     
     if(method == "POST"){
        $('input:text[name="m_name"]').val("${requestScope.m_name}");
        $('input:text[name="m_mobile"]').val("${requestScope.m_mobile}");
        
     }

     $("div.find_go2").click(function(){
       
        goCertification();
        
     }); // end of $('div.find_go2').click(function(){})
     
  }); // end of $(document).ready(function(){})--------------- 
  
  // Function Declaration
  

  function goCertification() {
     
     // alert("테스트");
     const certification = $("input:text[name='certification']").val();
     //console.log(value);
     
/*      if(certification == "${sessionScope.certification_code}") {
        alert("휴면해제가 완료되었습니다.");
     }
     else {
        alert("인증번호가 일치하지 않습니다.");
        return;
     } */
     const frm = document.mobileFrm;
     frm.action = "<%= ctxPath%>/member/idleEnd.cl";
     frm.method = "post";
     frm.submit();
  } // end of function goCertification() 
  
</script>

<form name="mobileFrm">
	<div class="Certification_number">
	<div class="find_logo">
               LOGO
           </div>
           <div class="find_title">
               휴면 해제하기
           </div>
           <div class="input_box">
        <br><input type="text" name="certification" placeholder="인증번호를 입력해주세요" />
    </div>
   <div class="find_go2" style="width: 333px; height: 48px; margin: 0 auto; margin-top: 28px;">
     인증번호확인
   </div>
   </div>
</form>







