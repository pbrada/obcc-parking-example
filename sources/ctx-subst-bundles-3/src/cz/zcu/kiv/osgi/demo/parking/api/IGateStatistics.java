package cz.zcu.kiv.osgi.demo.parking.api;

public interface IGateStatistics extends ICountingStatistics
{
	public int getNumberOfVehiclesEntering();
	public int getNumberOfVehiclesLeaving();	
}
