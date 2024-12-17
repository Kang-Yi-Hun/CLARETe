package delivery.model;

import java.sql.SQLException;

import delivery.domain.DeliveryVO;

public interface DeliveryDAO {

	//배송지 입력을 위한 메소드
	int InsertDelivery(DeliveryVO delivery) throws SQLException;

}
