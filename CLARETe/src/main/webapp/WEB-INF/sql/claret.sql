select * from tbl_member;

select * from tbl_product;

select * from tbl_log;

select * from tbl_order;

select * from tbl_option;

select * from tbl_cart;

commit;


-- 로그인 기록
SELECT m_id, m_name, m_point, m_lastpwd, NVL( lastlogingap, TRUNC( months_between(sysdate, m_register)) ) AS lastlogingap, 
m_idle, m_email, m_mobile, m_postcode, m_address, m_detail_address, m_extra 
FROM 
(
    SELECT m_id, m_name, m_point,
    trunc( months_between(sysdate, m_lastpwd) ) AS pwdchangegap, 
    m_register, m_idle, m_email, m_mobile, m_postcode, m_address, m_detail_address, m_extra 
    FROM tbl_member WHERE m_status = 1 AND m_id = 'codms' and m_pwd = 'qwer1234$' 
) M
CROSS JOIN 
( 
select TRUNC( months_between(sysdate, MAX(l_logindate))) AS lastlogingap
FROM tbl_log
WHERE fk_m_id = 'codms'
) H ;


-- 장바구니에 임의값 넣기
insert into tbl_cart (c_num, fk_p_num, fk_op_num, fk_m_id, c_count)
values (seq_cart.nextVal, 643, 53, 'gold12', 2);

-- 장바구니 조회 (selectCart)
select * from tbl_cart where fk_m_id = 'gold12';

-- 장바구니 조회 (selectCart) 조인해서 상품값 얻어오기
SELECT c_num, fk_p_num, fk_op_num, p_name, op_ml, op_price, fk_m_id, c_count
FROM (
    SELECT C.c_num, C.fk_p_num, C.fk_op_num, P.p_name, OP.op_ml, OP.op_price, C.fk_m_id, C.c_count
    FROM tbl_cart C
    JOIN tbl_product P
    ON C.fk_p_num = P.p_num
    JOIN tbl_option OP
    ON C.fk_op_num = OP.op_num
) S
WHERE 
    fk_m_id = 'gold12';



