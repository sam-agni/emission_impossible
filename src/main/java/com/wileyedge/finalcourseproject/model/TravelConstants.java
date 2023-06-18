package com.wileyedge.finalcourseproject.model;

import javax.persistence.Entity;

public class TravelConstants {

	public TravelConstants() {}
	public static double constantsCalculator(TravelFactor factor) {
		switch(factor) {
			default: // by default its small car 
			case Small_Car: return 145.6; // 145.6g/km
			case Heavy_Car: return 212.5; // 212.5g/km
			case Trains: return 200.5; // for now
			case Planes: return 200.5; // for now	
		}
	}
	
	public static TravelFactor stringConvert(String name) {
		if(name.equalsIgnoreCase("Small_Car")) {
			return TravelFactor.Small_Car;
		}
		else if (name.equalsIgnoreCase("Heavy_Car")) {
			return TravelFactor.Heavy_Car;
		}
		else if(name.equalsIgnoreCase("Trains")) {
			return TravelFactor.Trains;
		}
		else if(name.equalsIgnoreCase("Planes")) {
			return TravelFactor.Planes;
		}
		return null;
	}
	
	
}
