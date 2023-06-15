package com.wileyedge.finalcourseproject.Dao;

import java.util.List;

import com.wileyedge.finalcourseproject.model.User;

public interface IDao {
	public User save(User user);
	public List<User> findAll();
}
