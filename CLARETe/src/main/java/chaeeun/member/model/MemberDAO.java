package chaeeun.member.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import member.domain.MemberVO;

public interface MemberDAO {

   // 紐⑤�� ������ 議고������ 硫�����
   List<MemberVO> SelectAll_member() throws SQLException;

   // ����媛���
   int registerMember(MemberVO member) throws SQLException;

   // ���대�� 以�蹂듦���
   boolean idDuplicateCheck(String m_id) throws SQLException;

   // �대��� 以�蹂듦���
   boolean emailDuplicateCheck(String email) throws SQLException;

   // 濡�洹몄��
   MemberVO login(Map<String, String> paraMap) throws SQLException;
   
   // ���대��李얘린
   String findUserid(Map<String, String> paraMap) throws SQLException;

   // 鍮�諛�踰��몄갼湲�1
   boolean isUserExist(Map<String, String> paraMap) throws SQLException;

   // 鍮�諛�踰��� 李얘린
   int pwdUpdate(Map<String, String> paraMap) throws SQLException;

   // �������댄����   硫�����
   int memberDelete(Map<String, String> paraMap) throws SQLException;

   // �대㈃�댁��(����踰��몃�� �쇱����� ����紐��� ����吏�)
   int checkMobileName(Map<String, String> paraMap) throws SQLException;
}