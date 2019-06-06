package springboot.car_booking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springboot.car_details.CarDetails;

import springboot.car_details.CarDetailsService;
import springboot.randomorg.carrentalapp.exception.NoBookingFoundExecption;
import springboot.randomorg.carrentalapp.exception.NoCarFoundException;



@Service
@Component
public class CarBookingService {

	
	
	

	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	
	@Autowired
	private CarBookingRepository carBookingRepository;
	
	@Autowired
	private CarDetailsService carDetail;
	
	
	
	
public void CreateBooking(CarBookingDetails carbooking) {
	
	 log.debug("Entering the CreateBooking Method ");
	
	Date newStartDate = carbooking.getStartDate();
	Date newEndDate = carbooking.getEndDate();
	int newBookingId = carbooking.getBookingId();
	int carId = carbooking.getCarDetails().getId();
	
	
	
	
		// TODO Auto-generated method stub
	List<CarBookingDetails> test = carBookingRepository.getByBookingdateailfromcarId(carId,newBookingId);
	
	if(test.isEmpty())
	{
		carBookingRepository.save(carbooking);
		log.info("booking is succuesfully created");
	}
	else
	{
		
		for (int i = 0; i < test.size(); i++) {
			
			if(newEndDate.before(test.get(i).getStartDate()) || newStartDate.after(test.get(i).getStartDate()))
			{
				
				carBookingRepository.save(carbooking);
				log.info("booking is succuesfully created");
			}
			else
			{
				
				log.info("this car is not elligeble to create booking");
				
			}
		}
		
	}
	
	
}
	
	public ResponseEntity<?> getAllcardetails() throws NoBookingFoundExecption {
		
		 log.debug("Entering the getAllcardetails Method ");
	
		List<CarBookingDetails> booking = carBookingRepository.getAllBookingDetails();
	/*List<CarBookingDetails> bookingDetailds =new ArrayList();
	carBookingRepository.findAll().forEach(bookingDetailds::add);
	System.out.println("------------------------");
	System.out.println(bookingDetailds.get(0).getStartDate());
	return bookingDetailds;*/
		 log.debug("Exiting the getAllcardetails Method ");
	
		return bookingOcurence(booking,"no bookings found");
}


public ResponseEntity<?> getBybookingId(int bookingId) throws NoBookingFoundExecption {
	
	log.debug("Entering the getBybookingId Method ");
	// TODO Auto-generated method stub
	List<CarBookingDetails> booking =  carBookingRepository.findByBookingId(bookingId);
	log.debug("Exiting the getBybookingId Method ");
	return bookingOcurence(booking," no booking found for this id");
}


public ResponseEntity<?> getByPersonname(String personName) throws NoBookingFoundExecption {
	// TODO Auto-generated method stub
	log.debug("Entering the getByPersonname Method ");
	List<CarBookingDetails> booking =  carBookingRepository.findByPersonName(personName);
	log.debug("Exiting the getByPersonname Method ");
	return bookingOcurence(booking , "no booking found from this personName ");
}


public ResponseEntity<?> getByEmail(String email) throws NoBookingFoundExecption {
	// TODO Auto-generated method stub
	log.debug("Entering the getByEmail Method ");
	List<CarBookingDetails> booking =  carBookingRepository.findByEmail(email);
	log.debug("Exiting the getByEmail Method ");
	return bookingOcurence(booking,  "no booking found from this Email ");
}

public void deletebooking(int bookingId) throws NoBookingFoundExecption  {
	
	List<CarBookingDetails> t = carBookingRepository.findByBookingId(bookingId);
	
	if(t == null || t.isEmpty())
	{
		log.error("no booking is avilable to delete for thid id");
		throw new NoBookingFoundExecption("no booking is avilable to delete for thid id ");
	
	}
	log.info("succesfully deleted booking");
	carBookingRepository.delete(bookingId);
}

public ResponseEntity<?> getByCarId(int id) throws NoBookingFoundExecption{
	log.debug("Entering the getByCarId Method ");
	List<CarBookingDetails> booking = carBookingRepository.getByCarId(id);
	log.debug("Exiting the getByCarId Method ");
	return bookingOcurence(booking,"no booking found for this carId");
}



public void Editbooking(int bookingId, CarBookingDetails carbooking) throws NoBookingFoundExecption
{
	List<CarBookingDetails> t = carBookingRepository.findByBookingId(bookingId);
	if(t == null || t.isEmpty())
	{
		log.error("no booking is avilable to edit for thid id");
		throw new NoBookingFoundExecption("no booking is avilable to edit for thid id ");
	
	}
	
	CreateBooking(carbooking);
	log.info("succesfully edited booking");
	
	
}
	 
	


public ResponseEntity<?> getBystartDateandendDate(Date startDate, Date endDate) throws NoCarFoundException
{
	log.debug("Entering the getBystartDateandendDate Method ");
	
	HashSet h2 = new HashSet();
	// TODO Auto-generated method stub
	System.out.println("------------------------------------------");
	System.out.println(startDate);
	System.out.println(endDate);
	
	h2 = carBookingRepository.getBystartDateandendDate(startDate,endDate);
	
	System.out.println(h2);
	
	Iterator itr2 = h2.iterator();
	List<CarDetails> Detaildss = new ArrayList<CarDetails>();
	while(itr2.hasNext())
	{
		
		Detaildss.add(carDetail.getBycarId((int) itr2.next()));
	}
	
	log.debug("Exiting the getBystartDateandendDate Method ");
	return carDetail.carOcurence(Detaildss,"no car is avilable for this start and endDate");
	

}
public ResponseEntity<?>  bookingOcurence(List<CarBookingDetails> car , String str) throws NoBookingFoundExecption
{
	
	 if(car == null || car.isEmpty())
		{
			log.error("no booking Available");
			
			throw new NoBookingFoundExecption(str);
		}
			
		
	      log.info(" booking Available");
			return new ResponseEntity<>(car, HttpStatus.OK);
		
}



}
