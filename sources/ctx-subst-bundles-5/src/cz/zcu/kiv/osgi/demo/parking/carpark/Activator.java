package cz.zcu.kiv.osgi.demo.parking.carpark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import cz.zcu.kiv.osgi.demo.parking.carpark.flow.VehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.carpark.statistics.ParkingStatistics;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.ParkingStatus;

/**
 * CarPark bundle evolved, added ParkingStatistics: SPE-cialization diff.
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
		logger.info("CarPark.r5 activator: start");
		// fake service registration provoke provided services into log
		ParkingStatus.getInstance();
		VehicleFlow.getInstance();
		ParkingStatistics.getInstance();
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("CarPark.r5 activator: stop");
	}

}
