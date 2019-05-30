package springboot.car_booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springboot.car_details.CarDetails;
import springboot.car_details.CarDetailsRepository;
import springboot.car_details.CarDetailsService;
import springboot.randomorg.carrentalapp.exception.NoBookingFoundExecption;
import springboot.randomorg.carrentalapp.exception.NoCarFoundException;

@RestController
@Component
public class CarBookingController
{
  
	
	
	@Autowired
	private CarBookingService carBookingService;
	
	@Autowired
	private CarDetailsService carDetail;
	@Autowired
	private CarDetailsRepository carDetailsRepository;
	
	@Autowired
	private CarBookingRepository carBookingRepository;
	
	
	
	@RequestMapping(method=RequestMethod.POST, value="/createbooking/{id}")
	public void createbooking(@RequestBody CarBookingDetails carbooking,@PathVariable  int id)
	{
		
		CarDetails car = carDetailsRepository.findById(id);
		carbooking.setCarDetails(car);
		
		carBookingService.CreateBooking(carbooking);
	}
	@RequestMapping(method=RequestMethod.GET, value="/viewallbooking")
	public ResponseEntity<?> getAllinformation() throws NoBookingFoundExecption
	{
		ResponseEntity<?> bookinddetails = carBookingService.getAllcardetails();

        return bookinddetails;
	}
	
	@RequestMapping("/viewbooking/byid/{bookingId}")
	public ResponseEntity<?> getByBookingId(@PathVariable ("bookingId") int bookingId) throws NoBookingFoundExecption
	{
		return carBookingService.getBybookingId(bookingId);
	}
	
	@RequestMapping("/viewbooking/0/{personName}")
	public ResponseEntity<?> getByPersonName(@PathVariable ("personName") String personName) throws NoBookingFoundExecption
	{
		return carBookingService.getByPersonname(personName);
	}
	
	@RequestMapping("/viewbooking/bymail/{email}")
	public ResponseEntity<?> getByemail(@PathVariable ("email") String email) throws NoBookingFoundExecption
	{
		return carBookingService.getByEmail(email);
	}
	
	@RequestMapping("/viewbooking/bycarid/{id}")
	public ResponseEntity<?> getByemail(@PathVariable ("id") int id) throws NoBookingFoundExecption
	{
		return carBookingService.getByCarId(id);
				
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/deletebooking/{bookingId}")
	public void deleteBooking(@PathVariable int bookingId) throws NoBookingFoundExecption
	{
		carBookingService.deletebooking( bookingId);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/editbooking/{bookingId}")
	public void EditBookind(@RequestBody CarBookingDetails carbooking, @PathVariable int bookingId) throws NoBookingFoundExecption
	{
		
		carBookingService.Editbooking(bookingId, carbooking); 
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/checkingavailablitycar/{startDate}/{endDate}")
	public ResponseEntity<?> getBystartDateandendDate(@PathVariable ("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate, 
			@PathVariable ("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) throws NoCarFoundException
	{
		
		return carBookingService.getBystartDateandendDate( startDate, endDate );
	
	}
	
}
