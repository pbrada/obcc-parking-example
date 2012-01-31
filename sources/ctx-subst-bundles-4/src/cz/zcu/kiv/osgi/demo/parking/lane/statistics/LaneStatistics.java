package cz.zcu.kiv.osgi.demo.parking.lane.statistics;

import cz.zcu.kiv.osgi.demo.parking.statsbase.CountingStatisticsAbstractBaseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaneStatistics extends CountingStatisticsAbstractBaseImpl implements ILaneStatistics
{
	private static LaneStatistics instance = null;
	private Logger logger = null;
	
	private int vehicleCount = 0;
	private int secondsElapsed = 0;
	private long timerStart = 0L;
	
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
		logger.info("LaneStats.r4 <init>");
		clear();
	}
		
	@Override
	public int getCountVehiclesPassed()
	{
		logger.info(getIdentification()+": vehicles passed count {}", vehicleCount);
		return vehicleCount;
	}
	
    @Override
    public int getVehiclesPerInterval(int seconds)
    {
        secondsElapsed = (int)((System.currentTimeMillis() - timerStart) / 1000);
        int freq = (int) (1.0 * seconds / secondsElapsed * vehicleCount);
        if (freq == 0)
            freq = vehicleCount;
        logger.warn(getIdentification()+": getVehPerInterval() UNEXPECTEDLY CALLED!  (returning vehicle freq for {}-sec interval after {} secs of run time)",seconds,secondsElapsed);
        return freq;
    }

    @Override
    public String getIdentification()
    {
        return "LaneStatistics";
    }

	@Override
	public void clear()
	{
		super.clear();
		vehicleCount = 0;
		secondsElapsed = 0;
		timerStart = System.currentTimeMillis();
		logger.info(getIdentification()+": counters cleared");
	}

	public void vehiclesPassing(int cnt)
	{
		vehicleCount += cnt;
		addToEventCount(cnt);
	}

}
