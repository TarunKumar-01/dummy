package com.ORRS.booking.controller;

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

import com.ORRS.booking.model.Booking;
import com.ORRS.booking.model.PassengerList;
import com.ORRS.booking.model.ReservationType;
import com.ORRS.booking.model.Train;
import com.ORRS.booking.service.BookingService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Swagger2DemoRestController")
@RequestMapping("/booking")
@CrossOrigin(origins="http://localhost:4200")
public class BookController {

	RestTemplate restTemplate= new RestTemplate();
	@Autowired
	BookingService service;
	
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
	public Booking addNew(@RequestBody Booking book) throws AddressException, MessagingException, IOException
	{
		int a=book.getPassengers().size();
		Train t[]=restTemplate.getForObject("http://localhost:8082/train/getTrainNameWithDate/"+book.getTrainname()+"/"+book.getDate(), Train[].class);
		restTemplate.put("http://localhost:8082/train/book/"+book.getTrainname()+"/"+book.getDate()+"/"+book.getReservationtype()+"/"+a,Booking.class);

		int c=0; 

		for(Train e:t)
		{
			for(ReservationType d: e.getReservationVacancy())
			{
				if(d.getType().equals(book.getReservationtype())) 
				{ 
					c=d.getReservations(); 	  
	 
				}
			} 
		}
		if(c>0) 
		{
			for(PassengerList passenger:book.getPassengers())
			{
				int position=c;
				String seat=Integer.toString(position);
				passenger.setSeatNo(seat);
				c--;
			}
		}
		else
		{
			for(PassengerList passenger:book.getPassengers())
			{
		String seat="Waiting";
		passenger.setSeatNo(seat);
			}
		}
//		service.sendmail();
		return service.add(book);	
	}
	/**********
	 * Method:                                                show
     *Description:                                            It is used to fetch all booking available on booking collection
	 * @returns booking                               		 It returns all booking with details
	 **********/
	@GetMapping("/all")
	public List<Booking> show()
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
	public List<Booking> getByUserAndDate(@PathVariable("userName")String userName)
	{
		return service.getUserByDate(userName);
	}
	
	
	@GetMapping("/bookings/{trainName}/{date}")
	public List<Booking> getBytrainNameAndDate(@PathVariable("trainName")String trainName,@PathVariable("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date)
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
	public List<Booking> getbyUserName(@PathVariable("username")String userName)
	{
	return service.getByUser(userName);	
	}
	
	@GetMapping("/adminuser/{username}")
	public List<Booking> getUserByAdmin(@PathVariable("username")String name)
	{
	return service.getUserTicket(name);	
	}

	/**********
	 * Method:                                              getAllUser
   *Description:                                          	it will return all tickets
	 * @returns booking                               		 it will return list of tickets available on booking collection
	 **********/
	@GetMapping("/getalluser")
	public List<Booking> getByUserName()
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
		Booking book=service.getByPnr(pnr);
		int a=book.getPassengers().size();
		restTemplate.put("http://localhost:8082/train/cancel/"+book.getTrainname()+"/"+book.getDate()+"/"+book.getReservationtype()+"/"+a,Booking.class);
		service.deleteByPnr(pnr);
	}
	/**********
	 * Method:                                                getByPnr
     *Description:                                            It is used to fetch  booking details available on particular Pnr
     * @param booking:                                   		 pnr of the booking
	 * @returns booking                               		 It returns single booking with details
	 **********/
	@GetMapping("/getpnr/{pnr}")
	public Booking getbypnr(@PathVariable("pnr")String pnr)
	{
	return service.getByPnr(pnr);	
	}
	//This request will update ticket 
	@PutMapping("/adminquota")
	public Booking update(@RequestBody Booking book)
	{
		
		int a=book.getPassengers().size();
		Train train[]=restTemplate.getForObject("http://localhost:8082/train/getTrainNameWithDate/"+book.getTrainname()+"/"+book.getDate(), Train[].class);
		book.setReservationtype("1A");
		restTemplate.put("http://localhost:8082/train/book/"+book.getTrainname()+"/"+book.getDate()+"/"+book.getReservationtype()+"/"+a,Booking.class);

		int c=0; 

		for(Train trainDetails:train)
		{
			for(ReservationType reservation: trainDetails.getReservationVacancy()) 
			{
			  if(reservation.getType().equals(book.getReservationtype())) 
			  { 
			  c=reservation.getReservations(); 	  
			  } 
			 } 
		}
		if(c>0)
		{
		for(PassengerList passenger:book.getPassengers())
			{
			int position=c;
			String seat=Integer.toString(position);
			passenger.setSeatNo(seat);
			c--;
		}
	}
		else
		{
			for(PassengerList passenger:book.getPassengers())
			{
				
				String seat="Waiting";
				passenger.setSeatNo(seat);
			}
		}
				return service.add(book);	
	}
	}
	

