/**
 * 
 */

let isLoading = false;

$(document).ready(function(){
	
	let cname = $('span.cname').text();
	// console.log($('span.cname').text());
    let selectVal =  $('.select').val(); 
    // 페이지 들어갔을 때의 select태그의 값
	
    $('span#totalHITCount').hide();
    $('span#countHIT').hide();

    let start = 1;
    let lenHIT = 6;
    
	
    displayHIT(start, cname, selectVal);

    $('.select').change(function(e){
        let changeVal = $(e.target).val();
		
		$("ul.cardcontainer").empty();
        $("span#end").empty();
        $("span#countHIT").text("0");

        start = 1;

        displayHIT(start, cname, changeVal);
    });

	
    // === 스크롤 이벤트 발생시키기 시작 === //
    $(window).scroll(function(){
		
		$('.btn-info').css({
			'opacity': '1'
		});
		
        // alert('스크롤함요~')
		// console.log("$(window).height() => ", $(window).height()); // 창의 높이
		// console.log("$(window).scrollTop() => ", $(window).scrollTop()); // 현재 스크롤 위치
		// console.log("$(document).height() => ", $(document).height()); // 전체 문서 높이
		// console.log("$(document).height() - $(window).height() => ", $(document).height() - $(window).height()); // 문서의 끝과 창의 끝 차이

		// console.log("스크롤 위치 : ", $(window).scrollTop());
		// console.log("푸터 위치 : ", $('footer').offset().top);
        // console.log("전체 문서 높이 ", $(document).height()); // 전체 문서 높이

        if( $(window).scrollTop() >= $('footer').offset().top - 600) {
        
            // alert("기존 문서내용을 끝까지 봤습니다. 이제 새로운 내용을 읽어다가 보여드리겠습니다.");

            if( ( $("span#totalHITCount").text() != $("span#countHIT").text() ) && !isLoading) {
                start += lenHIT;
                displayHIT(start, cname, selectVal);

            }
        
        }

        if($(window).scrollTop() == 0){
            // 다시 처음부터 시작하도록 한다.
            $("ul.cardcontainer").empty();
            $("span#end").empty();
            $("span#countHIT").text("0");
			$('.select').val("신상품순");
			
			$('.btn-info').css({
				'opacity': ''
			});
			
            start = 1;
            displayHIT(start, cname, selectVal);
        }

    });
    // === 스크롤 이벤트 발생시키기 끝 === //

}); // end of $(document).ready(function()--------


// Function Decaration

let lenHIT = 6;


// HIT 상품 "더보기..." 버튼을 클릭할때 보여줄 상품의 개수(단위)크기

function displayHIT(start, cname, selectVal) { 
	// console.log(start);

	$('#loading').show();
	
    isLoading = true;

    $.ajax({// 상품을 6개씩 ajax로 서버에 요청해 값을 받아올거임	
        url:"mallDisplayJSON.cl",
    //  type:"get", 상품정보 조회라 보안상 별로 안 중요함 그래서 겟
        data:{"selectVal":selectVal,
              "cname":cname,
              "start":start, // "1"  "9"  "17"  "25"  "33"      // 몇번째 상품부터
              "len":lenHIT   //  8    8    8     8     8        // 몇개의 정보를 불러올 것이다.
        },
        dataType:"json",
        success:function(json){

            let v_html = ``;

            if(start == "1" && json.length == 0){
                // 처음부터 데이터가 존재하지 않는 경우
                /* !!!! 주의 !!!!
                    if(start == "1" && json == null) 이 아님!!!
                    if(start == "1" && json.length) 로 해야 함!!!
                */
                v_html = `<span></span> <span style="display:block; margin:20px; font-size: 12pt; font-weight: 400; color: #797979; text-align:center;">현재 상품 준비중 입니다.</span> <span></span>`;
                $('ul.cardcontainer').html(v_html);
            }
            else if(json.length > 0){

                $.each(json, function(index, item){
                    v_html += `<li class="cardbox">
	                               <a href="/CLARETe/shop/prodView.cl?p_num=${item.p_num}">
	                                   <div class="cardimg background-image: url(${item.p_image});">
									   <img src='${item.p_image}' style='width: 100%' />
	                                   </div>
	                                   <div class="cardname">
	                                       ${item.p_name}
	                                   </div>
	                                   <div class="cardprice">
	                                       ${item.p_price.toLocaleString('en')}원
	                                   </div>
	                               </a>
	                            </li> `;
                }); // end of $.each(json, function(index, item)------------

                // HIT 상품 결과를 출력하기
                $('ul.cardcontainer').append(v_html);

                // span#countHIT 에 지금까지 출력된 상품의 개수를 누적해서 기록한다.
                $('span#countHIT').text( Number($('span#countHIT').text()) + json.length);

                // 스크롤을 계속하여 countHIT 값과 totalHITCount 값이 일치하는 경우
                if( $('span#countHIT').text() == $('span#totalHITCount').text() ){
                    $('span#end').html('더이상 조회할 제품이 없습니다.');
                }
            }// end of else if(json.length > 0)--------------

            isLoading = false;
            
        },
		complete: function() {
            // 로딩 애니메이션 종료
            $('#loading').hide();
		},
        error: function(request, status, error){
            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
        }
    });


} // end of function displayHIT(start)---------


function goTop(){
    $(window).scrollTop(0);
} 