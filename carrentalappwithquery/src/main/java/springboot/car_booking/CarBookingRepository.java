
package springboot.car_booking;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springboot.car_details.CarDetails;

//import springboot.car_details.CarDetails;

@Repository
public interface CarBookingRepository extends CrudRepository<CarBookingDetails, Integer>
{

	public List<CarBookingDetails> findByBookingId(int bookingId);

	public List<CarBookingDetails> findByPersonName(String personName);

	public List<CarBookingDetails> findByEmail(String email);
	
	//public List<CarBookingDetails> findBystartDateandendDate(Date startDate,Date endDate );
	
	public List<CarBookingDetails> findBystartDate(Date startDate);

	@Query(value = "select * from car_booking_details b where b.id=?1 and b.booking_id !=?2" , nativeQuery = true)
	public List<CarBookingDetails> getByBookingdateailfromcarId(int carId, int newBookingId);

	@Query(value = "select * from car_booking_details" , nativeQuery = true)
	public List<CarBookingDetails> getAllBookingDetails();

	
	/*@Query(value = "select c.id from carbooking.car_details c where c.id not in (SELECT b.id FROM carbooking.car_booking_details b where not (b.start_date >:endDate or b.end_date <:start))" , nativeQuery = true)
	public HashSet getBystartDateandendDate(@Param("start")Date startDate,@Param("endDate") Date endDate);*/
	
	@Query(value = "select c.id from carbooking.car_details c where c.id not in (SELECT b.id FROM carbooking.car_booking_details b where not (b.start_date > ?2 or b.end_date < ?1))" , nativeQuery = true)
	public HashSet getBystartDateandendDate(Date startDate,Date endDate);

	@Query(value = "select * from car_booking_details b where b.id=?1 and b.booking_id =?2" , nativeQuery = true)
	public List<CarBookingDetails> getByBookingdateailforedit(int carId, int newBookingId);

	@Query(value = "SELECT * FROM carbooking.car_booking_details b where b.id=?1" , nativeQuery = true)
	public List<CarBookingDetails> getByCarId(int id);

	
   
}
