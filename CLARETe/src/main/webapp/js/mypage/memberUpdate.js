let b_idcheck_click = false;
let b_emailcheck_click = false;

$(document).ready(function() {
	
	$("span.error").hide();
	
	// "이름" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#m_name").blur((e) => {

		const name = $(e.target).val().trim();
		if (name == "") {
			// 이름이 공백 or 미입력 인 경우
			$("form[name='registerFrm'] :input").prop("disabled", true);
			$(e.target).prop("disabled", false);
			$(e.target).val("").focus();
			$(e.target).parent().find("span.error").show();
		} else {
			// 정상적으로 입력한 경우
			$("form[name='registerFrm'] :input").prop("disabled", false);
			$(e.target).parent().find("span.error").hide();
		}
	});	// end of $("input#m_name").blur((e) => {})
	
	// "비밀번호" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#m_pwd").blur((e) => {
		
		const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g);
		const bool = regExp_pwd.test($(e.target).val());
		
		if (!bool) {
			// 비밀번호가 정규표현식에 위배된 경우 
			$("form[name='registerFrm'] :input").prop("disabled", true);
			$(e.target).prop("disabled", false);
			$(e.target).val("").focus();
			$(e.target).parent().find("span.error").show();
		} else {
			// 정상적으로 입력한 경우
			$("form[name='registerFrm'] :input").prop("disabled", false);
			$(e.target).parent().find("span.error").hide();
		}

	});	// end of $("input#m_pwd").blur((e) => {})
	
	
	// "비밀번호확인" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#m_pwdCheck").blur((e) => {
		
		if( $("input#m_pwd").val() != $(e.target).val() ) {
			// 비밀번호 != 비밀번호확인 
			$("form[name='registerFrm'] :input").prop("disabled", true);
			$("input#m_pwd").prop("disabled", false);
			$(e.target).prop("disabled", false);
			$("input#m_pwd").val("").focus();
			$(e.target).val("");
			$(e.target).parent().find("span.error").show();
		} else {
			// 비밀번호 == 비밀번호확인 
			$("form[name='registerFrm'] :input").prop("disabled", false);
			$(e.target).parent().find("span.error").hide();
		}	
		
	});	// end of $("input#m_pwdCheck").blur((e) => {})
	
   
	
	
	
	// "휴대폰" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#m_mobile").blur((e) => {
		
		const regExp_mobile = new RegExp(/^010\d{8}$/i);
		const bool = regExp_mobile.test($(e.target).val());
		
		if (!bool) {
			// 휴대폰이 정규표현식에 위배된 경우 
			$("form[name='registerFrm'] :input").prop("disabled", true);
			$(e.target).prop("disabled", false);
			$(e.target).val("").focus();
			$(e.target).parent().find("span.error").show();
		} else {
			// 정상적으로 입력한 경우
			$("form[name='registerFrm'] :input").prop("disabled", false);
			$(e.target).parent().find("span.error").hide();
		}

	});	// end of $("input#m_mobile").blur((e) => {})
	

	// "우편번호" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#m_postcode").blur((e) => {
		
		const regExp_postcode = /^\d{5}$/;  
		const bool = regExp_postcode.test($(e.target).val());
		
		if (!bool) {
			// 우편번호가 정규표현식에 위배된 경우 
			$("input[type='button'][value='우편번호 검색']").prop("disabled", false); // 검색 버튼 활성화
			$("form[name='registerFrm'] :input").prop("disabled", true);
			$(e.target).prop("disabled", false);			
			$(e.target).val("").focus();
			$(e.target).parent().find("span.error").show();
		} else {
			// 정상적으로 입력한 경우
			$("form[name='registerFrm'] :input").prop("disabled", false);
			$(e.target).parent().find("span.error").hide();
		}

	});	// end of $("input#postcode").blur((e) => {})
	
	
	// 우편번호, 주소, 참고항목을 읽기전용으로
    $("input#m_postcode").attr("readonly", true);
    $("input#m_address").attr("readonly", true);
    $("input#m_extra").attr("readonly", true);
	
	$("input#datepicker").datepicker({
		dateFormat: 'yy-mm-dd'  
		,showOtherMonths: true  
		,showMonthAfterYear:true 
		,changeYear: true        
		,changeMonth: true                     
		,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12']
		,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] 
		,dayNamesMin: ['일','월','화','수','목','금','토'] 
		,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']       
	});
	
	$("input#datepicker").bind("keyup", e=>{
		$(e.target).val("").next().show();
	}); // 생년월일에 키보드로 입력하는 경우

	$("input#datepicker").bind("change", e=>{
	    if($(e.target).val() != "") {
	        $(e.target).next().hide();
	    }
	}); // 생년월일에 마우스로 값을 변경하는 경우
	
		
});


// === "우편번호 찾기" 버튼 클릭 이벤트 === //
function searchPostcode() {
      
   new daum.Postcode({
      oncomplete: function(data) {
         // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
    
            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let addr = ''; // 주소 변수
            let extraAddr = ''; // 참고항목 변수
    
            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
    
            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("m_extra").value = extraAddr;
            
            } else {
                document.getElementById("m_extra").value = '';
            }
    
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('m_postcode').value = data.zonecode;
            document.getElementById("m_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("m_detail_address").focus();
            
        }
    }).open();            
} // end of function searchPostcode() {}



// === "변경하기" 버튼 클릭 이벤트 === //
function goUpdate() {

	
   	const frm = document.registerFrm; 
   	console.log(frm);
   	frm.action = "mypage_memberUpdateEnd.cl";
   	frm.method = "post";
   	frm.submit();
   
} // end of function goRegister() {}