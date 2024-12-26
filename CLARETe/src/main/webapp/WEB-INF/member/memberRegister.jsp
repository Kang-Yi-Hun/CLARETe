<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /CLARETe
%> 

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/member/memberRegister.css" />

<!-- Optional JavaScript -->
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

<%-- 직접 만든 JS --%>
<script type="text/javascript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/member/memberRegister.js"></script>
    
<div id="container">
      
      <h2>LOGO</h2>
      <form name="registerFrm">
         <h4>회원가입</h4>
         <span id="mandatoryNote" ><span class="star">*</span> 필수입력사항</span>
         <hr class="divider">
         
            <div id="divId" class="formDiv">
               <span>아이디<span class="star">*</span></span>
               <input type="text" name="id" id="id" class="register-input requiredInfo" placeholder="아이디를 입력해주세요" />
               <input type="button" id="idCheck" value="중복확인" onclick="duplicateId()" />
			   <span id="idcheckResult"></span>
               <span class="error">5자 이상 16자 이하의 영문을 입력해주세요.</span>
            </div>
            
            
            <div id="divPwd" class="formDiv">
               <span>비밀번호<span class="star">*</span></span>
               <input type="password" name="pwd" id="pwd" class="register-input requiredInfo" placeholder="비밀번호를 입력해주세요" />
               <span class="error">영문/숫자/특수문자(공백제외)만 허용하며 2가지를 조합하세요.</span>
            </div>
            
            
            <div id="divPwdCheck" class="formDiv">
               <span>비밀번호확인<span class="star">*</span></span>
               <input type="password" id="pwdCheck" class="register-input requiredInfo" placeholder="비밀번호를 한번 더 입력해주세요" />
               <span class="error">비밀번호가 일치하지 않습니다.</span>
            </div>
         
            <div id="divName" class="formDiv">
               <span>이름<span class="star">*</span></span>
               <input type="text" name="name" id="name" class="register-input requiredInfo" placeholder="이름을 입력해주세요" />
               <span class="error">이름은 필수입력 사항입니다.</span>
            </div>
            
            
            <div id="divEmail" class="formDiv">
               <span>이메일<span class="star">*</span></span>
               <input type="text" name="email" id="email" class="register-input requiredInfo" placeholder="예: hongkd@naver.com" />
               <input type="button" id="emailCheck" value="중복확인" onclick="duplicateEmail()" />
               <span id="emailCheckResult"></span>
               <span class="error">이메일 형식에 맞지 않습니다.</span>
            </div>
            
            <div id="divMobile" class="formDiv">
               <span>휴대폰<span class="star">*</span></span>
               <input type="text" name="mobile" id="mobile" class="register-input requiredInfo" placeholder="숫자만 입력해주세요" />
               <span class="error">숫자만 입력해주세요.</span>
            </div>
            
            <div id="divPostcode" class="formDiv">
               <span>우편번호<span class="star">*</span></span>
               <input type="text" name="postcode" id="postcode" class="address-input requiredInfo" placeholder="우편번호" />
               <%-- 우편번호 찾기 --%>
               <input type="button" id="zipcodeSearch" value="우편번호 찾기" onclick="searchPostcode()"/>
               <span class="error">우편번호 형식에 맞지 않습니다.</span>
            </div>
            
            <div id="divAddress" class="formDiv">
               <span>주소<span class="star">*</span></span>
               <input type="text" name="address" id="address" class="register-input requiredInfo" size="40" maxlength="200" placeholder="주소" /><br>      
            </div>
            
            <div id="divAddress" class="formDiv">
            	<span>상세주소<span class="star">*</span></span>
               <input type="text" name="detailaddress" id="detailAddress" class="address-input requiredInfo" size="40" maxlength="200" placeholder="상세주소" />&nbsp;
               <input type="text" name="extraaddress" id="extraAddress" class="address-input requiredInfo" size="40" maxlength="200" placeholder="참고항목" />  
               <span class="error">주소를 입력하세요.</span>
            </div>
            
            <div id="divGender" class="formDiv">
               <span>성별</span>
               <input type="radio" name="gender" value="1" id="male" /><label for="male" style="margin-left: 1.5%;">남자</label>
                    <input type="radio" name="gender" value="2" id="female" style="margin-left: 10%;" /><label for="female" style="margin-left: 1.5%;">여자</label>
            </div>
            
            <div id="divBirthday" class="formDiv">
               <span>생년월일</span>
               <input type="text" name="birthday" id="datepicker" class="register-input" placeholder="YYYY   /   MM   /   DD" />
               <span class="error">생년월일은 마우스로만 클릭하세요.</span>  
            </div>
         
         <hr class="divider">
         
         <!-- 약관 동의 섹션 -->
		<div id="agreement-section">
		    <label>
		        <input type="checkbox" id="agreeAll" />
		        <strong>전체 동의합니다.</strong>
		    </label>
		    <div>
		        <label>
		            <input type="checkbox" name="agreeTerms" class="agree" />
		            이용약관 동의 (<span style="color: gray;">필수</span>)
		            <a id="toggleTerms1">약관 보기</a>
		        </label>
		        <!-- 숨겨진 약관 내용 -->
			    <div id="termsContent1" style="display: none; border: 1px solid #ddd; padding: 10px; margin-top: 10px; margin-botton: 10px;">
			        <p>이용약관 내용이 여기에 표시됩니다.</p>
			    </div>
		        <label>
		            <input type="checkbox" name="agreePrivacy" class="agree" />
		            개인정보 수집·이용 동의 (<span style="color: gray;">필수</span>)
		            <a id="toggleTerms2">약관 보기</a>
		        </label>
		        <!-- 숨겨진 약관 내용 -->
			    <div id="termsContent2" style="display: none; border: 1px solid #ddd; padding: 10px; margin-top: 10px; margin-botton: 10px;">
			        <p>이용약관 내용이 여기에 표시됩니다.</p>
			    </div>
		        <label>
		            <input type="checkbox" name="agreeAge" class="agree" />
		            본인은 만 14세 이상입니다. (<span style="color: gray;">필수</span>)
		        </label>
		    </div>
		</div>


        <div id="submit">
           <input type="button" value="가입하기" onclick="goRegister()" />
        </div>
         
         
      </form>
      
   </div>