$(document).ready(function() {
	
	let count = 1;

	// 이벤트 위임 방식으로 각 counter-container의 버튼에 이벤트 추가
	document.querySelectorAll('.counter-container').forEach(container => {
	    const counter = container.querySelector('.counter-value');
	    const decreaseBtn = container.querySelector('.decrease');
	    const increaseBtn = container.querySelector('.increase');

	    // 감소 버튼 클릭 이벤트
	    decreaseBtn.addEventListener('click', () => {
	        let count = parseInt(counter.textContent, 10);
	        counter.textContent = Math.max(1, count - 1); // 최소값 1
	    });

	    // 증가 버튼 클릭 이벤트
	    increaseBtn.addEventListener('click', () => {
	        let count = parseInt(counter.textContent, 10);
	        counter.textContent = count + 1;
	    });
	});
	
	
	
	
	// "전체선택" 버튼 클릭 이벤트 처리
	$("button#btn_select").click((e) => {
		console.log("ㅇㅇㅇ");
	    $("input.product-checkbox").prop("checked", true);
	});
	
	// "전체해제" 버튼 클릭 이벤트 처리
	$("button#btn_cancel").click((e) => {
		console.log("ㄴㄴㄴㄴ");
	    $("input.product-checkbox").prop("checked", false);
	});
		
		
}); // end of $(document).ready(function(){});-----

