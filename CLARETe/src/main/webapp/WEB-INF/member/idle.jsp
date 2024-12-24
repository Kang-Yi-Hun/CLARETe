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
        $("input:text[name='certification']").hide();
        
     }
     
     if(method == "POST"){
        $("div.find_go > span.check").html("");
        $('input:text[name="m_name"]').val("${requestScope.m_name}");
        $('input:text[name="m_mobile"]').val("${requestScope.m_mobile}");
        $("input:text[name='m_name']").hide();
        $("input:text[name='m_mobile']").hide();
        
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
      const m_mobile = $("input:text[name='m_mobile']").val();
      
      const regExp_m_mobile = new RegExp(/^010\d{8}$/i);  
      // 이메일 정규표현식 객체 생성 
         
      const bool = regExp_m_mobile.test(m_mobile);
 
      if(!bool) {
         // 전화번호가 정규표현식에 위배된 경우
         alert("전화번호를 올바르게 입력하세요!!");
       return; // 종료
      }    
      
      const frm = document.idFindFrm;
      frm.action = "<%= ctxPath%>/member/idle.cl";
      frm.method = "post";
      frm.submit();
      $("div.find_go > span.check").html("");
      $("input:text[name='certification']").show();
  } // end of function goFind() {})---------------- 
  
</script>

<form name="idFindFrm">
   <div class="find_check_container">
           <div class="find_logo">
               LOGO
           </div>
           <div class="find_title">
               휴면 해제하기
           </div>
           <div class="input_box">
   
               <div class="input_container">
                   <input type="text" name="m_name" placeholder="이름을 입력해주세요" />
                   <input type="text" name="m_mobile" placeholder="전화번호를 입력해주세요" />
               </div>
   
               <div class="Certification_number">
                	<br><input type="text" name="certification" placeholder="인증번호를 입력해주세요" />
               </div>
   
               <div class="find_go">
                   <span class="check"></span><a style="color: white;" class="close" href="<%= ctxPath%>/login/loginView.cl">인증번호확인</a>
               </div>
           </div>
       </div>
</form>







