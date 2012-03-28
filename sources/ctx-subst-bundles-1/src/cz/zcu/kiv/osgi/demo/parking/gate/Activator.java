package cz.zcu.kiv.osgi.demo.parking.gate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.statistics.IGateStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.impl.GateStatistics;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.ILaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.impl.LaneStatistics;

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
		context.registerService(IGateStatistics.class.getName(), GateStatistics.getInstance(), null);
		context.registerService(ILaneStatistics.class.getName(), LaneStatistics.getInstance(), null);
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Gate activator: stop");
	}

}
