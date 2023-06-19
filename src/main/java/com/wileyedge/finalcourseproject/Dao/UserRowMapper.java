package com.wileyedge.finalcourseproject.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wileyedge.finalcourseproject.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("userid");
		String username = rs.getString("username");
		String email = rs.getString("email");
		long mobile =  rs.getLong("mobile");
		Date dob = rs.getDate("dob");
		String address = rs.getString("address");
		String password = rs.getString("password");
		
		User e = new User(id, username, email,mobile,dob,address,password);
		return e;
	}

}
