package cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.statistics.impl.IGateUpdate;
import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.IVehicleSink;
import cz.zcu.kiv.osgi.demo.parking.carpark.flow.IVehicleFlow;


public class VehicleSink implements IVehicleSink
{

	private static VehicleSink instance = null;
	private Logger logger = null;
	private static final String lid = "VehicleSink.r3";
	
    private IGateUpdate gate;
    
	// dependencies
	private IVehicleFlow parkingPlace = null;
	
	/** 
	 * Create service instance.
	 */
	public static VehicleSink getInstance(IVehicleFlow flow, IGateUpdate gate) 
	{
		if (instance == null) {
			instance = new VehicleSink(flow, gate);
		}
		return instance;
	}
	
	
	protected VehicleSink(IVehicleFlow flow, IGateUpdate gate)
	{
		logger = LoggerFactory.getLogger("parking-demo");
		logger.info(lid+": <init>");
		parkingPlace = flow;
		this.gate = gate;
	}
	
	@Override
	public void consumeVehicle()
	{
		logger.info(lid+": consume");
		parkingPlace.arrive();
		gate.vehiclesArrived(1);
		// simulate vehicle departure
		Random r = new Random();
		int numVehiclesToLeave = r.nextInt(3);
		logger.info(lid+": dice made {} cars leave", numVehiclesToLeave);
		for (int i=0; i<numVehiclesToLeave; ++i) {
			parkingPlace.leave();
			gate.vehiclesDeparted(1);
		}
	}

}
