package springboot.aspect;

 

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class loggingAspect {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	/*@AfterReturning(pointcut="within(springboot.car_details.CarDetailsService)", returning="returningobj" )
	public void allArgument(JoinPoint joinPoint, Object returningobj)
		{
			System.out.println("++++++++++++++++++++++++++++");
			System.out.println(returningobj);
			
			log.info("dqwd===========================");
			
		    Object[]	 argg =   joinPoint.getArgs();
		
		   
		    for (int i = 0; i < argg.length; i++) {
				//System.out.println(joinPoint.toString()+" has a parameters  "+argg[i]);
				log.info(joinPoint.toString()+" has a parameters  "+argg[i]);
			}
			
		    log.info("dqwd===========================");
		}*/
	
	@AfterReturning(pointcut="within(springboot.*.*)", returning="returningobj" )
	public void allArgumentFromBooking(JoinPoint joinPoint, Object returningobj)
		{
			System.out.println("++++++++++++++++++++++++++++");
			System.out.println(returningobj);
			
			log.info("dqwd===========================");
			
		    Object[]	 argg =   joinPoint.getArgs();
		
		   
		    for (int i = 0; i < argg.length; i++) {
				//System.out.println(joinPoint.toString()+" has a parameters  "+argg[i]);
				log.info(joinPoint.toString()+" has a parameters  "+argg[i]);
				 
			}
			
		    log.info("dqwd===========================");
		}

	
	/*@Before(value = "myMethods()")
	public void LoggingAdvice(JoinPoint joinPoint) {
		System.err.println("\n\n\n");
		
		log.info(joinPoint.toString());
		log.info("dqwd===========================");
		log.info(null, joinPoint.getArgs());
		
		log.info("dqwd===========================");
		
		System.out.println("\n\n\n");
	}

	@Pointcut("within(springboot.car_details.CarDetailsService)")
	public void myMethods() {
		
	} */
	
	
	/*@AfterReturning(value= "myMethods() && args(type)" )
	public void allArgument(JoinPoint joinPoint, String type, Object returningobj)
		{
			System.out.println("++++++++++++++++++++++++++++");
			System.out.println(returningobj);
		}*/
	
	/*@Before("args(name)")
	public void stringArgumentMethod(String name)
	{
		System.out.println("\n\n\n");
		log.info("===========================");
		System.out.println("\n\n\n");
	}*/
	
	

}
