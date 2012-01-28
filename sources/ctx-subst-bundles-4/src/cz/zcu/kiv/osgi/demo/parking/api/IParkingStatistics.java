package cz.zcu.kiv.osgi.demo.parking.api;

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
