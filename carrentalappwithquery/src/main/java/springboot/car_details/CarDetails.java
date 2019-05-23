package springboot.car_details;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
public class CarDetails
{
	@Id
	private int id;
	private String type;
	private String modalname;
	private int seatcapacity;
	private int price;
	private int mileage;
	//private double tariffForWeekdays;
	//private double tariffForWeekends;
	
	public CarDetails() {
		
	}
	
	public CarDetails(int id, String type, String modalname, int seatcapacity, int price, int mileage) {
		super();
		this.id = id;
		this.type = type;
		this.modalname = modalname;
		this.seatcapacity = seatcapacity;
		this.price = price;
		this.mileage = mileage;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModalname() {
		return modalname;
	}
	public void setModalname(String modalname) {
		this.modalname = modalname;
	}
	public int getSeatcapacity() {
		return seatcapacity;
	}
	public void setSeatcapacity(int seatcapacity) {
		this.seatcapacity = seatcapacity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
}
