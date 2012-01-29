package cz.zcu.kiv.osgi.demo.parking.carpark.statistics;

import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;

/**
 * Added in this revision, INS-ertion diff.
 * 
 * @author brada
 *
 */
public interface IParkingStatistics extends ICountingStatistics
{
	public int getCountVehiclesArrived();
	public int getCountVehiclesDeparted();
}
