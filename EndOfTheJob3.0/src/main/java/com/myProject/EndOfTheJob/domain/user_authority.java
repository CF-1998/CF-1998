package com.myProject.EndOfTheJob.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class user_authority implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private int authorityId;
	
	public user_authority() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public user_authority(int userId, int authorityId) {
		super();
		this.userId = userId;
		this.authorityId = authorityId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	@Override
	public String toString() {
		return "user_authority [id=" + id + ", userId=" + userId + ", authorityId=" + authorityId + "]";
	}
	
}
