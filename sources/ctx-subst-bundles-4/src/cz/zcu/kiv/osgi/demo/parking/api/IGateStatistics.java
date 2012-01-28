package cz.zcu.kiv.osgi.demo.parking.api;

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
