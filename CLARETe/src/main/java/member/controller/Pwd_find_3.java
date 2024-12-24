package member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.security.Sha256;
import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
public class Pwd_find_3 extends AbstractController {
   
   private MemberDAO mdao = new MemberDAO_imple();
   
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        String m_id = request.getParameter("m_id");
         
        String method = request.getMethod(); // "GET"      "POST" 
      
        
        if("POST".equalsIgnoreCase(method)) {
           // "   몃 寃쏀  湲 " 踰  쇱    대┃     寃쎌  
           String new_m_pwd = request.getParameter("m_pwd");
           
           System.out.println("확인용 => "+new_m_pwd);
           
           Map<String, String> paraMap = new HashMap<>();
           paraMap.put("m_id", m_id);
           paraMap.put("new_m_pwd", new_m_pwd);
           
           int n = 0;
           try {
              n = mdao.pwdUpdate(paraMap);
              
              if(n == 1) {
            	  
            	  System.out.println("db 성공");
            	  super.setRedirect(true);
            	  super.setViewPage(request.getContextPath()+"/login/loginView.cl");
            	  
              }
              
              
              
           }catch (SQLException e) {

           }
           
           
        } // end of if(post)---------
        else {
        	 request.setAttribute("m_id", m_id);
             request.setAttribute("method", method);
           
             
     	       super.setRedirect(false);
     	       super.setViewPage("/WEB-INF/member/pwd_find_3.jsp");
        }
        
       
        
   }

}


