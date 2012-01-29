package cz.zcu.kiv.osgi.demo.parking.gate.statistics;

import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.IParkingStatus;

/**
 * Evolved in this version, by extending IParkingStatus: SPE-cialization diff.
 * 
 * @author brada
 *
 */
public interface IGateStatistics extends ICountingStatistics, IParkingStatus
{
	public int getNumberOfVehiclesEntering();
	public int getNumberOfVehiclesLeaving();
}
