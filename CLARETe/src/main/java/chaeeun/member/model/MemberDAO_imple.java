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

	private DataSource ds; // DataSource ds 占쏙옙 占쏙옙占쏙옙치占쏙옙캣占쏙옙 占쏙옙占쏙옙占싹댐옙 DBCP(DB Connection Pool)占싱댐옙.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private AES256 aes;

	// 占쏙옙占쏙옙占쏙옙
	public MemberDAO_imple() {

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/semioracle");

			aes = new AES256(SecretMyKey.KEY);
			// SecretMyKey.KEY 占쏙옙 占쎌리占쏙옙 占쏙옙占쏙옙 占쏙옙호화/占쏙옙호화 키占싱댐옙.
			//

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	// 占쏙옙占쏙옙占� 占쌘울옙占쏙옙 占쌥놂옙占싹댐옙 close() 占쌨소듸옙 占쏙옙占쏙옙占싹깍옙
	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// end of private void close()---------------

	// 占쏙옙占� 회占쏙옙占쏙옙 占쏙옙회占싹댐옙 占쌨소듸옙
	@Override
	public List<MemberVO> SelectAll_member() throws SQLException {

		List<MemberVO> memberList = null;

		try {
			conn = ds.getConnection();

			String sql = " select m_id, m_name, m_email, m_mobile, m_postcode, m_address, m_detail_address, m_extra, m_gender, m_birth, m_point, m_register, m_lastpwd, m_status, m_idle "
					+ " from tbl_member ";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return memberList;
	}// end of public boolean idDuplicateCheck(String userid) throws
		// SQLException------

	// 회占쏙옙占쏙옙占쏙옙
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

		} catch (GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}

	// 占쏙옙占싱듸옙 占쌩븝옙체크 (占쌩븝옙占싱몌옙 true 占쏙옙占쏙옙, 占쌩븝옙 占싣니몌옙 false 占쏙옙占쏙옙)
	@Override
	public boolean idDuplicateCheck(String m_id) throws SQLException {

		boolean isExists = false;

		try {
			conn = ds.getConnection();

			String sql = " select m_id " + " from tbl_member " + " where m_id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);

			rs = pstmt.executeQuery();

			isExists = rs.next(); // 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙(占쌩븝옙占쏙옙 userid) true,
									// 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙(占쏙옙諛∽옙占쏙옙占� userid) false

		} finally {
			close();
		}

		return isExists;
	}

	// 占싱몌옙占쏙옙 占쌩븝옙占싯삼옙
	@Override
	public boolean emailDuplicateCheck(String email) throws SQLException {

		boolean isExists = false;

		try {
			conn = ds.getConnection();

			String sql = " select m_email " + " from tbl_member " + " where m_email = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aes.encrypt(email));

			rs = pstmt.executeQuery();

			isExists = rs.next(); // 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙(占쌩븝옙占쏙옙 userid) true,
									// 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙(占쏙옙諛∽옙占쏙옙占� userid) false

		} catch (GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return isExists;

	}

	// 占싸깍옙占쏙옙
	@Override
	public MemberVO login(Map<String, String> paraMap) throws SQLException {

		MemberVO member = null;

		try {
			conn = ds.getConnection();

			String sql = " SELECT m_id, m_pwd, m_name, m_point, pwdchangegap, NVL( lastlogingap, TRUNC( months_between(sysdate, m_register)) ) AS lastlogingap, "
					   + " m_idle, m_email, m_mobile, m_postcode, m_address, m_detail_address, m_extra "
					   + " FROM "
					   + " ( "
					   + "    SELECT m_id, m_pwd, m_name, m_point, "
					   + "    trunc( months_between(sysdate, m_lastpwd) ) AS pwdchangegap, "
					   + "    m_register, m_idle, m_email, m_mobile, m_postcode, m_address, m_detail_address, m_extra "
					   + "    FROM tbl_member WHERE m_status = 1 AND m_id = ? and m_pwd = ? "
					   + " ) M "
					   + " CROSS JOIN "
					   + " ( "
					   + " select TRUNC( months_between(sysdate, MAX(l_logindate))) AS lastlogingap "
					   + " FROM tbl_log "
					   + " WHERE fk_m_id = ? "
					   + " ) H ";

			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("id")); // m_id 키 확占쏙옙
			pstmt.setString(2, Sha256.encrypt(paraMap.get("pwd"))); // 占쏙옙호화占쏙옙 占쏙옙橘占싫�
			pstmt.setString(3, paraMap.get("id")); // m_id 키 확占쏙옙
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberVO();

				member.setM_id(rs.getString("m_id"));
				member.setM_pwd(rs.getString("m_pwd"));
				member.setM_name(rs.getString("m_name"));
				member.setM_point(rs.getInt("m_point"));
				
						
				// 占쏙옙占쏙옙占쏙옙 占싸깍옙占쏙옙 1占쏙옙 占싱삼옙占싱몌옙 占쌨몌옙
				if (rs.getInt("pwdchangegap") >= 12) {
					member.setM_idle(0);

					if (rs.getInt("m_idle") == 1) {
						sql = " update tbl_member set m_idle = 1 " + " where m_id = ? ";

						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, paraMap.get("m_id"));

						pstmt.executeUpdate();
					}
				}

				// 占쌨몌옙 占싣댐옙 회占쏙옙占쏙옙 tbl_log占쏙옙 insert
				if (rs.getInt("lastlogingap") < 12) {

					sql = " insert into tbl_log(l_num, fk_m_id, l_ip) " + " values(seq_log.nextval, ?, ?) ";

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, paraMap.get("id"));
					pstmt.setString(2, paraMap.get("clientip"));

					pstmt.executeUpdate();

					if (rs.getInt("pwdchangegap") >= 3) {
						// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙호占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙짜占쏙옙 占쏙옙占쏙옙챨占쏙옙占쏙옙占� 占쏙옙占쏙옙 3占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 true
						// 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙호占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙짜占쏙옙 占쏙옙占쏙옙챨占쏙옙占쏙옙占� 占쏙옙占쏙옙 3占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占십억옙占쏙옙占쏙옙 false

						member.setRequirePwdChange(true); // 占싸깍옙占싸쏙옙 占쏙옙호占쏙옙 占쏙옙占쏙옙占쌔띰옙占� alert 占쏙옙 占쏙옙理듸옙占� 占쌀띰옙 占쏙옙占쏙옙磯占�.
					}
				}
				
				member.setM_email(aes.decrypt(rs.getString("m_email")));
				member.setM_mobile(aes.decrypt(rs.getString("m_mobile")));
				member.setM_postcode(rs.getString("m_postcode"));
				member.setM_address(rs.getString("m_address"));
				member.setM_detail_address(rs.getString("m_detail_address"));
				member.setM_extra(rs.getString("m_extra"));

				member.setM_idle(rs.getInt("m_idle")); 
			}
		} catch(GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return member;
	}
	
	// 占쏙옙占싱듸옙찾占쏙옙
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


	   // 占쏙옙橘占싫Ｃｏ옙占�1
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


	   // 占쏙옙橘占싫� 찾占쏙옙
	   @Override
	   public int pwdUpdate(Map<String, String> paraMap) throws SQLException {

	       int result = 0;

	       try {
	           conn = ds.getConnection();

	           String sql = " update tbl_member set m_pwd = ?, m_lastpwd = sysdate "
	                    + " where m_id = ? ";
	           
	           pstmt = conn.prepareStatement(sql);

	           pstmt.setString(1, Sha256.encrypt(paraMap.get("new_m_pwd")));
	           pstmt.setString(2, paraMap.get("m_id"));

	           result = pstmt.executeUpdate();

	       } finally {
	           close();
	       }

	       return result;
	   }


	   // 회占쏙옙탈占쏙옙 占쌨소듸옙
	   @Override
	   public int memberDelete(Map<String, String> paraMap) throws SQLException {
	      int result = 0;
	      
	       try {
	           conn = ds.getConnection();

	           String sql = " update tbl_member set m_status = 0 "
	                    + " where m_id = ? and m_pwd = ? ";

	           pstmt = conn.prepareStatement(sql);

	           pstmt.setString(1, paraMap.get("m_id"));
	           pstmt.setString(2, Sha256.encrypt(paraMap.get("m_pwd")));
	           
	           result = pstmt.executeUpdate();

	       } finally {
	           close();
	       }

	       return result;
	   }

	// 占쌨몌옙占쏙옙占쏙옙(占쏙옙화占쏙옙호占쏙옙 占쏙옙치占싹댐옙 회占쏙옙占쏙옙占쏙옙 占쌍댐옙占쏙옙)
	@Override
	public int idleUpdate(Map<String, String> paraMap) throws SQLException {
		
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
            	
            	MemberVO mvo = new MemberVO();
            	
            	mvo.setM_name(rs.getString("m_name"));
            	mvo.setM_mobile(rs.getString("m_mobile"));
            	String sql = " update tbl_member set m_idle = 1 "
            			+ " where m_name = ? and m_mobile = ? ";
            	
            	
            	pstmt = conn.prepareStatement(sql);
            	pstmt.setString(1, paraMap.get("m_name"));
            	pstmt.setString(2, aes.encrypt(paraMap.get("m_mobile")));
            	
            	result = pstmt.executeUpdate();
            	System.out.println(result);
            
		} catch(GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}

	
	// �대㈃���� 議고������ 硫�����
	@Override
	public boolean idlecheck(Map<String, String> paraMapS) throws SQLException {
		
		boolean isUserExist = false;
		try {
			conn = ds.getConnection();
			
			
			String sql = " select m_id, m_name, m_mobile"
					   + " from tbl_member "
					   + " where m_name = ? and m_mobile = ? and m_idle = 0 ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMapS.get("m_name"));
        	pstmt.setString(2, aes.encrypt(paraMapS.get("m_mobile")));
            
            rs = pstmt.executeQuery();
		
            if(rs.next()) {
            	isUserExist = true;
            }
		}  catch(GeneralSecurityException | UnsupportedEncodingException e) {
	        e.printStackTrace();
		} finally {
			close();
		}
		return isUserExist;
	}

	// 회원의 정보를 수정하는 메소드
	@Override
	public int updateMember(MemberVO member) throws SQLException {
		
		int result = 0;

		try {
			 conn = ds.getConnection();
			 
			 String sql = " update tbl_member set m_name = ? "
					    + "                     , m_pwd = ? "
					    + "                     , m_mobile = ? "
					    + "                     , m_postcode = ? " 
					    + "                     , m_address = ? "
					    + "                     , m_detail_address = ? "
					    + "                     , m_extra = ? "
					    + "                     , m_lastpwd = sysdate "
					    + " where m_id = ? ";
			 
			 pstmt = conn.prepareStatement(sql);
				
			 pstmt.setString(1, member.getM_name());
			 pstmt.setString(2, Sha256.encrypt(member.getM_pwd()) ); // ���몃�� SHA256 ��怨�由ъ��쇰� �⑤갑�� ���명�� ���⑤��.
			 pstmt.setString(3, aes.encrypt(member.getM_mobile()) ); // �대���곕��몃�� AES256 ��怨�由ъ��쇰� ��諛⑺�� ���명�� ���⑤��. 
			 pstmt.setString(4, member.getM_postcode());
			 pstmt.setString(5, member.getM_address());
			 pstmt.setString(6, member.getM_detail_address());
			 pstmt.setString(7, member.getM_extra());
			 pstmt.setString(8, member.getM_id());
			 
			 result = pstmt.executeUpdate();
			 
		} catch(GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
}