package minkyu.admin.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import delivery.domain.DeliveryVO;
//import admin.controller.Member;
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

	// 주문 배송 관리
	List<DeliveryVO> Delivery_list() throws SQLException;
	
	
	
	// ======================= 검색 ======================= //

	// 모든회원조회 검색기능
	List<MemberVO> searchMembers(Map<String, String> paraMap)  throws SQLException;

	// 상품조회 검색기능
	List<ProductVO> searchProduct(Map<String, String> paraMap) throws SQLException;

	// 주문회원조회 검색기능
	List<OrderVO> searchOrder(Map<String, String> paraMap) throws SQLException;
	
	// 주문 배송 관리 검색기능
	List<DeliveryVO> searchDelivery(Map<String, String> paraMap) throws SQLException;
	
	
	// ======================= 페이징 ======================= // 
	
	// "모든회원조회" 페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총페이지수 알아오기
	int getTotalPage(Map<String, String> paraMap) throws SQLException;

	//  모든회원조회 검색이 있는 또는 검색이 없는 회원의 총개수 알아오기 시작
	int getTotalMemberCount(Map<String, String> paraMap) throws SQLException;

	// "상품조회" 페이징 처리를 위한 검색이 있는 또는 검색이 없는 상품에 대한 총페이지수 알아오기
	int getProductTotalPage(Map<String, String> paraMap) throws SQLException;

	// "상품조회" 검색이 있는 또는 검색이 없는 상품의 총개수 알아오기 시작
	int getTotalProductCount(Map<String, String> paraMap) throws SQLException;

	// "주문회원조회" 페이징 처리를 위한 검색이 있는 또는 검색이 없는 주문에 대한 총페이지수 알아오기 
	int getOrderPage(Map<String, String> paraMap) throws SQLException;

	// "주문회원조회" 검색이 있는 또는 검색이 없는 주문의 총개수 알아오기 시작
	int getOrderCount(Map<String, String> paraMap) throws SQLException;

	// "주문배송조회" 페이징 처리를 위한 검색이 있는 또는 검색이 없는 주문에 대한 총페이지수 알아오기 
	int getDeliveryPage(Map<String, String> paraMap) throws SQLException;

	// "주문배송조회" 검색이 있는 또는 검색이 없는 주문의 총개수 알아오기 시작
	int getDeliveryCount(Map<String, String> paraMap) throws SQLException;


















	

}
