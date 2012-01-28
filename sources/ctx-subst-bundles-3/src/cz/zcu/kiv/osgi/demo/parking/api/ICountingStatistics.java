package cz.zcu.kiv.osgi.demo.parking.api;

public interface ICountingStatistics
{
	public String getIdentification();
	public int getEventCount();
	public void clear();
}
