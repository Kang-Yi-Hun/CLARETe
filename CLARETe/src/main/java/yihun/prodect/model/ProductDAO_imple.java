package yihun.prodect.model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import product.domain.ProductVO;
import util.security.AES256;
import util.security.SecretMyKey;

public class ProductDAO_imple implements ProductDAO {

	private DataSource ds;  // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다. 
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    private AES256 aes;
	
    // 생성자
    public ProductDAO_imple() {
       
       try {
          Context initContext = new InitialContext();
           Context envContext  = (Context)initContext.lookup("java:/comp/env");
           ds = (DataSource)envContext.lookup("jdbc/myoracle");
           
           aes = new AES256(SecretMyKey.KEY);
           // SecretMyKey.KEY 은 우리가 만든 암호화/복호화 키이다.
           
       } catch(NamingException e) {
          e.printStackTrace();
       } catch(UnsupportedEncodingException e) {
          e.printStackTrace();
       }
    }
    
    
    // 사용한 자원을 반납하는 close() 메소드 생성하기
    private void close() {
       try {
          if(rs    != null) {rs.close();     rs=null;}
          if(pstmt != null) {pstmt.close(); pstmt=null;}
          if(conn  != null) {conn.close();  conn=null;}
       } catch(SQLException e) {
          e.printStackTrace();
       }
    }// end of private void close()---------------
    
    
    
	// 상품등록을 해주는 메소드
	@Override
	public int insertProduct(ProductVO pvo) throws SQLException {
		int result = 0;

		try {
			conn = ds.getConnection();
			
			String sql = " insert into tbl_product(p_num, p_season, p_name, p_ex, p_price, p_inven, p_sale, p_gender, p_release, p_image, p_detail_image, p_register) "
					   + " values(seq_product.nextval, ?, ?, ?, ?, ?, ?, ?, to_date('?', 'yyyymmdd'), ?, ?, sysdate) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pvo.getP_season()); 
			pstmt.setString(2, pvo.getP_name());
			pstmt.setString(3, pvo.getP_ex());
			pstmt.setString(4, pvo.getP_price());
			pstmt.setInt(5, pvo.getP_inven());
			pstmt.setString(6, pvo.getP_sale());
			pstmt.setInt(7, pvo.getP_gender());
			pstmt.setString(8, pvo.getP_release());
			pstmt.setString(9, pvo.getP_image());
			pstmt.setString(10, pvo.getP_detail_image());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		
		return result;
	}

}
