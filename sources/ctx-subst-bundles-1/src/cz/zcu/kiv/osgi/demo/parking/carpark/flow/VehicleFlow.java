package cz.zcu.kiv.osgi.demo.parking.carpark.flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.status.ParkingStatus;


public class VehicleFlow implements IVehicleFlow
{
	private static VehicleFlow instance = null;
	private Logger logger = null;
	
	private ParkingStatus status = null;
	private int capacity = 0;
		
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
		logger.info("VehicleFlow.r1 <init>");
		this.status = (ParkingStatus) ParkingStatus.getInstance();
		this.capacity = 10;
	}	

	
	@Override
	public void arrive()
	{
		logger.info("VehicleFlow: arrive");
		if (--capacity <= 0) {
			capacity = 0;
			status.setFull(true);
		}
	}

	@Override
	public void leave()
	{
		logger.info("VehicleFlow: leave");
		// we don't check reaching max capacity for now
		if (++capacity > 0) {
			status.setFull(false);
		}
	}

}
