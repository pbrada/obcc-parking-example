package cz.zcu.kiv.osgi.demo.parking.carpark.status.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.status.IParkingStatus;


public class ParkingStatus implements IParkingStatus
{

	private static ParkingStatus instance = null;
	private Logger logger = null;
	
	private boolean isFull = false;
	

	public static ParkingStatus getInstance() 
	{
		if (instance == null) {
			instance = new ParkingStatus();
		}
		return instance;
	}
	
	
	protected ParkingStatus()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
		logger.info("ParkingStatus.r1 <init>");
	}	

	@Override
	public boolean isFull()
	{
		logger.info("ParkingStatus: isFull {}", isFull);
		return isFull;
	}
	
	public void setFull(boolean status)
	{
		this.isFull = status;
	}

}
