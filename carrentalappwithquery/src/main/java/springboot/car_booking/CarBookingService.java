package springboot.car_booking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springboot.car_details.CarDetails;
import springboot.car_details.CarDetailsRepository;
import springboot.car_details.CarDetailsService;



@Service
@Component
public class CarBookingService {

	
	private List<CarBookingDetails>  bookingDetailds = new ArrayList<>( Arrays.asList(	
			new CarBookingDetails()
			));
	
	HashSet h1 = new HashSet();
	HashSet h2 = new HashSet();
	HashSet h3 = new HashSet();
	//HashSet h4 = new HashSet();
	
	
	
	
	@Autowired
	private CarBookingRepository carBookingRepository;
	
	@Autowired
	private CarDetailsService carDetail;
	
	@Autowired
	private CarDetailsRepository carDetailsRepository;
	
	
public void CreateBooking(CarBookingDetails carbooking) {
	
	Date newStartDate = carbooking.getStartDate();
	Date newEndDate = carbooking.getEndDate();
	int newBookingId = carbooking.getBookingId();
	int carId = carbooking.getCarDetails().getId();
	
	System.out.println("===================================");
	System.out.println("new start date "+ newStartDate);
	System.out.println("===================================");
	
	System.out.println("--------------------");
	System.out.println(carId);
	
	
		// TODO Auto-generated method stub
	List<CarBookingDetails> test = carBookingRepository.getByBookingdateailfromcarId(carId,newBookingId);
	
	if(test.isEmpty())
	{
		carBookingRepository.save(carbooking);
	}
	else
	{
		
		for (int i = 0; i < test.size(); i++) {
			
			if(newEndDate.before(test.get(i).getStartDate()) || newStartDate.after(test.get(i).getStartDate()))
			{
				System.out.println("eligeble");
				carBookingRepository.save(carbooking);
			}
			else
			{
				System.out.println("--------------------");
				System.out.println("not eligeble");
				
				test.notify();
			}
		}
		
	}
	
	
	}
	
	
	
	
		
	


public List<CarBookingDetails> getAllcardetails() {
	
	return carBookingRepository.getAllBookingDetails();
	/*List<CarBookingDetails> bookingDetailds =new ArrayList();
	carBookingRepository.findAll().forEach(bookingDetailds::add);
	System.out.println("------------------------");
	System.out.println(bookingDetailds.get(0).getStartDate());
	return bookingDetailds;*/
	
	
}


public List<CarBookingDetails> getBybookingId(int bookingId) {
	// TODO Auto-generated method stub
	return carBookingRepository.findByBookingId(bookingId);
}


public List<CarBookingDetails> getByPersonname(String personName) {
	// TODO Auto-generated method stub
	return carBookingRepository.findByPersonName(personName);
}


public List<CarBookingDetails> getByEmail(String email) {
	// TODO Auto-generated method stub
	return carBookingRepository.findByEmail(email);
}

public void deletebooking(int bookingId) {
	
	List<CarBookingDetails> t=carBookingRepository.findByBookingId(bookingId);
	carBookingRepository.delete(bookingId);
}


public void Editbooking(int bookingId, CarBookingDetails carbooking) {
	
		
	
	
	Date newStartDate = carbooking.getStartDate();
	Date newEndDate = carbooking.getEndDate();
	int newBookingId = carbooking.getBookingId();
	int carId = carbooking.getCarDetails().getId();
	
	
	List<CarBookingDetails> test = carBookingRepository.getByBookingdateailfromcarId(carId,newBookingId);
	
	if(test.isEmpty())
	{
		carBookingRepository.save(carbooking);
	}
	else
	{
		
		for (int i = 0; i < test.size(); i++) {
			
			if(newEndDate.before(test.get(i).getStartDate()) || newStartDate.after(test.get(i).getStartDate()))
			{
				System.out.println("eligeble");
				
			}
			else
			{
				System.out.println("--------------------");
				System.out.println("not eligeble");
				carBookingRepository.save(carbooking);
				test.notify();
			}
		
		}
	}
	
}
	
	


public List<CarDetails> getBystartDateandendDate(Date startDate, Date endDate)
{
	
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
		
		Detaildss.addAll(carDetail.getBycarId((int) itr2.next()));
	}
	
	
	return  Detaildss;
	

}

}
