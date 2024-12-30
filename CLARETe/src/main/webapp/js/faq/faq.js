/*$(document).ready(function(){
	$("button#btnSubmit").click(()=>{
		alert("업로드 클릭 확인용")
		FAQupload();
	});
});//end of $(document).ready(function(){})--------------------


*/
//FAQ 업로드
function FAQupload(){
	
	if($("input#title").val().trim() == "") {
		alert("FAQ 제목을 작성해주세요.");
		$("input#title").val("").focus();
		return; //  함수 종료
	}
	
	if($("textarea#content").val().trim() == "") {
			alert("FAQ 내용을 작성해주세요.");
			$("input#content").val("").focus();
			return; //   함수 종료
	}
	const frm = document.faqFrm;
	frm.action = "/CLARETe/faq/faq_board.cl";
	frm.method = "post";
	frm.submit();
		
}// end of function FAQupload(){}				
