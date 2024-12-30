//Qna 등록
function QnaUpload(){
	
/*	if($("input#q_title").val().trim() == "") {
		alert("제목을 작성해주세요.");
		$("input#q_title").val("").focus();
		return; //  함수 종료
	}
	
	if($("textarea#q_ask").val().trim() == "") {
		alert("내용을 작성해주세요.");
		$("textarea#q_ask").val("").focus();
		return; //   함수 종료
	}

*/

/*var selectcategory = document.getElementById('q_category').value;*/





	const frm = document.qnaFrm;
	frm.action = "/CLARETe/qna/qna.cl";
	frm.method = "post";
	frm.submit();
		
}// end of function FAQupload(){}				