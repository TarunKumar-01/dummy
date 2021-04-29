package CaseStudy.Booking.Train.Model;

public class reservationType {
	
	private String type;
	private int reservations;
	private int fare;
	reservationType(){
		
	}
	public reservationType(String type, int reservations, int fare) {
		super();
		this.type = type;
		this.reservations = reservations;
		this.fare = fare;
	}
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
	
}