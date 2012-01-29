package cz.zcu.kiv.osgi.demo.parking.statsbase;

public abstract class CountingStatisticsAbstractBaseImpl implements ICountingStatistics
{
	
	int eventCount;
	
	public CountingStatisticsAbstractBaseImpl()
	{
		eventCount = 0;
	}

	@Override
	public abstract String getIdentification();

	@Override
	public int getEventCount()
	{
		return eventCount;
	}
	
	protected void incEventCount()
	{
		++eventCount;
	}
	
	protected void addToEventCount(int cnt)
	{
		eventCount += cnt;
	}
	
	public void clear()
	{
		eventCount = 0;
	}
	
	public String toString()
	{
		return getIdentification();
	}

}
