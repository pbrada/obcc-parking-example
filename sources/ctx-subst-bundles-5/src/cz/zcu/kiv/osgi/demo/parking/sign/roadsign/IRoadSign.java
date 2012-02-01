package cz.zcu.kiv.osgi.demo.parking.sign.roadsign;

public interface IRoadSign
{
    public void switchOn();
    public void switchOff();
    public void showMessage(String msg);
}
