package minkyu.admin.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import my.util.MyUtil;
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



	
	
	// 주문 배송 관리 
	@Override
	public List<DeliveryVO> Delivery_list() throws SQLException {
		
		List<DeliveryVO> deliveryList = new ArrayList<>();

	       try {
	           conn = ds.getConnection();

	           String sql = " SELECT "
		           		+ "    d_num, "
		           		+ "    fk_m_id, "
		           		+ "    d_address, "
		           		+ "    d_detail_address, "
		           		+ "    d_postcode, "
		           		+ "    d_extra, "
		           		+ "    d_mobile, "
		           		+ "    d_name "
		           		+ " FROM "
		           		+ "    tbl_delivery ";
	        		   
	           pstmt = conn.prepareStatement(sql);
	           rs = pstmt.executeQuery();

	           while (rs.next()) {
	        	   DeliveryVO delivery = new DeliveryVO();
	               MemberVO member = new MemberVO();
	        	   
	        	   delivery.setD_num(rs.getInt("d_num"));  						
	        	   delivery.setFk_m_id(rs.getString("fk_m_id"));   				
	        	   delivery.setD_address(rs.getString("d_address"));  					
	        	   delivery.setD_detail_address(rs.getString("d_detail_address")); 	
	        	   delivery.setD_postcode(rs.getString("d_postcode")); 	
	        	   delivery.setD_extra(rs.getString("d_extra")); 	
	        	   delivery.setD_mobile(rs.getString("d_mobile")); 	
	        	   delivery.setD_name(rs.getString("d_name")); 	

	        	   deliveryList.add(delivery);
				}
	           
			} finally {
				close();
			}

			return deliveryList;
		}
	
	
	

	
	
	////////////////////////////////////////////////////////////////////////////////// 검색기능 시작
	
	// 모든회원조회 검색 기능
    public List<MemberVO> searchMembers(Map<String, String> paraMap) throws SQLException {
        
    	List<MemberVO> memberList = new ArrayList<>();

    	try {
    		
    		 conn = ds.getConnection();
    		
    		    String sql = " SELECT RNO, m_id, m_name, m_email, m_gender, m_mobile, m_address, m_birth, m_point, m_register "
    	                   + " FROM ( "
    	                   + "  SELECT rownum AS RNO, m_id, m_name, m_email, m_gender, m_mobile, m_address, m_birth, m_point, m_register "
    	                   + "  FROM ( "
    	                   + "    SELECT m_id, m_name, m_email, m_gender, m_mobile, m_address, m_birth, m_point, m_register "
    	                   + "    FROM tbl_member ";
    		
    		 String searchType = paraMap.get("searchType");
    	     String searchWord = paraMap.get("searchWord");
//    	     String memberstatus = paraMap.get("memberstatus");
    	     

//    	     System.out.println(searchWord);
//    	     System.out.println(searchType);
    	     
    	     // 검색어가 있을 때만 WHERE 절 추가
    	        if (!searchType.isBlank() && !searchWord.isBlank()) {
    	            sql += " WHERE " + searchType + " like '%'|| ? ||'%' ";
    	        }
    	        
    	        sql += " order by m_register desc "
    	                +  "  ) V "
    	                +  " ) T "
    	                +  " WHERE T.RNO BETWEEN ? AND ? ";
    	        
//    	        sql = "SELECT * FROM tbl_member WHERE 1=1";
    	        
    	        pstmt = conn.prepareStatement(sql);
    		
    	        /*
                === 페이징처리의 공식 ===
                where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수); 
             */   
             int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
             int sizePerPage = Integer.parseInt(paraMap.get("sizePerPage"));

//             System.out.println(currentShowPageNo);
//             System.out.println(sizePerPage);
             
    	     // 검색어가 있을 경우 파라미터 설정
    	        if (!searchType.isBlank() && !searchWord.isBlank()) {
    	            pstmt.setString(1, searchWord); 
    	            pstmt.setInt(2, (currentShowPageNo * sizePerPage) - (sizePerPage - 1) ); // 공식 
    	            pstmt.setInt(3, (currentShowPageNo * sizePerPage));
    	        }
    	        else {
    	             // 검색이 없는 경우 
    	             pstmt.setInt(1, (currentShowPageNo * sizePerPage) - (sizePerPage - 1) ); // 공식 
    	             pstmt.setInt(2, (currentShowPageNo * sizePerPage));
    	          }

    	     rs = pstmt.executeQuery();
    	     
    	     
                while (rs.next()) {
                	
                    MemberVO member = new MemberVO();
                    
                    member.setM_id(rs.getString("m_id"));
                    member.setM_name(rs.getString("m_name"));
                    member.setM_email(aes.decrypt(rs.getString("m_email")));
                    member.setM_gender(rs.getString("m_gender"));
                    member.setM_mobile(aes.decrypt(rs.getString("m_mobile")));
                    member.setM_address(rs.getString("m_address"));
                    member.setM_birth(rs.getString("m_birth"));
                    member.setM_point(rs.getInt("m_point"));
                    member.setM_register(rs.getString("m_register"));

                    memberList.add(member);
                }
    } 
    	catch(GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
    	}
    	finally {
        close();
     }
     
     return memberList;  
    }


    
    
 // 상품조회 검색기능
	@Override
	public List<ProductVO> searchProduct(Map<String, String> paraMap) throws SQLException {
		
    	List<ProductVO> productList = new ArrayList<>();

    	try {
    		 conn = ds.getConnection();
    		
    		    String sql = " SELECT RNO, p_num, p_season, p_name, p_ex, p_price, p_inven, p_register, p_release, p_sale, p_gender "
	    		    	   + "     FROM ( "
	    		    	   + "     SELECT rownum AS RNO, p_num, p_season, p_name, p_ex, p_price, p_inven, p_register, p_release, p_sale, p_gender "
	    		    	   + "    	   FROM ( "
	    		    	   + "    	   SELECT p_num, p_season, p_name, p_ex, p_price, p_inven, p_register, p_release, p_sale, p_gender "
	    		    	   + "    	   FROM tbl_product ";
    		
    		 String searchType = paraMap.get("searchType");
    	     String searchWord = paraMap.get("searchWord");
    	     
//    	     System.out.println(searchWord);
//    	     System.out.println(searchType);
    	     
    	     // 검색어가 있을 때만 WHERE 절 추가
    	        if (!searchType.isBlank() && !searchWord.isBlank()) {
    	            sql += " WHERE " + searchType + " like '%'|| ? ||'%' ";
    	        }
//    	        sql += " WHERE RNO BETWEEN ? AND ?"; 
    	        
    	        sql += " order by p_gender desc "
	                +  "  ) V "
	                +  " ) T "
	                +  " WHERE T.RNO BETWEEN ? AND ? ";
    	            
    	        pstmt = conn.prepareStatement(sql);

				/*
				   === 페이징처리의 공식 === where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) -
				   (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
				*/
				int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
				int sizePerPage = Integer.parseInt(paraMap.get("sizePerPage"));

    	        // 검색어가 있을 경우 파라미터 설정
    	        if (!searchType.isBlank() && !searchWord.isBlank()) {
    	            pstmt.setString(1, searchWord);
    	            pstmt.setInt(2, (currentShowPageNo * sizePerPage) - (sizePerPage - 1) ); // 공식 
    	            pstmt.setInt(3, (currentShowPageNo * sizePerPage));
    	        }
    	        else {
    	             // 검색이 없는 경우 
    	             pstmt.setInt(1, (currentShowPageNo * sizePerPage) - (sizePerPage - 1) ); // 공식 
    	             pstmt.setInt(2, (currentShowPageNo * sizePerPage));
    	        }

    	        rs = pstmt.executeQuery();
    	     
    	        while (rs.next()) {

    	            ProductVO product = new ProductVO();

    	            product.setP_num(rs.getInt("p_num"));
    	            product.setP_season(rs.getString("p_season"));
    	            product.setP_name(rs.getString("p_name"));
    	            product.setP_ex(rs.getString("p_ex"));
    	            product.setP_price(rs.getInt("p_price"));
    	            product.setP_inven(rs.getInt("p_inven"));
    	            product.setP_register(rs.getString("p_register"));
    	            product.setP_release(rs.getString("p_release"));
    	            product.setP_sale(rs.getString("p_sale"));
    	            product.setP_gender(rs.getInt("p_gender"));

    	            productList.add(product);
    	        }
    	    } 
    	    catch (SQLException e) {
    	        e.printStackTrace();
    	    } 
    	    finally {
    	        close();
    	    }

    	    return productList;  
    	}


	
	
	// 주문회원조회 검색기능
	@Override
	public List<OrderVO> searchOrder(Map<String, String> paraMap) throws SQLException {
		
		List<OrderVO> orderList = new ArrayList<>();

    	try {
    		
    		 conn = ds.getConnection();
    		// RNO SELECT 문으로 바꾸기 
    		 String sql = " SELECT RNO, o_num, o_date, m_id, m_name, m_email, m_mobile, p_name, od_count, o_price, status, fk_op_num, fk_d_num "
    		 		+ " FROM ( "
    		 		+ "    SELECT rownum AS RNO, o_num, o_date, m_id, m_name, m_email, m_mobile, p_name, od_count, o_price, status, fk_op_num, fk_d_num "
    		 		+ "    FROM ( "
    		 		+ "        SELECT "
    		 		+ "            o.o_num, "
    		 		+ "            o.o_date, "
    		 		+ "            m.m_name, "
    		 		+ "            m.m_email, "
    		 		+ "            m.m_mobile, "
    		 		+ "			   m.m_id, "	
    		 		+ "            p.p_name, "
    		 		+ "            od.od_count, "
    		 		+ "            o.o_price, "
    		 		+ "            o.status, "
    		 		+ "            o.fk_op_num, "
    		 		+ "            o.fk_d_num "
    		 		+ "        FROM "
    		 		+ "            tbl_member m "
    		 		+ "        JOIN "
    		 		+ "            tbl_order o ON m.m_id = o.fk_m_id "
    		 		+ "        JOIN  "
    		 		+ "            tbl_orderdetail od ON od.fk_o_num = o.o_num "
    		 		+ "        JOIN  "
    		 		+ "            tbl_product p ON od.fk_p_num = p.p_num ";

    		
    		 String searchType = paraMap.get("searchType");
    	     String searchWord = paraMap.get("searchWord");

    	     if( !searchType.isBlank() && !searchWord.isBlank() ) {
                 sql += " and "+searchType+" like '%'|| ? ||'%' ";
              }
    	     
    	     
    	     // registerday 이 부분 바꾸기 (select문에서 맨 마지막 컬럼으로)
    	     sql += " order by fk_d_num desc "
    	              +  "  ) V "
    	              +  " ) T "
    	              +  " WHERE T.RNO BETWEEN ? AND ? ";
 
    	     pstmt = conn.prepareStatement(sql);

    	     /*
	             === 페이징처리의 공식 ===
	             where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수); 
    	      */   
				int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
				int sizePerPage = Integer.parseInt(paraMap.get("sizePerPage"));

				// if( ( searchType != null && !colname.trim().isEmpty() ) &&
				// ( searchWord != null && !searchWord.trim().isEmpty() ) ) {
				if (!searchType.isBlank() && !searchWord.isBlank()) {
					// 검색이 있는 경우
					pstmt.setString(1, searchWord);
					pstmt.setInt(2, (currentShowPageNo * sizePerPage) - (sizePerPage - 1)); // 공식
					pstmt.setInt(3, (currentShowPageNo * sizePerPage));
				} else {
					// 검색이 없는 경우
					pstmt.setInt(1, (currentShowPageNo * sizePerPage) - (sizePerPage - 1)); // 공식
					pstmt.setInt(2, (currentShowPageNo * sizePerPage));
				}
    	     
				rs = pstmt.executeQuery();
//    	     System.out.println(searchWord);
//    	     System.out.println(searchType);

    	     
    	        while (rs.next()) {
    	        	   OrderVO order = new OrderVO();
    	               MemberVO member = new MemberVO();
    	               ProductVO product = new ProductVO();
    	               orderdetailVO orderdetail = new orderdetailVO();
    	               OptionVO option = new OptionVO();

    	               // 주문 정보 설정
    	               order.setO_num(rs.getInt("o_num"));           // 주문번호
    	               order.setO_date(rs.getString("o_date"));      // 주문날짜
    	               product.setP_name(rs.getString("p_name"));    // 상품명
    	               orderdetail.setOd_count(rs.getInt("od_count"));// 제품수량
    	               order.setO_price(rs.getString("o_price"));    // 주문금액
    	               order.setStatus(rs.getInt("status"));         // 배송현황
    	               order.setFk_op_num(rs.getInt("fk_op_num"));   // 옵션번호
    	               order.setFk_d_num(rs.getInt("fk_d_num"));     // 배송지번호	
    	               member.setM_name(rs.getString("m_name"));
    	               member.setM_email(aes.decrypt(rs.getString("m_email")));
    	               member.setM_mobile(aes.decrypt(rs.getString("m_mobile")));
    	               member.setM_id(rs.getString("m_id"));         // 회원아이디 
    	               
    	               
    	               order.setProductvo(product);
    	               order.setOrderdetailvo(orderdetail);
    	               order.setMembervo(member);
    	               order.setOptionvo(option);
    	               
    	               orderList.add(order);

    	        }

			} catch (GeneralSecurityException | UnsupportedEncodingException e) {
				e.printStackTrace();
			} finally {
				close();
			}

			return orderList;
    	}
	
	
	
	
	
	// 배송주문조회 검색기능
	@Override
	public List<DeliveryVO> searchDelivery(Map<String, String> paraMap) throws SQLException {
		 
		List<DeliveryVO> deliveryList = new ArrayList<>();

	       try {
	           conn = ds.getConnection();

	           String sql = " SELECT RNO, d_num, fk_m_id, d_address, d_detail_address, d_postcode, d_extra, d_mobile, d_name "
		           		 + "    FROM ( "
		           		 + "    SELECT rownum AS RNO, d_num, fk_m_id, d_address, d_detail_address, d_postcode, d_extra, d_mobile, d_name "
		           		 + "    	FROM ( "
		           		 + "       		SELECT d_num, fk_m_id, d_address, d_detail_address, d_postcode, d_extra, d_mobile, d_name "
		           		 + "        	FROM tbl_delivery ";
	           
	           String searchType = paraMap.get("searchType");
	  	       String searchWord = paraMap.get("searchWord");
	        
	  	  // 검색어가 있을 때만 WHERE 절 추가
	  	     if (!searchType.isBlank() && !searchWord.isBlank()) {
	             if ("m_id".equals(searchType)) {
	                 searchType = "fk_m_id"; // 열 이름을 올바르게 변경
	             }
	             sql += "WHERE " + searchType + " LIKE '%' || ? || '%' ";
	         }
		        sql += " order by d_name desc "
	               +  "  ) V "
	               +  " ) T "
	               +  " WHERE T.RNO BETWEEN ? AND ? ";
		            
		        pstmt = conn.prepareStatement(sql);

		        /*
				   === 페이징처리의 공식 === where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) -
				   (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
				*/
				int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
				int sizePerPage = Integer.parseInt(paraMap.get("sizePerPage"));

		        // 검색어가 있을 경우 파라미터 설정
		        if (!searchType.isBlank() && !searchWord.isBlank()) {
		            pstmt.setString(1, searchWord);
		            pstmt.setInt(2, (currentShowPageNo * sizePerPage) - (sizePerPage - 1) ); // 공식 
		            pstmt.setInt(3, (currentShowPageNo * sizePerPage));
		        }
		        else {
		             // 검색이 없는 경우 
		             pstmt.setInt(1, (currentShowPageNo * sizePerPage) - (sizePerPage - 1) ); // 공식 
		             pstmt.setInt(2, (currentShowPageNo * sizePerPage));
		        }

		        rs = pstmt.executeQuery();
	           
	           

	           while (rs.next()) {
	        	   
	        	   DeliveryVO delivery = new DeliveryVO();
	        	   MemberVO member = new MemberVO();
	               
	               delivery.setD_num(rs.getInt("d_num"));  	
	               delivery.setFk_m_id(rs.getString("fk_m_id"));      
	               delivery.setD_address(rs.getString("d_address"));  				
	               delivery.setD_detail_address(rs.getString("d_detail_address"));  					
	               delivery.setD_postcode(rs.getString("d_postcode")); 		
	               delivery.setD_extra(rs.getString("d_extra")); 
	               delivery.setD_mobile(rs.getString("d_mobile")); 
	               delivery.setD_name(rs.getString("d_name")); 

	               deliveryList.add(delivery);
	           }
		    } 
		    catch (SQLException e) {
		        e.printStackTrace();
		    } 
		    finally {
		        close();
		    }

		    return deliveryList;  
		}
	
	
//////////////////////////////////////////////////////////////////////// 페이징처리 시작	

	// 모든회원조회 페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총페이지수 알아오기
	@Override
	public int getTotalPage(Map<String, String> paraMap) throws SQLException {
		
		   int totalPage = 0;
		      
		      try {
		          conn = ds.getConnection();
		          
		          String sql = " select ceil(count(*) / ?) " +
		                  " from tbl_member ";

		     String searchType = paraMap.get("searchType");
		     String searchWord = paraMap.get("searchWord");

		     // 검색 조건이 있는 경우 WHERE 절 추가
		     if (!searchType.isBlank() && !searchWord.isBlank()) { 
		         sql += " WHERE " + searchType + " like '%' || ? || '%' ";
		     }

		     pstmt = conn.prepareStatement(sql);
		     pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")));

		     if (!searchType.isBlank() && !searchWord.isBlank()) { 
		         pstmt.setString(2, searchWord);
		     }

		          
		          rs = pstmt.executeQuery();
		          
		          rs.next();
		          
		          totalPage = rs.getInt(1);
		          
		      } catch(SQLException e) {
		         e.printStackTrace();
		      } finally {
		         close();
		      }
		      
		      return totalPage;
	} // end of public int getTotalPage(Map<String, String> paraMap) throws SQLException {} ----------------------------


	//  모든회원조회 검색이 있는 또는 검색이 없는 회원의 총개수 알아오기 시작
	@Override
	public int getTotalMemberCount(Map<String, String> paraMap) throws SQLException {
		
	    int totalMemberCount = 0;

	    try {
	        conn = ds.getConnection();

	        String sql = "SELECT COUNT(*) " +
	                "FROM tbl_member ";

	   String searchType = paraMap.get("searchType");
	   String searchWord = paraMap.get("searchWord");

	   // 검색 조건이 있는 경우 WHERE 절 추가
	   if (!searchType.isBlank() && !searchWord.isBlank()) { 
	       sql += " WHERE " + searchType + " LIKE '%' || ? || '%' ";
	   }

	   pstmt = conn.prepareStatement(sql);

	   if (!searchType.isBlank() && !searchWord.isBlank()) { 
	       pstmt.setString(1, searchWord);
	   }


	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }

	    return totalMemberCount;
	
	} // end of public int getTotalMemberCount(Map<String, String> paraMap) throws SQLException {} ----------------------------


	
	
	
	// "상품조회" 페이징 처리를 위한 검색이 있는 또는 검색이 없는 상품에 대한 총페이지수 알아오기
	@Override
	public int getProductTotalPage(Map<String, String> paraMap) throws SQLException {
		
		int totalProductPage = 0;
	      
	      try {
	          conn = ds.getConnection();
	          
	          String sql = " select ceil(count(*) / ?) " +
	                  	" from tbl_product ";

	     String searchType = paraMap.get("searchType");
	     String searchWord = paraMap.get("searchWord");

	     // 검색 조건이 있는 경우 WHERE 절 추가
	     if (!searchType.isBlank() && !searchWord.isBlank()) { 
	         sql += " WHERE " + searchType + " like '%' || ? || '%' ";
	     }

	     pstmt = conn.prepareStatement(sql);
	     pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")));

	     if (!searchType.isBlank() && !searchWord.isBlank()) { 
	         pstmt.setString(2, searchWord);
	     }

	          
	          rs = pstmt.executeQuery();
	          
	          rs.next();
	          
	          totalProductPage = rs.getInt(1);
	          
	      } catch(SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      
	      return totalProductPage;
	}

	
	
	
	
	// "상품조회" 검색이 있는 또는 검색이 없는 상품의 총개수 알아오기 시작
	@Override
	public int getTotalProductCount(Map<String, String> paraMap) {
		
		int totalProductCount = 0;

	    try {
	        conn = ds.getConnection();

	        String sql = "SELECT COUNT(*) " +
	                "FROM tbl_product ";

	   String searchType = paraMap.get("searchType");
	   String searchWord = paraMap.get("searchWord");

	   // 검색 조건이 있는 경우 WHERE 절 추가
	   if (!searchType.isBlank() && !searchWord.isBlank()) { 
	       sql += " WHERE " + searchType + " LIKE '%' || ? || '%' ";
	   }

	   pstmt = conn.prepareStatement(sql);

	   if (!searchType.isBlank() && !searchWord.isBlank()) { 
	       pstmt.setString(1, searchWord);
	   }


	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }

	    return totalProductCount;
	}

	

	
	// "주문회원조회" 페이징 처리를 위한 검색이 있는 또는 검색이 없는 주문에 대한 총페이지수 알아오기 
	@Override
	public int getOrderPage(Map<String, String> paraMap) throws SQLException {

		int totalOrderPage = 0;
	      
	      try {
	          conn = ds.getConnection();
	          
	          String sql = " select ceil(count(*) / ?) " +
	                  		" from tbl_order ";

	     String searchType = paraMap.get("searchType");
	     String searchWord = paraMap.get("searchWord");

	     // 검색 조건이 있는 경우 WHERE 절 추가
	     if (!searchType.isBlank() && !searchWord.isBlank()) { 
	         sql += " WHERE " + searchType + " like '%' || ? || '%' ";
	     }

	     pstmt = conn.prepareStatement(sql);
	     pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")));

	     if (!searchType.isBlank() && !searchWord.isBlank()) { 
	         pstmt.setString(2, searchWord);
	     }

	          
	          rs = pstmt.executeQuery();
	          
	          rs.next();
	          
	          totalOrderPage = rs.getInt(1);
	          
	      } catch(SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      
	      return totalOrderPage;
	}


	
	// "주문회원조회" 검색이 있는 또는 검색이 없는 주문의 총개수 알아오기 시작
	@Override
	public int getOrderCount(Map<String, String> paraMap) throws SQLException {

		int totalOrderCount = 0;

	    try {
	        conn = ds.getConnection();

	        String sql = "SELECT COUNT(*) " +
	                	"FROM tbl_order ";

	   String searchType = paraMap.get("searchType");
	   String searchWord = paraMap.get("searchWord");

	   // 검색 조건이 있는 경우 WHERE 절 추가
	   if (!searchType.isBlank() && !searchWord.isBlank()) { 
	       sql += " WHERE " + searchType + " LIKE '%' || ? || '%' ";
	   }

	   pstmt = conn.prepareStatement(sql);

	   if (!searchType.isBlank() && !searchWord.isBlank()) { 
	       pstmt.setString(1, searchWord);
	   }


	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }

	    return totalOrderCount;
	}



	
	
	
	
	
	
	
	
	
	
	


	
	
	// "주문배송조회" 페이징 처리를 위한 검색이 있는 또는 검색이 없는 주문에 대한 총페이지수 알아오기 
	@Override
	public int getDeliveryPage(Map<String, String> paraMap) throws SQLException {

	    int totalDeliveryPage = 0;

	    try {
	        conn = ds.getConnection();

	        String sql = "SELECT ceil(count(*) / ?) " +
	                     "FROM tbl_delivery ";

	        String searchType = paraMap.get("searchType");
	        String searchWord = paraMap.get("searchWord");

	        // 검색 조건이 있는 경우 WHERE 절 추가
	        if (!searchType.isBlank() && !searchWord.isBlank()) {
	            if ("m_id".equals(searchType)) {
	                searchType = "fk_m_id"; // 열 이름을 올바르게 변경
	            }
	            sql += "WHERE " + searchType + " LIKE '%' || ? || '%'";
	        }

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")));

	        if (!searchType.isBlank() && !searchWord.isBlank()) {
	            pstmt.setString(2, searchWord);
	        }

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            totalDeliveryPage = rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }

	    return totalDeliveryPage;
	}


	
	// "주문배송조회" 검색이 있는 또는 검색이 없는 주문의 총개수 알아오기 시작
	@Override
	public int getDeliveryCount(Map<String, String> paraMap) throws SQLException {
		
		int totalOrderCount = 0;

	    try {
	        conn = ds.getConnection();

	        String sql = "SELECT COUNT(*) " +
	                	"FROM tbl_delivery ";

	   String searchType = paraMap.get("searchType");
	   String searchWord = paraMap.get("searchWord");

	   // 검색 조건이 있는 경우 WHERE 절 추가
	   if (!searchType.isBlank() && !searchWord.isBlank()) { 
	       sql += " WHERE " + searchType + " LIKE '%' || ? || '%' ";
	   }

	   pstmt = conn.prepareStatement(sql);

	   if (!searchType.isBlank() && !searchWord.isBlank()) { 
	       pstmt.setString(1, searchWord);
	   }


	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close();
	    }

	    return totalOrderCount;
	}



	}

	



