package delivery.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import delivery.domain.DeliveryVO;

public class DeliveryDAO_imple implements DeliveryDAO {
	private DataSource ds;  // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다. 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public DeliveryDAO_imple(){
		
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/semioracle");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	
	
}
	
	private void close() {
		try {
			if(rs    != null) {rs.close();	  rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(conn  != null) {conn.close();  conn=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}// end of private void close()---------------

	
	
	// == --------- 배송지 입력을 위한 메소드 --------- == //
	@Override
	public int InsertDelivery(DeliveryVO delivery) throws SQLException {
		
		int result = 0;
		
		//2024_12_16 수정 중
		try {
			
				conn = ds.getConnection();
		
			 
			String sql= " insert into tbl_delivery(d_num,   d_name, d_address, d_detail_address, d_postcode, d_extra, d_mobile, fk_m_id) "
					  + "       values(seq_delivery.NEXTVAL,?,      ?,         ?,                ?,?,?,'leess') ";
			
			pstmt=conn.prepareStatement(sql);
			System.out.println(delivery.getD_postcode());

			pstmt.setString(1, delivery.getD_name());
			pstmt.setString(2, delivery.getD_address());
			pstmt.setString(3, delivery.getD_detail_address());
			pstmt.setString(4, delivery.getD_postcode());
			pstmt.setString(5, delivery.getD_extra());
			pstmt.setString(6, delivery.getD_mobile());
			
			// pstmt.setString(7, delivery.getFk_m_id());
			
			
			result = pstmt.executeUpdate();
			
			
		} catch(SQLException e) {
		 e.printStackTrace();
		} finally {
			close();
		}
		
		
		return result;
	}
	
}
