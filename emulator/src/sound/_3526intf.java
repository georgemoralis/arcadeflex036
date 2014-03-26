
package sound;
import mame.sndintrf;
import mame.sndintrfH;
import static mame.sndintrfH.*;
import static sound._3812intfH.*;
import static mame.driverH.*;

public class _3526intf extends _3812intf
{
    public _3526intf()
    {
        sound_num=SOUND_YM3526;
        name="YM-3526";
    }
    @Override
    public int chips_num(sndintrfH.MachineSound msound) {
        return ((YM3526interface)msound.sound_interface).num;
    }

    @Override
    public int chips_clock(sndintrfH.MachineSound msound) {
        return ((YM3526interface)msound.sound_interface).baseclock;
    }

    @Override
    public int start(sndintrfH.MachineSound msound) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 0;
    }

    @Override
    public void stop() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        //no functionality expected
    }

    @Override
    public void reset() {
        //no functionality expected
    }
    public static ReadHandlerPtr YM3526_status_port_0_r = new ReadHandlerPtr() { public int handler(int offset)
    {
        return 0;//for now
    }};
    public static ReadHandlerPtr YM3526_status_port_1_r = new ReadHandlerPtr() { public int handler(int offset)
    {
        return 0;//for now
    }};
    public static WriteHandlerPtr YM3526_control_port_0_w = new WriteHandlerPtr() { public void handler(int offset, int data)
    {
            
    }};
    public static WriteHandlerPtr YM3526_write_port_0_w = new WriteHandlerPtr() { public void handler(int offset, int data)
    {
            
    }};
    public static WriteHandlerPtr YM3526_control_port_1_w = new WriteHandlerPtr() { public void handler(int offset, int data)
    {
            
    }};
    public static WriteHandlerPtr YM3526_write_port_1_w = new WriteHandlerPtr() { public void handler(int offset, int data)
    {
            
    }};
}
