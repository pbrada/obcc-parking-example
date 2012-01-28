package cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.api.IParkingStatus;
import cz.zcu.kiv.osgi.demo.parking.api.IVehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.api.IVehicleSink;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.GateStatistics;
import cz.zcu.kiv.osgi.demo.parking.carpark.flow.VehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.ParkingStatus;


/**
 * Provided method added thrown exception: semantic semSPE-cialization diff. The implementation
 * substitutes a missing vehicle departure thread in car park by a departure of random 0 to 2 cars on
 * consumeVehicle(). 
 * 
 * @author brada
 *
 */
public class VehicleSink implements IVehicleSink
{

	private static VehicleSink instance = null;
	private Logger logger = null;
	
	// dependencies
	private IVehicleFlow carPark = null;
	private IParkingStatus parkingStatus = null;
	
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
		logger.info("VehicleSink.r4 <init>");
		carPark = VehicleFlow.getInstance();
		gate = (GateStatistics) GateStatistics.getInstance();
		parkingStatus = ParkingStatus.getInstance();
	}
	
	
	@Override
	public void consumeVehicle() throws IllegalStateException
	{
		if (parkingStatus.isFull()) {
			logger.error("VehicleSink: cannot consumeVehicle(), car park full");
			throw new IllegalStateException("Car park full");
		}
		logger.info("VehicleSink: consumeVehicle");
		carPark.arrive();
		gate.vehiclesArrived(1);
		
		// simulate vehicle departure
		Random r = new Random();
		int numVehiclesToLeave = r.nextInt(3);
		logger.info("VehicleSink: dice made {} cars leave",numVehiclesToLeave);
		for (int i=0; i<numVehiclesToLeave; ++i) {
			carPark.leave();
			gate.vehiclesDeparted(1);
		}
 
	}

}
