/**
 * 
 */

$(document).ready(function(){

    

}) // end of $(document).ready(function()----------------------

function goCart() {

    let checkOK = check();

    if(checkOK == true){
        
        const op_num = $('select[name="selectOption"]').val(); // 선택한 옵션pk
        const p_num = $('input[name="p_num"]').val(); // 상품pk
        const m_id = $('input[name="m_id"]').val(); // 로그인한 id

		console.log(op_num +" , "+ p_num +" , "+ m_id)
		
        if(m_id == 0){
            alert('로그인 후 이용가능한 서비스입니다.');
        }
        else{
            // alert('로그인 했을 경우')
            goAjax(p_num, op_num, m_id);
        }

    }

}

// 유효성 검사 따로 뺌
function check(){
    // console.log($('body > section > div > div.productInfo_contaienr > form > div.option_container > div.select-container > select').val());
    const selectVal = $('body > section > div > div.productInfo_contaienr > form > div.option_container > div.select-container > select').val(); // select 태그 value 값

    if(selectVal == null){ // 선택하지 않았을 경우
        alert('옵션을 선택해주세요.');
        return false;
    }
    else{ // 선택한 경우
        return true;
    }
}


// 장바구니 ajax 따로 뺌
function goAjax(p_num, op_num, m_id){

    $.ajax({
        url:"insertCartJSON.cl",
        data:{"p_num":p_num,   // 상품pk
              "op_num":op_num, // 옵션pk
              "m_id":m_id      // 현재 로그인한 회원id
        },
        type: "post",
        dataType:"json",
        success:function(json){
            if (json.action === "insert") {
                alert("장바구니에 상품이 추가되었습니다.");
            } else if (json.action === "delete") {
                alert("장바구니에서 상품이 삭제되었습니다.");
            } else {
                alert("알 수 없는 작업이 수행되었습니다.");
            }
        },
        error: function(request, status, error){
            alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
        }
    })

}