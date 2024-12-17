package delivery.domain;

public class DeliveryVO {

	int d_num; 					// 배송지 번호
	String d_address; 			// 받는 사람 주소
	String d_detail_address; 	//받는 사람 상세 주소
	String d_postcode; 			//받는사람 우편항목
	String d_extra;				//받는사람 참고 항목
	String d_mobile;			//받는사람 전화번호
	String d_name;				//받는사람 이름
	String fk_m_id;				//유저 아이디
	
	
	

	////////////////////////////////////////////
	public int getD_num() {
		return d_num;
	}
	public void setD_num(int d_num) {
		this.d_num = d_num;
	}
	public String getD_address() {
		return d_address;
	}
	public void setD_address(String d_address) {
		this.d_address = d_address;
	}
	public String getD_detail_address() {
		return d_detail_address;
	}
	public void setD_detail_address(String d_detail_address) {
		this.d_detail_address = d_detail_address;
	}
	public String getD_postcode() {
		return d_postcode;
	}
	public void setD_postcode(String d_postcode) {
		this.d_postcode = d_postcode;
	}
	public String getD_extra() {
		return d_extra;
	}
	public void setD_extra(String d_extra) {
		this.d_extra = d_extra;
	}
	public String getD_mobile() {
		return d_mobile;
	}
	public void setD_mobile(String d_mobile) {
		this.d_mobile = d_mobile;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	

	public String getFk_m_id() {
		return fk_m_id;
	}
	public void setFk_m_id(String fk_m_id) {
		this.fk_m_id = fk_m_id;
	}

	
	
	
}
