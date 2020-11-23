package com.myProject.EndOfTheJob.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class orderWithUsers implements Serializable {
	private static final long serialVersionUID = 1L;
	//	订单实体类
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String order_id;//订单号
	private String username;//收货人姓名
	private String userAddress;//收货地址
	private String userEmail;//收货人邮箱
	private String userTel;//收货人联系方式
	private int shoppingKind;//商品种类数量
	private int shoppingNum;//商品数量
	private double price;//商品的总金额
	
	/*
	 * //订单与商品-图书是一对多的关系 // 订单与用户之间是多对一的关系
	 * 
	 * @JsonIgnore //图书与图书类型之间是多对一
	 * 
	 * @ManyToOne //@JoinColumn 注解的作用：用来指定与所操作实体或实体集合相关联的数据库表中的列字段。
	 * //name属性指明Comment对应的表中的列kindOfBooks_id做为外键，
	 * referencedColumnName指明kindOfBooks对应的表中列id为主键
	 * 
	 * @JoinColumn(name="Users_id",referencedColumnName="id") private Users Users;
	 */
	public orderWithUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public orderWithUsers( String order_id, String username, String userAddress, String userEmail,
			String userTel, int shoppingKind, int shoppingNum, double price) {
		super();
		this.order_id = order_id;
		this.username = username;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userTel = userTel;
		this.shoppingKind = shoppingKind;
		this.shoppingNum = shoppingNum;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public int getShoppingKind() {
		return shoppingKind;
	}

	public void setShoppingKind(int shoppingKind) {
		this.shoppingKind = shoppingKind;
	}

	public int getShoppingNum() {
		return shoppingNum;
	}

	public void setShoppingNum(int shoppingNum) {
		this.shoppingNum = shoppingNum;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "orderWithUsers [id=" + id + ", order_id=" + order_id + ", username=" + username + ", userAddress="
				+ userAddress + ", userEmail=" + userEmail + ", userTel=" + userTel + ", shoppingKind=" + shoppingKind
				+ ", shoppingNum=" + shoppingNum + ", price=" + price + "]";
	}

	




}
