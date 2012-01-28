package cz.zcu.kiv.osgi.demo.parking.sign;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.zcu.kiv.osgi.demo.parking.sign.roadsign.IRoadSign;
import cz.zcu.kiv.osgi.demo.parking.sign.roadsign.RoadSign;


public class Activator implements BundleActivator
{

    private Logger logger;
    private IRoadSign sign;

    public Activator()
    {
        this.logger = LoggerFactory.getLogger("parking-demo");
    }

    @Override
    public void start(BundleContext context) throws Exception
    {
        logger.info("RoadSign.r1 activator: start");
        // to provoke provided services to appear in log
        sign = RoadSign.getInstance();
        sign.switchOn();
    }

    @Override
    public void stop(BundleContext context) throws Exception
    {
        logger.info("RoadSign activator: stop");
        sign.switchOff();
    }

}
