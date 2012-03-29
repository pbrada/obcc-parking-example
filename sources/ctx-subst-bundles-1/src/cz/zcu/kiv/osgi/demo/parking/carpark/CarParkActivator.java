package cz.zcu.kiv.osgi.demo.parking.carpark;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceException;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.flow.IVehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.carpark.flow.impl.VehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.IParkingStatus;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.impl.ParkingStatus;

public class CarParkActivator implements BundleActivator
{
	
	private Logger logger;
	private ServiceRegistration statusSvcReg;
	private ServiceRegistration flowSvcReg;

	public CarParkActivator()
	{
		this.logger = LoggerFactory.getLogger("parking-demo");
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		logger.info("Carpark.r1 activator: starting");

		statusSvcReg = context.registerService(IParkingStatus.class.getName(), ParkingStatus.getInstance(), null);
		if (statusSvcReg == null)
			throw new ServiceException("Carpark.r1: IParkingStatus svc registration failed");
		logger.info("Carpark.r1 activator: registered svc ", context.getService(statusSvcReg.getReference()).getClass());

		flowSvcReg = context.registerService(IVehicleFlow.class.getName(), VehicleFlow.getInstance(), null);
		if (null == flowSvcReg) 
			throw new ServiceException("Carpark.r1: IVehicleFlow svc registration failed");
		logger.info("Carpark.r1 activator: registered svc ", context.getService(flowSvcReg.getReference()).getClass());
		
		logger.info("Carpark.r1 activator: started.");
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		logger.info("Carpark.r1 activator: stopping");
		flowSvcReg.unregister();
		logger.info("Carpark.r1 activator: unreg flow svc");
		statusSvcReg.unregister();
		logger.info("Carpark.r1 activator: unreg status svc");
		logger.info("Carpark.r1 activator: stopped.");
	}

}
