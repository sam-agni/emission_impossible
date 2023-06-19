package com.wileyedge.finalcourseproject.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wileyedge.finalcourseproject.Dao.IDao;
import com.wileyedge.finalcourseproject.exceptions.RegistrationFailedException;
import com.wileyedge.finalcourseproject.exceptions.UserNotFoundException;
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
	public String register(User user) throws RegistrationFailedException {
		User u = dao.save(user);
		if (u == null) {
			throw new RegistrationFailedException();
		}
		return u.getUsername();
	}

	@Override
	public String login(Map<String, String> credentials) throws UserNotFoundException {
		User u = dao.getUserByCredentials(credentials.get("username"), credentials.get("password"));
		if (u == null) {
			throw new UserNotFoundException();
		}
		return u.getUsername();
	}

	@Override
	public User getUserProfile(String username) throws UserNotFoundException {
		User u = dao.getUserByUsername(username);
		if (u == null) {
			throw new UserNotFoundException();
		}
		return u;
	}

	@Override
	public void editUserProfile(String username, User newInfo) {
		dao.editUserbyUsername(username, newInfo);
	}

	@Override
	public List<Map<String, String>> getActivitiesOnDay(String username, Date date) throws UserNotFoundException {
		List<Map<String, String>> result = new ArrayList<>();
		List<CarbonConsumption> activities = dao.getAllCarbonConsumptionByDay(username, date);
		if (activities == null) {
			throw new UserNotFoundException();
		}
		Map<String, String> activityAndCarbon = null;
		String activity = null;
		for (CarbonConsumption cc : activities) {
			activityAndCarbon = new HashMap<>();
			activity = cc.getKmDriven() + "km Journey by " + cc.getTravelType();
			activityAndCarbon.put("activity", activity);
			activityAndCarbon.put("carbonAmount", cc.getCo2Emission().toString());
			result.add(activityAndCarbon);
			activityAndCarbon = null;
			activity = null;
		}
		
		return result;
	}

	@Override
	public Map<String, String> getEmissionsDuringPeriod(String username, Date dateStart, Date dateEnd) throws UserNotFoundException {
		List<CarbonConsumption> activities = dao.getAllCarbonConsumptionDuringPeriod(username, dateStart, dateEnd);
		if (activities == null) {
			throw new UserNotFoundException();
		}
		Long bySmallCar = (long) 0, byHeavyCar = (long) 0, byTrain = (long) 0;
		Long byPlane = (long) 0, total = (long) 0;
		for (CarbonConsumption cc : activities) {
			if (cc.getTravelType() == TravelFactor.Small_Car) {
				bySmallCar += cc.getCo2Emission();
			} else if (cc.getTravelType() == TravelFactor.Heavy_Car) {
				byHeavyCar += cc.getCo2Emission();
			} else if (cc.getTravelType() == TravelFactor.Trains) {
				byTrain += cc.getCo2Emission();
			} else if (cc.getTravelType() == TravelFactor.Planes) {
				byPlane += cc.getCo2Emission();
			}
			total += cc.getCo2Emission();
		}
		Map<String, String> result = new HashMap<>();
		result.put("bySmallCar", bySmallCar.toString());
		result.put("byHeavyCar", byHeavyCar.toString());
		result.put("byTrain", byTrain.toString());
		result.put("byPlane", byPlane.toString());

		return result;
	}

	@Override
	public void addNewEmissionsEntry(String username, Map<String, String> entry) {
		// TODO Auto-generated method stub
		
	}
	
	
	/*
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

	@Override
	public CarbonConsumption addUserActivity(CarbonConsumption cc) {
		TravelFactor mode = TravelFactor.Small_Car;
		//CarbonConsumption activity1 = new CarbonConsumption(1,new Date(),mode,10);
		dao.addActivity(cc);
		return cc;
	}
	*/

}
