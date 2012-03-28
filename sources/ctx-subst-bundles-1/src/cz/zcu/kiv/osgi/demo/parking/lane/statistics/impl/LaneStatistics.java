package cz.zcu.kiv.osgi.demo.parking.lane.statistics.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.lane.statistics.ILaneStatistics;


public class LaneStatistics implements ILaneStatistics
{
	private static LaneStatistics instance = null;
	private Logger logger = null;
	
	private int  vehicleCount = 0;
	
	/** 
	 * Fake service provisioning.
	 */
	public static ILaneStatistics getInstance() 
	{
		if (instance == null) {
			instance = new LaneStatistics();
		}
		return instance;
	}
	
	
	protected LaneStatistics()
	{
		logger = LoggerFactory.getLogger("parking-demo");
		logger.info("LaneStats.r1 <init>");
		clear();
	}
	
	@Override
	public String getIdentification()
	{
		return "LaneStatistics";
	}

	@Override
	public int getEventCount()
	{
		logger.info(getIdentification()+": count {}", vehicleCount);
		return vehicleCount;
	}

	@Override
	public int getCountVehiclesPassed()
	{
		logger.info(getIdentification()+": vehicles passed count {}", vehicleCount);
		return vehicleCount;
	}

	@Override
	public void clear()
	{
		vehicleCount = 0;
		logger.info(getIdentification()+": counters cleared");
	}
	
	public void vehiclesPassing(int cnt)
	{
		vehicleCount += cnt;
		logger.info(getIdentification()+": increased count by {} to {}", cnt, vehicleCount);
	}
	
}
