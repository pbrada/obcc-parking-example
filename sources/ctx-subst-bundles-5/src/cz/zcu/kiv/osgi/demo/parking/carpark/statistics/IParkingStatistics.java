package cz.zcu.kiv.osgi.demo.parking.carpark.statistics;

import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;

/**
 * Evolved in this revision by removing the hasBeenFull() method, ie GEN-eralization diff.
 */
public interface IParkingStatistics extends ICountingStatistics
{
	public int getCountVehiclesArrived();
	public int getCountVehiclesDeparted();
}
