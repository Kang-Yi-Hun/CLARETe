package youjin.qna.model;

import java.sql.SQLException;
import java.util.List;

import qna.domain.QnaVO;

public interface QnaDAO {

	// 1:1문의 등록
	int qnaUpload(QnaVO qvo) throws SQLException;

	// Q&A 게시판
	List<QnaVO> qnaList() throws SQLException;

	// 선택한 Q&A 상세하게 보여주기	
	QnaVO selectOneQna(int q_num) throws SQLException;

	// 1:1문의 답변 등록
	int qnaAnswerUpload(QnaVO qvo) throws SQLException;
	
	

}
