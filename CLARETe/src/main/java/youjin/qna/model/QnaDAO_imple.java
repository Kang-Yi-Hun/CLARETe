package youjin.qna.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
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
		int n = 0;
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
			
		  
			  n = pstmt.executeUpdate();
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			close();
		}
			return n;
	
	}


	// Qna 게시판
	@Override
	public List<QnaVO> qnaList(Map<String, String> paraMap) throws SQLException {
		List<QnaVO> qnaList = new ArrayList<>();
				
		try {
			//System.out.println("qna게시판!");
			conn = ds.getConnection();
			
			String sql = " select t.rno, q_num, fk_m_id, q_title, q_ask, q_register, q_category, q_answer, q_answerdate "
					   + " from  "
					   + " ( "
					   + " select rownum as rno, q_num, fk_m_id, q_title, q_ask, q_register, q_category, q_answer, q_answerdate "
					   + " from  "
					   + " (  "
					   + " select q_num, fk_m_id, q_title, q_ask, q_register, q_category, q_answer, q_answerdate "
					   + " from tbl_qna  "
					   + " where fk_m_id != 'admin'    "
					   + " order by q_answer desc  "
					   + " ) v  "
					   + " ) t  "
					   + " where t.rno between ? and ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
			int sizePerPage = Integer.parseInt(paraMap.get("sizePerPage"));
			
			pstmt.setInt(1, (currentShowPageNo * sizePerPage) - (sizePerPage - 1) ); // 공식 
			pstmt.setInt(2, (currentShowPageNo * sizePerPage));
			
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
		
		int n = 0;
		try {
			  conn = ds.getConnection();
			  System.out.println("문의등록 sql 작성중...");
			  String sql = " update tbl_qna set q_answer= ? where q_num = ? "; 
			  
			  pstmt = conn.prepareStatement(sql);
			  
			  pstmt.setString(1, qvo.getQ_answer());
			  pstmt.setInt(2, qvo.getQ_num());
			 
			  n = pstmt.executeUpdate();
			  System.out.println("작성끝!");
			  System.out.println(qvo.getQ_answer());
			  System.out.println(qvo.getQ_num());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			close();
		}
			return n;		
	}

	
	// 회원 1:1문의 리스트
	@Override
	public List<QnaVO> myQnaList(String fk_m_id) throws SQLException {
		
		List<QnaVO> myQnaList = new ArrayList<>();
		
		try {
			//System.out.println("qna게시판!");
			conn = ds.getConnection();
			
			String sql = " select q_num, fk_m_id, q_title, q_ask, q_register, q_category, q_answer, q_answerdate "
					   + " from tbl_qna "
					   + " where fk_m_id = ? "
				       + " order by q_num desc ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,fk_m_id);
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

				
				
				myQnaList.add(qvo);
				
			}
			
		}  finally {
			close();
		}
		
		return myQnaList;
		
	}


	// 답변등록하기
	@Override
	public int updateAnswer(Map<String, String> paraMap) throws SQLException {

		int n = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " update tbl_qna set q_answer = ?, q_answerdate = to_date(sysdate, 'yyyy/mm/dd') "
					   + " where q_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
		    pstmt.setString(1, paraMap.get("q_answer"));
		    pstmt.setInt(2, Integer.parseInt(paraMap.get("q_num")));
			  
		    n = pstmt.executeUpdate();
		    
		} finally {
			close();
		}
		
		return n;
	} // end of public int updateAnswer(Map<String, String> paraMap) throws SQLException---------------


	// 페이징 처리를 위한 총페이지수 알아오기 //
	@Override
	public int getTotalPage(Map<String, String> paraMap) throws SQLException {
		int totalPage = 0;
	      
	      try {
	          conn = ds.getConnection();
	          
	          String sql = " select ceil(count(*)/?) "
	                   + " from tbl_qna "
	                    + " where fk_m_id = ? "; 
	          
	          pstmt = conn.prepareStatement(sql);
	          
	          pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")));
	          pstmt.setString(2, paraMap.get("m_id"));
	          
	          
	          rs = pstmt.executeQuery();
	          
	          rs.next();
	          
	          totalPage = rs.getInt(1);
	          
	      } finally {
	         close();
	      }
	      
	      return totalPage;
	}
	
	
	
	// 페이징 처리를 위한 총페이지수 알아오기 //
		@Override
		public int getTotalPageA(Map<String, String> paraMap) throws SQLException {
			int totalPage = 0;
		      
		      try {
		          conn = ds.getConnection();
		          
		          String sql = " select ceil(count(*)/?) "
		                   + " from tbl_qna "; 
		          
		          pstmt = conn.prepareStatement(sql);
		          
		          pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")));
		          
		          
		          rs = pstmt.executeQuery();
		          
		          rs.next();
		          
		          totalPage = rs.getInt(1);
		          
		      } finally {
		         close();
		      }
		      
		      return totalPage;
		}


	/* >>> 뷰단(adminBoard.jsp)에서 "페이징 처리시 보여주는 순번 공식" 에서 사용하기 위해 게시물의 총개수 알아오기 시작 <<< */
	@Override
	public int getTotalQnaCount(Map<String, String> paraMap) throws SQLException {
		
		int totalMemberCount = 0;
	      
	      try {
	          conn = ds.getConnection();
	          
	          String sql = " select count(*) "
	                   + " from tbl_qna "
	                    + " where fk_m_id != 'admin' "; 
	          
	          
	          pstmt = conn.prepareStatement(sql);
	          
	          
	          rs = pstmt.executeQuery();
	          
	          rs.next();
	          
	          totalMemberCount = rs.getInt(1);
	          
	      } finally {
	         close();
	      }
	      
	      return totalMemberCount;    
	}


	// 내 자신의 qna 리스트 페이징 조회
	@Override
	public List<QnaVO> select_qna_paging(Map<String, String> paraMap) throws SQLException {
		List<QnaVO> qnaList = new ArrayList<>();
		
		try {
			//System.out.println("qna게시판!");
			conn = ds.getConnection();
			
			String sql = " select t.rno, q_num, fk_m_id, q_title, q_ask, q_register, q_category, q_answer, q_answerdate "
					   + " from  "
					   + " ( "
					   + " select rownum as rno, q_num, fk_m_id, q_title, q_ask, q_register, q_category, q_answer, q_answerdate "
					   + " from  "
					   + " (  "
					   + " select q_num, fk_m_id, q_title, q_ask, q_register, q_category, q_answer, q_answerdate "
					   + " from tbl_qna  "
					   + " where fk_m_id = ?    "
					   + " order by q_answer desc  "
					   + " ) v  "
					   + " ) t  "
					   + " where t.rno between ? and ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
			int sizePerPage = Integer.parseInt(paraMap.get("sizePerPage"));
			
			pstmt.setString(1, paraMap.get("m_id"));
			pstmt.setInt(2, (currentShowPageNo * sizePerPage) - (sizePerPage - 1) ); // 공식 
			pstmt.setInt(3, (currentShowPageNo * sizePerPage));
			
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

	// 자신 문의 삭제
	@Override
	public int qnaDelete(String q_num) throws SQLException {
		int result;
		
		try {
	         conn = ds.getConnection();

	         String sql = " delete from tbl_qna "
	         			+ " where q_num = ? ";

	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, q_num);

	         result = pstmt.executeUpdate();

	      } finally {
	         close();
	      }
		
		return result;
	}
	
	
}	
