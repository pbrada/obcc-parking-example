package cz.zcu.kiv.osgi.demo.parking.lane;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		logger.info("TrafficLane.r3 activator: start");
		// lane statistics service still provided by Gate component in r3
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Traffic lane activator: stop");
	}

}

