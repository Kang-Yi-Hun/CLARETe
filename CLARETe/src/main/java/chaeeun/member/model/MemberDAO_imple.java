package chaeeun.member.model;

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

import member.domain.MemberVO;
import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class MemberDAO_imple implements MemberDAO {

   private DataSource ds;  // DataSource ds �� ����移��곗베�� ��怨듯���� DBCP(DB Connection Pool)�대��. 
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private AES256 aes;
   
   // ���깆��
   public MemberDAO_imple() {
      
      try {
         Context initContext = new InitialContext();
          Context envContext  = (Context)initContext.lookup("java:/comp/env");
          ds = (DataSource)envContext.lookup("jdbc/semioracle");
          
          aes = new AES256(SecretMyKey.KEY);
          // SecretMyKey.KEY �� �곕━媛� 留��� ���명��/蹂듯�명�� �ㅼ�대��.
          
      } catch(NamingException e) {
         e.printStackTrace();
      } catch(UnsupportedEncodingException e) {
         e.printStackTrace();
      }
   }
   
   
   // �ъ�⑺�� ������ 諛��⑺���� close() 硫����� ���깊��湲�
   private void close() {
      try {
         if(rs    != null) {rs.close();     rs=null;}
         if(pstmt != null) {pstmt.close(); pstmt=null;}
         if(conn  != null) {conn.close();  conn=null;}
      } catch(SQLException e) {
         e.printStackTrace();
      }
   }// end of private void close()---------------
   
   
   // 紐⑤�� ������ 議고������ 硫�����
   @Override
   public List<MemberVO> SelectAll_member() throws SQLException {
      
      List<MemberVO> memberList = null;
      
      try {
           conn = ds.getConnection();
           
           String sql = " select m_id, m_name, m_email, m_mobile, m_postcode, m_address, m_detail_address, m_extra, m_gender, m_birth, m_point, m_register, m_lastpwd, m_status, m_idle "
                     + " from tbl_member ";
           
           pstmt = conn.prepareStatement(sql);
           
           rs = pstmt.executeQuery();
           
           if(rs.next()) {
              
              memberList = new ArrayList<>();
              
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
           
          
           
      } catch(SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      
      return memberList;
   }// end of public boolean idDuplicateCheck(String userid) throws SQLException------


    // ����媛���
	@Override
	public int registerMember(MemberVO member) throws SQLException {

		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " insert into tbl_member(m_id, m_pwd, m_name, m_email, m_mobile, m_postcode, m_address, m_detail_address, m_extra, m_gender, m_birth) "
					   + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getM_id());
			pstmt.setString(2, Sha256.encrypt(member.getM_pwd()));
			pstmt.setString(3, member.getM_name());
			pstmt.setString(4, aes.encrypt(member.getM_email()));
			pstmt.setString(5, aes.encrypt(member.getM_mobile()));
			pstmt.setString(6, member.getM_postcode());
			pstmt.setString(7, member.getM_address());
			pstmt.setString(8, member.getM_detail_address());
			pstmt.setString(9, member.getM_extra());
			pstmt.setString(10, member.getM_gender());
			pstmt.setString(11, member.getM_birth());
			
			result = pstmt.executeUpdate();
			
		} catch(GeneralSecurityException | UnsupportedEncodingException e) {
			  e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}


	// ���대�� 以�蹂듭껜�� (以�蹂듭�대㈃ true 由ы��, 以�蹂� ����硫� false 由ы��)
	@Override
	public boolean idDuplicateCheck(String m_id) throws SQLException {
		
		boolean isExists = false;
		
		try {
			  conn = ds.getConnection();
			  
			  String sql = " select m_id "
			  		     + " from tbl_member "
			  		     + " where m_id = ? ";
			  
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setString(1, m_id);
			  
			  rs = pstmt.executeQuery();
			  
			  isExists = rs.next(); // ���� ���쇰㈃(以�蹂듬�� userid) true,
			                        // ���� ���쇰㈃(�ъ�⑷��ν�� userid) false
			  
		} finally {
			close();
		}
		
		return isExists;
	}


	// �대��� 以�蹂듦���
	@Override
	public boolean emailDuplicateCheck(String email) throws SQLException {

		boolean isExists = false;
		
		try {
			  conn = ds.getConnection();
			  
			  String sql = " select m_email "
			  		     + " from tbl_member "
			  		     + " where m_email = ? ";
			  
			  pstmt = conn.prepareStatement(sql);
			  pstmt.setString(1, aes.encrypt(email));
			  
			  rs = pstmt.executeQuery();
			  
			  isExists = rs.next(); // ���� ���쇰㈃(以�蹂듬�� userid) true,
			                        // ���� ���쇰㈃(�ъ�⑷��ν�� userid) false
			  
		} catch(GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return isExists;
		
	}


	// 濡�洹몄��
	@Override
	public MemberVO login(Map<String, String> paraMap) throws SQLException {
		
	    MemberVO member = null;

	    try {
	        conn = ds.getConnection();

	        String sql = " select m_id, m_pwd, m_name, m_email, m_mobile, m_postcode, "
	                   + " m_address, m_detail_address, m_extra, m_gender, m_birth "
	                   + " from tbl_member "
	                   + " where m_id = ? and m_pwd = ? ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, paraMap.get("id")); // m_id �� ����
	        pstmt.setString(2, paraMap.get("pwd")); // ���명���� 鍮�諛�踰���

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            member = new MemberVO();
	            member.setM_id(rs.getString("m_id"));
	            member.setM_pwd(rs.getString("m_pwd"));
	            member.setM_name(rs.getString("m_name"));
	            member.setM_email(rs.getString("m_email"));
	            member.setM_mobile(rs.getString("m_mobile"));
	            member.setM_postcode(rs.getString("m_postcode"));
	            member.setM_address(rs.getString("m_address"));
	            member.setM_detail_address(rs.getString("m_detail_address"));
	            member.setM_extra(rs.getString("m_extra"));
	            member.setM_gender(rs.getString("m_gender"));
	            member.setM_birth(rs.getString("m_birth"));
	        }
	    } finally {
	        close();
	    }

	    return member;
	}


	// 아이디찾기
	@Override
	public String findUserid(Map<String, String> paraMap) throws SQLException {
		String m_id = null;
	      
	      try {
	          conn = ds.getConnection();
	          
	          String sql = " select m_id "
	                    + " from tbl_member "
	                    + " where m_status = 1 and m_name = ? and m_email = ? ";
	          
	          pstmt = conn.prepareStatement(sql);
	          pstmt.setString(1, paraMap.get("m_name"));
	          pstmt.setString(2, aes.encrypt(paraMap.get("m_email")) );
	          
	          rs = pstmt.executeQuery();
	          
	          if(rs.next()) {
	             m_id = rs.getString("m_id");
	          }
	          
	      } catch(GeneralSecurityException | UnsupportedEncodingException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      
	      return m_id;
	}


	// 비밀번호찾기1
	@Override
	public boolean isUserExist(Map<String, String> paraMap) throws SQLException {
		
		boolean isUserExist = false;
	      
	      try {
	          conn = ds.getConnection();
	          
	          String sql = " select m_id "
	                    + " from tbl_member "
	                    + " where m_status = 1 and m_id = ? and m_email = ? ";
	          
	          pstmt = conn.prepareStatement(sql);
	          pstmt.setString(1, paraMap.get("m_id"));
	          pstmt.setString(2, aes.encrypt(paraMap.get("m_email")) );
	          
	          rs = pstmt.executeQuery();
	          
	          isUserExist = rs.next();
	          
	      } catch(GeneralSecurityException | UnsupportedEncodingException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      
	      return isUserExist;
	}


	// 비밀번호 찾기
	@Override
	public int pwdUpdate(Map<String, String> paraMap) throws SQLException {

	    int result = 0;

	    try {
	        conn = ds.getConnection();

	        String sql = " update tbl_member set m_pwd = ? "
	        		   + " WHERE m_id = ? ";
	        
	        System.out.println(paraMap.get("new_m_pwd"));
	        System.out.println(paraMap.get("m_id"));
	        
	        pstmt = conn.prepareStatement(sql);

	        pstmt.setString(1, paraMap.get("new_m_pwd"));
	        pstmt.setString(2, paraMap.get("m_id"));

	        result = pstmt.executeUpdate();

	    } finally {
	        close();
	    }

	    return result;
	}


	// 회원탈퇴 메소드
	@Override
	public int memberDelete(Map<String, String> paraMap) throws SQLException {
		int result = 0;
		
	    try {
	        conn = ds.getConnection();

	        String sql = " update tbl_member set m_status = 0 "
	        			+ " where m_id = ? and m_pwd = ? ";

	        pstmt = conn.prepareStatement(sql);

	        pstmt.setString(1, paraMap.get("m_id"));
	        pstmt.setString(2, paraMap.get("m_pwd"));
	        
	        result = pstmt.executeUpdate();

	    } finally {
	        close();
	    }

	    return result;
	}


}