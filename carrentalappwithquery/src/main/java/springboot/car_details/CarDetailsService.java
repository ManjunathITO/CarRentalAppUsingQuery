package springboot.car_details;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springboot.randomorg.carrentalapp.exception.InputErrors;

import springboot.randomorg.carrentalapp.exception.NoCarFoundException;

@Service
@Component
public class CarDetailsService {

	
			

	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
    @Autowired
	private CarDetailsRepository carDetailsRepository;
    
    public ResponseEntity<?> addcardetails(CarDetails cardetails)
    {
    log.debug("Entering the addcardetails Method ");
    	
    	
    	if(cardetails.getId() == 0 || cardetails.getMileage() == 0 || cardetails.getModalname() == null ||
    			cardetails.getPrice() == 0 || cardetails.getSeatcapacity()==0 ||  cardetails.getType() == null)
    		
    	{
    		log.error("no proper intputs are entered");
    		throw new InputErrors();
    		
    	}
    	CarDetails	car =carDetailsRepository.save(cardetails);
    	log.debug("Exiting from addcardetails Method ");
    	return new ResponseEntity<>(car, HttpStatus.OK);
		
}

	public ResponseEntity<?> getAllcardetails() throws NoCarFoundException {
		
		log.debug("Entering the getAllcardetails Method ");
		List<CarDetails>	car = carDetailsRepository.getAllCars();
		log.debug("Exiting the getAllcardetails Method ");
		return carOcurence(car,"all");
	}
	
	
	
	
	public 		ResponseEntity<?> getByType(String type) throws NoCarFoundException {
		
		
		log.debug("Entering the getByType Method ");
		List<CarDetails>	car = carDetailsRepository.findBycartype(type);
		log.debug("Exiting the getByType Method ");
		return carOcurence(car,"no car found for this type");
		
     }
		
	
		
		public ResponseEntity<?> getByModalname(String modalname) throws NoCarFoundException {
			log.debug("Entering the getByModalname Method ");
			List<CarDetails>	car =carDetailsRepository.findByModalname(modalname);
			log.debug("Exiting the getByModalname Method ");
			return carOcurence(car,"no car found for this modal");
	}

		public CarDetails getBycarId(int id) {
			log.debug("Entering the getBycarId Method ");
			// TODO Auto-generated method stub
			CarDetails car =  carDetailsRepository.findById(id);
			log.debug("Exiting the getBycarId Method ");
			return car;
		}

		public void updateinformation(int type, CarDetails cardetails) {
			// TODO Auto-generated method stub
			log.debug("Entering the updateinformation Method ");
			carDetailsRepository.save(cardetails);
			log.debug("Exiting the updateinformation Method ");
		}

		public void deleteinformation(int id) {
			log.debug("Entering the deleteinformation Method ");
			// TODO Auto-generated method stub
			CarDetails car = carDetailsRepository.findById(id);
			carDetailsRepository.delete(car);
			log.debug("Exiting the deleteinformation Method ");
		}

		public List<CarDetails> getAllInBetween(int id1, int id2) {
			// TODO Auto-generated method stub
			log.debug("Entering the getAllInBetween Method ");
			return carDetailsRepository.getInBetween(id1,id2);
		}

		public ResponseEntity<?> getBylessthenmount(int price) throws NoCarFoundException {
			log.debug("Entering the getBylessthenmount Method ");
			// TODO Auto-generated method stub
			List<CarDetails>	car = carDetailsRepository.getBylessthenmount(price);
			log.debug("Exiting the getBylessthenmount Method ");
			               return carOcurence(car,"no car less prive");
		}

		public ResponseEntity<?> getBymorethenmount(int price) throws NoCarFoundException {
			log.debug("Entering the getBymorethenmount Method ");
			// TODO Auto-generated method stub
			List<CarDetails>	car = carDetailsRepository.getBymorethenmount(price);
			log.debug("Exiting the getBymorethenmount Method ");
			return carOcurence(car,"no car prive");
			
		}   
		 public ResponseEntity<?>  carOcurence(List<CarDetails> car, String str) throws NoCarFoundException
		 {
			 if(car == null || car.isEmpty())
				{
					System.out.println("====================");
					log.error("No car found");
					throw new NoCarFoundException(str);
				}
					
				
			        log.info("Car Details found");
					return new ResponseEntity<>(car, HttpStatus.OK);
				
		     }
		 }
		 
		
			

