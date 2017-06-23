package vo;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mem")
public class Mem_VO  implements Serializable {
	@Id
	private int mem_id;
	private	String mem_name;
	private String mem_mail;
	private byte[] mem_pwd;
	private byte[] mem_photo;
	private String mem_info;
	private java.util.Date mem_regtime;
	
	//MEM_VO 新增的部分
	
	private ShoppingCart shoppingCart;
	private List<MemShipping> memShippingList;
	private List<UserPayment> UserPaymentList;
	private List<Order> orderList;
	
	
	@Override
	public String toString() {
		return "Mem_VO [mem_id=" + mem_id + ", mem_name=" + mem_name + ", mem_mail=" + mem_mail + ", mem_pwd="
				+ Arrays.toString(mem_pwd) + ", mem_photo=" + Arrays.toString(mem_photo) + ", mem_info=" + mem_info
				+ ", mem_regtime=" + mem_regtime + "]";
	}
	public int getmem_id() {
		return mem_id;
	}
	public void setmem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public String getmem_name() {
		return mem_name;
	}
	public void setmem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getmem_mail() {
		return mem_mail;
	}
	public void setmem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public byte[] getmem_pwd() {
		return mem_pwd;
	}
	public void setmem_pwd(byte[] mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public byte[] getmem_photo() {
		return mem_photo;
	}
	public void setmem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}
	public String getmem_info() {
		return mem_info;
	}
	public void setmem_info(String mem_info) {
		this.mem_info = mem_info;
	}
	public java.util.Date getmem_regtime() {
		return mem_regtime;
	}
	public void setmem_regtime(java.util.Date mem_regtime) {
		this.mem_regtime = mem_regtime;
	}
	
	
	//MEM_VO 新增的部分
	
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public List<MemShipping> getMemShippingList() {
		return memShippingList;
	}
	public void setMemShippingList(List<MemShipping> memShippingList) {
		this.memShippingList = memShippingList;
	}
	public List<UserPayment> getUserPaymentList() {
		return UserPaymentList;
	}
	public void setUserPaymentList(List<UserPayment> userPaymentList) {
		UserPaymentList = userPaymentList;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	


}
