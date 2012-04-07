package cz.zcu.kiv.osgi.demo.parking.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.lane.statistics.ILaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;

public class Dashboard implements Runnable
{
	private static final int NUM_CYCLES = 10;
	private static final long PAUSE_TIME = 200;

	Logger logger = null;
	private static final String lid = "Dashboard.r1";
	
	// dependencies, intentionally generalized type on this endpoint
	ICountingStatistics gateStats = null;
	ILaneStatistics laneStats = null;
	
	public Dashboard(ICountingStatistics gate, ILaneStatistics lane) 
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
		logger.info(lid+": <init>");
		
		gateStats = gate;
		gateStats.clear();
		laneStats = lane;
		laneStats.clear();
	}

	@Override
	public void run()
	{
		int gateNum;
		int laneNum;
		
		gateNum = gateStats.getEventCount();
		laneNum = laneStats.getCountVehiclesPassed();
		logger.info(lid+": initial stats -- lane passed {}, gate events {}" , laneNum, gateNum );
		for (int i=0; i<NUM_CYCLES; ++i) {
			logger.info("*** "+lid+": loop {}",i);
			gateNum = gateStats.getEventCount();
			laneNum = laneStats.getCountVehiclesPassed();
			logger.info("*** "+lid+" stats: lane passed {}, gate events {}" , laneNum, gateNum );
			try {
				Thread.sleep(PAUSE_TIME);
			}
			catch (InterruptedException e) {
				logger.warn(lid+": thread interrupted");
				e.printStackTrace();
			}
			Thread.yield();
		}
			
	}

}
