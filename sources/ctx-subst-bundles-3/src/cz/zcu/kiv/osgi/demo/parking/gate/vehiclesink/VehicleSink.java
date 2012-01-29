package cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.statistics.GateStatistics;
import cz.zcu.kiv.osgi.demo.parking.carpark.flow.IVehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.carpark.flow.VehicleFlow;


public class VehicleSink implements IVehicleSink
{

	private static VehicleSink instance = null;
	private Logger logger = null;
	
	// dependencies
	private IVehicleFlow parkingPlace = null;
	
	private GateStatistics gate;
	
	/** 
	 * Fake service provisioning.
	 */
	public static IVehicleSink getInstance() 
	{
		if (instance == null) {
			instance = new VehicleSink();
		}
		return instance;
	}
	
	
	protected VehicleSink()
	{
		logger = LoggerFactory.getLogger("parking-demo");
		logger.info("VehicleSink.r3 <init>");
		parkingPlace = VehicleFlow.getInstance();
		gate = (GateStatistics) GateStatistics.getInstance();
	}
	
	@Override
	public void consumeVehicle()
	{
		logger.info("VehicleSink: consume");
		parkingPlace.arrive();
		gate.vehiclesArrived(1);
		// simulate vehicle departure
		Random r = new Random();
		int numVehiclesToLeave = r.nextInt(3);
		logger.info("VehicleSink: dice made {} cars leave", numVehiclesToLeave);
		for (int i=0; i<numVehiclesToLeave; ++i) {
			parkingPlace.leave();
			gate.vehiclesDeparted(1);
		}
	}

}
