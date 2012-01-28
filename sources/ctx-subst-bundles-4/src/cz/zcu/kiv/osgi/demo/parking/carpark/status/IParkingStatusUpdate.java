package cz.zcu.kiv.osgi.demo.parking.carpark.status;

/**
 * Enable manipulating carpark status.
 * 
 * @author brada
 *
 */
public interface IParkingStatusUpdate
{
	public void decreaseFreePlaces(int amount);
	public void increaseFreePlaces(int amount);
	public void reset();
}
