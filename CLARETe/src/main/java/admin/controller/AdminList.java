package admin.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;
import minkyu.admin.model.AdminDAO;
import minkyu.admin.model.AdminDAO_imple;

public class AdminList extends AbstractController {

	private AdminDAO adao = new AdminDAO_imple();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");

		if (loginuser != null && "admin".equals(loginuser.getM_id())) {
			// 관리자(admin)으로 로그인 했을 경우
			String searchType = request.getParameter("searchType");
			String searchWord = request.getParameter("searchWord");

			System.out.println("~~~ 확인용 searchType : " + searchType);
			System.out.println("~~~ 확인용 searchWord : " + searchWord);

			if (searchType == null || (!"userid".equals(searchType) && !"username".equals(searchType)
					&& !"delete".equals(searchType) && !"hyumen".equals(searchType))) {
				searchType = "";
			} // userid, username, delete, hyumen 뺀 나머지는 장난치지 못하게 함.

			if (searchWord == null) {
				searchWord = "";
			}

			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("searchType", searchType); // 검색대상
			paraMap.put("searchWord", searchWord); // 검색어

			try {
				List<MemberVO> memberList = adao.select_Member_paging(paraMap);

				request.setAttribute("memberList", memberList);

				request.setAttribute("searchType", searchType);
				request.setAttribute("searchWord", searchWord);

				super.setRedirect(false);
				super.setViewPage("/WEB-INF/admin/adminSelectAll.jsp");

			} catch (SQLException e) {
				e.printStackTrace();

				super.setRedirect(true);
//				super.setViewPage(request.getContextPath()+".error.cl");
			}
		}

		else {
			String message = "관리자만 접근이 가능합니다.";
			String loc = "javascript:history.back";

			super.setRedirect(false);
//			super.setViewPage("/WEB-INF/error.jsp");
		}

	}

}
