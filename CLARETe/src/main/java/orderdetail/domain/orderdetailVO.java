package orderdetail.domain;

public class orderdetailVO {
	int od_num; // �ֹ����Ϸù�ȣ
	int fk_o_num; // �ֹ���ȣ
	int fk_p_num; // ��ǰ������ȣ
	int od_count; // �ֹ���
	String od_price; // ����
	
	public int getOd_num() {
		return od_num;
	}
	public void setOd_num(int od_num) {
		this.od_num = od_num;
	}
	public int getFk_o_num() {
		return fk_o_num;
	}
	public void setFk_o_num(int fk_o_num) {
		this.fk_o_num = fk_o_num;
	}
	public int getFk_p_num() {
		return fk_p_num;
	}
	public void setFk_p_num(int fk_p_num) {
		this.fk_p_num = fk_p_num;
	}
	public int getOd_count() {
		return od_count;
	}
	public void setOd_count(int od_count) {
		this.od_count = od_count;
	}
	public String getOd_price() {
		return od_price;
	}
	public void setOd_price(String od_price) {
		this.od_price = od_price;
	}
	
	
}
