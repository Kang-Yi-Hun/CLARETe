package product.domain;

import java.util.List;
import java.util.Map;

import option.domain.OptionVO;

public class ProductVO {
   
   int p_num;            //제품고유번호
   String p_season;      //카테고리(계절)
   String p_name;         //제품명
   String p_ex;         //제품설명
   int p_price;         //가격
   int p_inven;         //재고
   String p_register;      //제품등록일
   String p_release;      //발매일
   String p_sale;         //할인률
   int p_gender;         //성별 향수
   String p_image;         //상품이미지
   String p_detail_image;   //상품상세이미지
   
   
   Map<String, String> opMap;
   
   private List<OptionVO> opVoList;
   
   public List<OptionVO> getOpVoList() {
      return opVoList;
   }
   
   public void setOpVoList(List<OptionVO> opVoList) {
      this.opVoList = opVoList;
   }
   
   public Map<String, String> getOpMap() {
      return opMap;
   }
   public void setOpMap(Map<String, String> opMap) {
      this.opMap = opMap;
   }
   public int getP_num() {
      return p_num;
   }
   public void setP_num(int p_num) {
      this.p_num = p_num;
   }
   public String getP_season() {
      return p_season;
   }
   public void setP_season(String p_season) {
      this.p_season = p_season;
   }
   public String getP_name() {
      return p_name;
   }
   public void setP_name(String p_name) {
      this.p_name = p_name;
   }
   public String getP_ex() {
      return p_ex;
   }
   public void setP_ex(String p_ex) {
      this.p_ex = p_ex;
   }
   public int getP_price() {
      return p_price;
   }
   public void setP_price(int i) {
      this.p_price = i;
   }
   public int getP_inven() {
      return p_inven;
   }
   public void setP_inven(int p_inven) {
      this.p_inven = p_inven;
   }
   public String getP_register() {
      return p_register;
   }
   public void setP_register(String p_register) {
      this.p_register = p_register;
   }
   public String getP_release() {
      return p_release;
   }
   public void setP_release(String p_release) {
      this.p_release = p_release;
   }
   public String getP_sale() {
      return p_sale;
   }
   public void setP_sale(String p_sale) {
      this.p_sale = p_sale;
   }
   public int getP_gender() {
      return p_gender;
   }
   public void setP_gender(int p_gender) {
      this.p_gender = p_gender;
   }
   public String getP_image() {
      return p_image;
   }
   public void setP_image(String p_image) {
      this.p_image = p_image;
   }
   public String getP_detail_image() {
      return p_detail_image;
   }
   public void setP_detail_image(String p_detail_image) {
      this.p_detail_image = p_detail_image;
   }
   
   
   /////////////////////////////////////////////////////
   // *** 제품의 할인률 ***
   public int getDiscountPrice() {
   // 할인율 / 100 >> 백분율
   // 백분율 * 가격 >> 가격에 해당되는 할인 가격
   // 가격 금액 - 가격

   return (int) (p_price - ((Double.parseDouble(p_sale) / 100) * p_price));
   }

   
}
