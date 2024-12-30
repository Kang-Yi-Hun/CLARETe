package faq.domain;

public class FaqVO {
	int FAQ_num; // ���ֹ���������ȣ
	String ask; // ����
	String answer; // �亯
	
	
	public int getFAQ_num() {
		return FAQ_num;
	}
	public void setFAQ_num(int faq_num) {
		FAQ_num = faq_num;
	}
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
