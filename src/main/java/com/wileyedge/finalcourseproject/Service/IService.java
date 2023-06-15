package com.wileyedge.finalcourseproject.Service;

import java.util.List;

import com.wileyedge.finalcourseproject.model.User;

public interface IService {
	public List<User> retrieveAllUsers();
	public User save(User user);
}
