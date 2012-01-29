package cz.zcu.kiv.osgi.demo.parking.lane;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.gate.statistics.IGateStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.GateStatistics;

import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.IVehicleSink;
import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.VehicleSink;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.LaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.sign.roadsign.IRoadSign;
import cz.zcu.kiv.osgi.demo.parking.sign.roadsign.RoadSign;


public class TrafficLane implements Runnable
{

    private static final int NUM_CYCLES = 10;
    private static final long PAUSE_TIME = 300;
    private static final int MAX_VEHICLES_IN_BATCH = 10;

    private LaneStatistics lane;
    private Logger logger;

    // dependencies
    private IVehicleSink vehicleSink;
    private IGateStatistics gateStats;
    private IRoadSign sign;

    public TrafficLane()
    {
        logger = LoggerFactory.getLogger("parking-demo");
        logger.info("TrafficLane.r4 <init>");
        this.vehicleSink = VehicleSink.getInstance();
        this.lane = LaneStatistics.getInstance();
        this.gateStats = GateStatistics.getInstance();
        this.sign = RoadSign.getInstance();
    }

    /**
     * Simulates traffic by "injecting" vehicles into the VehicleSink.
     */
    @Override
    public void run()
    {
        logger.info("(!) TrafficLane: thread starting");
        Random r = new Random();
        int didNotFitIn = 0;

        sign.showMessage("Car park opening, places free " + gateStats.getNumFreePlaces());

        for (int i = 0; i < NUM_CYCLES; ++i) {
            logger.info("TrafficLane: loop #{}", i);
            int batch = r.nextInt(MAX_VEHICLES_IN_BATCH);
            logger.info("TrafficLane: Generating {} vehicles in the lane", batch);
            for (int v = 0; v < batch; ++v) {
                try {
                    vehicleSink.consumeVehicle();
                }
                catch (IllegalStateException ise) {
                    logger.error("TrafficLane: vehicle sink threw {}", ise.getMessage());
                    ++didNotFitIn;
                }
            }
            lane.vehiclesPassing(batch);
            sign.showMessage("PARKING FREE " + gateStats.getNumFreePlaces());
            try {
                Thread.sleep(PAUSE_TIME);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Thread.yield();
        }
        logger.info("(!) TrafficLane: traffic simulation ended. Vehicles not parked: {}", didNotFitIn);
    }

}
