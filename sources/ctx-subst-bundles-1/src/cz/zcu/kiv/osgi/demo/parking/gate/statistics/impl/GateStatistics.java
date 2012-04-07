package cz.zcu.kiv.osgi.demo.parking.gate.statistics.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.flow.IVehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.IGateStatistics;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.impl.LaneStatistics;

/**
 * Bootstrap version of GateStatistics, depends only on VehicleFlow.
 * 
 * @author brada
 *
 */
public class GateStatistics implements IGateStatistics
{
	private static GateStatistics instance;
	
	private Logger logger = null;
	private static final String lid = "GateStats.r1";
	
	// dependencies
	private IVehicleFlow parkingPlace = null;
	
	private LaneStatistics laneStats = null;
	private int entered = 0;
	private int leaved = 0;
	private Random r = null;
	
	
	/**
	 * Create service instance.
	 */
	public static IGateStatistics getInstance(IVehicleFlow parking) 
	{
		if (instance == null) {
			instance = new GateStatistics(parking);
		}
		return instance;
	}
	
	
	protected GateStatistics(IVehicleFlow parking)
	{
		logger = LoggerFactory.getLogger("parking-demo");
		logger.info(lid+": <init>");
		
		parkingPlace = parking;
		laneStats = (LaneStatistics) LaneStatistics.getInstance();
		r = new Random();
		clear();
	}
	
	@Override
	public int getNumberOfVehiclesEntering()
	{
		// simulate incoming traffic
		int numVehicles = r.nextInt(10);
		logger.info(getIdentification()+": {} vehicles about to enter",numVehicles);
		laneStats.vehiclesPassing(numVehicles);
		for (int cnt = 0; cnt < numVehicles; ++cnt) {
			parkingPlace.arrive();
			entered++;
		}
		logger.info(getIdentification()+": get entering {}", entered);
		return entered;
	}

	@Override
	public int getNumberOfVehiclesLeaving()
	{
		// simulate departing traffic
		int numVehicles = r.nextInt(10);
		logger.info(getIdentification()+": {} vehicles about to leave",numVehicles);
		for (int cnt = 0; cnt < numVehicles; ++cnt) {
			parkingPlace.leave();
			leaved++;
		}
		logger.info(getIdentification()+": get leaving {}", leaved);
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
		return lid;
	}

	@Override
	public void clear()
	{
		leaved = 0;
		entered = 0;
		logger.info(getIdentification()+": counters cleared");
	}


}
