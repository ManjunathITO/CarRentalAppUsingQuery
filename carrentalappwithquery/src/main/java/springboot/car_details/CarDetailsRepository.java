package springboot.car_details;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailsRepository extends CrudRepository<CarDetails, Integer>
{

   @Query(value = "select * from car_details c where c.type=?1", nativeQuery = true)
   public List<CarDetails> findBycartype(String type);
   
   public List<CarDetails> findByModalname(String modalname);

   public CarDetails findById(int id);

   @Query(value = "select * from car_details", nativeQuery = true)
   public List<CarDetails> getAllCars();

   @Query(value = "select * from car_details c where c.id not in  (?1 , ?2)", nativeQuery = true)
   public List<CarDetails> getInBetween(int id1, int id2);

   @Query(value = "select * from car_details c where c.price < ?1", nativeQuery = true)
   public List<CarDetails> getBylessthenmount(int price);

   @Query(value = "select * from car_details c where c.price > ?1", nativeQuery = true)
   public List<CarDetails> getBymorethenmount(int price);
   
   
}
