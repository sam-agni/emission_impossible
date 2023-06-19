package com.wileyedge.finalcourseproject.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class User {

	@Id
	private String username;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile")
	private long mobile;
	
	@Temporal(value = TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name="dob")
	private Date dob;
	
	@Column(name="address")
	private String address;
	
	@Column(name="password")
	private String password; 
	
	public static int idGenerator = 0;
	
	public User() {
		idGenerator=idGenerator+1 ;
	}
	
	public User(int userid, String username, String email, long mobile, Date dob, String address, String password) {
		super();
		this.userid = idGenerator+1;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		this.address = address;
		this.password = password;
		idGenerator=idGenerator+1 ;
	}
	
	public User(String username, String email, long mobile, Date dob, String address, String password) {
		super();
		this.userid = idGenerator+1;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		this.address = address;
		this.password = password;
		idGenerator=idGenerator+1 ;
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
