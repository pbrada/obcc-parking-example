package cz.zcu.kiv.osgi.demo.parking.carpark.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.status.ParkingStatus;


/**
 * The implementation of VehicleSink in Gate substitutes a missing vehicle departure thread 
 * in car park by a 50:50 chance of departure on consumeVehicle(), invoking this.leave().
 * 
 * @author brada
 *
 */
public class VehicleFlow implements IVehicleFlow
{
	
	private static VehicleFlow instance = null;
	private Logger logger = null;
	
	private ParkingStatus status = null;
		
	/** 
	 * Fake service provisioning.
	 */
	public static IVehicleFlow getInstance()
	{
		if (instance == null) {
			instance = new VehicleFlow();
		}
		return instance;		
	}

	
	protected VehicleFlow()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
		logger.info("VehicleFlow.r4 <init>");
		this.status = (ParkingStatus) ParkingStatus.getInstance();
	}	

	
	@Override
	public void arrive() throws IllegalStateException
	{
		if (status.isFull()) {
			logger.error("VehicleFlow: arrive(): No places left free for parking");
			throw new IllegalStateException("No places left free for parking");
		}
		logger.info("VehicleFlow: arrive");
		status.decreaseFreePlaces(1);		
	}

	@Override
	public void leave() throws IllegalStateException
	{
		if (status.getNumFreePlaces() == status.getCapacity()) {
			logger.error("VehicleFlow: leave(): No car can leave an empty car park");
			throw new IllegalStateException("No car can leave an empty car park");
		}
		logger.info("VehicleFlow: leave");
		status.increaseFreePlaces(1);
	}

}
