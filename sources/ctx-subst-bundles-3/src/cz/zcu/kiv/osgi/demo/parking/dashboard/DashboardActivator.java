package cz.zcu.kiv.osgi.demo.parking.dashboard;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.statistics.IGateStatistics;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.ILaneStatistics;

public class DashboardActivator implements BundleActivator
{
	
	private Logger logger;
	private static final String lid = "Dashboard.r3 Activator";
	
	private Thread t;
	
	// service dependencies
	private IGateStatistics gateStats = null;	// intentionally using superinterface
	private ILaneStatistics laneStats = null; 		

	private Dashboard dashboard;

	public DashboardActivator()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		logger.info(lid+": starting");
		
		// required services

		ServiceReference sr;
		sr = context.getServiceReference(IGateStatistics.class.getName());
		if (sr == null) {
			logger.error(lid+": no gate stats registered");
		}
		else {
			gateStats = (IGateStatistics) context.getService(sr);
			if (gateStats == null) {
				logger.error(lid+": no gate stats service available");
			}
			else {
				logger.info(lid+": got gate stats");
			}
		}

		sr = context.getServiceReference(ILaneStatistics.class.getName());
		if (sr == null) {
			logger.error(lid+": no lane stats registered");
		}
		else {
			laneStats = (ILaneStatistics) context.getService(sr);
			if (laneStats == null) {
				logger.error(lid+": no lane stats service available");
			}
			else {
				logger.info(lid+": got lane stats");
			}
		}

		if (gateStats == null || laneStats == null) {
			logger.error(lid+": some service unavailable, exiting");
			throw new BundleException(lid+": some service unavailable, exiting");
		}

		// requirements ok, go ahead
		
		dashboard = new Dashboard(gateStats, laneStats);
		t = new Thread(dashboard);
		logger.info(lid+": spawning thread");
		t.start();
		logger.info(lid+": thread spawned");

		logger.info(lid+": started.");

	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info(lid+": stopped.");
	}

}
