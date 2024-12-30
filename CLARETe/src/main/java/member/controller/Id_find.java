package member.controller;

import common.controller.AbstractController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;

public class Id_find extends AbstractController {

   private MemberDAO mdao = new MemberDAO_imple();
   
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      String method = request.getMethod(); // "GET" 또는 "POST"
      
      if("POST".equalsIgnoreCase(method)) {
            // 아이디 찾기 모달창에서 "찾기" 버튼을 클릭했을 경우
      
         String m_name = request.getParameter("m_name");
         String m_email = request.getParameter("m_email");
         
         Map<String, String> paraMap = new HashMap<>();
         paraMap.put("m_name", m_name);
         paraMap.put("m_email", m_email);
         
         String m_id = mdao.findUserid(paraMap);
         
         if(m_id != null) {
            request.setAttribute("m_id", m_id);
         }
         else {
            request.setAttribute("m_id", "존재하지 않습니다.");
         }
      
         request.setAttribute("m_name", m_name);
         request.setAttribute("m_email", m_email);
         
      } // end of if("POST".equalsIgnoreCase(method))----------
      
      request.setAttribute("method", method);
      
      
      super.setRedirect(false);
       super.setViewPage("/WEB-INF/member/id_find.jsp");

      
   }

}
