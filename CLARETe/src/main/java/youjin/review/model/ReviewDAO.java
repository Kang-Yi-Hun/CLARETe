package youjin.review.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import qna.domain.QnaVO;
import review.domain.ReviewVO;

public interface ReviewDAO {

	// 리뷰 등록
	int ReviewUpload(ReviewVO rvo) throws SQLException;

	// 리뷰 작성 가능한 목록보기
	List<ReviewVO> myreviewUpList(String fk_m_id) throws SQLException;

	// 작성한 리뷰 보기
	List<ReviewVO> myreviewList(String fk_m_id) throws SQLException;

	// 구매한 제품이 맞는지 확인하기
	int OderReviewCheck(String fk_m_id, String p_num) throws SQLException;

	// 해당 제품번호의 리뷰 등록 창 보여주기
	//ReviewVO ReviewUploadview(int p_num) throws SQLException;


}
