package cz.zcu.kiv.osgi.demo.parking.carpark.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.flow.IVehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.impl.ParkingStatus;


public class VehicleFlow implements IVehicleFlow
{
	private static VehicleFlow instance = null;
	private Logger logger = null;
	private static final String lid = "VehicleFlow.r1";
	
	private ParkingStatus status = null;
	private int capacity = 0;
		

	public static VehicleFlow getInstance()
	{
		if (instance == null) {
			instance = new VehicleFlow();
		}
		return instance;		
	}

	
	protected VehicleFlow()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
		logger.info(lid+": <init>");
		this.status = ParkingStatus.getInstance();
		this.capacity = 10;
	}	

	
	@Override
	public void arrive()
	{
		logger.info(lid+": arrive");
		if (--capacity <= 0) {
			capacity = 0;
			status.setFull(true);
		}
	}

	@Override
	public void leave()
	{
		logger.info(lid+": leave");
		// we don't check reaching max capacity for now
		if (++capacity > 0) {
			status.setFull(false);
		}
	}

}
