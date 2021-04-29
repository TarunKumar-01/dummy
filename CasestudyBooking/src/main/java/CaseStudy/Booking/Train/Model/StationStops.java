package CaseStudy.Booking.Train.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class StationStops {

private String stationName;
	
	public StationStops() {
		
	}
	public StationStops(String stationName, LocalTime arrivalTime, LocalTime departureTime, LocalDate localDate) {
		super();
		this.stationName = stationName;
		
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	


}