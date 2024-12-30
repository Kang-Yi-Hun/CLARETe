package youjin.faq.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import faq.domain.FaqVO;

public interface FaqDAO {

	// FAQ 업로드
	int faqUpload(FaqVO faq) throws SQLException;

	// FAQ 게시판
	List<FaqVO> faqList() throws SQLException;


	
}
