package cz.zcu.kiv.osgi.demo.parking.lane.statistics;

import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;

/**
 * Evolved in this revision by replacing a method with another one -- a MUT-ation difference. However, the 
 * replaced method should not have been called anywhere in previous application revisions' clients, so this
 * API change should not matter.
 */
public interface ILaneStatistics extends ICountingStatistics
{
	public int getCountVehiclesPassed();
	// method getVehiclesPerInterval() replaced by the following one
	public double getVehiclesPerSecond();
}
