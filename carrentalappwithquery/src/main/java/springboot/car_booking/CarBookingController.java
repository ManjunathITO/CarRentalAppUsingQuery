package springboot.car_booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springboot.car_details.CarDetails;
import springboot.car_details.CarDetailsRepository;
import springboot.car_details.CarDetailsService;

@RestController
@Component
public class CarBookingController
{
  
	HashSet h1 = new HashSet();
	HashSet h2 = new HashSet();
	HashSet h3 = new HashSet();
	HashSet h4 = new HashSet();
	
	
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
		//carbooking.setCardetails(new  CarDetails(id,"","","","",""));
		
		//carbooking.setCarDetails(new  CarDetails(id,""," ",0,0,0 ));
		List<CarDetails> car = carDetailsRepository.findById(id);
		carbooking.setCarDetails(car.get(0));
		
		carBookingService.CreateBooking(carbooking);
	}
	@RequestMapping(method=RequestMethod.GET, value="/viewallbooking")
	public List<CarBookingDetails> getAllinformation()
	{
		List<CarBookingDetails> bookinddetails = carBookingService.getAllcardetails();

        return bookinddetails;
	}
	
	@RequestMapping("/viewbooking/byid/{bookingId}")
	public List<CarBookingDetails> getByBookingId(@PathVariable ("bookingId") int bookingId)
	{
		return carBookingService.getBybookingId(bookingId);
	}
	
	@RequestMapping("/viewbooking/bypersonname/{personName}")
	public List<CarBookingDetails> getByPersonName(@PathVariable ("personName") String personName)
	{
		return carBookingService.getByPersonname(personName);
	}
	
	@RequestMapping("/viewbooking/bymail/{email}")
	public List<CarBookingDetails> getByemail(@PathVariable ("email") String email)
	{
		return carBookingService.getByEmail(email);
	}
	
	@RequestMapping("/viewbooking/bycarid/{id}")
	public CarBookingDetails getByemail(@PathVariable ("id") int id)
	{
		//return carBookingService.getByid(id);
		System.out.println("------------------");
		CarBookingDetails c=carBookingRepository.findOne(id);
		System.out.println(c);
		System.out.println("----------------");
		return c;
				
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/deletebooking/{bookingId}")
	public void deleteBooking(@PathVariable int bookingId)
	{
		carBookingService.deletebooking( bookingId);
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/editbooking/{bookingId}")
	public void EditBookind(@RequestBody CarBookingDetails carbooking, @PathVariable int bookingId)
	{
		
		carBookingService.Editbooking(bookingId, carbooking); 
	}
	/*@RequestMapping(method=RequestMethod.PUT, value="/checkingavailablitycar/{startDate}/{endDate}")
	public List<CarBookingDetails> getBystartDateandendDate(@PathVariable ("startDate") Date startDate,@PathVariable ("endDate") Date endDate)
	{
		return carBookingService.getBystartDateandendDate( startDate, endDate );
	}*/ 
	
	@RequestMapping(method=RequestMethod.GET, value="/checkingavailablitycar/{startDate}/{endDate}")
	public List<CarDetails> getBystartDateandendDate(@PathVariable ("startDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate, 
			@PathVariable ("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate)
	{
		
		List<CarDetails> carr =carBookingService.getBystartDateandendDate( startDate, endDate );
		
		
		return carr;
	}
	
	
	
	
	
	
	
	
	
}
