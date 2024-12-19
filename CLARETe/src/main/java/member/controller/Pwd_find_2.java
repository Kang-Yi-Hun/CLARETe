package member.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Pwd_find_2 extends AbstractController {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String method = request.getMethod(); // "GET" 또는 "POST"

        // 세션불러오기
        HttpSession session = request.getSession();
     //   System.out.println("~~~~~~~~~~~~~~~~~" + session.getAttribute("certification_code"));
        // 세션에서 certification_code 가져오기
        String certificationCode = (String) session.getAttribute("certification_code");
        
        // certificationCode가 null인 경우, 인증 코드가 세션에 저장되지 않았음을 처리
    //    if(certificationCode != null) {
    //        System.out.println("인증 코드: " + certificationCode);
    //    } else {
    //        System.out.println("인증 코드가 세션에 저장되지 않았습니다.");
    //    }

        // "GET" 요청 시 인증 코드를 출력하거나 추가적인 로직 처리 가능
        request.setAttribute("method", method);
        request.setAttribute("certificationCode", certificationCode);  // 인증 코드를 JSP로 전달할 경우

        super.setRedirect(false);
        request.getRequestDispatcher("/WEB-INF/member/pwd_find_2.jsp").forward(request, response);

    }
}
