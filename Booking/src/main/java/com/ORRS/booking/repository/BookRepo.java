package com.ORRS.booking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ORRS.booking.model.Booking;



@Repository
public interface BookRepo extends MongoRepository<Booking,String> {

	void deleteBypnr(String name);
	List<Booking> findByusername(String name);
	Booking findBypnr(String pnr);
	List<Booking> findBytrainname(String trainName);
	
}