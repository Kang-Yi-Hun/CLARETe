package cart.domain;

public class CartVO {

	 int c_num;			//장바구니번호
	 int fk_p_num;		//제품고유번호
	 int fk_op_num;		//옵션번호
	 String fk_m_id;		//userid
	 int c_count;		//제품수량
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public int getFk_p_num() {
		return fk_p_num;
	}
	public void setFk_p_num(int fk_p_num) {
		this.fk_p_num = fk_p_num;
	}
	public int getFk_op_num() {
		return fk_op_num;
	}
	public void setFk_op_num(int fk_op_num) {
		this.fk_op_num = fk_op_num;
	}
	public String getFk_m_id() {
		return fk_m_id;
	}
	public void setFk_m_id(String fk_m_id) {
		this.fk_m_id = fk_m_id;
	}
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	
	 
	 
}
