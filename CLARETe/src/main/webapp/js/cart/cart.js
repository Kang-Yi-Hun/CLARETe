$(document).ready(function() {

	// 체크박스 다 체크되게 초기화
	$("input.product-checkbox").prop("checked", true);
	updateTotalPrice();


	// 상품 개수 조절 이벤트 처리
	$(".counter-container").each(function() {
		const container = $(this);
		const counterValue = container.find(".counter-value");
		const decreaseBtn = container.find(".decrease");
		const increaseBtn = container.find(".increase");
		const priceSpan = container.closest(".product").find(".priceSpan");
		const basePrice = parseFloat(priceSpan.data("price"));

		// 감소 버튼 클릭 이벤트
		decreaseBtn.click(function() {
			let count = parseInt(counterValue.attr("data-count"), 10);
			if (count > 1) {
				count--;
				updateProductPrice(count);
			}
		});

		// 증가 버튼 클릭 이벤트
		increaseBtn.click(function() {
			let count = parseInt(counterValue.attr("data-count"), 10);
			count++;
			updateProductPrice(count);
		});

		// 가격 업데이트
		function updateProductPrice(count) {
			counterValue.attr("data-count", count); // 수량 업데이트
			counterValue.text(count); 				// 수량 텍스트 업데이트

			const newPrice = basePrice * count; 	// 향수 가격 * 구매 수량
			priceSpan.attr("data-price", newPrice); // 새 가격 데이터 업데이트
			priceSpan.text(new Intl.NumberFormat().format(newPrice));

			updateTotalPrice();
		}

	});


	// 총 금액 업데이트 함수
	function updateTotalPrice() {
		let total = 0;
		let shipping = 0;

		$("input.product-checkbox:checked").each(function() {

			const priceSpan = $(this).closest(".product").find(".priceSpan");
			const price = parseFloat(priceSpan.text().replace(/,/g, "")) || 0; // 콤마 제거 후 숫자로 변환

			total += price;
		});


		// 총 상품 금액 업데이트
		$(".product_price").text(new Intl.NumberFormat().format(total));
		$("#total_product").text(new Intl.NumberFormat().format(total));

		// 총 상품 금액이 10만원 이상이면
		if (total > 100000) {
			shipping = 3000;
			$(".shipping_price").text(new Intl.NumberFormat().format(3000));
			$("#total_shipping").text(new Intl.NumberFormat().format(3000));
		} else {
			shipping = 0;
			$(".shipping_price").text(new Intl.NumberFormat().format(0));
			$("#total_shipping").text(new Intl.NumberFormat().format(0));
		}

		$(".total_price").text(new Intl.NumberFormat().format(total + shipping));
		$("#total_total").text(new Intl.NumberFormat().format(total + shipping));

	}


	// "전체선택" 버튼 클릭 이벤트 처리
	$("#btn_select").click(function() {
		$("input.product-checkbox").prop("checked", true);
		updateTotalPrice();
	});

	// "전체해제" 버튼 클릭 이벤트 처리
	$("#btn_cancel").click(function() {
		$("input.product-checkbox").prop("checked", false);
		updateTotalPrice();
	});

	// 체크박스 선택/해제에 따른 총 금액 업데이트
	$("input.product-checkbox").change(function() {
		updateTotalPrice();
	});

	// SVG 아이콘 클릭 시 div 삭제
	$("svg").click(function() {

		if (confirm("정말로 삭제하시겠습니까?")) {
			const productDiv = $(this).closest(".product"); // 클릭한 SVG가 속한 .product div
			productDiv.remove(); // 해당 div 삭제
			updateTotalPrice(); // 총 금액 다시 계산
		}

	});



	/////////////////////////////////////////////////////////////////////////

	
	
	// 폼 제출 시 데이터 동적으로 추가
	$("#order_form").submit(function(e) {
		e.preventDefault(); // 기본 제출 방지

		// 기존 동적 입력 제거
		$(".dynamic-input").remove();

		// 체크된 상품만 데이터 추가
		$("input.product-checkbox:checked").each(function() {
			const productDiv = $(this).closest(".product");
			const productNames = productDiv.data("id");
			const quantity = productDiv.find(".counter-value").attr("data-count");
			const price = productDiv.find(".priceSpan").data("price");

			// 동적으로 input 태그 만들어서 서버로 전송
			
			// 상품이름
			$("<input>")
				.attr("type", "hidden")
				.attr("name", "productIds")
				.addClass("dynamic-input")
				.val(productNames)
				.appendTo("#order_form");

			$("<input>")
				.attr("type", "hidden")
				.attr("name", "quantities")
				.addClass("dynamic-input")
				.val(quantity)
				.appendTo("#order_form");

			$("<input>")
				.attr("type", "hidden")
				.attr("name", "prices")
				.addClass("dynamic-input")
				.val(price)
				.appendTo("#order_form");
		});

		// 서버로 폼 전송
		this.submit();
	});


});
