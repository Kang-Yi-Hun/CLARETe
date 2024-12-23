package yihun.prodect.model;

import java.sql.SQLException;

import product.domain.ProductVO;

public interface ProductDAO {

	// 상품등록을 해주는 메소드
	int insertProduct(ProductVO pvo) throws SQLException;

	// 상품 아이디를 알아오는 메소드
	ProductVO selectSeq() throws SQLException;

}
