package cz.zcu.kiv.osgi.demo.parking.gate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.statistics.GateStatistics;
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
		logger.info("Gate.r1 activator: start");
		// fake service registration, provoke constructor entry in log
		GateStatistics.getInstance();
		LaneStatistics.getInstance();
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Gate activator: stop");
	}

}
