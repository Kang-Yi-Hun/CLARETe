package minkyu.order.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import order.domain.OrderVO;

public interface OrderDAO {

	
	// �α����� ȸ���� �ֹ������� �������� �޼ҵ�
	List<OrderVO> recentOrder(Map<String, String> paraMap) throws SQLException;
}
