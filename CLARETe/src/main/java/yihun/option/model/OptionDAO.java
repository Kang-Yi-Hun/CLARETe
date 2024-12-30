package yihun.option.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import option.domain.OptionVO;

public interface OptionDAO {

	// 상품 옵션 정보를 알아온다
	List<OptionVO> selectOption(int p_num) throws SQLException;

}
