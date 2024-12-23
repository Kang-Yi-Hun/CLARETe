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

	// 아이디찾기
	String findUserid(Map<String, String> paraMap) throws SQLException;

	// 비밀번호찾기1
	boolean isUserExist(Map<String, String> paraMap) throws SQLException;

	// 비밀번호 찾기
	int pwdUpdate(Map<String, String> paraMap) throws SQLException;

	// 회원탈퇴하는 메소드
	int memberDelete(Map<String, String> paraMap) throws SQLException;

}