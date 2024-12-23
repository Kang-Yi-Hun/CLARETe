package minkyu.admin.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import member.domain.MemberVO;
import order.domain.OrderVO;
import product.domain.ProductVO;

public interface AdminDAO {

   // 전체 회원 조회
   List<MemberVO> SelectAll_member() throws SQLException;

   // 주문 회원 조회
	List<OrderVO> SelectOrderMember() throws SQLException;

	// 상품 조회
	List<ProductVO> ProductAll_member() throws SQLException;

	// *** admin > 페이징 처리를 한 모든 회원 또는 검색한 회원 목록 보여주기 //
	List<MemberVO> select_Member_paging(Map<String, String> paraMap);
}
