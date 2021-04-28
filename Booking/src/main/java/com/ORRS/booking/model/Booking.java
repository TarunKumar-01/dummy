package com.ORRS.booking.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.ORRS.booking.model.PassengerList;
@Document("booking")
public class Booking {

	

	private String pnr;
	private String username;
	private List<PassengerList> passengers;
	private String trainname;
	private String source;
	private String destination;
	private LocalDate date;
	private String reservationtype;
	private int fare;
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<PassengerList> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerList> passengers) {
		this.passengers = passengers;
	}
	public String getTrainname() {
		return trainname;
	}
	public void setTrainname(String trainname) {
		this.trainname = trainname;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getReservationtype() {
		return reservationtype;
	}
	public void setReservationtype(String reservationtype) {
		this.reservationtype = reservationtype;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	@Override
	public String toString() {
		return "Booking [pnr=" + pnr + ", username=" + username + ", passengers=" + passengers + ", trainname="
				+ trainname + ", source=" + source + ", destination=" + destination + ", date=" + date
				+ ", reservationtype=" + reservationtype + ", fare=" + fare + "]";
	}
	public Booking(String pnr, String username, List<PassengerList> passengers, String trainname, String source,
			String destination, LocalDate date, String reservationtype, int fare) {
		super();
		this.pnr = pnr;
		this.username = username;
		this.passengers = passengers;
		this.trainname = trainname;
		this.source = source;
		this.destination = destination;
		this.date = date;
		this.reservationtype = reservationtype;
		this.fare = fare;
	}
	
	
}
