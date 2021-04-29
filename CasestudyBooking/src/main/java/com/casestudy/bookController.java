package com.casestudy;

import java.io.IOException;


import java.time.LocalDate;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import Booking.Model.booking;
import Booking.Model.passengerList;
import CaseStudy.Booking.Train.Model.Train;
import CaseStudy.Booking.Train.Model.reservationType;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Swagger2DemoRestController")
@RequestMapping("/booking")
@CrossOrigin(origins="http://localhost:4200")
public class bookController {

	RestTemplate restTemplate= new RestTemplate();
	@Autowired
	bookingService service;
	
	/**********
	 * Method:                                                addNew
     *Description:                                            It is used to save booking details into booking collection and also it will fetch available reservation vacancy for particular train from train microService 
     * @param booking:                                   		  booking reference data
	 * @throws IOException 
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @returns booking                               		 It returns added booking with details
	 **********/
	@PostMapping("/add")
	public booking addNew(@RequestBody booking book) throws AddressException, MessagingException, IOException
	{
		int a=book.getPassengers().size();
		Train t[]=restTemplate.getForObject("http://localhost:8082/train/getTrainNameWithDate/"+book.getTrainname()+"/"+book.getDate(), Train[].class);
		restTemplate.put("http://localhost:8082/train/book/"+book.getTrainname()+"/"+book.getDate()+"/"+book.getReservationtype()+"/"+a,booking.class);

		int c=0; 

		for(Train e:t)
		{
			for(reservationType d: e.getReservationVacancy())
			{
				if(d.getType().equals(book.getReservationtype())) 
				{ 
					c=d.getReservations(); 	  
	 
				}
			} 
		}
		if(c>0) 
		{
			for(passengerList passenger:book.getPassengers())
			{
				int position=c;
				String seat=Integer.toString(position);
				passenger.setSeatNo(seat);
				c--;
			}
		}
		else
		{
			for(passengerList passenger:book.getPassengers())
			{
		String seat="Waiting";
		passenger.setSeatNo(seat);
			}
		}
		service.sendmail();
		return service.add(book);	
	}
	/**********
	 * Method:                                                show
     *Description:                                            It is used to fetch all booking available on booking collection
	 * @returns booking                               		 It returns all booking with details
	 **********/
	@GetMapping("/all")
	public List<booking> show()
	{
	return service.showAll();	
	}
	
	/**********
	 * Method:                                                getByUserAndDate
     *Description:                                            It is used to fetch  booking details available on particular userName and date of booking is also compared with todays date
     * @param booking:                                   		 userName of the booking
	 * @returns booking                               		 It returns List of bookings with details
	 **********/
	@GetMapping("/getuserbydate/{userName}")
	public List<booking> getByUserAndDate(@PathVariable("userName")String userName)
	{
		return service.getUserByDate(userName);
	}
	
	
	@GetMapping("/bookings/{trainName}/{date}")
	public List<booking> getBytrainNameAndDate(@PathVariable("trainName")String trainName,@PathVariable("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date)
	{
		return service.getTrainNameAndDate(trainName,date);
	}
	
	
	/**********
	 * Method:                                                getByUserName
     *Description:                                            It is used to fetch all booking available on particular userName
     * @param booking:                                   		 userName of the booking
	 * @returns booking                               		 It returns list of booking with details
	 **********/
	@GetMapping("/getuser/{username}")
	public List<booking> getbyUserName(@PathVariable("username")String userName)
	{
	return service.getByUser(userName);	
	}
	
	@GetMapping("/adminuser/{username}")
	public List<booking> getUserByAdmin(@PathVariable("username")String name)
	{
	return service.getUserTicket(name);	
	}

	/**********
	 * Method:                                              getAllUser
   *Description:                                          	it will return all tickets
	 * @returns booking                               		 it will return list of tickets available on booking collection
	 **********/
	@GetMapping("/getalluser")
	public List<booking> getByUserName()
	{
	return service.getAllUsers();	
	}
	
	/**********
	 * Method:                                                del
     *Description:                                            It is used to delete  booking details available on particular Pnr and also it will add cancelled  reservation seats  for particular train to train microService 
     * @param booking:                                   		 pnr of the booking
	 **********/
	
	@DeleteMapping("/del/{pnr}")
	public void del(@PathVariable("pnr")String pnr)
	{
		booking book=service.getByPnr(pnr);
		int a=book.getPassengers().size();
		restTemplate.put("http://localhost:8082/train/cancel/"+book.getTrainname()+"/"+book.getDate()+"/"+book.getReservationtype()+"/"+a,booking.class);
		service.deleteByPnr(pnr);
	}
	/**********
	 * Method:                                                getByPnr
     *Description:                                            It is used to fetch  booking details available on particular Pnr
     * @param booking:                                   		 pnr of the booking
	 * @returns booking                               		 It returns single booking with details
	 **********/
	@GetMapping("/getpnr/{pnr}")
	public booking getbypnr(@PathVariable("pnr")String pnr)
	{
	return service.getByPnr(pnr);	
	}
	//This request will update ticket 
	@PutMapping("/adminquota")
	public booking update(@RequestBody booking book)
	{
		
		int a=book.getPassengers().size();
		Train train[]=restTemplate.getForObject("http://localhost:8082/train/getTrainNameWithDate/"+book.getTrainname()+"/"+book.getDate(), Train[].class);
		book.setReservationtype("1A");
		restTemplate.put("http://localhost:8082/train/book/"+book.getTrainname()+"/"+book.getDate()+"/"+book.getReservationtype()+"/"+a,booking.class);

		int c=0; 

		for(Train trainDetails:train)
		{
			for(reservationType reservation: trainDetails.getReservationVacancy()) 
			{
			  if(reservation.getType().equals(book.getReservationtype())) 
			  { 
			  c=reservation.getReservations(); 	  
			  } 
			 } 
		}
		if(c>0)
		{
		for(passengerList passenger:book.getPassengers())
			{
			int position=c;
			String seat=Integer.toString(position);
			passenger.setSeatNo(seat);
			c--;
		}
	}
		else
		{
			for(passengerList passenger:book.getPassengers())
			{
				
				String seat="Waiting";
				passenger.setSeatNo(seat);
			}
		}
				return service.add(book);	
	}
	}
	

