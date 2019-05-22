package springboot.car_booking;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import springboot.car_details.CarDetails;

@Entity
@Component
public class CarBookingDetails
{
	
	@Id
	@GeneratedValue
    private int bookingId;
	private String personName;
	private double contactNumber;
	private String email;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id")
	private CarDetails carDetails;
	
	

	/*lic void setCardetails(CarDetails cardetails) {
		this.cardetails = cardetails;
	}*/


	

	public CarDetails getCarDetails() {
		return carDetails;
	}


	public void setCarDetails(CarDetails carDetails) {
		this.carDetails = carDetails;
	}


	public CarBookingDetails() {
		
	}
	
	
	public CarBookingDetails(int bookingId, String personName, double contactNumber, String email, Date startDate,
			Date endDate,CarDetails carDetails) {
		super();
		this.bookingId = bookingId;
		this.personName = personName;
		this.contactNumber = contactNumber;
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.carDetails=  carDetails;
	}
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public double getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(double contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	
	
	
	
	
}
