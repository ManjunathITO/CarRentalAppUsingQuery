package springboot.car_details;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CarDetailsService {

	private List<CarDetails>  Detailds = new ArrayList<>( Arrays.asList(	
			new CarDetails()
			
			
     ));
	
    @Autowired
	private CarDetailsRepository carDetailsRepository;
    
    public void addcardetails(CarDetails cardetails) {
		
		carDetailsRepository.save(cardetails);
		
	}

	public List<CarDetails> getAllcardetails() {
		
		return carDetailsRepository.getAllCars();
		
	}
	
	
	
	
	public List<CarDetails> getByType(String type) {
		return carDetailsRepository.findBycartype(type);
	}
		
		public List<CarDetails> getByModalname(String modalname) {
			return carDetailsRepository.findByModalname(modalname);
	}

		public List<CarDetails> getBycarId(int id) {
			// TODO Auto-generated method stub
			return carDetailsRepository.findById(id);
		}

		public void updateinformation(int type, CarDetails cardetails) {
			// TODO Auto-generated method stub
			carDetailsRepository.save(cardetails);
		}

		public void deleteinformation(int id) {
			// TODO Auto-generated method stub
			List<CarDetails> car = carDetailsRepository.findById(id);
			carDetailsRepository.delete(car);
		}

		public List<CarDetails> getAllInBetween(int id1, int id2) {
			// TODO Auto-generated method stub
			return carDetailsRepository.getInBetween(id1,id2);
		}

		public List<CarDetails> getBylessthenmount(int price) {
			// TODO Auto-generated method stub
			return carDetailsRepository.getBylessthenmount(price);
		}

		public List<CarDetails> getBymorethenmount(int price) {
			// TODO Auto-generated method stub
			return carDetailsRepository.getBymorethenmount(price);
		}   
		
			
}
