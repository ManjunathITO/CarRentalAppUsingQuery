	package springboot.car_details;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import springboot.car_booking.CarBookingDetails;
import springboot.car_booking.CarBookingRepository;
import springboot.log.LogMarker;
import springboot.randomorg.carrentalapp.exception.NoBookingFoundExecption;
import springboot.randomorg.carrentalapp.exception.NoCarFoundException;
//import springboot.randomorg.carrentalapp.exception.handleHttpRequestMethodNotSupported;

@RestController
@Component
public class CarDetailsController
{
	@Autowired
	private CarDetailsService carDetailsService;
	
	@Autowired
	private CarBookingRepository carBookingRepository;

	 Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	
	 
	
	@RequestMapping(method=RequestMethod.POST, value="/carinformation")
	public void adddetails(@RequestBody CarDetails cardetails )
	{
		ResponseEntity<?> car = carDetailsService.addcardetails(cardetails);
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/carinformation")
	public ResponseEntity<?> getAllinformation() throws NoCarFoundException
	{
		log.debug("Gettind All the car deatils");
		
		return carDetailsService.getAllcardetails();
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/carinformation/inbetween/{id1}/{id2}")
	public List<CarDetails> getAllInBetween(@PathVariable ("id1") int id1,@PathVariable ("id2") int id2)
	{
		return carDetailsService.getAllInBetween(id1,id2);
		
	}
	
	@RequestMapping("/carinformation/bytype/{type}")
	public ResponseEntity<?> getByType(@PathVariable ("type") String type) throws NoCarFoundException
	{
		return carDetailsService.getByType(type);
	}
	
	@RequestMapping("/carinformation/bymodal/{modalname}")
	public ResponseEntity<?> getModalname(@PathVariable ("modalname") String modalname) throws NoCarFoundException
	{
		return carDetailsService.getByModalname(modalname);
	}
	
	@RequestMapping("/carinformation/lessthenmount/{price}")
	public ResponseEntity<?> getBylessthenmount(@PathVariable ("price") int price) throws NoCarFoundException
	{
		return carDetailsService.getBylessthenmount(price);
	}
	
	@RequestMapping("/carinformation/morethenmount/{price}")
	public ResponseEntity<?> getBymorethenmount(@PathVariable ("price") int price) throws NoCarFoundException
	{
		return carDetailsService.getBymorethenmount(price);
	}
	
	
	
	@RequestMapping("/carinformation/bycarid/{id}")
	public ResponseEntity<?> getById(@PathVariable ("id") int id) throws NoCarFoundException
	{
		CarDetails car = carDetailsService.getBycarId(id);
		
	System.out.println(car);
		
		if(car == null)
		{
			
			log.error("No car found");
			throw new NoCarFoundException("no Car found for this id");
		}
		log.info("Car Details found");
		return new ResponseEntity<>(car, HttpStatus.OK);
     }
	
	@RequestMapping(method=RequestMethod.PUT,value="/carinformation/updatecar/{id}")
	public String updateinformation(@RequestBody CarDetails cardetails, @PathVariable int id) throws NoCarFoundException
	{
		CarDetails car = carDetailsService.getBycarId(id);
		if(car == null)
		{
			log.error("No car found to update");
			throw new NoCarFoundException("bo car found to update for this id ");
		
		}
		log.info("Car Details found to update");
		carDetailsService.updateinformation(id, cardetails); 
		String response = "{\"success\": true, \"message\": Car has been added successfully.}";
        return response;
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/carinformation/delete/{id}")
	public void deleteinformation(@PathVariable int id) throws NoCarFoundException
	{
		CarDetails car = carDetailsService.getBycarId(id);
		if(car == null)
		{
			log.error("No car found to delete");
			throw new NoCarFoundException("no car found to delete for this id");
		
		}
		else
		{
		List<CarBookingDetails> booking = carBookingRepository.getByCarId(id);
		
		if(booking == null || booking.isEmpty())
		{
			
			log.info("Car Details found to delete");
			carDetailsService.deleteinformation(id);
		}
		else
			log.info("we can not delete this, car as been booked alreday for other customer");
	    throw new NoCarFoundException("we can not delete this, car as been booked alreday for other customer");
		}
			
		
	    
		
	}
	
}
