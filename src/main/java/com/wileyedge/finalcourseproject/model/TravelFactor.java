package com.wileyedge.finalcourseproject.model;

public enum TravelFactor{
	Small_Car(0),
	Heavy_Car(1),
	Trains(2),
	Planes(3);

	int t;
	TravelFactor(int i) {
		t = i;
	}
	public int getValue(){
		return t;
	}
	
	@Override
	public String toString() {
		String result = "";
		if (this == TravelFactor.Small_Car) {
			result = "Small Car";
		} else if (this == TravelFactor.Heavy_Car) {
			result = "Heavy Car";
		} else if (this == TravelFactor.Trains) {
			result = "Train";
		} else if (this == TravelFactor.Planes) {
			result = "Plane";
		}

		return result;
	}
	
}
