package youjin.qna.model;

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

import faq.domain.FaqVO;
import qna.domain.QnaVO;

public class QnaDAO_imple implements QnaDAO {

	private DataSource ds;  // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다. 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	

	 // 생성자
	   public QnaDAO_imple() {
	      
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

	   
	   
	   
	
	//1:1문의 등록
	@Override
	public int qnaUpload(QnaVO qna) {
		int result = 0;
		try {
			  conn = ds.getConnection();
			  
			  String sql = " insert into tbl_qna(q_num, fk_m_id, q_title, q_ask, q_category) "
			  			+ " values(seq_faq.nextval, ?, ?, ?, ?) "; 
			  
			  pstmt = conn.prepareStatement(sql);
			  
			  pstmt.setString(1, qna.getFk_m_id());
				/* pstmt.setInt(2, qna.getFk_p_num()); */
			  pstmt.setString(2, qna.getQ_title());
			  pstmt.setString(3, qna.getQ_ask());
			  pstmt.setInt(4, qna.getQ_category());
			
		  
			  result = pstmt.executeUpdate();
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			close();
		}
			return result;
	
	}


	// Qna 게시판
	@Override
	public List<QnaVO> qnaList() throws SQLException {
		List<QnaVO> qnaList = new ArrayList<>();
		
		
		try {
			System.out.println("qna게시판!");
			conn = ds.getConnection();
			
			String sql = " select q_num, fk_m_id, q_title, q_ask, q_register, q_category, q_answer, q_answerdate "
					   + " from tbl_qna "
				       + " order by q_num desc ";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				QnaVO qvo = new QnaVO();
			
				
				qvo.setQ_num(rs.getInt("q_num"));
				qvo.setFk_m_id(rs.getString("fk_m_id"));
				qvo.setQ_title(rs.getString("q_title"));
				qvo.setQ_ask(rs.getString("q_ask"));
				qvo.setQ_register(rs.getString("q_register"));
				qvo.setQ_category(rs.getInt("q_category"));
				qvo.setQ_answer(rs.getString("q_answer"));
				qvo.setQ_answerdate(rs.getString("q_answerdate"));

				
				
				qnaList.add(qvo);
				
			}
			
		}  finally {
			close();
		}
		
		return qnaList;
	}

	// 선택한 Q&A 상세하게 보여주기
	@Override
	public QnaVO selectOneQna(int q_num) throws SQLException {
		
		QnaVO qna = null;
		
		try {
			 conn = ds.getConnection();
			 
			 String sql = " SELECT q_register, q_category, q_num, fk_m_id, q_title, q_ask "		 		   
			 		    + " FROM tbl_qna "
			 		    + " WHERE q_num = ? ";
			 		    
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setInt(1, q_num);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 qna = new QnaVO();
				 
				 qna.setQ_register(rs.getString("q_register"));
				 qna.setQ_category(rs.getInt("q_category"));
				 qna.setQ_num(rs.getInt("q_num"));
				 qna.setFk_m_id(rs.getString("fk_m_id"));
				 qna.setQ_title(rs.getString("q_title"));
				 qna.setQ_ask(rs.getString("q_ask"));				 
			 }
			 
			 System.out.println("테스트 = 조회완료");
			
		} catch (SQLException e) {		
			e.printStackTrace();
			
		} finally {
			close();
		}
			return qna;
	}//end of public QnaDAO selectOneQna(String q_num) throws SQLException {}

	
	
	
	// 1:1문의 답변 등록
	@Override
	public int qnaAnswerUpload(QnaVO qvo) throws SQLException {
		
		int result = 0;
		try {
			  conn = ds.getConnection();
			  System.out.println("문의등록 sql 작성중...");
			  String sql = " update tbl_qna set q_answer= ? where q_num = ? "; 
			  
			  pstmt = conn.prepareStatement(sql);
			  
			  pstmt.setString(1, qvo.getQ_answer());
			  pstmt.setInt(2, qvo.getQ_num());
			 
			  result = pstmt.executeUpdate();
			  System.out.println("작성끝!");
			  System.out.println(qvo.getQ_answer());
			  System.out.println(qvo.getQ_num());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			close();
		}
			return result;		
	}
	
	
}	
