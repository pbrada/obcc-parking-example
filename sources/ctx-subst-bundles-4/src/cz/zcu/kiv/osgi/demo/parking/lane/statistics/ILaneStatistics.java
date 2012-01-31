package cz.zcu.kiv.osgi.demo.parking.lane.statistics;

import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;

public interface ILaneStatistics extends ICountingStatistics
{
	public int getCountVehiclesPassed();
	/** 
	 * DON'T CALL THIS METHOD PLEASE, for demonstration purposes -- to show
	 * in future that even a contravariant change (future removal of this method) 
	 * can be behaviourally safe.
	 */
	public int getVehiclesPerInterval(int seconds);
}
