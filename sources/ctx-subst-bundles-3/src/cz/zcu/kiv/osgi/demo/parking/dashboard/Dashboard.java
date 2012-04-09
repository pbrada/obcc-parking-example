package cz.zcu.kiv.osgi.demo.parking.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.lane.statistics.ILaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.IGateStatistics;

public class Dashboard implements Runnable
{
	// TODO learn how to use config admin service to set these values
	private static final int NUM_CYCLES = 12;
	private static final long PAUSE_TIME = 300;
	
	Logger logger = null;
    private static final String lid = "Dashboard.r3";

	// dependencies, full gate stats now
	IGateStatistics gateStats = null;
	ILaneStatistics laneStats = null;
	
	public Dashboard(IGateStatistics gate, ILaneStatistics lane) 
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
		logger.info(lid+": <init>");
		
		gateStats = gate;
		gateStats.clear();
		laneStats = lane;
		laneStats.clear();    // from Gate, not from TrafficLane bundle
	}
	
	@Override
	public void run()
	{
		int gateNum;
		int laneNum;
		
		logger.info("(!)"+lid+": thread starting");
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

		logger.info("(!)"+lid+": thread stopping");
		
		logger.info("-----");
		logger.info(lid+": final stats: lane events {}" , laneStats.getEventCount() );
		logger.info(lid+": final stats: lane vehicles {}", laneStats.getCountVehiclesPassed() );
		logger.info(lid+": final stats: gate events {}" , gateStats.getEventCount() );
		logger.info(lid+": final stats: gate entered {}", gateStats.getNumberOfVehiclesEntering() );
		logger.info(lid+": final stats: gate leaved  {}",  gateStats.getNumberOfVehiclesLeaving() );
		logger.info("-----");
	}

}
