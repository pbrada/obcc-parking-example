package cz.zcu.kiv.osgi.demo.parking.carpark.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.statsbase.CountingStatisticsAbstractBaseImpl;

public class ParkingStatistics extends CountingStatisticsAbstractBaseImpl implements IParkingStatistics
{
	private static ParkingStatistics instance = null;
	private Logger logger;

	int cntArrived;
	int cntDeparted;
	
	public ParkingStatistics()
	{
		super();
		logger = LoggerFactory.getLogger("parking-demo");
		logger.info("ParkingStats.r4 <init>");
		clear();
	}
	
	// fake service provisioning
	public static ParkingStatistics getInstance()
	{
		if (instance == null) {
			instance = new ParkingStatistics();
		}
		return instance;
	}
	
	@Override
	public String getIdentification()
	{
		return "ParkingStatistics";
	}

	@Override
	public int getCountVehiclesArrived()
	{
		logger.info("{}: getArrived {}",getIdentification(),cntArrived);
		return cntArrived;
	}
	
	public void vehiclesArrived(int cnt)
	{
		logger.info("{}: newly arrived {}",getIdentification(),cnt);
		cntArrived += cnt;
		addToEventCount(cnt);
	}

	@Override
	public int getCountVehiclesDeparted()
	{
		logger.info("{}: getDeparted {}",getIdentification(),cntDeparted);
		return cntDeparted;
	}
	
	public void vehiclesDeparted(int cnt)
	{
		logger.info("{}: newly departed {}",getIdentification(),cnt);
		cntDeparted += cnt;
		addToEventCount(cnt);
	}

	@Override
	public void clear()
	{
		cntArrived = 0;
		cntDeparted = 0;
	}

}
