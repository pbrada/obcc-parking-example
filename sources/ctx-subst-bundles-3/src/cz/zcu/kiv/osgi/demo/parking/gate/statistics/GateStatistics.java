package cz.zcu.kiv.osgi.demo.parking.gate.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.api.IGateStatistics;
import cz.zcu.kiv.osgi.demo.parking.api.IParkingStatus;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.ParkingStatus;

/**
 * Extended version of GateStatistics, depends on both VehicleFlow and ParkingStatus.
 * 
 * @author brada
 *
 */
public class GateStatistics implements IGateStatistics
{
	private static GateStatistics instance;	
	
	private Logger logger = null;
	
	// dependencies
//	private IVehicleFlow parkingPlace = null;
	private IParkingStatus parkingStatus = null;
	
	private int entered = 0;
	private int leaved = 0;
	
	/** 
	 * Fake service provisioning.
	 */
	public static IGateStatistics getInstance() 
	{
		if (instance == null) {
			instance = new GateStatistics();
		}
		return instance;
	}
	
	
	protected GateStatistics()
	{
		logger = LoggerFactory.getLogger("parking-demo");
		logger.info("GateStats.r3 <init>");
		
//		parkingPlace = VehicleFlow.getInstance();
		parkingStatus = ParkingStatus.getInstance();
		clear();
	}
	
	public void vehiclesArrived(int cntArrived)
	{
		logger.info("Gate: {} vehicles about to enter",cntArrived);
		entered += cntArrived;
	}
	
	public void vehiclesDeparted(int cntDeparted)
	{
		logger.info("Gate: {} vehicles about to leave",cntDeparted);
		leaved += cntDeparted;	// FIXME can lead to inconsistent state when  entered < leaved 
	}
	
	@Override
	public int getNumberOfVehiclesEntering()
	{
		logger.info(getIdentification()+": get entering {}, full? {}", entered, parkingStatus.isFull());
		return entered;
	}

	@Override
	public int getNumberOfVehiclesLeaving()
	{
		logger.info(getIdentification()+": get leaving {}, full? {}", leaved, parkingStatus.isFull());
		return leaved;
	}

	@Override
	public int getEventCount()
	{
		int cnt = getNumberOfVehiclesEntering()+getNumberOfVehiclesLeaving();
		logger.info(getIdentification()+": get count {}", cnt);
		return cnt;
	}

	@Override
	public String getIdentification()
	{
		return "GateStatistics";
	}

	@Override
	public void clear()
	{
		leaved = 0;
		entered = 0;
		logger.info(getIdentification()+": counters cleared");
	}


}
