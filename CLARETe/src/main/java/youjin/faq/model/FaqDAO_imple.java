package youjin.faq.model;

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

import faq.domain.FaqVO;
import util.security.AES256;
import util.security.SecretMyKey;

public class FaqDAO_imple implements FaqDAO {
	
	private DataSource ds;  // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다. 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	 // 생성자
	   public FaqDAO_imple() {
	      
	      try {
	         Context initContext = new InitialContext();
	         Context envContext  = (Context)initContext.lookup("java:/comp/env");
	         ds = (DataSource)envContext.lookup("jdbc/semioracle");
	          
				/* aes = new AES256(SecretMyKey.KEY); */
	          // SecretMyKey.KEY 은 우리가 만든 암호화/복호화 키이다.
	          
	      } catch(NamingException e) {
	         e.printStackTrace();
	      } 
	   }
	
	
	
	// 사용한 자원을 반납하는 close() 메소드 생성하기
	private void close() {
		try {
			if(rs    != null) {rs.close();	  rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(conn  != null) {conn.close();  conn=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}// end of private void close()---------------


	
	
	
	// FAQ 업로드
	@Override
	public int faqUpload(FaqVO faq) throws SQLException {
		System.out.println("옴?");
		int result = 0;
		try {
			  conn = ds.getConnection();
			  
			  String sql = " insert into tbl_faq(faq_num, ask, answer) "
			  			+ " values(seq_faq.nextval, ?, ?) "; 
			  
			  pstmt = conn.prepareStatement(sql);
			  
			  pstmt.setString(1, faq.getAsk());
			  pstmt.setString(2, faq.getAnswer());
			  
			  result = pstmt.executeUpdate();
		
			
		} finally {
			close();
		}
			return result;

	}// end of public int faqUpload(faq_VO faq) {}-------------------------


	// FAQ 게시판
	@Override
	public List<FaqVO> faqList() throws SQLException{
		List<FaqVO> faqList = new ArrayList<>();
		
		try {
			System.out.println("faq게시판!");
			conn = ds.getConnection();
			
			String sql = " select faq_num, ask, answer "
					   + " from tbl_faq "
				       + " order by faq_num desc ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FaqVO fvo = new FaqVO();
			
				fvo.setFAQ_num(rs.getInt("faq_num"));
				fvo.setAsk(rs.getString("ask"));
				fvo.setAnswer(rs.getString("answer"));
				
				faqList.add(fvo);
				
			}
					
		}   finally {
			close();
		}
		
		return faqList;
	}


}
