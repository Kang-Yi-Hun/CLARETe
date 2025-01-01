package member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;

import chaeeun.member.model.MemberDAO;
import chaeeun.member.model.MemberDAO_imple;
import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.nurigo.java_sdk.api.Message;

public class SmsSend extends AbstractController {

   private MemberDAO mdao = new MemberDAO_imple();
   
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
	   
      // >> SMS발송 <<
      // HashMap 에 받는사람번호, 보내는사람번호, 문자내용 등 을 저장한뒤 Coolsms 클래스의 send를 이용해 보냅니다.

      // String api_key = "발급받은 본인의 API Key"; // 발급받은 본인 API Key

      String api_key = "NCSYCWYEMGNOF1R9";
      // String api_secret = "발급받은 본인의 API Secret"; // 발급받은 본인 API Secret

      String api_secret = "BG9LUJKAMDRASRZEFKOEG6F3RD1T0CHR";
      Message coolsms = new Message(api_key, api_secret);
      // net.nurigo.java_sdk.api.Message 임.
      // 먼저 다운 받은 javaSDK-2.2.jar 와 json-simple-1.1.1.jar 를 /MyMVC/src/main/webapp/WEB-INF/lib/ 안에 넣어서 build 시켜야 함.

      String m_name = request.getParameter("m_name");
      String m_mobile = request.getParameter("m_mobile");
      request.setAttribute("m_mobile", m_mobile);
      request.setAttribute("m_name", m_name);
         System.out.println("수신 어디로 가는지 확인"+m_mobile);
         Random random = new Random();
         String certification_code = "";
          
         // == 4개 파라미터(to, from, type, text)는 필수사항이다. ==
         HashMap<String, String> paraMap = new HashMap<>();
        
         	paraMap.put("m_name", m_name);
            paraMap.put("to", m_mobile); // 수신번호
            paraMap.put("from", "01024587544"); // 발신번호
            // 2020년 10월 16일 이후로 발신번호 사전등록제로 인해 등록된 발신번호로만 문자를 보내실 수 있습니다
            paraMap.put("type", "SMS"); // Message type ( SMS(단문), LMS(장문), MMS, ATA )
            
            Map<String, String> paraMapS = new HashMap<>();
            paraMapS.put("m_name", m_name);
            paraMapS.put("m_mobile", m_mobile);
            boolean bool = mdao.idlecheck(paraMapS);
            System.out.println("bool 확인"+bool);
            if(bool) {
            	
            	int randomNum = 0;
            	for(int i=0; i<4; i++) {
            		randomNum = random.nextInt(10);
            		certification_code += Integer.toString(randomNum);
            	}
            	
            	paraMap.put("text", "CLARETE 인증번호["+certification_code+"]"); // 인증번호
            	System.out.println(certification_code);
            	// 세션불러오기
            	HttpSession session = request.getSession();
            	session.setAttribute("certification_code", certification_code);
            	// 발급한 인증코드를 세션에 저장시킴.
            	
            	paraMap.put("app_version", "JAVA SDK v2.2"); // application name and version
            	
            	
            	JSONObject jsobj = (JSONObject) coolsms.send(paraMap);
            	/*
               org.json.JSONObject 이 아니라 
               org.json.simple.JSONObject 이어야 한다.
            	 */
            	
            	String json = jsobj.toString();
            	
            	System.out.println("~~~~ 확인용 json => " + json);
            	// ~~~~ 확인용 json =>
            	// {"group_id":"R2GWPBT7UoW308sI","success_count":1,"error_count":0}
            	
            	request.setAttribute("json", json);
            }
            
            // super.setRedirect(false);
            super.setViewPage("/WEB-INF/jsonview.jsp");
            
      
      
   }

}