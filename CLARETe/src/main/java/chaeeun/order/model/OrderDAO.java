package chaeeun.order.model;

import java.sql.SQLException;
import java.util.Map;

public interface OrderDAO {

	// tbl_order에 주문 insert
	int insertOrder(Map<String, String> paraMap) throws SQLException;

}
