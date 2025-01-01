package common.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;

public abstract class AbstractController implements InterCommand {

	/*
	    === �ㅼ���� ���ㅻ�� 寃��� �곕━�쇰━�� �쎌���대��. ===
	
	    �� view �� ���댁�(.jsp)濡� �대���� forward 諛⑸�(dispatcher)�쇰� �대�����ㅺ��� ���ㅻ�쇰㈃ 
	       �����대����(/webapp/WEB-INF/Command.properties ���쇱�� 湲곕��� �대���ㅻ���)������ 遺�紐⑦�대���ㅼ���� ���깊�대�� 硫����� �몄��� ������ 媛��� ��硫� ��寃��� ����.
	     
	       super.setRedirect(false); 
	       super.setViewPage("/WEB-INF/index.jsp");
	    
	    
	    �� URL 二쇱��瑜� 蹂�寃쏀���� ���댁� �대�����ㅺ��� ���ㅻ�쇰㈃
	      利�, sendRedirect 瑜� ��怨��� ���ㅻ�쇰㈃    
	      �����대���ㅼ������ 遺�紐⑦�대���ㅼ���� ���깊�대�� 硫����� �몄��� ������ 媛��� ��硫� ��寃��� ����.
	          
	      super.setRedirect(true);
	      super.setViewPage("registerMember.cl");               
	 */
	
	private boolean isRedirect = false;
	// isRedirect 蹂����� 媛��� false �대�쇰㈃ view�� ���댁�(.jsp)濡� forward 諛⑸�(dispatcher)�쇰� �대�����ㅺ���. 
	// isRedirect 蹂����� 媛��� true �대�쇰㈃ sendRedirect 濡� ���댁��대���� ���ㅺ���.
	
	private String viewPage;
	// viewPage �� isRedirect 媛��� false �대�쇰㈃ view�� ���댁�(.jsp)�� 寃쎈�紐� �닿�,
	// isRedirect 媛��� true �대�쇰㈃ �대���댁�쇳�� ���댁� URL 二쇱�� �대��.

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getViewPage() {
		return viewPage;
	}

	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	
	
	public boolean checkLogin(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		
		if(loginuser != null) {
			// 濡�洹몄�� �� 寃쎌��
			return true;
		}
		else {
			return false;
		}
		
	} // end of public boolean checkLogin(HttpServletRequest request)----------
	
}
