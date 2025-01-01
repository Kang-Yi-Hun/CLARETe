package member.controller;

import java.util.HashMap;
import java.util.Map;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Idle extends AbstractController {

   private MemberDAO mdao = new MemberDAO_imple();
   
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      String method = request.getMethod(); // "GET"  Ǵ  "POST"
      
      request.setAttribute("method", method);
      
      if("POST".equalsIgnoreCase(method)) {
         
         String m_name = request.getParameter("m_name");
         String m_mobile = request.getParameter("m_mobile");
         Map<String, String> paraMap = new HashMap<>();
         paraMap.put("m_name", m_name);
         paraMap.put("m_mobile", m_mobile);
         
            
            String certification =request.getParameter("certification");
            
            HttpSession session = request.getSession();
            String certification_code = (String)session.getAttribute("certification_code");
            
            if(certification.equals(certification_code)) {
               
               int n = mdao.idleUpdate(paraMap);
               
               if(n == 1) {
                  request.setAttribute("m_name", m_name);
                  request.setAttribute("m_mobile", m_mobile);
                  
               }
            } // end of if(certification == certification_code)
            
      } // end of if("POST".equalsIgnoreCase(method))
      super.setRedirect(false);
      super.setViewPage("/WEB-INF/member/idle.jsp");
         
   }

}