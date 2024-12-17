<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
 String ctxPath = request.getContextPath();
		
		
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>금쪽이</title>
    <link rel="stylesheet" href="<%= ctxPath %>/css/mypage/header_02.css">
	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/footer.css">
	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/mapage.css">
	<link rel="stylesheet" href="<%= ctxPath %>/css/mypage/media_nav.css">    
	<link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    
   <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<script type="text/javascript">
  $(document).ready(function() {
	  $("#toggleDelivery").hide();
	  
    $("#toggleButton").click(function(e) {
      e.preventDefault(); // 기본 동작인 페이지 이동을 막습니다
      //console.log("확인용 버튼 클릭"); // 디버깅을 위한 로그
        $("#toggleDelivery").show();
    });

	  
	  $("#toggleColse").click(function(e) {
		  $("#toggleDelivery").hide();		
	});
  });
  
  
  
  
  function goInsertDelivery(){

	  
	  // alert('das')
      //console.log("확인용 클릭");
  	   // ** == 문항들이 입력 되어있는지 확인 검사 시작. ==** //
      let d_deliveryInfo = true; // 입력된 값이 있다 true
  	   
  	   console.log($("input.d_deliveryInfo"));

      $("input.d_deliveryInfo").each(function(index, elmt){
          const data = $(elmt).val().trim();

          if(data==""){ //입력이 공백일 경우
              alert("표시 항목을 전부 입력 해주세요.");
              d_deliveryInfo = false; // 값이 없다.

              return false; // break 라는 뜻이다.
          }
      if(!d_deliveryInfo){ //d_deliveryInfo 가 참일 경우
              return;// 함수를 종료 시킨다.
      }
  	}); // end of ("input.d_deliveryInfo").each(function(index, elmt){})-------------------------------------------------------

  	// ** == 문항들이 입력 되어있는지 확인 검사 끝. ==** //

  	// *** 우편번호 및 주소에 값을 입력했는지 검사하기 시작 *** //
  	let d_addressInfo = true;t

  	const arr_addressInfo = [];
  	arr_addressInfo.push($("input[name='d_postcode']"));
  	arr_addressInfo.push($("input[name='d_address']"));
  	arr_addressInfo.push($("input[name='detail']"));
  	arr_addressInfo.push($("input[name='extra']"));


  	for(let i=0; i<arr_addressInfo.length; i++){
  	    if(arr_addressInfo==""){
  	        alert("주소를 입력하세요!")
  	        d_addressInfo = false;
  	        break;
  	    }//end of if(arr_addressInfo==""){}---------------------------------------------
  	}//end of for(let i=0; i<arr_addressInfo.length; i++){}-------------------------------------------------------

  	if(!arr_addressInfo){
  	    return;// 함수를 종료한다.
  	}
  	// *** 우편번호 및 주소에 값을 입력했는지 검사하기 끝 *** //

  	const frm = document.frm;
  	frm.action = "insertDelivery.cl";
  	frm.method = "post";
  	frm.submit();


  }  // end of function goInsertDelivery()=========================================================  
  
  
</script>
  
  
</head>
<body> 
    <div id="container">
        <div class="header-container">
        <header>
                <div class="hamburgerbar" id="hamburger">
                    <ul>
                        <li class="hamburger"></li>
                        <li class="hamburger"></li>
                        <li class="hamburger"></li>
                    </ul>
                </div>
                <section class="header-logo">
                    <h2>LOGO</h2>
                </section>
                <section>
                    <div class="header-icon-wrapper">
                        <ul>
                            <li><a href="#">Login</a></li>
                            <li><a href="#">Sign Up</a></li>
                            <li><a href="#">My Page</a></li>
                        </ul>
                    </div>
                </section>
            </header>
        </div>
        <div class="nav-container">
            <nav>
                <div class="nav_inner">
                    <ul class="nav_list">
                        <li class="menu">
                            <a>ABOUT</a>
                            <div class="depth">
                                <ul>
                                    <li><a>BRAND STORY</a></li>
                                    <li><a>BRAND CAMPAIGN</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="menu">
                            <a>PRODUCT</a>
                            <div class="depth">
                                <ul>
                                    <li><a>SPRING</a></li>
                                    <li><a>SUMMER</a></li>
                                    <li><a>AUTUMN</a></li>
                                    <li><a>WINTER</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="menu">
                            <a>CATEGORY</a>
                            <div class="depth">
                                <ul>
                                    <li><a>For Him</a></li>
                                    <li><a>For Her</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>

        <section class="section1">
            <div class="mypage_container">
                <div class="mypage_sadebar">
                    <div class="mapage_hello">WELLCOME!</div>
                    <ul>
                        <li>
                            <div>내 정보</div>
                            <ul>
                                <li><a href="#">내 정보 수정</a></li>
                                <li><a href="#" id="toggleButton" >배송지 입력</a></li>
                                <li><a href="#">회원탈퇴</a></li>
                            </ul>
                        </li>
                        <li>
                            <div>향수</div>
                            <ul>
                                <li><a href="#">최근 주문 향수</a></li>
                                <li><a href="#">최근 본 향수</a></li>
                                <li><a href="#">장바구니</a></li>
                            </ul>
                        </li>
                        <li>
                            <div>나의 리뷰</div>
                            <ul>
                                <li><a href="#">내가 쓴 리뷰</a></li>
                                <li><a href="#">리뷰작성</a></li>
                            </ul>
                        </li>
                        <li>
                            <div>문의하기</div>
                        </li>
                    </ul>
                </div>

                <div class="mypage_contants">
                    <div class="mypage_contants_top">
                        <div class="top_title">
                            <span>강이훈</span><span>님 반갑습니다.</span>
                            <div><a href="#">회원 정보 수정</a></div>
                        </div>
                        <div class="top_contants">
                            <ul>
                                <li>
                                    <div>
                                        장바구니
                                    </div>
                                    <div>
                                        <span>3</span><span>개</span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        최근 본 상품
                                    </div>
                                    <div>
                                        <span>0</span><span>개</span>
                                    </div>
                                </li>
                                <li>
                                    <div>
                                        POINT
                                    </div>
                                    <div>
                                        <span>500</span><span>P</span>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!-- === 반응형 nav바 === -->
                    <ul class="media_nav">
                        <li>
                            <div style="pointer-events: none;">내 정보</div>
                            <ul>
                                <li><a href="#">내 정보 수정</a></li>
                                <li><a href="#">배송지 입력</a></li>
                                <li><a href="#">회원탈퇴</a></li>
                            </ul>
                        </li>
                        <li>
                            <div style="pointer-events: none;">향수</div>
                            <ul>
                                <li><a href="#">최근 주문 향수</a></li>
                                <li><a href="#">최근 본 향수</a></li>
                                <li><a href="#">장바구니</a></li>
                            </ul>
                        </li>
                        <li>
                            <div style="pointer-events: none;">나의 리뷰</div>
                            <ul>
                                <li><a href="#">내가 쓴 리뷰</a></li>
                                <li><a href="#">배송지 입력</a></li>
                            </ul>
                        </li>
                        <li>
                            <div style="pointer-events: none;">문의하기</div>
                        </li>
                    </ul>
                    <!-- === 반응형 nav바 === -->

                    <div class="mypage_contants_bottom">
                        <div class="recent-orders-title">최근 주문 향수</div>
                        <div class="recent-orders-box">
                            <div>최근 주문 내역이 없습니다.</div>
                        </div>

                        <div class="recent-orders-title">방금 본 향수</div>
                        <div class="recent-orders-box">
                            <div>장바구니의 상품이 없습니다.</div>
                        </div>
                    </div>
                </div>

            </div> <!-- mypage_container -->



        </section> <!-- 가상 높이 준거임 컨텐츠 없으면 해더 고장나서 안 돌아감! -->
        
        <!-- 배송지 입력을 눌렀을 경우에 -->
        

 <div id="toggleDelivery">


<div id="clickOk">
</div>

	<div id="deliveryFrm">
		<form  id="frm" name="frm">
			<h4><button type="button" id="toggleColse" style="border:white; background-color:#ffff; color:black">x</button></h4>
			<table id="deliveryinputTbl">
		
				<thead>
					 <tr>
                   <th> 배송지를 입력해주세요</th>
               
                </tr>
					
				</thead>
				
				
				<tbody>
					<tr id="tr_margin"><!-- 이름 -->
						<td>
							<label id="infotitle">받는 사람</label><input type="text" name="d_name" id="userinfo" class="d_deliveryInfo"></input>
						</td>
					</tr>
					
					<tr id="tr_margin"><!-- 전화번호 -->
						<td>
							<label id="infotitle">받는 사람 전화번호</label><input type="text" name="d_mobile" id="userinfo" class="d_deliveryInfo"></input>
						</td>
					</tr>
					
					<tr id="tr_margin"><!-- 우편번호 -->
						<td>
							<label id="infotitle">받는 사람 우편번호</label><input type="text" name="d_postcode" id="userinfo" class="d_deliveryInfo"></input>
						</td>
					</tr>
					
					<tr id="tr_margin"><!-- 주소 -->
						<td>
							<label id="infotitle">받는 사람 주소</label><input type="text" name="d_address" id="userinfo" class="d_deliveryInfo"></input>
						</td>
					</tr>
					
					<tr id="tr_margin"><!-- 상세주소 -->
						<td>
							<label id="infotitle">받는 사람 상세주소</label><input type="text" name="d_detail_address" id="userinfo" class="d_deliveryInfo"></input>
						</td>
					</tr>
					
					<tr id="tr_margin"><!-- 참고항목 -->
						<td>
							<label id="infotitle">받는 사람 참고항목</label><input type="text" name="d_extra" id="userinfo" class="d_deliveryInfo"></input>
						</td>
					</tr>					
				</tbody>
			</table>
			<input type="hidden" name="fk_m_id" id="userinfo" class="d_deliveryInfo" value="">
			<input type="button" class="btn_success" value="배송지 입력" onclick="goInsertDelivery()" />
            <input type="reset"  class="btn_reset" value="취소하기" onclick="goReset()" />
		</form>
	</div>

</div>

<!-- 배송지 토클창 끝 -->
        

        <footer>
            <div>
                <div class="footer-logo">
                    FOOTER LOGO
                </div>
                <div class="footer-contants">
                    <div class="infobox">
                        <div class="contants-title">
                            Info.
                        </div>
                        <div class="footer-contant">
상호 : 금쪽이 캠퍼니  |  조장 : 장민규 |  이메일 : rmaWhrdl12@gmail.com <br>
사업자등록번호 : 000-11-22222  | <br>
주소 : 서울시 홍대입구역 내리면 있는 쌍용강북센터  |  개인정보보호책임자 : 강이훈 
                        </div>
                    </div>
                    <div class="infobox">
                        <div class="contants-title">
                            Guide.
                        </div>
                        <div class="footer-contant">
                            <div><a href="#">이용약관</a></div>
                            <div><a href="#">개인정보처리방침</a></div>
                            <div><a href="#">자주묻는질문</a></div>
                        </div>
                    </div>
                    <div class="infobox">
                        <div class="contants-title">
                            Contact us.
                        </div>
                        <div class="footer-contant">
                            <div>플러스친구 @장민규</div>
                            <div>※ 유선 상담은 진행하지 않습니다.</div>
                            <div>평일 10:00 - 17:00 (점심 12:00 - 13:00)<br>주말 및 공휴일 휴무</div>
                        </div>
                    </div>
                    <div>
                        Copyright ⓒ 2024. 금쪽이와 아이들.
                    </div>
                </div>
            </div>
        </footer> 
    </div>
    
    <script>
        let hamburger_cnt=0;

        $('#hamburger').click(()=>{
            hamburger_cnt++;

            if(hamburger_cnt%2!=0){
                $('.nav-container').css({
                    'left': '0'
                });
            }
            else if(hamburger_cnt%2==0){
                $('.nav-container').css({
                    'left': ''
                });
            }
        })
        


        // === 반응형 nav창 === //
        $('.media_nav > li > ul').hide();

        $('ul.media_nav > li').click((e)=>{
            const idx = $(e.target).index();

            $('.media_nav > li > ul').eq(idx).slideToggle();
        });
        // === 반응형 nav창 === //
        
    </script>
    
</body>
</html>
