package cz.zcu.kiv.osgi.demo.parking.gate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.statistics.GateStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.VehicleSink;
import cz.zcu.kiv.osgi.demo.parking.lane.TrafficLane;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.LaneStatistics;

public class Activator implements BundleActivator
{
	
	private Logger logger;

	public Activator()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		logger.info("Gate.r3 activator: start");
		// to provoke provided services to appear in log
		GateStatistics.getInstance();
		LaneStatistics.getInstance();
		VehicleSink.getInstance();
		
		// start traffic simulator
		TrafficLane lane = new TrafficLane();
		Thread t = new Thread(lane);
		logger.info("Gate activator: spawn thread");
		t.start();
		logger.info("Gate activator: thread spawned");
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Gate activator: stop");
	}

}
