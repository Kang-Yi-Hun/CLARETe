package member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Pwd_find_1 extends AbstractController {

	private MemberDAO mdao = new MemberDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod(); // "GET" 또는 "POST"
		
		if("POST".equalsIgnoreCase(method)) {
	         // 비밀번호 찾기 모달창에서 "찾기" 버튼을 클릭했을 경우
		
			String userid = request.getParameter("userid");
			String email = request.getParameter("email");
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("userid", userid);
			paraMap.put("email", email);
			
			boolean isUserExist = mdao.isUserExist(paraMap);
			
			//////////////////////////////////////////////////////
			boolean sendMailSuccess = false; // 메일이 정상적으로 전송되었는지 유무를 알아오기 위한 용도
			
			if(isUserExist) {
				// 회원으로 존재하는 경우
				
				// 인증키를 랜덤하게 생성하도록 한다.
				Random rnd = new Random();
				
				String certification_code = "";
				// 인증키는 영문소문자 5글자 + 숫자 7글자로 만들겠습니다.
				
				char randchar = ' ';
				for(int i=0; i<5; i++) {
				/*
					min 부터 max 사이의 값으로 랜덤한 정수를 얻으려면 
	                int rndnum = rnd.nextInt(max - min + 1) + min;
               */
				randchar = (char)(rnd.nextInt('z' - 'a' + 1) + 'a');
				certification_code += randchar;
				}
				
				
				int randnum = 0;
				for(int i=0; i<7; i++) {
				/*
					min 부터 max 사이의 값으로 랜덤한 정수를 얻으려면 
	                int rndnum = rnd.nextInt(max - min + 1) + min;
	                	숫자 0부터 9까지 랜덤하게 1개 만든다.
               */
				randnum = (int)(rnd.nextInt(9 - 0 + 1) + 0);
				certification_code += randnum;
				}
				
				// System.out.println("~~~~ 확인용 certification_code : "+certification_code);
				
				// 랜덤하게 생성한 인증코드(certification_code)를 비밀번호 찾기를 하고자 하는 사용자의 email 로 전송시킨다.
				GoogleMail mail = new GoogleMail();
				
				try {
					mail.send_certification_code(email, certification_code);
					sendMailSuccess = true; // 메일 전송 성공했음을 기록함.
					
					// 세션불러오기
					HttpSession session = request.getSession();
					session.setAttribute("certification_code", certification_code);
					// 발급한 인증코드를 세션에 저장시킴.
					
				}catch (Exception e) {
					// 메일 전송이 실패한 경우
					e.printStackTrace();
					sendMailSuccess = false; // 메일 전송 실패했음을 기록함.
				}
				
				
			} // end of if(isUserExist)-------
			//////////////////////////////////////////////////////
			
			request.setAttribute("isUserExist", isUserExist);
			request.setAttribute("sendMailSuccess", sendMailSuccess);
			request.setAttribute("email", email);
			request.setAttribute("userid", userid);
			
		} // end of if("POST".equalsIgnoreCase(method))----------
		
		request.setAttribute("method", method);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/member/pwd_find_1.jsp");
	}

}
