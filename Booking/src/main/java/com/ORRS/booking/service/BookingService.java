package com.ORRS.booking.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ORRS.booking.model.Booking;
import com.ORRS.booking.repository.BookRepo;





@Service
public class BookingService {
	
	@Autowired
	BookRepo book;
	
	/**********
	 * Method:                                                add
     *Description:                                            It is used to save booking details into booking collection
     * @param booking:                                   		  booking reference data
	 * @returns booking                               		 It returns added booking with details
	 **********/
	public Booking add(Booking bookDetails)
	{
		return book.save(bookDetails);
	}
	/**********
	 * Method:                                                showAll
     *Description:                                            It is used to fetch all booking available on booking collection
	 * @returns booking                               		 It returns all booking with details
	 **********/
	public List<Booking> showAll()
	{
		List<Booking> booking=new ArrayList<>();
		book.findAll()
		.forEach(booking::add);
		return booking;
	
	}
	/**********
	 * Method:                                                getByUser
     *Description:                                            It is used to fetch all booking available on particular userName
     * @param booking:                                   		 userName of the booking
	 * @returns booking                               		 It returns list of booking with details
	 **********/
	public List<Booking> getByUser(String name)
	{		
		List<Booking> booking=new ArrayList<>();
		List<Booking>bookUser=book.findByusername(name);
		for(Booking bookk:bookUser)
		{
			if(bookk.getDate().isBefore(LocalDate.now()))
					booking.add(bookk);
		}
		return booking;
		}
	/**********
	 * Method:                                                getByPnr
     *Description:                                            It is used to fetch  booking details available on particular Pnr
     * @param booking:                                   		 pnr of the booking
	 * @returns booking                               		 It returns single booking with details
	 **********/
	public Booking getByPnr(String pnr)
	{	
		Booking bookTicket=book.findBypnr(pnr);
		return bookTicket;	
	}
	/**********
	 * Method:                                                deleteByPnr
     *Description:                                            It is used to delete  booking details available on particular Pnr
     * @param booking:                                   		 pnr of the booking
	 **********/
	public void deleteByPnr(String pnr)
	{
		book.deleteBypnr(pnr);
	}
	
	/**********
	 * Method:                                                getUserByDate
     *Description:                                            It is used to fetch  booking details available on particular userName and date of booking is also compared with todays date
     * @param booking:                                   		 userName of the booking
	 * @returns booking                               		 It returns List of bookings with details
	 **********/
	public List<Booking> getUserByDate(String username)
	{
		List<Booking> booking=new ArrayList<>();
				List<Booking>bookUser=book.findByusername(username);
				for(Booking bookk:bookUser)
				{
					if(bookk.getDate().isAfter(LocalDate.now()))
							booking.add(bookk);
				}
				return booking;
	}
	
	public List<Booking> getTrainNameAndDate(String trainName, LocalDate date)
	{
		
		List<Booking>booking=new ArrayList<>();
		List<Booking>train=new ArrayList<>();
		book.findBytrainname(trainName)
		.forEach(train::add);
		for(Booking trainBook:train)
		{
			if(trainBook.getDate().equals(date))
			booking.add(trainBook);
		}
		return booking;
	}
	/**********
	 * Method:                                              getAllUser
   *Description:                                          	it will return all tickets
	 * @returns booking                               		 it will return list of tickets available on booking collection
	 **********/
	public List<Booking> getAllUsers() 
	{	
	return book.findAll();
	}
	/**********
	 * Method:                                              getUserTicket
   *Description:                                          	it will return all tickets available for particular userName
   * @param booking:                                   		 userName  of the booking
	 * @returns booking                               		 it will return list of tickets available on particular userName
	 **********/
	public List<Booking> getUserTicket(String name)
	{	
		List<Booking> trainDate=new ArrayList<>();
		book.findByusername(name)
		.forEach(trainDate::add);
		return trainDate;
	}
	
	
//	
//	
//	  public void sendmail() throws AddressException, MessagingException,
//	  IOException { Properties props = new Properties();
//	  props.put("mail.smtp.auth", "true"); props.put("mail.smtp.starttls.enable",
//	  "true"); props.put("mail.smtp.host", "smtp.gmail.com");
//	  props.put("mail.smtp.port", "587");
//	  
//	  Session session = Session.getInstance(props, new javax.mail.Authenticator() 
//	  {
//	  protected PasswordAuthentication getPasswordAuthentication() { return new
//	  PasswordAuthentication("tarunkumaar00@gmail.com", "feauxpnydasvqdoz");
//	  }
//	  });
//	  Message msg = new MimeMessage(session); msg.setFrom(new  InternetAddress("tarunkumaar00@gmail.com", false));
//	  
//	  msg.setRecipients(Message.RecipientType.TO,
//	  InternetAddress.parse("tarunkumaar00@gmail.com"));
//	  msg.setSubject("Hello Your ticket has been confirmed");
//	  msg.setContent("Hello Your ticket has been confirmed", "text/html"); msg.setSentDate(new
//	  Date());
//	  
//	  MimeBodyPart messageBodyPart = new MimeBodyPart();
//	  messageBodyPart.setContent("Hello Your ticket has been confirmed", "text/html");
//	  
//	  Multipart multipart = new MimeMultipart();
//	  multipart.addBodyPart(messageBodyPart);
//	  
//	  
//	  Transport.send(msg); }
//	 
	}
	