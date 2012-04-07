package cz.zcu.kiv.osgi.demo.parking.carpark.status.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.status.IParkingStatus;


public class ParkingStatus implements IParkingStatus
{

	private static ParkingStatus instance = null;
	private Logger logger = null;
	private static final String lid = "ParkingStatus.r1";
	
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
		logger.info(lid+": <init>");
	}	

	@Override
	public boolean isFull()
	{
		logger.info(lid+": isFull {}", isFull);
		return isFull;
	}
	
	public void setFull(boolean status)
	{
		this.isFull = status;
	}

}
