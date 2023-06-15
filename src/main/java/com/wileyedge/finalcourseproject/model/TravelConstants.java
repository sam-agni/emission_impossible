package com.wileyedge.finalcourseproject.model;

import javax.persistence.Entity;

enum TravelFactor{
	Small_Car(1),
	Heavy_Car(2),
	Trains(3),
	Planes(4);

	int t;
	TravelFactor(int i) {
		t = i;
	}
	public int getValue(){
		return t;
	}
}

public class TravelConstants {
	public static double constantsCalculator(TravelFactor factor) {
		switch(factor) {
			default: // by default its small car 
			case Small_Car: return 145.6; // 145.6g/km
			case Heavy_Car: return 212.5; // 212.5g/km
			case Trains: return 200.5; // for now
			case Planes: return 200.5; // for now	
		}
	}
	
	
}
