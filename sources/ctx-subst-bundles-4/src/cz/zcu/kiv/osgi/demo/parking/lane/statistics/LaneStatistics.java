package cz.zcu.kiv.osgi.demo.parking.lane.statistics;

import cz.zcu.kiv.osgi.demo.parking.statsbase.CountingStatisticsAbstractBaseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaneStatistics extends CountingStatisticsAbstractBaseImpl implements ILaneStatistics
{
	private static LaneStatistics instance = null;
	private Logger logger = null;
	
	private int vehicleCount = 0;
	
	/**
	 * Fake service provisioning.
	 */
	public static LaneStatistics getInstance()
	{
		if (instance == null) {
			instance = new LaneStatistics();
		}
		return instance;		
	}

	protected LaneStatistics()
	{
		logger = LoggerFactory.getLogger("parking-demo");
		logger.info("LaneStats.r4 <init>");
		clear();
	}
		
	@Override
	public String getIdentification()
	{
		return "LaneStatistics";
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
		super.clear();
		vehicleCount = 0;
		logger.info(getIdentification()+": counters cleared");
	}

	public void vehiclesPassing(int cnt)
	{
		vehicleCount += cnt;
		addToEventCount(cnt);
	}

}
