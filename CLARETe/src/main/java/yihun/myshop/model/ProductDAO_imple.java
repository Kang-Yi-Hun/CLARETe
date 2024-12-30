package yihun.myshop.model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
          ds = (DataSource)envContext.lookup("jdbc/semioracle");
          
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
	
	
	
	// ajax로 받아온 값을 조회하는 메소드
	@Override
	public List<ProductVO> selectProduct(Map<String, String> paraMap) throws SQLException {
		
		List<ProductVO> productList = new ArrayList<>();
		
		try {
			 conn = ds.getConnection();
			 
			 // System.out.println(paraMap.get("cname"));
			 // System.out.println(paraMap.get("start"));
			 // System.out.println(paraMap.get("end"));
			
			String sql = " SELECT p_num, p_name, p_season, p_image, p_sale, p_price, p_register "
					   + " FROM  "
					   + " ( "
					   + "     SELECT row_number() over(order by p_num desc) AS RNO "
					   + "          , p_num, p_name, p_season, p_image, p_sale, p_price "
					   + "          , to_char(p_register, 'yyyy-mm-dd') AS p_register "
					   + "     FROM tbl_product ";
					   
			
			
			
			 if("0".equals(paraMap.get("cname"))) {
				 // 전체 all 을 클릭한 경우
				sql += " ) V "
					 + " WHERE rno between ? and ? "; 
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, Integer.parseInt(paraMap.get("start")));
				pstmt.setInt(2, Integer.parseInt(paraMap.get("end")));
			 }
			 else {
				// 카테고리 봄, 여름, 가을, 겨울 줄 하나를 클릭한 경우
				sql += " WHERE p_season = ? "
			         + " ) V "
			   	     + " WHERE rno between ? and ? ";
				 
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, paraMap.get("cname"));
				pstmt.setInt(2, Integer.parseInt(paraMap.get("start")));
				pstmt.setInt(3, Integer.parseInt(paraMap.get("end")));
			 }

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ProductVO pvo = new ProductVO();
				
				// p_num, p_name, p_season, p_image, p_sale, p_price, p_register
				
				pvo.setP_num(rs.getInt("p_num"));				// 향수번호
				pvo.setP_name(rs.getString("p_name"));			// 향수명
				pvo.setP_image(rs.getString("p_image"));		// 향수 이미지
				pvo.setP_price(rs.getInt("p_price"));			// 가격
				
				productList.add(pvo);
				
			} // end of while(rs.next())-------
			
		} finally {
			close();
		}
		
		return productList;
	}

	
	// 카테고리 상품의 전체개수를 알아온다.
	@Override
	public int totalPspecCount(String fk_snum) throws SQLException {
		int totalCount = 0;
	      
        try {
           conn = ds.getConnection();
           
           String sql = " select count(*) "
                      + " from tbl_product ";
         
           if("0".equals(fk_snum)) {
        	// 전체 all 을 클릭한 경우의 전체 개수
        	   sql += "";
        	   pstmt = conn.prepareStatement(sql);
           }
           else {
        	// 카테고리 봄, 여름, 가을, 겨울 줄 하나를 클릭한 경우의 개수
        	   sql += " where p_season = ? ";
        	   pstmt = conn.prepareStatement(sql);
        	   
        	   pstmt.setString(1, fk_snum);
           }
           
           rs = pstmt.executeQuery();
         
           rs.next();
         
           totalCount = rs.getInt(1);
         
        } finally {
           close();
        }
      
      return totalCount;
	}

}
