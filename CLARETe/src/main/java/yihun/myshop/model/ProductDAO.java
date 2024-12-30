package yihun.myshop.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import product.domain.ProductVO;

public interface ProductDAO {

	// ajax로 받아온 값을 조회하는 메소드
	List<ProductVO> selectProduct(Map<String, String> paraMap) throws SQLException;

	// 카테고리 상품의 전체개수를 알아온다.
	int totalPspecCount(String string) throws SQLException;

}
