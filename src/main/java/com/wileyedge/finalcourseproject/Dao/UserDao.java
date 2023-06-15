package com.wileyedge.finalcourseproject.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wileyedge.finalcourseproject.model.User;

@Repository
public class UserDao implements IDao {

	private static List<User> users = new ArrayList<User>();
	
	@Override
	public User save(User user) {
		System.out.println("Inside save");
		users.add(user);
		return user;
	}

	@Override
	public List<User> findAll() {
		return users;
	}

}
