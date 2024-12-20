package chaeeun.member.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import member.domain.MemberVO;

public interface MemberDAO {

   // 모든 회원을 조회하는 메소드
   List<MemberVO> SelectAll_member() throws SQLException;

   // 회원가입
   int registerMember(MemberVO member) throws SQLException;

   // 아이디 중복검사
   boolean idDuplicateCheck(String m_id) throws SQLException;

   // 이메일 중복검사
   boolean emailDuplicateCheck(String email) throws SQLException;

   // 로그인
   MemberVO login(Map<String, String> paraMap) throws SQLException;

   // 아이디 찾기
   String findUserid(Map<String, String> paraMap) throws SQLException;

   // 비밀번호 찾기1
   boolean isUserExist(Map<String, String> paraMap) throws SQLException;

   // 비밀번호 변경하기
   int pwdUpdate(Map<String, String> paraMap) throws SQLException;
   
}