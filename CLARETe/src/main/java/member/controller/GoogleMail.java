package member.controller;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class GoogleMail {

	public void send_certification_code(String recipient, String certification_code) throws Exception { 
											// 諛��� �щ��		��寃�// �몄�肄��� 瑜� 蹂대�쇨��대��
		// 1. ��蹂대�� �닿린 ���� 媛�泥�
    	Properties prop = new Properties(); 
    	
    	
    	// 2. SMTP(Simple Mail Transfer Protocoal) ��踰��� 怨��� �ㅼ��
   	    //    Google Gmail 怨� �곌껐�� 寃쎌�� Gmail �� email 二쇱��瑜� 吏��� 
    	prop.put("mail.smtp.user", "semi20241210@gmail.com"); 
    			
		
    	// 3. SMTP ��踰� ��蹂� �ㅼ��
    	//    Google Gmail �� 寃쎌��  smtp.gmail.com
    	prop.put("mail.smtp.host", "smtp.gmail.com");
         	
    	
    	prop.put("mail.smtp.port", "465");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.smtp.debug", "true");
    	prop.put("mail.smtp.socketFactory.port", "465");
    	prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	prop.put("mail.smtp.socketFactory.fallback", "false");
    	
    	prop.put("mail.smtp.ssl.enable", "true");
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); // MAC ������ �대��� 蹂대�닿린 媛��ν����濡� ��寃���. ���� 留��쎌�� SMTP ��踰�瑜� google ���� naver 瑜� �ъ�⑺���ㅻ㈃ �닿��� �댁＜�댁�� ��.
      	
    /*  
        �뱀���� 465 �ы�몄�� �곌껐�� �� ���ㅻ�� ���щ���吏�媛� ���ㅻ㈃ ������ 3媛�瑜� �ｌ�댁＜硫� �닿껐����.
    	prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
    */ 
    	
    	Authenticator smtpAuth = new MySMTPAuthenticator();
    	Session ses = Session.getInstance(prop, smtpAuth);
    		
    	// 硫��쇱�� ���≫�� �� ���명�� ���⑹�� 肄����� 異��ν����.
    	ses.setDebug(true);
    	        
    	// 硫��쇱�� �댁�⑹�� �닿린 ���� 媛�泥댁����
    	MimeMessage msg = new MimeMessage(ses);

    	// ��紐� �ㅼ��
    	String subject = "CLARET 회원님의 비밀번호를 찾기위한 인증코드 발송";
    	msg.setSubject(subject);
    	        
    	// 蹂대�대�� �щ���� 硫��쇱＜��
    	String sender = "semi20241210@gmail.com";
    	Address fromAddr = new InternetAddress(sender);
    	msg.setFrom(fromAddr);
    	        
    	// 諛��� �щ���� 硫��쇱＜��
    	Address toAddr = new InternetAddress(recipient);
    	msg.addRecipient(Message.RecipientType.TO, toAddr);
    	        
    	// 硫���吏� 蹂몃Ц�� �댁�⑷낵 ����, 罹�由��� �� �ㅼ��
    	msg.setContent("발송된 인증코드 : <span style='font-size:14pt; color:red;'>"+certification_code+"</span>", "text/html;charset=UTF-8"); 
    	
    	// 硫��� 諛��≫��湲�
    	Transport.send(msg);
    	
	}// end of public void send_certification_code(String recipient, String certification_code) throws Exception--------	

	
	public void sendmail_OrderFinish(String recipient, String name, String emailContents) throws Exception {
		
		// 1. ��蹂대�� �닿린 ���� 媛�泥�
    	Properties prop = new Properties(); 
    	
    	
    	// 2. SMTP(Simple Mail Transfer Protocoal) ��踰��� 怨��� �ㅼ��
   	    //    Google Gmail 怨� �곌껐�� 寃쎌�� Gmail �� email 二쇱��瑜� 吏��� 
    	prop.put("mail.smtp.user", "semi20241210@gmail.com"); 
    			
		
    	// 3. SMTP ��踰� ��蹂� �ㅼ��
    	//    Google Gmail �� 寃쎌��  smtp.gmail.com
    	prop.put("mail.smtp.host", "smtp.gmail.com");
         	
    	
    	prop.put("mail.smtp.port", "465");
    	prop.put("mail.smtp.starttls.enable", "true");
    	prop.put("mail.smtp.auth", "true");
    	prop.put("mail.smtp.debug", "true");
    	prop.put("mail.smtp.socketFactory.port", "465");
    	prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	prop.put("mail.smtp.socketFactory.fallback", "false");
    	
    	prop.put("mail.smtp.ssl.enable", "true");
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); // MAC ������ �대��� 蹂대�닿린 媛��ν����濡� ��寃���. ���� 留��쎌�� SMTP ��踰�瑜� google ���� naver 瑜� �ъ�⑺���ㅻ㈃ �닿��� �댁＜�댁�� ��.
      	
    /*  
        �뱀���� 465 �ы�몄�� �곌껐�� �� ���ㅻ�� ���щ���吏�媛� ���ㅻ㈃ ������ 3媛�瑜� �ｌ�댁＜硫� �닿껐����.
    	prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.starttls.required", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
    */ 
    	
    	Authenticator smtpAuth = new MySMTPAuthenticator();
    	Session ses = Session.getInstance(prop, smtpAuth);
    		
    	// 硫��쇱�� ���≫�� �� ���명�� ���⑹�� 肄����� 異��ν����.
    	ses.setDebug(true);
    	        
    	// 硫��쇱�� �댁�⑹�� �닿린 ���� 媛�泥댁����
    	MimeMessage msg = new MimeMessage(ses);

    	// ��紐� �ㅼ��
    	String subject = "localhost:9090/CLARETe/mallHome1.up "+name+"���� 二쇰Ц�� �깃났���듬����.";
    	msg.setSubject(subject);
    	        
    	// 蹂대�대�� �щ���� 硫��쇱＜��
    	String sender = "semi20241210@gmail.com";
    	Address fromAddr = new InternetAddress(sender);
    	msg.setFrom(fromAddr);
    	        
    	// 諛��� �щ���� 硫��쇱＜��
    	Address toAddr = new InternetAddress(recipient);
    	msg.addRecipient(Message.RecipientType.TO, toAddr);
    	        
    	// 硫���吏� 蹂몃Ц�� �댁�⑷낵 ����, 罹�由��� �� �ㅼ��
    	msg.setContent("<div style='font-size:14pt; color:red;'>"+emailContents+"</div>", "text/html;charset=UTF-8");  
    	        
    	// 硫��� 諛��≫��湲�
    	Transport.send(msg);		
		
	}// end of public void sendmail_OrderFinish(String recipient, String name, String emailContents)----------
	
}
