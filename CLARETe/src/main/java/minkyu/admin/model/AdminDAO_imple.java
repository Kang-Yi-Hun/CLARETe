package minkyu.admin.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.text.NumberFormat;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cart.domain.CartVO;
import delivery.domain.DeliveryVO;
import member.domain.MemberVO;
import option.domain.OptionVO;
import order.domain.OrderVO;
import orderdetail.domain.orderdetailVO;
import product.domain.ProductVO;
import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;


public class AdminDAO_imple implements AdminDAO {

   private DataSource ds;  // DataSource ds ?? ????占 ??占쎌베?? ??怨듸옙???? DBCP(DB Connection Pool)?占쏙옙??. 
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private AES256 aes;
   
   // ???占쏙옙??
   public AdminDAO_imple() {
      
      try {
         Context initContext = new InitialContext();
          Context envContext  = (Context)initContext.lookup("java:/comp/env");
          ds = (DataSource)envContext.lookup("jdbc/semioracle");
          
          aes = new AES256(SecretMyKey.KEY);
          // SecretMyKey.KEY ?? ?占쎈━占 ? 占 ??? ???占쏙옙??/蹂듸옙?占쏙옙?? ?占쏙옙?占쏙옙??.
          
      } catch(NamingException e) {
         e.printStackTrace();
      } catch(UnsupportedEncodingException e) {
         e.printStackTrace();
      }
   }
   
   
   // ?占쏙옙?占쏙옙?? ?????? 占 ??占쏙옙???? close() 占 ????? ???占쏙옙??占 ?
   private void close() {
      try {
         if(rs    != null) {rs.close();     rs=null;}
         if(pstmt != null) {pstmt.close(); pstmt=null;}
         if(conn  != null) {conn.close();  conn=null;}
      } catch(SQLException e) {
         e.printStackTrace();
      }
   }// end of private void close()---------------
   
   @Override
   public List<MemberVO> SelectAll_member() throws SQLException {
       List<MemberVO> memberList = new ArrayList<>();

       try {
           conn = ds.getConnection();

           String sql = " SELECT m_id, m_name, m_email, m_mobile, m_postcode, m_address, " +
                        " m_detail_address, m_extra, m_gender, m_birth, m_point, " +
                        " m_register, m_lastpwd, m_status, m_idle " +
                        " FROM tbl_member ";

           pstmt = conn.prepareStatement(sql);
           rs = pstmt.executeQuery();

           while (rs.next()) {
               MemberVO member = new MemberVO();
               member.setM_id(rs.getString("m_id"));
               member.setM_name(rs.getString("m_name"));
               member.setM_email(rs.getString("m_email"));
               member.setM_mobile(rs.getString("m_mobile"));
               member.setM_postcode(rs.getString("m_postcode"));
               member.setM_address(rs.getString("m_address"));
               member.setM_detail_address(rs.getString("m_detail_address"));
               member.setM_extra(rs.getString("m_extra"));
               member.setM_gender(rs.getString("m_gender"));
               member.setM_birth(rs.getString("m_birth"));
               member.setM_point(rs.getInt("m_point"));
               member.setM_register(rs.getString("m_register"));
               member.setM_lastpwd(rs.getString("m_lastpwd"));
               member.setM_status(rs.getInt("m_status"));
               member.setM_idle(rs.getInt("m_idle"));

               memberList.add(member);
           }
       } finally {
           close();
       }

       return memberList;
   }

   
   
   // 주문회원조회
   @Override
   public List<OrderVO> SelectOrderMember() throws SQLException {
	   
       List<OrderVO> orderList = new ArrayList<>(); // 주문 정보를 담을 리스트

       try {
           conn = ds.getConnection(); 

           String sql = " SELECT "
	           		  + "   od_count, "
	           		  + "	o_num, "
	           		  + "	fk_m_id, "
	           		  + "	fk_d_num, "
	           		  + "	fk_op_num, "
	           		  + "	o_date, "
	           		  + "	 status, "
	           		  + "	o_price, "
	           		  + "	m_name, "
	           		  + "	m_email, "
	           		  + "	m_mobile, "
	           		  + "	p_name "
	           		  + " FROM tbl_member m "
	           		  + " JOIN tbl_order o ON m.m_id = o.fk_m_id "
	           		  + " join tbl_orderdetail od on od.fk_o_num = o.o_num "
	           		  + " JOIN tbl_product p ON od.fk_p_num = p.p_num ";

           pstmt = conn.prepareStatement(sql); 
           rs = pstmt.executeQuery(); 


           while (rs.next()) {
               OrderVO order = new OrderVO();
               MemberVO member = new MemberVO();
               ProductVO product = new ProductVO();
               orderdetailVO orderdetail = new orderdetailVO();

               // 주문 정보 설정
               order.setO_num(rs.getInt("o_num"));          
               order.setFk_m_id(rs.getString("fk_m_id"));   
               order.setFk_d_num(rs.getInt("fk_d_num"));    
               order.setFk_op_num(rs.getInt("fk_op_num"));  
               order.setO_date(rs.getString("o_date"));     
               order.setStatus(rs.getInt("status"));        
               order.setO_price(rs.getString("o_price"));   
               
               String priceStr = rs.getString("o_price");
               if (priceStr != null && !priceStr.trim().isEmpty()) {
                   // "원" 또는 "," 제거 후 숫자로 변환 및 포맷
                   priceStr = priceStr.replace(",", "").replace("원", "").trim();
                   int price = Integer.parseInt(priceStr);

                   // 콤마 추가된 문자열로 설정
                   order.setO_price(NumberFormat.getInstance().format(price) + "원");
               } else {
                   order.setO_price("0원");
               }

               
               member.setM_name(rs.getString("m_name"));
               member.setM_email(rs.getString("m_email"));
               member.setM_mobile(rs.getString("m_mobile"));
               
               product.setP_name(rs.getString("p_name"));
               
               orderdetail.setOd_count(rs.getInt("od_count"));
               
               
               order.setProductvo(product);
               order.setOrderdetailvo(orderdetail);
               order.setMembervo(member);

               
               orderList.add(order);
           }

       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           close(); 
       }

       return orderList;
   }


   
   // 전체상품조회
	@Override
	public List<ProductVO> ProductAll_member() throws SQLException {
		
		 List<ProductVO> productList = new ArrayList<>();

	       try {
	           conn = ds.getConnection();

	           String sql = " SELECT p_num, p_season, p_name, p_ex, p_price, p_inven, "
		           		  + " TO_CHAR(p_register, 'YYYY-MM-DD') AS p_register, "
		           		  + " TO_CHAR(p_release, 'YYYY-MM-DD') AS p_release, "
		           		  + " p_sale, p_gender "
		           		  + " FROM tbl_product ";
	        		   
	           pstmt = conn.prepareStatement(sql);
	           rs = pstmt.executeQuery();

	           while (rs.next()) {
	               ProductVO product = new ProductVO();
	               
	               product.setP_num(rs.getInt("p_num"));  						//제품고유번호
	               product.setP_season(rs.getString("p_season"));  				//카테고리(계절)
	               product.setP_name(rs.getString("p_name"));  					//제품명
	               product.setP_ex(rs.getString("p_ex")); 						//제품설명
	               
	            // p_price 처리
	               String priceStr = rs.getString("p_price");
	               if (priceStr != null && !priceStr.trim().isEmpty()) {
	                   try {
	                       // "원" 또는 "," 제거 후 숫자로 변환
	                       priceStr = priceStr.replace(",", "").replace("원", "").trim();
	                       product.setP_price(Integer.parseInt(priceStr)); // 숫자로 저장
	                   } catch (NumberFormatException e) {
	                       System.err.println("p_price 변환 실패: " + priceStr);
	                       e.printStackTrace();
	                       product.setP_price(0); // 기본값 설정
	                   }
	               } else {
	                   product.setP_price(0); // 기본값 설정
	               }
	               
	               
	               product.setP_inven(rs.getInt("p_inven"));  					//재고
	               product.setP_register(rs.getString("p_register"));  			//제품등록일
	               product.setP_release(rs.getString("p_release"));  			//발매일
	               product.setP_sale(rs.getString("p_sale"));  					//할인률
	               product.setP_gender(rs.getInt("p_gender"));  				//성별 향수
//	               product.setP_image(rs.getString("p_image"));  				//상품이미지
//	               product.setP_detail_image(rs.getString("p_detail_image"));  	//상품상세이미지

				   productList.add(product);
				}
	           
			} finally {
				close();
			}

			return productList;
		}

}










