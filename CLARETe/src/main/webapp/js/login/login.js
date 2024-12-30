$(document).ready(function() {
	
    // "로그인" 버튼 click 이벤트 처리
    $("button#login-btn").click(() => {
        goLogin();  // 로그인 시도
    }); 

    // 비밀번호 창에서 keydown 이벤트 처리
    $("input#pwd").bind("keydown", (e) => {

        if (e.keyCode == 13) {  // 암호입력란에 엔터를 했을 경우
            goLogin();  // 로그인 시도
        }
    });

});


// Function Declaration
function goLogin() {
    
    if ($("input#id").val().trim() == "") {
        alert("아이디를 입력하세요.");
        $("input#pwd").val("").focus();
        return;     // goLogin() 함수 종료
    }

    if ($("input#id").val().trim() == "") {
        alert("암호를 입력하세요.");
        $("input#pwd").val("").focus();
        return;     // goLogin() 함수 종료
    }

	
	if ($('input:checkbox[id="saveid"]').prop('checked')) {
		localStorage.setItem('saveid', $('input#id').val());
	}
	else {
		localStorage.removeItem('saveid');
	}
	
    const frm = document.loginFrm;
    frm.submit();


    //alert("로그인 처리");
}