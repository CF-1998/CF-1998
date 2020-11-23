package com.myProject.EndOfTheJob.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String password;
	private String sex;
	private Date birthday;
	private String address;
	private String email;
	private String tel_num;
	private boolean valid;
    
	
	/*
	 * @OneToMany( fetch=FetchType.LAZY,
	 * //表示抓取策略,默认为FetchType.LAZY,因为关联的多个对象通常不必从数据库预先读取到内存
	 * targetEntity=orderWithUsers.class,//由One的一方指向Many的一方
	 * mappedBy="Users",//这个属性应该等于Many的一方中含有One类的属性的名称
	 * cascade={CascadeType.PERSIST,CascadeType.REMOVE}//表示级联操作策略 ) private
	 * List<orderWithUsers> orderWithUsers = new ArrayList<>();
	 */
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String userName, String password, String sex, Date birthday, String address, String email,
			String tel_num) {
		super();
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
		this.email = email;
		this.tel_num = tel_num;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel_num() {
		return tel_num;
	}
	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
	}
	/*
	 * public List<orderWithUsers> getOrder() { return orderWithUsers; } public void
	 * setOrder(List<orderWithUsers> order) { this.orderWithUsers = order; }
	 */
	@Override
	public String toString() {
		return "Users [id=" + id + ", userName=" + userName + ", password=" + password + ", sex=" + sex + ", birthday="
				+ birthday + ", address=" + address + ", email=" + email + ", tel_num=" + tel_num + ", valid=" + valid
				+ "]";
	}
	
	 

	
}
