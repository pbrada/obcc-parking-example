package cz.zcu.kiv.osgi.demo.parking.gate.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.api.CountingStatisticsAbstractBaseImpl;
import cz.zcu.kiv.osgi.demo.parking.api.IGateStatistics;
import cz.zcu.kiv.osgi.demo.parking.api.IParkingStatus;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.ParkingStatus;

/**
 * Extended version of GateStatistics, depends on both VehicleFlow and ParkingStatus.
 * 
 * @author brada
 *
 */
public class GateStatistics extends CountingStatisticsAbstractBaseImpl implements IGateStatistics
{
	private static GateStatistics instance;	
	private Logger logger = null;
	
	// dependencies
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
		logger.info("GateStats.r4 <init>");
		parkingStatus = ParkingStatus.getInstance();
		clear();
	}
	
	public void vehiclesArrived(int cntArrived)
	{
		logger.info("Gate: {} vehicles about to enter",cntArrived);
		entered += cntArrived;
		addToEventCount(cntArrived);
	}
	
	public void vehiclesDeparted(int cntDeparted)
	{
		logger.info("Gate: {} vehicles about to leave",cntDeparted);
		leaved += cntDeparted;	// FIXME can lead to inconsistent state when  entered < leaved 
		addToEventCount(cntDeparted);
	}
	
	@Override
	public int getNumberOfVehiclesEntering()
	{
		logger.info(getIdentification()+": get entering {}", entered);
		return entered;
	}

	@Override
	public int getNumberOfVehiclesLeaving()
	{
		logger.info(getIdentification()+": get leaving {}", leaved);
		return leaved;
	}

	@Override
	public String getIdentification()
	{
		return "GateStatistics,r4";
	}
	

	@Override
	public boolean isFull()
	{
		logger.info(getIdentification()+": isFull {}", parkingStatus.isFull());
		return parkingStatus.isFull();
	}


	@Override
	public int getCapacity()
	{
		logger.info(getIdentification()+": getCapacity {}", parkingStatus.getCapacity());
		return parkingStatus.getCapacity();
	}


	@Override
	public int getNumFreePlaces()
	{
		logger.info(getIdentification()+": getNumFreePlaces {}", parkingStatus.getNumFreePlaces());
		return parkingStatus.getNumFreePlaces();
	}


	@Override
	public void clear()
	{
		super.clear();
		leaved = 0;
		entered = 0;
		if (parkingStatus instanceof ParkingStatus) {
			((ParkingStatus)parkingStatus).reset();
		}
		logger.info(getIdentification()+": counters cleared");
	}

}
