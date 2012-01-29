package cz.zcu.kiv.osgi.demo.parking.lane;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.IVehicleSink;
import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.VehicleSink;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.LaneStatistics;

public class TrafficLane implements Runnable
{

	private static final int NUM_CYCLES = 10;
	private static final long PAUSE_TIME = 300;
	private static final int MAX_VEHICLES_IN_BATCH = 10;
	
	private LaneStatistics lane;
	private Logger logger;
	
	// dependencies
	private IVehicleSink vehicleSink;

	public TrafficLane()
	{
		logger = LoggerFactory.getLogger("parking-demo");
		logger.info("LaneStats@Gate.r3 <init>");
		this.vehicleSink = VehicleSink.getInstance();
		this.lane = (LaneStatistics) LaneStatistics.getInstance();
	}
	
	/**
	 * Simulates traffic by "injecting" vehicles into the VehicleSink. Run by Gate 
	 * activator in r3 since Gate is the stats provider.
	 */
	@Override
	public void run()
	{
		logger.info("(!) TrafficLane: thread starting");
		Random r = new Random();
		
		for (int i = 0; i < NUM_CYCLES; ++i) {
			logger.info("TrafficLane: loop #{}", i);
			int batch = r.nextInt(MAX_VEHICLES_IN_BATCH);
			logger.info("TrafficLane: Generating {} vehicles in the lane", batch);
			for (int v = 0; v < batch; ++v) {
				vehicleSink.consumeVehicle();
			}
			lane.vehiclesPassing(batch);
			try {
				Thread.sleep(PAUSE_TIME);
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.yield();
		}
		logger.info("(!) TrafficLane: traffic simulation ended");
	}

}
