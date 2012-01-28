package cz.zcu.kiv.osgi.demo.parking.dashboard;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Activator implements BundleActivator
{
	
	private Logger logger;
	private Thread t;
	
	private Dashboard dashboard;

	public Activator()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		logger.info("Dashboard.r1 activator: start");
		dashboard = new Dashboard();
		t = new Thread(dashboard);
		logger.info("Dashboard activator: spawn thread");
		t.start();
		logger.info("Dashboard activator: thread spawned");
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Dashboard activator: stop");
	}

}
