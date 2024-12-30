package yihun.cart.model;

import java.sql.SQLException;

public interface CartDAO {

	// 회원이 장바구니에 넣었었는지 상태 체크
	boolean cartCheckInsert(String m_id, String op_num, String p_num) throws SQLException;

	// 회원이 장바구니에 새로 넣는 거라면 insert
	int cartInsert(String m_id, String op_num, String p_num) throws SQLException;

	// 회원이 장바구니에 넣었었다면 delete
	int cartDelete(String m_id, String op_num, String p_num) throws SQLException;

}
