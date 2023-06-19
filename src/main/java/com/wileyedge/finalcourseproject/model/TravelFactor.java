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
	
	public static TravelFactor fromInt(int i) {
		switch (i) {
		case 0:
			return TravelFactor.Small_Car;
		case 1:
			return TravelFactor.Heavy_Car;
		case 2:
			return TravelFactor.Trains;
		case 3:
			return TravelFactor.Planes;
		default:
			return TravelFactor.Small_Car;
		}
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
