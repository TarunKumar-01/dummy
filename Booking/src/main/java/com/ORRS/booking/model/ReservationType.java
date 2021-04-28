package com.ORRS.booking.model;

public class ReservationType {

	private String type;
	private int reservations;
	private int fare;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getReservations() {
		return reservations;
	}
	public void setReservations(int reservations) {
		this.reservations = reservations;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	@Override
	public String toString() {
		return "ReservationType [type=" + type + ", reservations=" + reservations + ", fare=" + fare + "]";
	}
	public ReservationType(String type, int reservations, int fare) {
		super();
		this.type = type;
		this.reservations = reservations;
		this.fare = fare;
	}
	
	
}
