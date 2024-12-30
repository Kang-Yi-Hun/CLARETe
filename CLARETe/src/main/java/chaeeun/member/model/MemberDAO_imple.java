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

   private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다.
   private Connection conn;
   private PreparedStatement pstmt;
   private ResultSet rs;

   private AES256 aes;

   // 생성자
   public MemberDAO_imple() {

      try {
         Context initContext = new InitialContext();
         Context envContext = (Context) initContext.lookup("java:/comp/env");
         ds = (DataSource) envContext.lookup("jdbc/semioracle");

         aes = new AES256(SecretMyKey.KEY);
         // SecretMyKey.KEY 은 우리가 만든 암호화/복호화 키이다.
         //

      } catch (NamingException e) {
         e.printStackTrace();
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
   }

   // 사용한 자원을 반납하는 close() 메소드 생성하기
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

   // 모든 회원을 조회하는 메소드
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

   // 회원가입
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

   // 아이디 중복체크 (중복이면 true 리턴, 중복 아니면 false 리턴)
   @Override
   public boolean idDuplicateCheck(String m_id) throws SQLException {

      boolean isExists = false;

      try {
         conn = ds.getConnection();

         String sql = " select m_id " + " from tbl_member " + " where m_id = ? ";

         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, m_id);

         rs = pstmt.executeQuery();

         isExists = rs.next(); // 행이 있으면(중복된 userid) true,
                           // 행이 없으면(사용가능한 userid) false

      } finally {
         close();
      }

      return isExists;
   }

   // 이메일 중복검사
   @Override
   public boolean emailDuplicateCheck(String email) throws SQLException {

      boolean isExists = false;

      try {
         conn = ds.getConnection();

         String sql = " select m_email " + " from tbl_member " + " where m_email = ? ";

         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, aes.encrypt(email));

         rs = pstmt.executeQuery();

         isExists = rs.next(); // 행이 있으면(중복된 userid) true,
                           // 행이 없으면(사용가능한 userid) false

      } catch (GeneralSecurityException | UnsupportedEncodingException e) {
         e.printStackTrace();
      } finally {
         close();
      }

      return isExists;

   }

   // 로그인
   @Override
   public MemberVO login(Map<String, String> paraMap) throws SQLException {

      MemberVO member = null;

      try {
         conn = ds.getConnection();

         String sql = " SELECT m_id, m_name, m_point, pwdchangegap, NVL( lastlogingap, TRUNC( months_between(sysdate, m_register)) ) AS lastlogingap, "
                  + " m_idle, m_email, m_mobile, m_postcode, m_address, m_detail_address, m_extra "
                  + " FROM "
                  + " ( "
                  + "    SELECT m_id, m_name, m_point, "
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
         pstmt.setString(1, paraMap.get("id")); // m_id 키 확인
         pstmt.setString(2, Sha256.encrypt(paraMap.get("pwd"))); // 암호화된 비밀번호
         pstmt.setString(3, paraMap.get("id")); // m_id 키 확인
         
         rs = pstmt.executeQuery();

         if (rs.next()) {
            member = new MemberVO();

            member.setM_id(rs.getString("m_id"));
            member.setM_name(rs.getString("m_name"));
            member.setM_point(rs.getInt("m_point"));
            
                  
            // 마지막 로그인 1년 이상이면 휴면
            if (rs.getInt("lastlogingap") >= 12) {
               member.setM_idle(0);

               if (rs.getInt("m_idle") == 1) {
                  sql = " update tbl_member set m_idle = 0 " + " where m_id = ? ";

                  pstmt = conn.prepareStatement(sql);
                  pstmt.setString(1, paraMap.get("m_id"));

                  pstmt.executeUpdate();
               }
            }

            // 휴면 아닌 회원만 tbl_log에 insert
            if (rs.getInt("lastlogingap") < 12) {

               sql = " insert into tbl_log(l_num, fk_m_id, l_ip) " + " values(seq_log.nextval, ?, ?) ";

               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, paraMap.get("id"));
               pstmt.setString(2, paraMap.get("clientip"));

               pstmt.executeUpdate();

               if (rs.getInt("pwdchangegap") >= 3) {
                  // 마지막으로 암호를 변경한 날짜가 현재시각으로 부터 3개월이 지났으면 true
                  // 마지막으로 암호를 변경한 날짜가 현재시각으로 부터 3개월이 지나지 않았으면 false

                  member.setRequirePwdChange(true); // 로그인시 암호를 변경해라는 alert 를 띄우도록 할때 사용한다.
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