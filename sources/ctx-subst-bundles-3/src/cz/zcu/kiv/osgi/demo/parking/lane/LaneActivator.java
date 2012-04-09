package cz.zcu.kiv.osgi.demo.parking.lane;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaneActivator implements BundleActivator
{
	
	private Logger logger;
	private static final String lid = "Lane.r3 Activator";
	
	public LaneActivator()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		logger.info(lid+": start");
		// lane statistics service still provided by Gate component in r3
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info(lid+": stop");
	}

}

