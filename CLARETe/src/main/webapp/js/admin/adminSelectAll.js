$(document).ready(function() {
   $("tbody#selectAll").hide();
});

// === "모든회원조회" 버튼 클릭 이벤트 === //
function memberSelectAll() {
   
   $("tbody#selectAll").show();
   
}

/*// === "modal" 기능 ===
document.addEventListener("DOMContentLoaded", function () {
    // 모든 "상세보기" 버튼 선택
    const detailButtons = document.querySelectorAll('.btn[data-toggle="modal"]');

    detailButtons.forEach(button => {
        button.addEventListener("click", function () {
            // 버튼의 data-* 속성에서 데이터 읽기
            const name = this.getAttribute("data-name");
            const email = this.getAttribute("data-email");
            const mobile = this.getAttribute("data-mobile");

            // 모달에 데이터 삽입
            document.getElementById("modal-name").textContent = name;
            document.getElementById("modal-email").textContent = email;
            document.getElementById("modal-mobile").textContent = mobile;
        });
    });
});
*/


/*document.addEventListener("DOMContentLoaded", function () {
    const detailButtons = document.querySelectorAll('.btn[data-toggle="modal"]');

    detailButtons.forEach(button => {
        button.addEventListener("click", function () {
            const name = this.getAttribute("data-name");
            const email = this.getAttribute("data-email");
            const mobile = this.getAttribute("data-mobile");
            const postcode = this.getAttribute("data-postcode");
            const address = this.getAttribute("data-address");
            const detail_address = this.getAttribute("data-detail_address");
            const extra = this.getAttribute("data-extra");
            const gender = this.getAttribute("data-gender");
            const birth = this.getAttribute("data-birth");
            const point = this.getAttribute("data-point");
            const register = this.getAttribute("data-register");
            const lastpwd = this.getAttribute("data-lastpwd");
            const status = this.getAttribute("data-status");
            const idle = this.getAttribute("data-idle");
            
            // 모달에 데이터 삽입
            document.getElementById("modal-name").textContent = name;
            document.getElementById("modal-email").textContent = email;
            document.getElementById("modal-mobile").textContent = mobile;
            document.getElementById("modal-postcode").textContent = postcode;
            document.getElementById("modal-address").textContent = address;
            document.getElementById("modal-detail_address").textContent = detail_address;
            document.getElementById("modal-extra").textContent = extra;
            document.getElementById("modal-gender").textContent = gender;
            document.getElementById("modal-birth").textContent = birth;
            document.getElementById("modal-point").textContent = point;
            document.getElementById("modal-register").textContent = register;
            document.getElementById("modal-lastpwd").textContent = lastpwd;
            document.getElementById("modal-status").textContent = status;
            document.getElementById("modal-idle").textContent = idle;
            
        });
    });
});*/