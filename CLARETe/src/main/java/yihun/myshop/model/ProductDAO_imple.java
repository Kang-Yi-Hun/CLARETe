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
		
		String selectStatus = paraMap.get("selectVal");
		
		// System.out.println(paraMap.get("selectVal"));
		
		try {
			 conn = ds.getConnection();
			 
			 // System.out.println(paraMap.get("cname"));
			 // System.out.println(paraMap.get("start"));
			 // System.out.println(paraMap.get("end"));
			
			 if("판매순".equals(paraMap.get("selectVal"))) { // 판매순은 쿼리문 달라서 이렇게 처리
				 String sql = " select rno, p_num, p_name, p_season, p_image, p_sale, p_price, p_register "
				 			+ " from ( "
				 			+ "     select row_number() over (order by nvl(sum(o.o_cnt), 0) desc) as rno,  "
				 			+ "         p.p_num, p.p_name, p.p_season, p.p_image, p.p_sale, p.p_price, p.p_register, "
				 			+ "         nvl(sum(o.o_cnt), 0) as total_sales  "
				 			+ "     from tbl_product p "
				 			+ "     left outer join tbl_option opt on p.p_num = opt.fk_p_num "
				 			+ "     left outer join tbl_order o on opt.op_num = o.fk_op_num ";

				 if("0".equals(paraMap.get("cname"))) {
					 // 전체 all 에서의 판매순 정렬
					 sql += " group by p.p_num, p.p_name, p.p_season, p.p_image, p.p_sale, p.p_price, p.p_register "
					 	  + " ) v "
					 	  + " where rno between ? and ? "
					 	  + " order by rno desc ";
					 
					 pstmt = conn.prepareStatement(sql);
					 
					 pstmt.setInt(1, Integer.parseInt(paraMap.get("start")));
					 pstmt.setInt(2, Integer.parseInt(paraMap.get("end")));
					 
				 }
				 else if("5".equals(paraMap.get("cname")) || "6".equals(paraMap.get("cname"))  || "7".equals(paraMap.get("cname"))) {
					 // 남성, 여성, 공용에서의 판매순 정렬
					 int cname;
					 
					 if("5".equals(paraMap.get("cname"))) {
							 cname = 1;
					 }
					 else if("6".equals(paraMap.get("cname"))) {
							 cname = 2;
					 }
					 else {
							 cname = 0;
					 }
					 
					 sql += " where p_gender = ? "
					 	  + " group by p.p_num, p.p_name, p.p_season, p.p_image, p.p_sale, p.p_price, p.p_register "
					 	  + " ) v "
					 	  + " where rno between ? and ? "
					 	  + " order by rno desc ";
					 
					 pstmt = conn.prepareStatement(sql);
					 
					 pstmt.setInt(1, cname);
					 pstmt.setInt(2, Integer.parseInt(paraMap.get("start")));
					 pstmt.setInt(3, Integer.parseInt(paraMap.get("end")));
					 
				 }
				 else {
					 // 봄, 여름, 가을, 겨울에서의 판매순 정렬
					 sql += " where p_season = ? "
					 	  + " group by p.p_num, p.p_name, p.p_season, p.p_image, p.p_sale, p.p_price, p.p_register "
					 	  + " ) v "
					 	  + " where rno between ? and ? "
					 	  + " order by rno desc ";
					 
					 pstmt = conn.prepareStatement(sql);
					 
					 pstmt.setInt(1, Integer.parseInt(paraMap.get("cname")));
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
				 
			 }
			 else {
			 
				 String sql = " SELECT p_num, p_name, p_season, p_image, p_sale, p_price, p_register, p_gender "
				 		    + " FROM  "
				 		    + " ( "
						    + "     SELECT row_number() over(order by p_num desc) AS RNO "
						    + "          , p_num, p_name, p_season, p_image, p_sale, p_price, p_gender "
						    + "          , to_char(p_register, 'yyyy-mm-dd') AS p_register "
						    + "     FROM tbl_product ";
						   
				 if("0".equals(paraMap.get("cname"))) {
					 // 전체 all 을 클릭한 경우
					sql += " ) V "
						 + " WHERE rno between ? and ? "; 
					
					switch (selectStatus) {
					case "신상품순":
						sql += " order by p_register desc ";
						break;
						
					case "높은금액순":
						sql += " order by p_price desc ";
						break;
	
					case "낮은금액순":
						sql += " order by p_price asc ";
						break;
					} // end of switch (selectStatus)------------ 
					
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, Integer.parseInt(paraMap.get("start")));
					pstmt.setInt(2, Integer.parseInt(paraMap.get("end")));
				 }
				 else if("5".equals(paraMap.get("cname")) || "6".equals(paraMap.get("cname"))  || "7".equals(paraMap.get("cname"))) {
					 
					 int cname;
					 
					 if("5".equals(paraMap.get("cname"))) {
						 cname = 1;
					 }
					 else if("6".equals(paraMap.get("cname"))) {
						 cname = 2;
					 }
					 else {
						 cname = 0;
					 }
					 
					 // 남자 또는 여자를 선택한 경우
					 sql += " WHERE p_gender = ? "
					         + " ) V "
					   	     + " WHERE rno between ? and ? ";
						
					 switch (selectStatus) {
					 case "신상품순":
						 sql += " order by p_register desc ";
						 break;
						
					 case "높은금액순":
						 sql += " order by p_price desc ";
						 break;
	
					 case "낮은금액순":
						 sql += " order by p_price asc ";
						 break;
					 } // end of switch (selectStatus)------------
					
					 pstmt = conn.prepareStatement(sql);
	
					 pstmt.setInt(1, cname);
					 pstmt.setInt(2, Integer.parseInt(paraMap.get("start")));
					 pstmt.setInt(3, Integer.parseInt(paraMap.get("end")));
				 }
				 else {
					// 카테고리 봄, 여름, 가을, 겨울 줄 하나를 클릭한 경우
					sql += " WHERE p_season = ? "
				         + " ) V "
				   	     + " WHERE rno between ? and ? ";
					
					switch (selectStatus) {
					case "신상품순":
						sql += " order by p_register desc ";
						break;
						
					case "높은금액순":
						sql += " order by p_price desc ";
						break;
	
					case "낮은금액순":
						sql += " order by p_price asc ";
						break;
					} // end of switch (selectStatus)------------
					
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
			}
			
		} finally {
			close();
		}
		
		return productList;
	} // end of public List<ProductVO> selectProduct(Map<String, String> paraMap) throws SQLException-----------

	
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
           else if("5".equals(fk_snum) || "6".equals(fk_snum) || "7".equals(fk_snum) ) {
        	   int cname;
				 
			   if("5".equals(fk_snum)) {
					 cname = 1;
			   }
			   else if("6".equals(fk_snum)) {
					 cname = 2;
			   }
			   else {
					 cname = 0;
			   }
			   
			   sql += " where p_gender = ? ";
			   pstmt = conn.prepareStatement(sql);
			   
			   pstmt.setInt(1, cname);
			   
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
	} // end of public int totalPspecCount(String fk_snum) throws SQLException------------

	
	// 상품 상세페이지의 정보를 알아온다
	@Override
	public ProductVO selectProductOne(int p_num) throws SQLException {
		
		ProductVO pvo = new ProductVO();
	      
        try {
        	conn = ds.getConnection();
        	
        	String sql = " select p_num, p_season, p_name, p_ex, p_inven, p_sale, p_register, p_release, p_gender, p_image, p_detail_image, p_price "
        			   + " from tbl_product "
        			   + " where p_num = ? ";
        	
        	pstmt = conn.prepareStatement(sql);
        	
        	pstmt.setInt(1, p_num);
        	
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()) {
        		pvo = new ProductVO();
        		
        		pvo.setP_num(rs.getInt("p_num"));
        		pvo.setP_season(rs.getString("p_season"));
        		pvo.setP_name(rs.getString("p_name"));
        		pvo.setP_ex(rs.getString("p_ex"));
        		pvo.setP_inven(rs.getInt("p_inven"));
        		pvo.setP_sale(rs.getString("p_sale"));
        		pvo.setP_register(rs.getString("p_register"));
        		pvo.setP_release(rs.getString("p_release"));
        		pvo.setP_gender(rs.getInt("p_gender"));
        		pvo.setP_image(rs.getString("p_image"));
        		pvo.setP_detail_image(rs.getString("p_detail_image"));
        		pvo.setP_price(rs.getInt("p_price"));
        		
        	}
        	// System.out.println("할인가 확인 : " + pvo.getP_sale());
        	// System.out.println("할인가 확인 : " + pvo.getDiscountPrice());
        } finally {
           close();
        }
      
      return pvo;
	}

}
