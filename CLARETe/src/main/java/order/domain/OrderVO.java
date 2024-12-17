package order.domain;

import cart.domain.CartVO;
import delivery.domain.DeliveryVO;
import member.domain.MemberVO;
import option.domain.OptionVO;
import product.domain.ProductVO;

public class OrderVO {
	int o_num; // �ֹ���ȣ
	String fk_m_id; // fk_userid
	int fk_d_num; // �������ȣ
	int fk_op_num; //  �ɼǹ�ȣ
	String o_date; // �ֹ���¥
	int status; // �����Ȳ �����0, �����1, �����2
	String o_price; // �ֹ��ݾ�
	
// MemberVO 조인	
	private MemberVO membervo;

	public MemberVO getMembervo() {
		return membervo;
	}
	public void setMembervo(MemberVO membervo) {
		this.membervo = membervo;
	}
	
	
// DeliveryVO 조인 
	private DeliveryVO deliveryvo;

	public DeliveryVO getDeliveryvo() {
		return deliveryvo;
	}

	public void setDeliveryvo(DeliveryVO deliveryvo) {
		this.deliveryvo = deliveryvo;
	}
	

// OptionVO 조인
	private OptionVO optionvo;

	public OptionVO getOptionvo() {
		return optionvo;
	}

	public void setOptionvo(OptionVO optionvo) {
		this.optionvo = optionvo;
	}
	

// ProductVO 조인 
	private ProductVO productvo;

	public ProductVO getProductvo() {
		return productvo;
	}

	public void setProductvo(ProductVO productvo) {
		this.productvo = productvo;
	}
	
	
// CartVO 조인 
	private CartVO cartvo;

	public CartVO getCartvo() {
		return cartvo;
	}

	public void setCartvo(CartVO cartvo) {
		this.cartvo = cartvo;
	}
	
	
	

	public int getO_num() {
		return o_num;
	}
	public void setO_num(int o_num) {
		this.o_num = o_num;
	}
	public String getFk_m_id() {
		return fk_m_id;
	}
	public void setFk_m_id(String fk_m_id) {
		this.fk_m_id = fk_m_id;
	}
	public int getFk_d_num() {
		return fk_d_num;
	}
	public void setFk_d_num(int fk_d_num) {
		this.fk_d_num = fk_d_num;
	}
	public int getFk_op_num() {
		return fk_op_num;
	}
	public void setFk_op_num(int fk_op_num) {
		this.fk_op_num = fk_op_num;
	}
	public String getO_date() {
		return o_date;
	}
	public void setO_date(String o_date) {
		this.o_date = o_date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getO_price() {
		return o_price;
	}
	public void setO_price(String o_price) {
		this.o_price = o_price;
	}

	
}
