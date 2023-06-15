package com.wileyedge.finalcourseproject.model;

public enum TravelFactor{
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
