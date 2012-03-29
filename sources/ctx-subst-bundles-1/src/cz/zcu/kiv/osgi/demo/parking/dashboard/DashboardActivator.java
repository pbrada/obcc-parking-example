package cz.zcu.kiv.osgi.demo.parking.dashboard;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.lane.statistics.ILaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;

public class DashboardActivator implements BundleActivator
{
	
	private Logger logger;
	private Thread t;
	
	// service dependencies
	private ICountingStatistics gateStats = null;	// intentionally using superinterface
	private ILaneStatistics laneStats = null; 		

	private Dashboard dashboard;

	public DashboardActivator()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		logger.info("Dashboard.r1 activator: starting");
		
		// required services

		ServiceReference sr;
		sr = context.getServiceReference(ICountingStatistics.class.getName());
		if (sr == null) {
			logger.error("Dashboard.r1 activator: no gate stats registered");
		}
		else {
			gateStats = (ICountingStatistics) context.getService(sr);
			if (gateStats == null) {
				logger.error("Dashboard.r1 activator: no gate stats service available");
			}
			else {
				logger.info("Dashboard.r1 activator: got gate stats");
			}
		}

		sr = context.getServiceReference(ILaneStatistics.class.getName());
		if (sr == null) {
			logger.error("Dashboard.r1 activator: no lane stats registered");
		}
		else {
			laneStats = (ILaneStatistics) context.getService(sr);
			if (laneStats == null) {
				logger.error("Dashboard.r1 activator: no lane stats service available");
			}
			else {
				logger.info("Dashboard.r1 activator: got lane stats");
			}
		}

		if (gateStats == null || laneStats == null) {
			logger.error("Dashboard.r1 activator: some service unavailable, exiting");
			throw new BundleException("Dashboard.r1 activator: some service unavailable, exiting");
		}

		// requirements ok, go ahead
		
		dashboard = new Dashboard(gateStats, laneStats);
		t = new Thread(dashboard);
		logger.info("Dashboard activator: spawning thread");
		t.start();
		logger.info("Dashboard activator: thread spawned");

		logger.info("Dashboard.r1 activator: started.");

	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Dashboard activator: stopped.");
	}

}
