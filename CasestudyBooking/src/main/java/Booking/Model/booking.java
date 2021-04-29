package Booking.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("booking")
public class booking {
	
	private String pnr;
	private String username;
	private List<passengerList> passengers;
	private String trainname;
	private String source;
	private String destination;
	private LocalDate date;
	private String reservationtype;
	private int fare;
	booking(){
		
	}
	public booking(String pnr, String username, List<passengerList> passengers, String trainname, String source,
			String destination, LocalDate date, String reservationtype,int fare) {
		super();
		int i = ThreadLocalRandom.current().nextInt();   
		pnr=String.valueOf(i);
		this.pnr = pnr;
		this.username = username;
		this.passengers = passengers;
		this.trainname = trainname;
		this.source = source;
		this.destination = destination;
		this.date = date;
		this.reservationtype = reservationtype;
		this.setFare(fare);
	}
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
	public List<passengerList> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<passengerList> passengers) {
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
}