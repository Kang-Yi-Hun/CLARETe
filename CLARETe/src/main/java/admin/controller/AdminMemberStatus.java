package admin.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;
import minkyu.admin.model.AdminDAO;
import minkyu.admin.model.AdminDAO_imple;
import my.util.MyUtil;

public class AdminMemberStatus extends AbstractController {

	private AdminDAO adao = new AdminDAO_imple();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// == 관리자(admin)로 로그인 했을 때만 회원조회가 가능하도록 해야 한다. == //
		HttpSession session = request.getSession();

		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");

		if (loginuser != null && "admin".equals(loginuser.getM_id())) {
			// 관리자(admin) 로 로그인 했을 경우
			// 파라미터 값 처리
			String searchType = request.getParameter("searchType");
			String searchWord = request.getParameter("searchWord");
			String currentShowPageNo = request.getParameter("currentShowPageNo");

			String sizePerPage = "5"; // 페이지에서 보여줄 회원 수
/////////////데이터 많아지면 여기서 바꾸기!!!

			// 기본값 설정
			if (searchType == null
					|| (!"m_name".equals(searchType) && !"m_id".equals(searchType) && !"m_email".equals(searchType))) {
				searchType = "";
			}

			if (searchWord == null) {
				searchWord = "";
			}

			if (currentShowPageNo == null) {
				currentShowPageNo = "1";
			}

			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("searchType", searchType);
			paraMap.put("searchWord", searchWord);
			paraMap.put("currentShowPageNo", currentShowPageNo);
			paraMap.put("sizePerPage", sizePerPage); // 한페이지당 보여줄 행의 개수

			// 회원탈퇴 파라미터 처리
			String memberstatus = request.getParameter("memberstatus");
			if (memberstatus == null) {
				memberstatus = "false"; // 기본값: 탈퇴 회원 제외
			}
			paraMap.put("memberstatus", memberstatus);

//        System.out.println("나 서블릿 ~ " + paraMap.get("searchType"));
//        System.out.println("ㅎㅇㅎㅇ " + paraMap.get("searchWord"));       
//        System.out.println(paraMap.get(searchType));

			// **** 페이징 처리를 한 모든 회원목록 또는 검색되어진 회원목록 보여주기 **** //
			// 페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총페이지수 알아오기 //
			int totalPage = adao.getmemberstatusPage(paraMap);
			// System.out.println("~~~~ 확인용 totalPage => " + totalPage);

			try {
				if (Integer.parseInt(currentShowPageNo) > totalPage || Integer.parseInt(currentShowPageNo) <= 0) {
					currentShowPageNo = "1";
					paraMap.put("currentShowPageNo", currentShowPageNo);
				}
			} catch (NumberFormatException e) {
				currentShowPageNo = "1";
				paraMap.put("currentShowPageNo", currentShowPageNo);
			}

			String pageBar = "";

			int blockSize = 10; ///////////// 데이터 많아지면 여기서 바꾸기!!!
			// blockSize 는 블럭(토막)당 보여지는 페이지 번호의 개수이다.

			int loop = 1;
			// loop 는 1 부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다.

			// ==== !!! 다음은 pageNo 구하는 공식이다. !!! ==== //
			int pageNo = ((Integer.parseInt(currentShowPageNo) - 1) / blockSize) * blockSize + 1;
			// pageNo 는 페이지바에서 보여지는 첫번째 번호이다.

			// *** [맨처음][이전] 만들기 *** //
			pageBar += "<li class='page-item'><a class='page-link' href='adminMemberStatus.cl?searchType=" + searchType
					+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage
					+ "&currentShowPageNo=1'>[맨처음]</a></li>";

			if (pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='adminMemberStatus.cl?searchType=" + searchType
						+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo="
						+ (pageNo - 1) + "'>[이전]</a></li>";
			}

			while (!(loop > blockSize || pageNo > totalPage)) {

				if (pageNo == Integer.parseInt(currentShowPageNo)) {
					pageBar += "<li class='page-item active'><a class='page-link' href='#'>" + pageNo + "</a></li>";

				} else {
					pageBar += "<li class='page-item'><a class='page-link' href='adminMemberStatus.cl?searchType=" + searchType
							+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo="
							+ pageNo + "'>" + pageNo + "</a></li>";
				}

				loop++; // 1 2 3 4 5 6 7 8 9 10 11 12 13 ...

				pageNo++; // 1 2 3 4 5 6 7 8 9 10 ...
							// 11 12 13 14 15 16 17 18 19 20 ...
							// 21 22 23 24 25 26 27 28 29 30 ...
							// 31 32
			} // end of while()-----------------------------

			// *** [다음][마지막] 만들기 *** //
			// pageNo ==> 11
			if (pageNo <= totalPage) {
				pageBar += "<li class='page-item'><a class='page-link' href='adminMemberStatus.cl?searchType=" + searchType
						+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo=" + pageNo
						+ "'>[다음]</a></li>";

			}
			pageBar += "<li class='page-item'><a class='page-link' href='adminMemberStatus.cl?searchType=" + searchType
					+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo=" + totalPage
					+ "'>[마지막]</a></li>";
			// ==== 페이지바 만들기 끝 ==== //

			// *** ====== 현재 페이지를 돌아갈 페이지(goBackURL)로 주소 지정하기 ======= *** //
			String currentURL = MyUtil.getCurrentURL(request);
			// 회원조회를 했을시 현재 그 페이지로 그대로 되돌아가길 위한 용도로 쓰임.

			// System.out.println("currentURL : => " + currentURL);

			try {

				// 회원 목록 가져오기
				List<MemberVO> memberstatusList = adao.searchMemberstatus(paraMap);

				// 뷰에 데이터 전달
				request.setAttribute("memberstatusList", memberstatusList);

				request.setAttribute("searchType", searchType);
				request.setAttribute("searchWord", searchWord);

				request.setAttribute("sizePerPage", sizePerPage);

				request.setAttribute("pageBar", pageBar);

				request.setAttribute("currentURL", currentURL);

				/*
				 * >>> 뷰단(memberList.jsp)에서 "페이징 처리시 보여주는 순번 공식" 에서 사용하기 위해 검색이 있는 또는 검색이 없는 회원의
				 * 총개수 알아오기 시작 <<<
				 */
				int totalMemberstatusCount = adao.getTotalMemberstatusCount(paraMap);
				// System.out.println("~~~ 확인용 totalMemberCount :" + totalMemberCount);

				request.setAttribute("totalMemberstatusCount", totalMemberstatusCount);
				request.setAttribute("currentShowPageNo", currentShowPageNo);
				/* >>> 검색이 있는 또는 검색이 없는 회원의 총개수 알아오기 끝 <<< */

				super.setRedirect(false);
				super.setViewPage("/WEB-INF/admin/adminMemberStatus.jsp");

			} catch (SQLException e) {
				e.printStackTrace();

				super.setRedirect(true);
//            super.setViewPage(request.getContextPath() + "/error.up");
			}

		} else {
			// 로그인을 안한 경우 또는 일반사용자로 로그인 한 경우
			String message = "관리자만 접근이 가능합니다.";
			String loc = "javascript:history.back()";

			request.setAttribute("message", message);
			request.setAttribute("loc", loc);

			super.setRedirect(false);
			super.setViewPage("/WEB-INF/msg.jsp");
		}

	}

}