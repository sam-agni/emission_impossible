package com.wileyedge.finalcourseproject.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wileyedge.finalcourseproject.model.CarbonConsumption;
import com.wileyedge.finalcourseproject.model.User;

@Repository
public class UserDao implements IDao {
	
	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	
	private static List<User> users = new ArrayList<User>();
	static {
		users.add(new User("jack_02","jack@gmail.com",88997765, new Date(), "Rhodes Ave, New York City - 100"));
	}
	
	public UserDao() {
		System.out.println("Object of UserDao created");
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public User save(User user) {	
		String sql = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?)";
		template.update(sql, 
				user.getUserid(), 
				user.getUsername(),
				user.getEmail(),
				user.getMobile(),
				user.getDob(),
				user.getAddress());
		return user;
	}

	@Override
	public List<User> findAll() {
		return users;
	}

//	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	public void addActivity(CarbonConsumption activity) {
////		String sql = "INSERT INTO Carbon_Consumption VALUES (?, ?, ?, ?, ?, ?)";
////		template.update(sql, 
////				activity.getCo2EmissionID(),
////				activity.getUserID(),
////				activity.getDate(),
////				activity.getTravelType(),
////				activity.getKmDriven(),
////				activity.getCo2Emission());
//	}

}
