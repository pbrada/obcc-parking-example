package cz.zcu.kiv.osgi.demo.parking.gate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.flow.IVehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.IGateStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.impl.GateStatistics;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.ILaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.impl.LaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;

public class GateActivator implements BundleActivator
{
	
	private Logger logger;

	private ServiceRegistration gateSvcReg;
	private ServiceRegistration laneSvcReg;
	
	// dependencies
	private IVehicleFlow parking = null;
	
	public GateActivator()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		logger.info("Gate.r1 activator: starting");
		
		// required services
		
		ServiceReference sr = context.getServiceReference(IVehicleFlow.class.getName());
		if (sr == null) {
			logger.error("Gate.r1 activator: no parking registered");
		}
		else {
			parking = (IVehicleFlow) context.getService(sr);
			if (parking == null) {
				logger.error("Gate.r1 activator: no parking service available");
			}
			else {
				logger.info("Gate.r1 activator: got parking service");
			}
		}

		if (parking == null) {
			logger.error("Gate.r1 activator: some service unavailable, exiting");
			throw new BundleException("Gate.r1 activator: some service unavailable, exiting");
		}

		// provided services
		
		String[] gateIds = new String[] {
				ICountingStatistics.class.getName(),
				IGateStatistics.class.getName()
		};
		gateSvcReg = context.registerService(gateIds, GateStatistics.getInstance(parking), null);
		if (null == gateSvcReg)
			throw new ServiceException("Gate.r1: gate svc registration failed");
		logger.info("Gate.r1 activator: registered svc ", context.getService(gateSvcReg.getReference()).getClass());

		String[] laneIds = new String[] {
				ICountingStatistics.class.getName(),
				ILaneStatistics.class.getName()
		};
		laneSvcReg = context.registerService(laneIds, LaneStatistics.getInstance(), null);
		if (null == laneSvcReg)
			throw new ServiceException("Gate.r1: lane svc registration failed");
		logger.info("Gate.r1 activator: registered svc ", context.getService(laneSvcReg.getReference()).getClass());

		logger.info("Gate.r1 activator: started.");		
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Gate.r1 activator: stopping");
		gateSvcReg.unregister();
		logger.info("Gate.r1 activator: unreg gate svc");
		laneSvcReg.unregister();
		logger.info("Gate.r1 activator: unreg lane svc");
		logger.info("Gate.r1 activator: stopped.");
	}

}
