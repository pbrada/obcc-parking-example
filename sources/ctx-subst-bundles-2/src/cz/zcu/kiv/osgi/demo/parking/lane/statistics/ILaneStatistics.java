package cz.zcu.kiv.osgi.demo.parking.lane.statistics;

import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;

public interface ILaneStatistics extends ICountingStatistics
{
	public int getCountVehiclesPassed();
}
