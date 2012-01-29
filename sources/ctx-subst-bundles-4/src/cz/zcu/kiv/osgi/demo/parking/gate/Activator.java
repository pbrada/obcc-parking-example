package cz.zcu.kiv.osgi.demo.parking.gate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.statistics.GateStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.VehicleSink;

/**
 * Gate bundle evolved, provides extended GateStatistics, removed LaneStatistics: MUT-ation diff.
 * 
 * @author brada
 *
 */
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
		logger.info("Gate.r4 activator: start");
		// fake service registration to provoke provided services to appear in log
		GateStatistics.getInstance();
		// removed LaneStatistics.getInstance();
		VehicleSink.getInstance();
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Gate activator: stop");
	}

}
