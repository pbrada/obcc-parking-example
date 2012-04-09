package cz.zcu.kiv.osgi.demo.parking.gate.statistics.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.flow.IVehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.IParkingStatus;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.IGateStatistics;


/**
 * Extended version of GateStatistics, depends on both VehicleFlow and
 * ParkingStatus.  Simulation of traffic flow moved to TrafficLane
 * started from Gate activator.
 * 
 * @author brada
 * 
 */
public class GateStatistics implements IGateStatistics, IGateUpdate
{
    private static GateStatistics instance;

    private Logger logger = null;
    private static final String lid = "GateStats.r3";

    // dependencies
//    private IVehicleFlow parkingPlace = null;
    private IParkingStatus parkingStatus = null;

//    private LaneStatistics laneStats = null;
    private int entered = 0;
    private int leaved = 0;
//    private Random r = null;

    /**
     * Create service instance.
     */
    public static GateStatistics getInstance(IVehicleFlow parking, IParkingStatus status)
    {
        if (instance == null) {
            instance = new GateStatistics(parking, status);
        }
        return instance;
    }


    protected GateStatistics(IVehicleFlow parking, IParkingStatus status)
    {
        logger = LoggerFactory.getLogger("parking-demo");
        logger.info(lid + ": <init>");

//        parkingPlace = parking;
        parkingStatus = status;
//        laneStats = (LaneStatistics) LaneStatistics.getInstance();
//        r = new Random();
        clear();
    }

    @Override
    public void vehiclesArrived(int cntArrived)
    {
        logger.info("Gate: {} vehicles about to enter", cntArrived);
        entered += cntArrived;
    }

    @Override
    public void vehiclesDeparted(int cntDeparted)
    {
        logger.info("Gate: {} vehicles about to leave", cntDeparted);
        leaved += cntDeparted;	// FIXME can lead to inconsistent state when
                               // entered < leaved
    }

    @Override
    public int getNumberOfVehiclesEntering()
    {
        logger.info(getIdentification() + ": get entering {}, full? {}", entered, parkingStatus.isFull());
        return entered;
    }

    @Override
    public int getNumberOfVehiclesLeaving()
    {
        logger.info(getIdentification() + ": get leaving {}, full? {}", leaved, parkingStatus.isFull());
        return leaved;
    }


    @Override
    public int getEventCount()
    {
        int cnt = getNumberOfVehiclesEntering() + getNumberOfVehiclesLeaving();
        logger.info(getIdentification() + ": get count {}", cnt);
        return cnt;
    }


    @Override
    public String getIdentification()
    {
        return lid;
    }

    @Override
    public void clear()
    {
        leaved = 0;
        entered = 0;
        logger.info(getIdentification() + ": counters cleared");
    }


}
