package springboot.log;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LogMarker {
	
	
	 /**
	   * 
	   * default constructor with protected scope as its a utility class
	   *
	   */
	  protected LogMarker() {
	    super();
	  }

	  public static final Marker ENTRY = MarkerFactory.getMarker("Entering:");
	  public static final Marker EXIT = MarkerFactory.getMarker("Exiting:");
	  public static final Marker PERSISTANCE = MarkerFactory.getMarker("Persistance:");
	  public static final Marker RAISE_ALERT = MarkerFactory.getMarker("RaiseAlert");
	  public static final Marker NOTIFY_ADMIN = MarkerFactory.getMarker("NotifyAdmin:");


}
