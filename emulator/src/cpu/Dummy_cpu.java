package cpu;

import mame.cpuintrfH.cpu_interface;
import static mame.cpuintrfH.*;
import static mame.driverH.*;
import static mame.memoryH.*;


public class Dummy_cpu extends cpu_interface {
    public Dummy_cpu()
    {
         cpu_num = CPU_DUMMY;
         num_irqs = 1;
         default_vector = 0;
         overclock = 1.0;
         no_int = 0;
         irq_int = -1;
         nmi_int = -1;
         address_shift = 0;
         address_bits = 16;
         endianess = CPU_IS_LE;
         align_unit = 1;
         max_inst_len = 1;
         abits1 = ABITS1_16;
         abits2 = ABITS2_16;
         abitsmin = ABITS_MIN_16;
         
        //intialize interfaces
        burn = burn_function;
    }
    @Override
    public String cpu_info(Object context, int regnum) {
        if( context==null && regnum!=0 )
		return "";
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public burnPtr burn_function = new burnPtr() { public void handler(int cycles)
    {
     throw new UnsupportedOperationException("Not supported yet.");
    }};

    @Override
    public Object init_context() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reset(Object param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set_irq_callback(irqcallbacksPtr callback) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int execute(int cycles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int get_pc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void set_op_base(int pc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
