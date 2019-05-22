package springboot.car_details;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class CarDetailsController
{
	@Autowired
	private CarDetailsService carDetailsService;

	
	@RequestMapping(method=RequestMethod.POST, value="/carinformation")
	public void adddetails(@RequestBody CarDetails cardetails )
	{
		carDetailsService.addcardetails(cardetails);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/carinformation")
	public List<CarDetails> getAllinformation()
	{
		return carDetailsService.getAllcardetails();
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/carinformation/inbetween/{id1}/{id2}")
	public List<CarDetails> getAllInBetween(@PathVariable ("id1") int id1,@PathVariable ("id2") int id2)
	{
		return carDetailsService.getAllInBetween(id1,id2);
		
	}
	
	@RequestMapping("/carinformation/fortype/{type}")
	public List<CarDetails> getByType(@PathVariable ("type") String type)
	{
		return carDetailsService.getByType(type);
	}
	
	@RequestMapping("/carinformation/formodal/{modalname}")
	public List<CarDetails> getModalname(@PathVariable ("modalname") String modalname)
	{
		return carDetailsService.getByModalname(modalname);
	}
	
	@RequestMapping("/carinformation/lessthenmount/{price}")
	public List<CarDetails> getBylessthenmount(@PathVariable ("price") int price)
	{
		return carDetailsService.getBylessthenmount(price);
	}
	
	@RequestMapping("/carinformation/morethenmount/{price}")
	public List<CarDetails> getBymorethenmount(@PathVariable ("price") int price)
	{
		return carDetailsService.getBymorethenmount(price);
	}
	
	
	
	@RequestMapping("/carinformation/bycarid/{id}")
	public List<CarDetails> getById(@PathVariable ("id") int id)
	{
		return carDetailsService.getBycarId(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/carinformation/updatecar/{id}")
	public void updateinformation(@RequestBody CarDetails cardetails, @PathVariable int id)
	{
		carDetailsService.updateinformation(id, cardetails); 
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/carinformation/delete/{id}")
	public void deleteinformation(@PathVariable int id)
	{
		carDetailsService.deleteinformation(id);
	}
	
	
}
