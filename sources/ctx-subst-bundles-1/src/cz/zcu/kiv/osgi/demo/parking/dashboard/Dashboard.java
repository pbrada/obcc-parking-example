package cz.zcu.kiv.osgi.demo.parking.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.api.ICountingStatistics;
import cz.zcu.kiv.osgi.demo.parking.api.ILaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.GateStatistics;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.LaneStatistics;

public class Dashboard implements Runnable
{
	private static final int NUM_CYCLES = 10;
	private static final long PAUSE_TIME = 200;

	Logger logger = null;
	
	// dependencies, intentionally generalized type on this endpoint
	ICountingStatistics gateStats = null;
	ILaneStatistics laneStats = null;
	
	public Dashboard() 
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
		logger.info("Dashboard.r1 <init>");
		
		gateStats = GateStatistics.getInstance();
		gateStats.clear();
		laneStats = LaneStatistics.getInstance();
		laneStats.clear();
	}

	@Override
	public void run()
	{
		int gateNum;
		int laneNum;
		
		gateNum = gateStats.getEventCount();
		laneNum = laneStats.getCountVehiclesPassed();
		logger.info("Dashboard initial stats: lane passed {}, gate events {}" , laneNum, gateNum );
		for (int i=0; i<NUM_CYCLES; ++i) {
			logger.info("*** Dashboard: loop {}",i);
			gateNum = gateStats.getEventCount();
			laneNum = laneStats.getCountVehiclesPassed();
			logger.info("*** Dashboard stats: lane passed {}, gate events {}" , laneNum, gateNum );
			try {
				Thread.sleep(PAUSE_TIME);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.yield();
		}
			
	}

}
