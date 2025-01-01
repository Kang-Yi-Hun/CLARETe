let b_idcheck_click = false;
let b_emailcheck_click = false;

$(document).ready(function() {
	
	$("span.error").hide();
	$("input#id").focus();
	
	// "아이디" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#id").blur((e) => {
		
		const regExp_id = /^[a-zA-Z][a-zA-Z0-9]{5,15}$/;
		const bool = regExp_id.test($(e.target).val());

		if (!bool) {
			// 아이디가 정규표현식에 위배된 경우
			$("form[name='registerFrm'] :input").prop("disabled", true);
			$(e.target).prop("disabled", false);
			$(e.target).val("").focus();
			$(e.target).parent().find("span#idCheckResult").hide();
			$(e.target).parent().find("span.error").show();		
		} else {
			// 정상적으로 입력한 경우
			$("form[name='registerFrm'] :input").prop("disabled", false);
			$(e.target).parent().find("span#idCheckResult").show();
			$(e.target).parent().find("span.error").hide();
		}

	});	// end of $("input#id").blur((e) => {})
	
	
	// "비밀번호" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#pwd").blur((e) => {
		
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

	});	// end of $("input#pwd").blur((e) => {})
	
	
	// "비밀번호확인" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#pwdCheck").blur((e) => {
		
		if( $("input#pwd").val() != $(e.target).val() ) {
			// 비밀번호 != 비밀번호확인 
			$("form[name='registerFrm'] :input").prop("disabled", true);
			$("input#pwd").prop("disabled", false);
			$(e.target).prop("disabled", false);
			$("input#pwd").val("").focus();
			$(e.target).val("");
			$(e.target).parent().find("span.error").show();
		} else {
			// 비밀번호 == 비밀번호확인 
			$("form[name='registerFrm'] :input").prop("disabled", false);
			$(e.target).parent().find("span.error").hide();
		}	
		
	});	// end of $("input#pwdCheck").blur((e) => {})
	
   
	// "이름" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#name").blur((e) => {
		
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
	});	// end of $("input#name").blur((e) => {})
	
	
	// "이메일" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#email").blur((e) => {
		
		const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);
		const bool = regExp_email.test($(e.target).val());
		
		if (!bool) {
			// 이메일이 정규표현식에 위배된 경우 
			$("form[name='registerFrm'] :input").prop("disabled", true);
			$(e.target).prop("disabled", false);
			$(e.target).val("").focus();
			$(e.target).parent().find("span.error").show();
		} else {
			// 정상적으로 입력한 경우
			$("form[name='registerFrm'] :input").prop("disabled", false);
			$(e.target).parent().find("span.error").hide();
		}

	});	// end of $("input#email").blur((e) => {})
	
	
	// "휴대폰" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#mobile").blur((e) => {
		
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

	});	// end of $("input#mobile").blur((e) => {})
	

	// "우편번호" input 태그 포커스 벗어나면 blur 이벤트 처리
	$("input#postcode").blur((e) => {
		
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
    $("input#postcode").attr("readonly", true);
    $("input#address").attr("readonly", true);
    $("input#extraAddress").attr("readonly", true);
	
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
	
	// "전체 동의" 체크박스 클릭 이벤트 처리
	$("input#agreeAll").change((e) => {
	    const isChecked = $(e.target).is(":checked");
	    // 전체 동의 상태에 따라 모든 체크박스 상태 변경
	    $("input[name='agreeTerms'], input[name='agreePrivacy'], input[name='agreeAge']").prop("checked", isChecked);
	});

	// 개별 체크박스 클릭 이벤트 처리 (하나라도 해제되면 전체동의 해제)
	$("input[name='agreeTerms'], input[name='agreePrivacy'], input[name='agreeAge']").change(() => {
	    const allChecked = $("input[name='agreeTerms'], input[name='agreePrivacy'], input[name='agreeAge']").length ===
	                       $("input[name='agreeTerms']:checked, input[name='agreePrivacy']:checked, input[name='agreeAge']:checked").length;
	    $("input#agreeAll").prop("checked", allChecked);
	});

	// "약관 보기" 클릭 이벤트
   	$("#toggleTerms1").click((e) => {
       	e.preventDefault(); // 링크 기본 동작 방지
       	$("#termsContent1").toggle(); // 약관 내용 보이기/숨기기
   	});
	
	// "약관 보기" 클릭 이벤트
   	$("#toggleTerms2").click((e) => {
       	e.preventDefault(); // 링크 기본 동작 방지
       	$("#termsContent2").toggle(); // 약관 내용 보이기/숨기기
   	});
		
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
                document.getElementById("extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("extraAddress").value = '';
            }
    
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
            
        }
    }).open();            
} // end of function searchPostcode() {}


// === "아이디중복확인" 버튼 클릭 이벤트 === //
function duplicateId() {
	
	b_idcheck_click = true;

	$.ajax({
		url: "idDuplicateCheck.cl",
		data: {"m_id": $("input#id").val()},  
		type: "post", 
		dataType: "json", 

        success: function(json) {
            if (json.isExists) {
				console.log("사용중");
                // 입력한 userid가 이미 사용중이라면
                $("span#idcheckResult").html($("input#id").val() + "은 이미 사용중 이므로 다른 아이디를 입력하세요").css({"color":"red"});
                $("input#userid").val("");
            } else {
				console.log("사용가능");
                // 입력한 userid가 존재하지 않는 경우라면
                $("span#idcheckResult").html($("input#id").val() + "은 사용가능 합니다.").css({"color":"navy"});
            }
        },
        error: function(request, status, error){
            alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
        }
    });  

} // end of function duplicateId() {}

// 아이디 값이 변경되면 가입하기 버튼을 클릭 시 "아이디중복확인"을 클릭유무를 알아보기 위한 용도 초기화 시키기
$("input#id").bind("change", function(){
	b_idcheck_click = false;
});

// === "이메일중복확인" 버튼 클릭 이벤트 === //
function duplicateEmail() {
	
	b_emailcheck_click = true;
	
	$.ajax({
		url: "emailDuplicateCheck.cl",
        data: {"m_email": $("input#email").val()},    
        type: "post",
        async: true,    
        dataType: "json",
        success: function(json) {
            if (json.isExists) {
                // 입력한 email가 이미 사용중이라면
                $("span#emailCheckResult").html($("input#email").val() + "은 이미 사용중 이므로 다른 이메일을 입력하세요").css({"color":"red"});
                $("input#email").val("");
            } else {
                // 입력한 email가 존재하지 않는 경우라면
                $("span#emailCheckResult").html($("input#email").val() + "은 사용가능 합니다.").css({"color":"navy"});
            }
        },
        error: function(request, status, error) {
            alert("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
        }
	});
	
} // end of function duplicateEmail() {}

// 이메일 값이 변경되면 가입하기 버튼을 클릭 시 "이메일중복확인"을 클릭유무를 알아보기 위한 용도 초기화 시키기
$("input#email").bind("change", function(){
	b_emailcheck_click = false;
});

// === "가입하기" 버튼 클릭 이벤트 === //
function goRegister() {

	// 필수 입력사항에 모두 입력이 되었는지 검사하기 시작 //
	let b_requiredInfo = true;
	
	const requiredInfo_list = document.querySelectorAll("input.requiredInfo");
   	for(let i=0; i<requiredInfo_list.length; i++) {
      	const val = requiredInfo_list[i].value.trim();
      	if(val == "") {
         	alert("*로 표시된 필수입력사항은 모두 입력하셔야 합니다.");
         	b_requiredInfo = false;
        	break;
      	}
   	}
	
	if(!b_requiredInfo){
		return;// goRegister() 함수를 종료한다.
	}
	
	if(!b_idcheck_click){
      	// "아이디 중복확인"을 클릭하지 않은 경우
      	alert("아이디 중복확인을 클릭하셔야 합니다.");
      	return; // goRegister() 함수 종료
   	}
	
	// ** 우편번호 및 주소를 입력했는지 검사하기 시작 ** //
   	let b_addressInfo = true;
	
	const arr_addressInfo = [];
   	arr_addressInfo.push($("input#postcode").val());
   	arr_addressInfo.push($("input#address").val());
   	arr_addressInfo.push($("input#detailAddress").val());
   	arr_addressInfo.push($("input#extraAddress").val());
	   
	for(let i=0; i<arr_addressInfo.length; i++){
  		if(arr_addressInfo[i].trim() == "") {
    		alert("우편번호 및 주소를 입력하셔야 합니다.");
     		b_addressInfo = false;
     		break;
  		}
	}
	
	if(!b_addressInfo){
		return;// goRegister() 함수를 종료한다.
	}
	   
	// ** 약관에 동의를 했는지 검사하기 시작 ** //
	const totalCheckboxes = $("input.agree").length; // 약관 체크박스 총 개수
	const checkedCheckboxes = $("input.agree:checked").length; // 체크된 약관 개수

	if (checkedCheckboxes < totalCheckboxes) {
	    alert("모든 필수 약관에 동의하셔야 합니다.");
	    return; // 함수 종료
	}
	
   	const frm = document.registerFrm; 
   	console.log(frm);
   	frm.action = "memberRegister.cl";
   	frm.method = "post";
   	frm.submit();
   
} // end of function goRegister() {}