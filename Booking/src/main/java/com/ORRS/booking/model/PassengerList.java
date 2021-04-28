package com.ORRS.booking.model;

public class PassengerList {

	private String name;
	private int age;
	private String gender;
	private String city;
	private String seatNo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	@Override
	public String toString() {
		return "PassengerList [name=" + name + ", age=" + age + ", gender=" + gender + ", city=" + city + ", seatNo="
				+ seatNo + "]";
	}
	public PassengerList(String name, int age, String gender, String city, String seatNo) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.city = city;
		this.seatNo = seatNo;
	}
	
}
