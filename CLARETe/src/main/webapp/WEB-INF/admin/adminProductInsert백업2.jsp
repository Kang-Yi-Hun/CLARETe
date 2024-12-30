<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%
    String ctxPath = request.getContextPath();
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 상품관리</title>
    <link rel="stylesheet" href="<%= ctxPath%>/css/admin/admin_header.css">
    <link rel="stylesheet" href="<%= ctxPath%>/css/admin/admin_section.css">
    <link rel="stylesheet" href="<%= ctxPath%>/css/admin/admin_insert.css">
    
    <!-- Bootstrap CSS -->
	<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css" > 
	
	<!-- Optional JavaScript -->
	<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 

	<%-- jQueryUI CSS 및 JS --%>
	<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
	<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script> 

	<!-- Bootstrap Select CSS -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.14.0-beta3/dist/css/bootstrap-select.min.css">

</head>
<body class="allbody" style="background-color: #F1F5F9 !important; width: 100% !important;">
    
    <jsp:include page="adminheader.jsp"></jsp:include>
    
    <section>
        <div style="display: flex; flex-wrap: wrap;">
	        <div style="margin-left: 10px; color:#999;"><span class="text-danger">*</span>은 필수입력항목입니다.</div>
	            <div class="first-div">

		            <form name="myFrm" method="post" action="<%= ctxPath%>/admin/adminProductInsertGo.cl">
			            <table class="table table-bordered">
			            <thead class="table-dark">
				            <th scope="col" class="w-25 p-3">컬럼</th>
						    <th scope="col">데이터</th>
			            </thead>
				            <tbody>
				            	<tr>
				            		<th scope="col"><label class="form-label">상품명<span class="text-danger">*</span></label></th>
				            		<td>
				            			<input class="form-control w-25 p-3" type="text" name="p_name" placeholder="상품명" />
				            			<div class="text-danger p_name_error">상품명은 필수 입력사항입니다.</div>
				            		</td>
				            	</tr>
				            	<tr>
				            		<th><label>카테고리<span class="text-danger">*</span></label></th>
				            		<td>
										<select name="p_season" class="selectpicker show-tick ">
										   <option value="" selected disabled style="color: #7B7B7B;">- 카테고리를 선택해 주세요 -</option>
										   <option value="1">봄</option>
										   <option value="2">여름</option>
										   <option value="3">가을</option>
										   <option value="4">겨울</option>
										</select>
										<div class="text-danger p_season_error">카테고리는 필수 입력사항입니다.</div>
									</td>
				            	</tr>
				            	
				            	<tr >
				            		<td>
				            		    <label id="optionadd" class="btn btn-outline-dark">옵션추가하기</label>
				            		    <input type="hidden" name="option_select_name" />
				            		    <input type="hidden" name="option_input_name" />
				            		</td>		
				            		<td id="add"><span style="color:#999; font-size: 14px;">옵션 미추가인 경우 기본값인 50ml, 0원이 등록됩니다.</span></td>
				            	</tr>
				            	
				            	<tr>
				            		<th><label>제품설명<span class="text-danger">*</span></label></th>
				            		<td>
				            			<textarea class="form-control" type="text" name="p_ex" placeholder="제품설명" rows="5"/></textarea>
				            			<div class="text-danger p_ex_error">제품설명은 필수 입력사항입니다.</div>	
				            		</td>
				            	</tr>
				            	
				            	<tr>
				            		<th><label>가격<span class="text-danger">*</span></label></th>
				            		<td>
				            			<input class="form-control w-25 p-3" type="text" name="p_price" placeholder="가격" />
				            			<div class="text-danger p_price_error">가격은 숫자만 입력 가능하며, 필수 입력사항입니다.</div>	
				            		</td>
				            	</tr>
				            	
				            	<tr>
				            		<th><label>재고<span class="text-danger">*</span></label></th>
				            		<td>
				            			<input class="form-control w-25 p-3" type="text" name="p_inven" placeholder="재고" />
				            			<div class="text-danger p_inven_error">재고는 숫자만 입력 가능하며, 필수 입력사항입니다.</div>
				            		</td>
				            	</tr>
				            	
				            	<tr>
				            		<th><label>발매일<span class="text-danger">*</span></label></th>
				            		<td>
				            			<input class="form-control w-25 p-3" id="datepicker" type="text" name="p_release" placeholder="발매일" />
				            			<div class="text-danger p_release_error">발매일은 마우스로만 클릭할 수 있으며, 필수 입력사항입니다.</div>
				            		</td>
				            	</tr>
				            	
				            	<tr>
				            		<th><label>할인률</label></th>
				            		<td><input class="form-control w-25 p-3" type="text" name="p_sale" placeholder="할인률" /></td>
				            	</tr>
				            	
				            	<tr>
				            		<th><label>성별</label></th>
				            		<td>
										<select name="p_gender" class="selectpicker show-tick">
										   <option value="" selected disabled style="color: #7B7B7B;">- 성별을 선택해 주세요 -</option>
										   <option value="0">공용</option>
										   <option value="1">남자</option>
										   <option value="2">여자</option>
										</select>
										<span style="color:#999; font-size: 14px;">성별 미선택인 경우 기본값인 공용향수로 등록됩니다.</span>
									</td>
				            		
				            		
				            	</tr>
				            	
				            	<tr>
				            		<th><label>상품이미지<span class="text-danger">*</span></label></th>
				            		<td>
				            			<input class="form-control w-25 p-3" type="text" name="p_image" placeholder="상품이미지" />
				            			<div class="text-danger p_image_error">상품이미지는 필수 입력사항입니다.</div>	
				            		</td>
				            	</tr>
				            	
				            	<tr>
				            		<th><label>상품상세이미지</label></th>
				            		<td><input class="form-control w-25 p-3" type="text" name="p_detail_image" placeholder="상품상세이미지" /></td>
				            	</tr>
			            	</tbody>
			            </table>
			            <div style="display: flex; justify-content: center; gap: 2%;">
			            	<input class="btn btn-dark" type="submit" value="등록"/> <input class="btn btn-outline-dark" type="reset" value="취소"/>
			            </div>
			            
			            <input type="hidden" name="p_num" value="${requestScope.p_num}" />
			        </form>
	            
	            	
	            </div>
	            	
        </div>
        
    </section>

<script>

  $(document).ready(function(){
	
	let op_cnt = 0;
	let div_opt_length = 0;
	
	$('label#optionadd').click(function(e) {
		
		op_cnt++;  // 1  2  3  4
		
		if(div_opt_length < 3) {
			$('td#add').append(
					"<div class='opt'>"+
						"<label>옵션</label>" +
		        		"<span>" +
		        		"<select class='op_ml ' name='op_ml"+op_cnt+"'>" +
						   "<option value='0' selected disabled hidden style='color: #7B7B7B; !important'>- 옵션을 선택해 주세요 -</option>" +
						   "<option value='1'>50ml</option>" +
						   "<option value='2'>75ml</option>" +
						   "<option value='3'>100ml</option>" +
						"</select>" +
						"<input type='text' class='op_price form-control w-25 p-4' name='op_price"+op_cnt+"' placeholder='옵션 별 가격' />" +
						"<span class='op_close btn btn-outline-dark'>닫기</span>" +
						"</span>" +
					"</div>"
			);
			
			div_opt_length = $("td#add").find("div.opt").length; // 1  2  3
			
            ////////////////////////////////////////////////////////////////////////
			const select_list = $("td#add").find("div.opt").find("select.op_ml");
			
			let arr_select_name = [];
			
			select_list.each(function(idx, elmt){
				arr_select_name.push($(elmt).attr("name"));
			});
			
			
			const input_list = $("td#add").find("div.opt").find("input.op_price");
			
			let arr_input_name = [];
			
			input_list.each(function(idx, elmt){
				arr_input_name.push($(elmt).attr("name"));
			});
			
		//	alert(arr_select_name.join(","));
            ////////////////////////////////////////////////////////////////////////
		    
			$("input:hidden[name='option_select_name']").val(arr_select_name.join(","));
			$("input:hidden[name='option_input_name']").val(arr_input_name.join(","));
		} // end of if(div_opt_length < 3)--------------------------------	
	
		
		
	}); // end of $('.optionadd').click(function(e)--------
	
	$(document).on('click', 'span.op_close', function(e){

		$(e.target).parent().parent().remove();
		div_opt_length = $("td#add").find("div.opt").length; // 1  2  3
		
		////////////////////////////////////////////////////////////////////////
		const select_list = $("td#add").find("div.opt").find("select.op_ml");
		
		let arr_select_name = [];
		
		select_list.each(function(idx, elmt){
			arr_select_name.push($(elmt).attr("name"));
		});
		
		
		const input_list = $("td#add").find("div.opt").find("input.op_price");
		
		let arr_input_name = [];
		
		input_list.each(function(idx, elmt){
			arr_input_name.push($(elmt).attr("name"));
		});
        ////////////////////////////////////////////////////////////////////////
		
		$("input:hidden[name='option_select_name']").val(arr_select_name.join(","));
		$("input:hidden[name='option_input_name']").val(arr_input_name.join(","));
        
	}); // end of $(document).on('click', '.op_close', function(e)-------
			
			
	$(document).on('change', 'select.op_ml', function(e){
		const ch_index = $('select.op_ml').index(this);
		// 선택한 셀랙트태그의 index 값
		const ch_val = $(e.target).val();
		// 선택한 셀랙트태그의 value 값
		
		// alert(ch_index +", "+ch_val);

		$('select.op_ml').each(function(idx, elmt){
			const all_index = $('select.op_ml').index(this);
			// 전체 셀랙트태그의 value 값
			const all_val = $('select.op_ml').eq(idx).val();
			// 전체 셀랙트태그의 value 값
			
			if(all_val == ch_val && ch_index != all_index){
				alert('같은 용량은 선택하실 수 없습니다. 다시 선택해주세요');
				$('select.op_ml').eq(ch_index).val("0").prop("selected", true);
			}
		})
		
	}); // end of $(document).on('change', 'select.op_ml', function(e)--------
	
	$('.selectpicker').selectpicker();
	
	
	
	
	// 상품명 유효성 검사 //
	$('.p_name_error').hide();
	let name_bool = false;
	
	$('input:text[name="p_name"]').blur(function(){
		if($(this).val().trim() == ''){
			$('.p_name_error').show();
			$(this).focus();
			$(this).val('');
		}
		else{
			$('.p_name_error').hide();
			name_bool = true;
		}
	});
	// 상품명 유효성 검사 //
	
	
	
	// 상품설명 유효성 검사 //	
	$('.p_ex_error').hide();
	let ex_bool = false;
	
	$('textarea[name="p_ex"]').blur(function(){
		if($(this).val().trim() == ''){
			$('.p_ex_error').show();
			$(this).focus();
			$(this).val('');
		}
		else{
			$('.p_ex_error').hide();
			ex_bool = true;
		}
	});
	// 상품설명 유효성 검사 //	
	
	
	
	// 상품금액 유효성 검사 //	
	$('.p_price_error').hide();
	let price_bool = false;
	
	$('input:text[name="p_price"]').blur(function(){
		if($(this).val().trim() == '' || !(solution($(this).val()))){
			$('.p_price_error').show();
			$(this).focus();
			$(this).val('');
		}
		else{
			$('.p_price_error').hide();
			price_bool = true;
		}
	});
	// 상품금액 유효성 검사 //
	
	
	
	// 상품재고 유효성 검사 //	
	$('.p_inven_error').hide();
	let inven_bool = false;
	
	$('input:text[name="p_inven"]').blur(function(){
		if($(this).val().trim() == '' || !(solution($(this).val()))){
			$('.p_inven_error').show();
			$(this).focus();
			$(this).val('');
		}
		else{
			$('.p_inven_error').hide();
			inven_bool = true;
		}
	});
	// 상품재고 유효성 검사 //
	
	
	
	// 상품이미지 유효성 검사 //
	$('.p_image_error').hide();
	let image_bool = false;
	
	$('input:text[name="p_image"]').blur(function(){
		if($(this).val().trim() == ''){
			$('.p_image_error').show();
			$(this).focus();
			$(this).val('');
		}
		else{
			$('.p_image_error').hide();
			image_bool = true;
		}
	});
	// 상품이미지 유효성 검사 //
	
	
	
	// 상품발매일 데이트피커 //
	$('.p_release_error').hide();
	
	$("input#datepicker").datepicker({
		dateFormat: 'yy-mm-dd'  
		,showOtherMonths: true  
		,showMonthAfterYear:true 
		,changeYear: true        
		,changeMonth: true                     
		,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12']
		,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] 
		,dayNamesMin: ['일','월','화','수','목','금','토'] 
		,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']       
	});
	
	$("input#datepicker").bind("keyup", e=>{
		$(e.target).val("").next().show();
	}); // 상품발매일에 키보드로 입력하는 경우

	$("input#datepicker").bind("change", e=>{
	    if($(e.target).val() != "") {
	        $(e.target).next().hide();
	    }
	}); // 상품발매일에 마우스로 값을 변경하는 경우
	// 상품발매일 데이트피커 //
	
	
	
	$('.p_season_error').hide();
	
	$('.selectpicker').change(function(){
		if($('.selectpicker').val() != null){
			$('.p_season_error').hide();
		}
	});
	
	
	
	// 등록하기 버튼 눌렀을 경우 유효성 체크 //
	$('form[name="myFrm"]').submit(function(e){
		const input_cnt = $('input:text');
		
		
		if(!name_bool){
			$('.p_name_error').show();
			alert('필수항목을 확인해주세요.')
			e.preventDefault(); // 이거 return false랑 같은 역할임
		}
		else if($('.selectpicker').val() == null){
			$('.p_season_error').show();
			alert('필수항목을 확인해주세요.')
			e.preventDefault();
		}
		else if(!ex_bool){
			$('.p_ex_error').show();
			alert('필수항목을 확인해주세요.')
			e.preventDefault();
		}
		else if(!price_bool){
			$('.p_price_error').show();
			alert('필수항목을 확인해주세요.')
			e.preventDefault();
		}
		else if(!inven_bool){
			$('.p_inven_error').show();
			alert('필수항목을 확인해주세요.')
			e.preventDefault();
		}
		else if($('#datepicker').val() == ''){
			$('.p_release_error').show();
			alert('필수항목을 확인해주세요.')
			e.preventDefault();
		}
		else if(!image_bool){
			$('.p_image_error').show();
			alert('필수항목을 확인해주세요.')
			e.preventDefault();
		}
		
		
		
	});
	// 등록하기 버튼 눌렀을 경우 유효성 체크 //
	
   });// end of $(document).ready(function(){})------------------------------------

   
   
   
   // 숫자만 있는지 체크하는 함수
   function solution(s) {
	    var regex = /^[0-9]*$/; // 숫자만 체크
	    if(regex.test(s)){
	        return true;
	    }else{
	        return false;
	    }
	}
</script>


<!-- Bootstrap Select JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.14.0-beta3/dist/js/bootstrap-select.min.js"></script>
</body>
</html>