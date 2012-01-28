package cz.zcu.kiv.osgi.demo.parking.sign.roadsign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RoadSign implements IRoadSign
{
    private static RoadSign instance = null;
    private Logger logger = null;

    private boolean isOn;

    public static IRoadSign getInstance()
    {
        if (instance == null) {
            instance = new RoadSign();
        }
        return instance;
    }

    protected RoadSign()
    {
        logger = LoggerFactory.getLogger("parking-demo");
        logger.info("RoadSign.r5 <init>");
        isOn = false;
    }

    @Override
    public void switchOn() throws IllegalStateException
    {
        if (isOn) {
            logger.error("RoadSign: switchOn(): already on");
            throw new IllegalStateException("RoadSign already on");
        }
        logger.info("RoadSign: switchOn");
        isOn = true;
    }

    @Override
    public void switchOff() throws IllegalStateException
    {
        if (!isOn) {
            logger.error("RoadSign: switchOff(): already off");
            throw new IllegalStateException("RoadSign already off");
        }
        logger.info("RoadSign: switchOff");
        isOn = false;
    }

    @Override
    public void showMessage(String msg)
    {
        logger.info("RoadSign: show msg ", msg);
        System.out.println("[ " + msg + " ]");
    }

}
