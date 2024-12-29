package chaeeun.cart.model;

import java.sql.SQLException;
import java.util.List;

import cart.controller.Cart;
import cart.domain.CartVO;
import delivery.domain.DeliveryVO;

public interface CartDAO {

	// 장바구니 조회
	List<CartVO> selectCart(String m_id) throws SQLException;

	// 배송 정보 조회
	List<DeliveryVO> selectDeliveryList(String m_id) throws SQLException;

}
