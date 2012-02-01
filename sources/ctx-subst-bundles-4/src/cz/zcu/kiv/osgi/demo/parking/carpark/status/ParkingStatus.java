package cz.zcu.kiv.osgi.demo.parking.carpark.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Evolved in this version, see IParkingStatus: SPE-cialization diff.
 */
public class ParkingStatus implements IParkingStatus, IParkingStatusUpdate
{
	private static final int CARPARK_CAPACITY = 10;
	
	private static ParkingStatus instance = null;
	private Logger logger = null;
	
	private int numPlacesFree;
	
	/** 
	 * Fake service provisioning.
	 */
	public static IParkingStatus getInstance() 
	{
		if (instance == null) {
			instance = new ParkingStatus();
		}
		return instance;
	}
	
	
	protected ParkingStatus()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
		logger.info("ParkingStatus.r4 <init>");
		this.reset();
	}	

	@Override
	public boolean isFull()
	{
		boolean isFull = (getNumFreePlaces() <= 0);
		logger.info("ParkingStatus: isFull {} (free {} places)", isFull, getNumFreePlaces());
		return isFull;
	}
	
	@Override
	public int getCapacity()
	{
		return CARPARK_CAPACITY;
	}

	@Override
	public int getNumFreePlaces()
	{
		return numPlacesFree;
	}

	@Override
	public void decreaseFreePlaces(int amount)
	{
		if (amount > numPlacesFree)
			numPlacesFree = 0;
		else
			numPlacesFree -= amount;
		logger.info("ParkingStatus: decreased free places by {} to {}", amount, numPlacesFree);
	}
	
	@Override
	public void increaseFreePlaces(int amount)
	{
		numPlacesFree += amount;
		if (numPlacesFree > CARPARK_CAPACITY)
			numPlacesFree = CARPARK_CAPACITY;
		logger.info("ParkingStatus: increased free places by {} to {}", amount, numPlacesFree);
	}

	@Override
	public void reset()
	{
		numPlacesFree = CARPARK_CAPACITY;
		logger.info("ParkingStatus: reset");
	}

}
