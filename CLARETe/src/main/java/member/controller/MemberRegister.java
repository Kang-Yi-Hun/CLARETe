package member.controller;

import java.sql.SQLException;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;
import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;

public class MemberRegister extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		System.out.println(method);
		
		if ("GET".equalsIgnoreCase(method)) {
			// GET 방식이면 페이지 이동 안되게
		//	super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/memberRegister.jsp");	
		} else {
			// POST 방식일 때만 회원가입 가능
			
			// 회원가입 form 에 입력한 정보들 받아와서 String 변수에 저장
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String postcode = request.getParameter("postcode");
			String address = request.getParameter("address");
			String detailaddress = request.getParameter("detailaddress");
			String extraaddress = request.getParameter("extraaddress");
			String gender = request.getParameter("gender");
			String birthday = request.getParameter("birthday");
			
			// 회원 객체에 정보 set 해줌
			MemberVO member = new MemberVO();
			member.setM_id(id);
			member.setM_pwd(pwd);
			member.setM_name(name);
			member.setM_email(email);
			member.setM_mobile(mobile);
			member.setM_postcode(postcode);
			member.setM_address(address);
			member.setM_detail_address(detailaddress);
			member.setM_extra(extraaddress);
			member.setM_gender(gender);
			member.setM_birth(birthday);
			
			
			// 회원가입 성공시 alert 띄우고 시작페이지로 ~~~
			String message = "";
			String loc = "";
			
			try {
				int n = mdao.registerMember(member);
				
				if (n == 1) {	
					message = "회원가입 성공";
					loc = request.getContextPath()  + "/index.cl";	// 시작페이지로 이동한다. (돌아갈 페이지가 JSP라서 request.getContextPath() 필요)
					
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				message = "회원가입 실패";
				loc = "javascript:history.back";		// JS를 이용한 이전페이지로 이동하는 것
			}
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
		//	super.setRedirect(true);
			super.setViewPage("/WEB-INF/msg.jsp");
		}

	}

}