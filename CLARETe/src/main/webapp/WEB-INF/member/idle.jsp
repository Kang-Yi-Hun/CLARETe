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
        $("div.find_go2").hide();
        $("div.Certification_number").hide();
     }
     
     if(method == "POST"){
        $("div.find_go").hide();
//        $('input:text[name="m_name"]').val("${requestScope.m_name}");
//        $('input:text[name="m_mobile"]').val("${requestScope.m_mobile}");
        $("input:text[name='m_name']").hide();
        $("input:text[name='m_mobile']").hide();
        $("div.Certification_number").show();
        
     }
     
     
     $('div.find_go').click(function(){
        goFind();
 
        dataObj = {"mobile":m_mobile,
			  	 "certification_code":"${sessionScope.certification_code}"};
  	  
	   $.ajax({
	 	  url:"${pageContext.request.contextPath}/member/smsSend.cl",
	 	  type:"get",
	 	  data:dataObj,
	 	  dataType: "json",
	 	  success:function(json) { 
	           
	           if(json.success_count == 1) {
	              alert("인증번호가 전송되었습니다.!!");
	           }
	           else if(json.error_count != 0) {
	         	  alert("인증번호 발송 실패!!");
	           }
	      },
         error: function(request, status, error) {
          	alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
	      }
	   });
     console.log("${sessionScope.certification_code}");
     
     }); // end of $('div.find_go').click(function(){})
     
     $("div.find_go2").click(function(){
    	
    	 goCertification();
    	 
     }); // end of $('div.find_go2').click(function(){})
     
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
      $("div.find_go").hide();
      $("div.find_go2").show();
      
  } // end of function goFind() {})---------------- 
  
  function goCertification() {
	  
	  // alert("테스트");
	  const value = $("input:text[name='certification']").val();
	  // console.log(value);
	  
	  if(value == "${sessionScope.certification_code}") {
		  alert("인증번호 일치");
	  }
	  const frm = document.mobileFrm;
      frm.action = "<%= ctxPath%>/member/idle.cl";
      frm.method = "post";
      frm.submit();
      $("div.find_go").hide();
      $("div.find_go2").show();
  } // end of function goCertification() 
  
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
                   확인
               </div>
               
           </div>
       </div>
</form>

<form name="mobileFrm">
	<div class="find_go2" style="width: 333px; height: 48px; margin: 0 auto; margin-top: 28px;">
	  인증번호확인
	</div>
</form>







