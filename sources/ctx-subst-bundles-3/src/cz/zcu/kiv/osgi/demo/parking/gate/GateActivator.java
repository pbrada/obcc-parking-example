package cz.zcu.kiv.osgi.demo.parking.gate;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.carpark.flow.IVehicleFlow;
import cz.zcu.kiv.osgi.demo.parking.carpark.status.IParkingStatus;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.impl.GateStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.statistics.IGateStatistics;
import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.IVehicleSink;
import cz.zcu.kiv.osgi.demo.parking.gate.vehiclesink.impl.VehicleSink;
import cz.zcu.kiv.osgi.demo.parking.lane.TrafficLane;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.ILaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.lane.statistics.impl.LaneStatistics;
import cz.zcu.kiv.osgi.demo.parking.statsbase.ICountingStatistics;


public class GateActivator implements BundleActivator
{

    private Logger logger;
    private static final String lid = "Gate.r3 Activator";

    private ServiceRegistration gateSvcReg;
    private ServiceRegistration laneSvcReg;
    private ServiceRegistration sinkSvcReg;

    // dependencies
    private IVehicleFlow parking = null;
    private IParkingStatus status = null;

    
    public GateActivator()
    {
        this.logger = LoggerFactory.getLogger("parking-demo");
    }

    
    @Override
    public void start(BundleContext context) throws Exception
    {
        logger.info(lid + ": starting");

        // required services

        ServiceReference sr;
        sr = context.getServiceReference(IVehicleFlow.class.getName());
        if (sr == null) {
            logger.error(lid + ": no parking registered");
        }
        else {
            parking = (IVehicleFlow) context.getService(sr);
            if (parking == null) {
                logger.error(lid + ": no parking service available");
            }
            else {
                logger.info(lid + ": got parking service");
            }
        }
        sr = context.getServiceReference(IParkingStatus.class.getName());
        if (sr == null) {
            logger.error(lid + ": no parking registered");
        }
        else {
            status = (IParkingStatus) context.getService(sr);
            if (status == null) {
                logger.error(lid + ": no parking service available");
            }
            else {
                logger.info(lid + ": got parking service");
            }
        }

        if ((parking == null) || (status == null)) {
            logger.error(lid + ": some service unavailable, exiting");
            throw new BundleException(lid + ": some service unavailable, exiting");
        }

        // provided services

        GateStatistics gateStatsImpl = GateStatistics.getInstance(parking, status);
        LaneStatistics laneStatsImpl = LaneStatistics.getInstance();
        VehicleSink sinkImpl = VehicleSink.getInstance(parking,gateStatsImpl);
        
        String[] gateIds = new String[] {
                ICountingStatistics.class.getName(),
                IGateStatistics.class.getName()
        };
        gateSvcReg = context.registerService(gateIds, gateStatsImpl, null);
        if (null == gateSvcReg)
            throw new ServiceException(lid + ": gate svc registration failed");
        logger.info(lid + ": registered svc ", context.getService(gateSvcReg.getReference()).getClass());

        String[] laneIds = new String[] {
                ICountingStatistics.class.getName(),
                ILaneStatistics.class.getName()
        };
        laneSvcReg = context.registerService(laneIds, laneStatsImpl, null);
        if (null == laneSvcReg)
            throw new ServiceException(lid + ": lane svc registration failed");
        logger.info(lid + ": registered svc ", context.getService(laneSvcReg.getReference()).getClass());

        String[] sinkIds = new String[] {
                IVehicleSink.class.getName()
        };
        sinkSvcReg = context.registerService(sinkIds, sinkImpl, null);
        if (null == sinkSvcReg)
            throw new ServiceException(lid + ": vehicle sink svc registration failed");
        logger.info(lid + ": registered svc ", context.getService(sinkSvcReg.getReference()).getClass());

        // start traffic simulator ('coz lane stats still provided by this
        // bundle, not the already added TrafficLane)
        TrafficLane lane = new TrafficLane(sinkImpl, laneStatsImpl);
        Thread t = new Thread(lane);
        logger.info(lid + ": spawn thread");
        t.start();
        logger.info(lid + ": thread spawned");
    }

    
    @Override
    public void stop(BundleContext context) throws Exception
    {
        logger.info(lid + ": stopping");
        gateSvcReg.unregister();
        logger.info(lid + ": unreg gate svc");
        laneSvcReg.unregister();
        logger.info(lid + ": unreg lane svc");
        sinkSvcReg.unregister();
        logger.info(lid + ": unreg sink svc");
        logger.info(lid + ": stopped.");
    }

}
