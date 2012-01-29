package cz.zcu.kiv.osgi.demo.parking.lane;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.lane.statistics.LaneStatistics;


public class Activator implements BundleActivator
{

    private Logger logger;

    public Activator()
    {
        this.logger = LoggerFactory.getLogger("parking-demo");
    }

    @Override
    public void start(BundleContext context) throws Exception
    {
        logger.info("TrafficLane.r5 activator: start");
        // fake service registration to provoke provided services to appear in log
        LaneStatistics.getInstance();

        // start traffic simulator
        TrafficLane lane = new TrafficLane();
        Thread t = new Thread(lane);
        logger.info("Traffic lane activator: spawn thread");
        t.start();
        logger.info("Traffic lane activator: thread spawned");

    }

    @Override
    public void stop(BundleContext context) throws Exception
    {
        logger.info("TrafficLane.r5 activator: stop");
    }

}
