package yihun.cart.model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
	
	   
	   
	// 회원이 장바구니에 넣었었는지 상태 체크
	@Override
	public boolean cartCheckInsert(String m_id, String op_num, String p_num) throws SQLException {
		
		boolean isExists = false;

	      try {
	         conn = ds.getConnection();

	         String sql = " select * "
	         			+ " from tbl_cart "
	         			+ " where fk_m_id = ? and fk_p_num = ? and fk_op_num = ? ";

	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, m_id);
	         pstmt.setString(2, p_num);
	         pstmt.setString(3, op_num);

	         rs = pstmt.executeQuery();

	         isExists = rs.next(); // 행이 있으면(중복된 userid) true,
	                           	   // 행이 없으면(사용가능한 userid) false

	      } finally {
	         close();
	      }

	      return isExists;
	} // end of public boolean cartCheckInsert(String m_id) throws SQLException--------

	
	// 회원이 장바구니에 새로 넣는 거라면 insert
	@Override
	public int cartInsert(String m_id, String op_num, String p_num) throws SQLException{
		int result;
		
		try {
	         conn = ds.getConnection();

	         String sql = " insert into tbl_cart(c_num, fk_p_num, fk_op_num, fk_m_id, c_count) values(seq_cart.nextval, ?, ?, ?, 1) ";

	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, p_num);
	         pstmt.setString(2, op_num);
	         pstmt.setString(3, m_id);

	         result = pstmt.executeUpdate();

	      } finally {
	         close();
	      }
		
		return result;
	}

	
	// 회원이 장바구니에 넣었었다면 delete
	@Override
	public int cartDelete(String m_id, String op_num, String p_num) throws SQLException {
		int result;
		
		try {
	         conn = ds.getConnection();

	         String sql = " delete from tbl_cart "
	         			+ " where fk_m_id = ? and fk_p_num = ? and fk_op_num = ? ";

	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, m_id);
	         pstmt.setString(2, p_num);
	         pstmt.setString(3, op_num);

	         result = pstmt.executeUpdate();

	      } finally {
	         close();
	      }
		
		return result;
	}

}
