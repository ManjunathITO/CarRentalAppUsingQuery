package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"springboot.car_details","springboot.car_booking"})
public class CarBookingApp 
{
	public static void main(String[] args)
    {
		SpringApplication.run(CarBookingApp.class, args);
	}
}
 	