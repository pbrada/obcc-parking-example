package cz.zcu.kiv.osgi.demo.parking.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import cz.zcu.kiv.osgi.demo.parking.api.ICountingStatistics;
import cz.zcu.kiv.osgi.demo.parking.api.IGateStatistics;
import cz.zcu.kiv.osgi.demo.parking.api.ILaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.GateStatistics;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.LaneStatistics;

public class Dashboard implements Runnable
{
	// TODO learn how to use config admin service to set these values
	private static final int NUM_CYCLES = 12;
	private static final long PAUSE_TIME = 300;
	
	Logger logger = null;

	// dependencies, full gate stats now
	IGateStatistics gateStats = null;
	ILaneStatistics laneStats = null;
	
	public Dashboard() 
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
		logger.info("Dashboard.r3 <init>");
		
		gateStats = GateStatistics.getInstance();
		gateStats.clear();
		laneStats = LaneStatistics.getInstance();	// from Gate, not from TrafficLane bundle; see imports
		laneStats.clear();
	}
	
	@Override
	public void run()
	{
		int gateNum;
		int laneNum;
		
		logger.info("(!) Dashboard: thread starting");
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

		logger.info("(!) Dashboard: thread stopping");
		
		logger.info("-----");
		logger.info("Dashboard final stats: lane events {}" , laneStats.getEventCount() );
		logger.info("Dashboard final stats: lane vehicles {}", laneStats.getCountVehiclesPassed() );
		logger.info("Dashboard final stats: gate events {}" , gateStats.getEventCount() );
		logger.info("Dashboard final stats: gate entered {}", gateStats.getNumberOfVehiclesEntering() );
		logger.info("Dashboard final stats: gate leaved  {}",  gateStats.getNumberOfVehiclesLeaving() );
		
	}

}
