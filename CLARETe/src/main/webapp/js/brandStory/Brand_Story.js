/**
 * 
 */




        $(document).ready(function(){
            
            $('.brand_title > div:nth-child(1)').css({
                'opacity': '1',
                'top': '-20px'
            });
            

            setTimeout(function(){
                $('.brand_title > div:nth-child(2)').css({
                'opacity': '1',
                'top': '0px'
            });
            }, 200)
            
            
			
			// === 토글 되는 nav바 시작 === //
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
			        });
			        // === 토글 되는 nav바 끝 === //
			        

			        // === --- 스크롤링 이벤트 시작 --- === //
			        $(window).scroll(() => {
			            let scroll_position = $(window).scrollTop(); // 스크롤 실시간 위치
			            let scroll_ratio = Math.min(scroll_position / 1080, 1); 

			            let section2_offset = $('.section2').offset().top; // .section2의 화면에서의 위치
			            let section2_middle = section2_offset + ($('.section2').outerHeight() / 2); // .section2의 중간 지점

			            // RGB 색상과 투명도를 스크롤 비율에 따라 계산
			            let background_color = `rgba(0, 0, 0, ${scroll_ratio})`;

			            $('.header-container').css({
			                'background-color': '#fff'
			            });

			            $('body').css({
			                'background-color': background_color
			            });

			            $('.scroll_info').css({
			                'display' : 'none'
			            });

			            if (scroll_position >= section2_middle) {
			                let max_scroll = $(document).height() - $(window).height(); // 최대 스크롤 값
			                let scroll_ratio = Math.min(Math.max((scroll_position - section2_middle) / 500, 0), 1); // 스크롤 비율 (0 ~ 1, 500px 기준)

			                // 투명도 및 크기 조정
			                $('.overlay-image').css({
			                    'opacity': scroll_ratio, // 투명도 증가
			                    'transform': `scale(${3 - (2 * scroll_ratio)})` // 크기 감소
			                });
			            }
			            else{
			                $('.overlay-image').css({
			                    'opacity': '', // 투명도 증가
			                    'transform': '' // 크기 감소
			                });
			            }

			            // 스크롤 비율에 따라 width 조정
			            let width_percentage = scroll_ratio * 100; // 0% ~ 100%
			            $('.center-box').css({
			                'width': `${scroll_ratio * 100}%`
			            });

			            

			            // 스크롤 맨 위로 올릴 때 초기화
			            if (scroll_position == 0) {
			                $('.header-container').css({
			                    'background-color': ''
			                });

			                $('body').css({
			                    'background-color': ''
			                });

			                $('.scroll_info').css({
			                'display' : ''
			            });
			            }
			        });

			        // === --- 스크롤링 이벤트 끝 --- === //
        })