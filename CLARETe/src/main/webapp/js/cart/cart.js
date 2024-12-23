$(document).ready(function() {
	
	let count = 1;

    // DOM 요소 가져오기
    const counter = document.getElementById('counter');
    const decreaseBtn = document.getElementById('decrease');
    const increaseBtn = document.getElementById('increase');

    // 감소 버튼 클릭 이벤트
    decreaseBtn.addEventListener('click', () => {
		console.log("감소");
        if (count > 1) { // 최소값 제한
            count--;
            counter.textContent = count;
        }
    });

    // 증가 버튼 클릭 이벤트
    increaseBtn.addEventListener('click', () => {
		console.log("증가");
        count++;
        counter.textContent = count;
    });
	
});