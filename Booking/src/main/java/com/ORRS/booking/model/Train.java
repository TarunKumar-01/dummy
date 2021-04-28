package com.ORRS.booking.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;



@Document("train")
public class Train {
	

	private long trainNo;
	
	private String trainName;
	
	private String source;

	private String destination;
	
	private LocalDate date;
	private LocalTime arrivalTime;
	private LocalTime departureTime;

	private int duration;
	private LocalTime destinationTime;
private List<ReservationType> reservationVacancy;
private List<StationStops> stops;

public Train(long trainNo, String trainName, String source, String destination, LocalDate date,
		LocalTime arrivalTime, LocalTime departureTime, int duration, LocalTime destinationTime,
		List<ReservationType> reservationVacancy, List<StationStops> stops) {
	super();
	this.trainNo = trainNo;
	this.trainName = trainName;
	this.source = source;
	this.destination = destination;
	this.date = date;
	this.arrivalTime = arrivalTime;
	this.departureTime = departureTime;
	this.duration = duration;
	this.destinationTime = destinationTime;
	this.reservationVacancy = reservationVacancy;
	this.stops = stops;
}
public long getTrainNo() {
	return trainNo;
}
public void setTrainNo(long trainNo) {
	this.trainNo = trainNo;
}
public String getTrainName() {
	return trainName;
}
public void setTrainName(String trainName) {
	this.trainName = trainName;
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
public LocalTime getArrivalTime() {
	return arrivalTime;
}
public void setArrivalTime(LocalTime arrivalTime) {
	this.arrivalTime = arrivalTime;
}
public LocalTime getDepartureTime() {
	return departureTime;
}
public void setDepartureTime(LocalTime departureTime) {
	this.departureTime = departureTime;
}
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public LocalTime getDestinationTime() {
	return destinationTime;
}
public void setDestinationTime(LocalTime destinationTime) {
	this.destinationTime = destinationTime;
}
public List<ReservationType> getReservationVacancy() {
	return reservationVacancy;
}
public void setReservationVacancy(List<ReservationType> reservationVacancy) {
	this.reservationVacancy = reservationVacancy;
}
public List<StationStops> getStops() {
	return stops;
}
public void setStops(List<StationStops> stops) {
	this.stops = stops;
}

}
