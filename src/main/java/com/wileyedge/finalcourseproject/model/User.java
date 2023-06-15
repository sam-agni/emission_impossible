package com.wileyedge.finalcourseproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private int userid;
	
	@Column
	private String username;
	
	@Column
	private String email;
	
	@Column
	private long mobile;
	
	@Column()
	private Date dob;
	
	@Column
	private String Address;
	
	public User() {}
	
	public User(int userid, String username, String email, long mobile, Date dob, String address) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		Address = address;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}

}
