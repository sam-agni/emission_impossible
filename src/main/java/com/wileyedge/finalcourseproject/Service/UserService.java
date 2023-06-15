package com.wileyedge.finalcourseproject.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wileyedge.finalcourseproject.Dao.IDao;
import com.wileyedge.finalcourseproject.model.CarbonConsumption;
import com.wileyedge.finalcourseproject.model.TravelConstants;
import com.wileyedge.finalcourseproject.model.TravelFactor;
import com.wileyedge.finalcourseproject.model.User;

@Service
public class UserService implements IService {
	
	@Autowired
	@Qualifier(value="jparepos")
	private IDao dao;
	
	@Override
	public List<User> retrieveAllUsers() {
		System.out.println("Inside retrieveAllUsers() of UserService");
		List<User> users = dao.findAll();
		return users;
	}

	@Override
	public User save(User user) {
		System.out.println("user save service");
		User u = dao.save(user);
		return u;
	}

//	@Override
//	public CarbonConsumption addUserActivity(CarbonConsumption cc) {
//		TravelFactor mode = TravelFactor.Small_Car;
//		//CarbonConsumption activity1 = new CarbonConsumption(1,new Date(),mode,10);
//		dao.addActivity(cc);
//		return cc;
//	}


}
