package cz.zcu.kiv.osgi.demo.parking.gate.statistics;

import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;

public interface IGateStatistics extends ICountingStatistics
{
	public int getNumberOfVehiclesEntering();
	public int getNumberOfVehiclesLeaving();	
}
