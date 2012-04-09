package cz.zcu.kiv.osgi.demo.parking.lane;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.IVehicleSink;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.impl.ILaneUpdate;

/**
 * Traffic simulator, run by Gate activator in this app revision 3.
 * 
 * @author Premek Brada (brada@kiv.zcu.cz)
 */
public class TrafficLane implements Runnable
{

	private static final int NUM_CYCLES = 10;
	private static final long PAUSE_TIME = 300;
	private static final int MAX_VEHICLES_IN_BATCH = 10;
	
	private ILaneUpdate lane;
	private Logger logger;
	private static final String lid = "TrafficLane@Gate.r3";
	
	// dependencies
	private IVehicleSink vehicleSink;

	public TrafficLane(IVehicleSink sink, ILaneUpdate lane)
	{
		logger = LoggerFactory.getLogger("parking-demo");
		logger.info(lid+": <init>");
		this.vehicleSink = sink;
		this.lane = lane;
	}
	
	/**
	 * Simulates traffic by "injecting" vehicles into the VehicleSink. Run by Gate 
	 * activator in r3 since Gate is the stats provider.
	 */
	@Override
	public void run()
	{
		logger.info("(!) "+lid+": traffic simulation thread starting");
		Random r = new Random();
		
		for (int i = 0; i < NUM_CYCLES; ++i) {
			logger.info(lid+": loop #{}", i);
			int batch = r.nextInt(MAX_VEHICLES_IN_BATCH);
			logger.info(lid+": Generating {} vehicles in the lane", batch);
			for (int v = 0; v < batch; ++v) {
				vehicleSink.consumeVehicle();
			}
			lane.vehiclesPassing(batch);
			try {
				Thread.sleep(PAUSE_TIME);
			}
			catch (InterruptedException e) {
			    logger.warn(lid+": thread interrupted");
				e.printStackTrace();
			}
			Thread.yield();
		}
		logger.info("(!) "+lid+": traffic simulation thread ended");
	}

}
