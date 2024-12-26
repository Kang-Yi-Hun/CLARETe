package chaeeun.cart.model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cart.controller.Cart;
import cart.domain.CartVO;
import member.domain.MemberVO;
import option.domain.OptionVO;
import product.domain.ProductVO;
import util.security.AES256;
import util.security.SecretMyKey;

public class CartDAO_imple implements CartDAO {
	
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private AES256 aes;
	
	// 생성자
	public CartDAO_imple() {

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/semioracle");

			aes = new AES256(SecretMyKey.KEY);
			// SecretMyKey.KEY 은 우리가 만든 암호화/복호화 키이다.
			//

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
		
	// 사용한 자원을 반납하는 close() 메소드 생성하기
	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// end of private void close()---------------
	
	
	// 장바구니 조회
	@Override
	public List<CartVO> selectCart(String m_id) throws SQLException {

		List<CartVO> cartList = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " SELECT c_num, fk_p_num, fk_op_num, p_name, op_ml, op_price, fk_m_id, c_count "
					   + " FROM ( "
					   + "    SELECT C.c_num, C.fk_p_num, C.fk_op_num, P.p_name, OP.op_ml, OP.op_price, C.fk_m_id, C.c_count "
					   + "    FROM tbl_cart C "
					   + "    JOIN tbl_product P "
					   + "    ON C.fk_p_num = P.p_num "
					   + "    JOIN tbl_option OP "
					   + "    ON C.fk_op_num = OP.op_num "
					   + " ) S "
					   + " WHERE fk_m_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
					
				CartVO cvo = new CartVO();
				
				cvo.setC_num(rs.getInt("c_num"));				// 장바구니번호
				cvo.setFk_p_num(rs.getInt("fk_p_num"));	 		// 제품고유번호
				cvo.setFk_op_num(rs.getInt("fk_op_num"));		// 옵션번호
							
				ProductVO pvo = new ProductVO();
				pvo.setP_name(rs.getString("p_name"));			// 제품명
				cvo.setPvo(pvo);

				OptionVO opvo = new OptionVO();
				opvo.setOp_ml(rs.getString("op_ml"));			// 용량
				opvo.setOp_price(rs.getString("op_price"));		// 가격
				cvo.setOpvo(opvo);
				
				cvo.setFk_m_id(rs.getString("fk_m_id"));		// 회원아이디
				cvo.setC_count(rs.getInt("c_count"));			// 수량

				
				cartList.add(cvo);
			}
			
		} finally {
			close();
		}
		
		return cartList;
		
	} // end of public List<Cart> selectCart(String m_id) throws SQLException----- (장바구니 조회)

}
