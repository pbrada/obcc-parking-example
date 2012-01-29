package cz.zcu.kiv.osgi.demo.parking.carpark;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.flow.VehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.ParkingStatus;

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
		logger.info("Parking.r1 activator: start");
		// fake service registration to provoke log appearance
		VehicleFlow.getInstance();
		ParkingStatus.getInstance();
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Parking.r1 activator: stop");
	}

}
