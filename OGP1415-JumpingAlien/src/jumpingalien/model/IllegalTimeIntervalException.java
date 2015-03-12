package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signalling an illegal time interval for Mazub.
 * 
 * @author Joren Dhont & Pieterjan Beerden
 */
public class IllegalTimeIntervalException extends RuntimeException {
	
	/**
	 * Initialize this new illegal time interval exception with the given time interval.
	 * 
	 * @param 	timeInterval
	 * 			The time interval for this illegal time interval exception.
	 * @post	The time interval of this new illegal time interval exception
	 * 			is equal to the given time interval.
	 * 			| new.getTimeInterval == timeInterval
	 */
	public IllegalTimeIntervalException(double timeInterval) {
		this.timeInterval = timeInterval;
	}
	
	/**
	 * Return the time interval registered for the illegal time interval.
	 */
	@Basic @Immutable
	public double getTimeInterval() {
		return this.timeInterval;
	}
	
	/**
	 * Variable registering the time interval in this IllegalTimeIntervalException.
	 */
	public final double timeInterval;
	
	/**
	 * Serial Version ID, strongly advised by the Java API.
	 */
	private static final long serialVersionUID = -2276065059969438299L;
}
