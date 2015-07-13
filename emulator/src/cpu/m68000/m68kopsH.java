package cpu.m68000;

import static cpu.m68000.m68kcpu.*;
import static arcadeflex.libc_old.*;
import static cpu.m68000.m68kcpuH.*;

public class m68kopsH {

    public static abstract interface opcode {

        public abstract void handler();
    }

    public static opcode m68000_1010 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_1111 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_abcd_rr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_abcd_mm_ax7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_abcd_mm_ay7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_abcd_mm_axy7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_abcd_mm = new opcode() { /*recheck*/

        public void handler() {
            long src = m68ki_read_8(--m68k_cpu.ar[(int) (get_CPU_IR() & 7)]);
            long ea = --m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)];
            long dst = m68ki_read_8(ea);
            long res = LOW_NIBBLE(src) + LOW_NIBBLE(dst) + ((m68k_cpu.x_flag != 0) ? 1L : 0L);

            if (res > 9) {
                res += 6;
            }
            res += HIGH_NIBBLE(src) + HIGH_NIBBLE(dst);
            if ((m68k_cpu.x_flag = m68k_cpu.c_flag = (res > 0x99) ? 1L : 0L) != 0L) {
                res -= 0xa0;
            }

            m68k_cpu.n_flag = GET_MSB_8(res); /* officially undefined */

            m68ki_write_8(ea, res);

            if (MASK_OUT_ABOVE_8(res) != 0) {
                m68k_cpu.not_z_flag = 1;
            }
            USE_CLKS(18);
            if (m68klog != null) {
                fprintf(m68klog, "abcd_mm :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_add_er_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_ai_8 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = m68ki_read_8(EA_AI());
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_8(src + dst);

            set_DX(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_8(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_8(src, dst, res);
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "add_er_ai_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_add_er_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = get_DY();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(src + dst);

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "add_er_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_add_er_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_di_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = m68ki_read_16(EA_DI());
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(src + dst);

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "add_er_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_add_er_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_i_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = m68ki_read_imm_16();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(src + dst);

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "add_er_i_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_add_er_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_er_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_di_16 = new opcode() {
        public void handler() {
            long ea = EA_DI();
            long src = get_DX();
            long dst = m68ki_read_16(ea);
            long res = MASK_OUT_ABOVE_16(src + dst);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "add_re_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_add_re_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_add_re_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_d_16 = new opcode() {
        public void handler() {
            long a_dst = get_AX();

            set_AX(MASK_OUT_ABOVE_32(a_dst + MAKE_INT_16(get_DY())));
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "adda_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_adda_a_16 = new opcode() {
        public void handler() {
            long a_dst = get_AX();

            set_AX(MASK_OUT_ABOVE_32(a_dst + MAKE_INT_16(get_AY())));
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "adda_a_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_adda_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_i_16 = new opcode() {
        public void handler() {
            long a_dst = get_AX();

            set_AX(MASK_OUT_ABOVE_32(a_dst + MAKE_INT_16(m68ki_read_imm_16())));
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "adda_i_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_adda_d_32 = new opcode() {
        public void handler() {
            long a_dst = get_AX();
            set_AX(MASK_OUT_ABOVE_32(a_dst + get_DY()));
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "adda_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_adda_a_32 = new opcode() {
        public void handler() {
            long a_dst = get_AX();

            set_AX(MASK_OUT_ABOVE_32(a_dst + get_AY()));
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "adda_a_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_adda_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_adda_i_32 = new opcode() {
        public void handler() {
            long a_dst = get_AX();

            set_AX(MASK_OUT_ABOVE_32(a_dst + m68ki_read_imm_32()));
            USE_CLKS(6 + 10);
            if (m68klog != null) {
                fprintf(m68klog, "adda_i_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addi_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long src = m68ki_read_imm_16();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(src + dst);

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "addi_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addi_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_d_32 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long src = m68ki_read_imm_32();
            long dst = d_dst;
            set_DY(MASK_OUT_ABOVE_32(src + dst));
            long res = get_DY();

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_32(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_32(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_32(src, dst, res);
            USE_CLKS(16);
            if (m68klog != null) {
                fprintf(m68klog, "addi_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addi_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addi_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_d_8 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_8(src + dst);

            set_DY(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_8(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_8(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "addq_d_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addq_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_di_8 = new opcode() {
        public void handler() {
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long ea = EA_DI();
            long dst = m68ki_read_8(ea);
            long res = MASK_OUT_ABOVE_8(src + dst);

            m68ki_write_8(ea, res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_8(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_8(src, dst, res);
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "addq_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addq_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(src + dst);

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "addq_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addq_a_16 = new opcode() {
        public void handler() {
            long a_dst = get_AY();
            set_AY(MASK_OUT_ABOVE_32(a_dst + (((get_CPU_IR() >>> 9) - 1) & 7) + 1));
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "addq_a_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addq_ai_16 = new opcode() {
        public void handler() {
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long ea = EA_AI();
            long dst = m68ki_read_16(ea);
            long res = MASK_OUT_ABOVE_16(src + dst);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "addq_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addq_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_di_16 = new opcode() {
        public void handler() {
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long ea = EA_DI();
            long dst = m68ki_read_16(ea);
            long res = MASK_OUT_ABOVE_16(src + dst);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "addq_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }

        }
    };
    public static opcode m68000_addq_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_al_16 = new opcode() {
        public void handler() {
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long ea = EA_AL();
            long dst = m68ki_read_16(ea);
            long res = MASK_OUT_ABOVE_16(src + dst);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(8 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "addq_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addq_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_a_32 = new opcode() {
        public void handler() {
            long a_dst = get_AY();

            set_AY(MASK_OUT_ABOVE_32(a_dst + (((get_CPU_IR() >>> 9) - 1) & 7) + 1));
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "addq_a_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addq_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addq_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addx_rr_8 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = get_DY();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_8(src + dst + ((m68k_cpu.x_flag != 0) ? 1L : 0L));

            set_DX(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            if (res != 0L) {
                m68k_cpu.not_z_flag = 1;
            }
            m68k_cpu.v_flag = VFLAG_ADD_8(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_8(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "addx_rr_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addx_rr_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = get_DY();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(src + dst + ((m68k_cpu.x_flag != 0) ? 1L : 0L));

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            if (res != 0L) {
                m68k_cpu.not_z_flag = 1;
            }
            m68k_cpu.v_flag = VFLAG_ADD_16(src, dst, res);
            m68k_cpu.x_flag = CFLAG_ADD_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_ADD_16(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "addx_rr_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_addx_rr_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addx_mm_8_ax7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addx_mm_8_ay7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addx_mm_8_axy7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addx_mm_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addx_mm_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_addx_mm_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_ai_8 = new opcode() {
        public void handler() {
            set_DX(get_DX() & (m68ki_read_8(EA_AI()) | 0xffffff00L));
            long res = MASK_OUT_ABOVE_8(get_DX());

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "and_er_ai_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_and_er_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_di_8 = new opcode() {
        public void handler() {
            set_DX(get_DX() & (m68ki_read_8(EA_DI()) | 0xffffff00L));
            long res = MASK_OUT_ABOVE_8(get_DX());

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "and_er_di_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_and_er_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_al_8 = new opcode() {
        public void handler() {
            set_DX(get_DX() & (m68ki_read_8(EA_AL()) | 0xffffff00L));
            long res = MASK_OUT_ABOVE_8(get_DX());

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "and_er_al_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_and_er_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_d_16 = new opcode() {
        public void handler() {
            set_DX(get_DX() & (get_DY() | 0xffff0000L));
            long res = MASK_OUT_ABOVE_16(get_DX());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "and_er_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_and_er_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_di_16 = new opcode() {
        public void handler() {
            set_DX(get_DX() & (m68ki_read_16(EA_DI()) | 0xffff0000L));
            long res = MASK_OUT_ABOVE_16(get_DX());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "and_er_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_and_er_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_al_16 = new opcode() {
        public void handler() {
            set_DX(get_DX() & (m68ki_read_16(EA_AL()) | 0xffff0000L));
            long res = MASK_OUT_ABOVE_16(get_DX());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "and_er_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_and_er_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_i_16 = new opcode() {
        public void handler() {
            set_DX(get_DX() & (m68ki_read_imm_16() | 0xffff0000L));
            long res = MASK_OUT_ABOVE_16(get_DX());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "and_er_i_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_and_er_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_er_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_al_16 = new opcode() {
        public void handler() {
            long ea = EA_AL();
            long res = MASK_OUT_ABOVE_16(get_DX() & m68ki_read_16(ea));

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(8 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "and_re_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_and_re_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_and_re_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_to_ccr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_to_sr = new opcode() {
        public void handler() {
            long and_val = m68ki_read_imm_16();

            if (get_CPU_S() != 0) {
                m68ki_set_sr(m68ki_get_sr() & and_val);
                USE_CLKS(20);
                if (m68klog != null) {
                    fprintf(m68klog, "andi_to_sr(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            m68ki_exception(EXCEPTION_PRIVILEGE_VIOLATION);
            if (m68klog != null) {
                fprintf(m68klog, "andi_to_sr(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_andi_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_di_8 = new opcode() {
        public void handler() {
            long tmp = m68ki_read_imm_8();
            long ea = EA_DI();
            long res = tmp & m68ki_read_8(ea);

            m68ki_write_8(ea, res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "andi_di_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_andi_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_d_16 = new opcode() {
        public void handler() {
            set_DY(get_DY() & (m68ki_read_imm_16() | 0xffff0000L));
            long res = MASK_OUT_ABOVE_16(get_DY());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "andi_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_andi_ai_16 = new opcode() {
        public void handler() {
            long tmp = m68ki_read_imm_16();
            long ea = EA_AI();
            long res = tmp & m68ki_read_16(ea);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(12 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "andi_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_andi_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_di_16 = new opcode() {
        public void handler() {
            long tmp = m68ki_read_imm_16();
            long ea = EA_DI();
            long res = tmp & m68ki_read_16(ea);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "andi_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_andi_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_d_32 = new opcode() {
        public void handler() {
            set_DY(get_DY() & (m68ki_read_imm_32()));
            long res = get_DY();

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(14);
            if (m68klog != null) {
                fprintf(m68klog, "andi_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_andi_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_andi_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_s_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_s_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long src = MASK_OUT_ABOVE_16(d_dst);
            long res = src >>> shift;

            if (GET_MSB_16(src) != 0) {
                res |= m68k_shift_16_table[(int) shift];
            }

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.x_flag = (src >>> (shift - 1)) & 1;
            m68k_cpu.c_flag = (src >>> (shift - 1)) & 1;
            USE_CLKS((int) ((shift << 1) + 6));
            if (m68klog != null) {
                fprintf(m68klog, "asr_s_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_asr_s_32 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long src = MASK_OUT_ABOVE_32(d_dst);
            long res = src >>> shift;

            if (GET_MSB_32(src) != 0L) {
                res |= m68k_shift_32_table[(int) shift];
            }

            set_DY(res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.x_flag = (src >>> (shift - 1)) & 1;
            m68k_cpu.c_flag = (src >>> (shift - 1)) & 1;
            USE_CLKS((int) ((shift << 1) + 8));
            if (m68klog != null) {
                fprintf(m68klog, "asr_s_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_asr_r_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_r_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_r_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_ea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_ea_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_ea_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_ea_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_ea_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_ea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asr_ea_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_s_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_s_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_s_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_r_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_r_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_r_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_ea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_ea_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_ea_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_ea_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_ea_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_ea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_asl_ea_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bhi_16 = new opcode() {
        public void handler() {
            if (CONDITION_HI()) {
                m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bhi_16(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "bhi_16(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_bhi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bhi_8 = new opcode() {
        public void handler() {
            if (CONDITION_HI()) {
                m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bhi_8(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "bhi_8(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bls_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bls_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bls_8 = new opcode() {
        public void handler() {
            if (CONDITION_LS()) {
                m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bls_8(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "bls_8(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bcc_16 = new opcode() {
        public void handler() {
            if (CONDITION_CC()) {
                m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bcc_16(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "bcc_16(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_bcc_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bcc_8 = new opcode() {
        public void handler() {
            if (CONDITION_CC()) {
                m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bcc_8(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "bcc_8(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bcs_16 = new opcode() {
        public void handler() {
            if (CONDITION_CS()) {
                m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bcs_16(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "bcs_16(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_bcs_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bcs_8 = new opcode() {
        public void handler() {
            if (CONDITION_CS()) {
                m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bcs_8(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "bcs_8(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bne_16 = new opcode() {
        public void handler() {
            if (CONDITION_NE()) {
                m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bne_16(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "bne_16(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_bne_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bne_8 = new opcode() {
        public void handler() {
            if (CONDITION_NE()) {
                m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bne_8(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "bne_8(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_beq_16 = new opcode() {
        public void handler() {
            if (CONDITION_EQ()) {
                m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "beq_16(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "beq_16(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_beq_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_beq_8 = new opcode() {
        public void handler() {
            if (CONDITION_EQ()) {
                m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "beq_8(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "beq_8(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bvc_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bvc_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bvc_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bvs_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bvs_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bvs_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bpl_16 = new opcode() {
        public void handler() {
            if (CONDITION_PL()) {
                m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bpl_16(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "bpl_16(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_bpl_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bpl_8 = new opcode() {
        public void handler() {
            if (CONDITION_PL()) {
                m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bpl_8(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "bpl_8(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bmi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bmi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bmi_8 = new opcode() {
        public void handler() {
            if (CONDITION_MI()) {
                m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "bmi_8(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "bmi_8(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bge_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bge_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bge_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_blt_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_blt_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_blt_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bgt_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bgt_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bgt_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ble_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_ble_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ble_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_r_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_s_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_s_ai = new opcode() {
        public void handler() {
            long mask = 1 << (m68ki_read_imm_8() & 7);
            long ea = EA_AI();
            long src = m68ki_read_8(ea);

            m68k_cpu.not_z_flag = src & mask;
            m68ki_write_8(ea, src ^ mask);
            USE_CLKS(12 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "bchg_s_ai :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bchg_s_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_s_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_s_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_s_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_s_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_s_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_s_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bchg_s_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_r_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_s_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_s_ai = new opcode() {
        public void handler() {
            long mask = 1 << (m68ki_read_imm_8() & 7);
            long ea = EA_AI();
            long src = m68ki_read_8(ea);

            m68k_cpu.not_z_flag = src & mask;
            m68ki_write_8(ea, src & ~mask);
            USE_CLKS(12 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "bclr_s_ai :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bclr_s_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_s_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_s_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_s_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_s_di = new opcode() {
        public void handler() {
            long mask = 1 << (m68ki_read_imm_8() & 7);
            long ea = EA_DI();
            long src = m68ki_read_8(ea);

            m68k_cpu.not_z_flag = src & mask;
            m68ki_write_8(ea, src & ~mask);
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "bclr_s_di :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bclr_s_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_s_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bclr_s_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfchg_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfchg_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfchg_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfchg_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfchg_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfchg_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfclr_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfclr_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfclr_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfclr_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfclr_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfclr_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfexts_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfexts_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfexts_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfexts_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfexts_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfexts_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfexts_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfexts_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfextu_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfextu_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfextu_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfextu_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfextu_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfextu_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfextu_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfextu_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfffo_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfffo_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfffo_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfffo_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfffo_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfffo_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfffo_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfffo_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfins_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfins_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfins_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfins_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfins_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfins_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfset_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfset_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfset_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfset_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfset_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bfset_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bftst_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bftst_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bftst_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bftst_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bftst_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bftst_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bftst_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_bftst_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_bkpt = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bra_16 = new opcode() {
        public void handler() {
            m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
            USE_CLKS(10);
            if (m68klog != null) {
                fprintf(m68klog, "bra_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_bra_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bra_8 = new opcode() {
        public void handler() {
            m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
            USE_CLKS(10);
            if (m68klog != null) {
                fprintf(m68klog, "bra_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bset_r_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_r_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_r_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_r_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_r_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_r_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_r_di = new opcode() {
        public void handler() {
            long ea = EA_DI();
            long src = m68ki_read_8(ea);
            long mask = 1 << (get_DX() & 7);

            m68k_cpu.not_z_flag = src & mask;
            m68ki_write_8(ea, src | mask);
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "bset_r_di :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bset_r_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_r_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_r_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_s_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_s_ai = new opcode() {
        public void handler() {
            long mask = 1 << (m68ki_read_imm_8() & 7);
            long ea = EA_AI();
            long src = m68ki_read_8(ea);

            m68k_cpu.not_z_flag = src & mask;
            m68ki_write_8(ea, src | mask);
            USE_CLKS(12 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "bset_s_ai :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bset_s_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_s_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_s_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_s_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_s_di = new opcode() {
        public void handler() {
            long mask = 1 << (m68ki_read_imm_8() & 7);
            long ea = EA_DI();
            long src = m68ki_read_8(ea);

            m68k_cpu.not_z_flag = src & mask;
            m68ki_write_8(ea, src | mask);
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "bset_s_di :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bset_s_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_s_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bset_s_al = new opcode() {
        public void handler() {
            long mask = 1 << (m68ki_read_imm_8() & 7);
            long ea = EA_AL();
            long src = m68ki_read_8(ea);

            m68k_cpu.not_z_flag = src & mask;
            m68ki_write_8(ea, src | mask);
            USE_CLKS(12 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "bset_s_al :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_bsr_16 = new opcode() {
        public void handler() {
            m68ki_push_32(get_CPU_PC() + 2);
            m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
            USE_CLKS(18);
            if (m68klog != null) {
                fprintf(m68klog, "bsr_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_bsr_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_bsr_8 = new opcode() {
        public void handler() {
            m68ki_push_32(get_CPU_PC());
            m68ki_branch_byte(MASK_OUT_ABOVE_8(get_CPU_IR()));
            USE_CLKS(18);
            if (m68klog != null) {
                fprintf(m68klog, "bsr_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_btst_r_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_r_ai = new opcode() {
        public void handler() {
            m68k_cpu.not_z_flag = m68ki_read_8(EA_AI()) & (1 << (get_DX() & 7));
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "btst_r_ai :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_btst_r_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_r_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_r_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_r_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_r_di = new opcode() {
        public void handler() {
            m68k_cpu.not_z_flag = m68ki_read_8(EA_DI()) & (1 << (get_DX() & 7));
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "btst_r_di :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_btst_r_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_r_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_r_al = new opcode() {
        public void handler() {
            m68k_cpu.not_z_flag = m68ki_read_8(EA_AL()) & (1 << (get_DX() & 7));
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "btst_r_al :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_btst_r_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_r_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_r_i = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_s_d = new opcode() {
        public void handler() {
            m68k_cpu.not_z_flag = get_DY() & (1 << (m68ki_read_imm_8() & 0x1f));
            USE_CLKS(10);
            if (m68klog != null) {
                fprintf(m68klog, "btst_s_d :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_btst_s_ai = new opcode() {
        public void handler() {
            long bit = m68ki_read_imm_8() & 7;

            m68k_cpu.not_z_flag = m68ki_read_8(EA_AI()) & (1 << bit);
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "btst_s_ai :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_btst_s_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_s_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_s_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_s_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_s_di = new opcode() {
        public void handler() {
            long bit = m68ki_read_imm_8() & 7;

            m68k_cpu.not_z_flag = m68ki_read_8(EA_DI()) & (1 << bit);
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "btst_s_di :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_btst_s_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_s_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_s_al = new opcode() {
        public void handler() {
            long bit = m68ki_read_imm_8() & 7;

            m68k_cpu.not_z_flag = m68ki_read_8(EA_AL()) & (1 << bit);
            USE_CLKS(8 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "btst_s_al :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_btst_s_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_btst_s_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_callm_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_callm_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_callm_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_callm_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_callm_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_callm_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_callm_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas2_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cas2_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_d_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_chk_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_chk2_cmp2_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_d_16 = new opcode() {
        public void handler() {
            set_DY(get_DY() & 0xffff0000L);

            m68k_cpu.n_flag = 0;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            m68k_cpu.not_z_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "clr_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_clr_ai_16 = new opcode() {
        public void handler() {
            m68ki_write_16(EA_AI(), 0);

            m68k_cpu.n_flag = 0;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            m68k_cpu.not_z_flag = 0;
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "clr_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_clr_pi_16 = new opcode() {
        public void handler() {
            m68ki_write_16(EA_PI_16(), 0);

            m68k_cpu.n_flag = 0;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            m68k_cpu.not_z_flag = 0;
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "clr_pi_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_clr_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_di_16 = new opcode() {
        public void handler() {
            m68ki_write_16(EA_DI(), 0);

            m68k_cpu.n_flag = 0;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            m68k_cpu.not_z_flag = 0;
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "clr_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_clr_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_al_16 = new opcode() {
        public void handler() {
            m68ki_write_16(EA_AL(), 0);

            m68k_cpu.n_flag = 0;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            m68k_cpu.not_z_flag = 0;
            USE_CLKS(8 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "clr_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_clr_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_ai_32 = new opcode() {
        public void handler() {
            m68ki_write_32(EA_AI(), 0);

            m68k_cpu.n_flag = 0;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            m68k_cpu.not_z_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "clr_ai_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_clr_pi_32 = new opcode() {
        public void handler() {
            m68ki_write_32(EA_PI_32(), 0);

            m68k_cpu.n_flag = 0;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            m68k_cpu.not_z_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "clr_pi_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_clr_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_di_32 = new opcode() {
        public void handler() {
            m68ki_write_32(EA_DI(), 0);

            m68k_cpu.n_flag = 0;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            m68k_cpu.not_z_flag = 0;
            USE_CLKS(12 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "clr_di_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_clr_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_clr_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_ai_8 = new opcode() {
        public void handler() {
            long src = m68ki_read_8(EA_AI());
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_8(dst - src);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_8(src, dst, res);
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_ai_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmp_pi_8 = new opcode() {
        public void handler() {
            long src = m68ki_read_8(EA_PI_8());
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_8(dst - src);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_8(src, dst, res);
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_pi_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmp_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_ix_8 = new opcode() {
        public void handler() {
            long src = m68ki_read_8(EA_IX());
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_8(dst - src);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_8(src, dst, res);
            USE_CLKS(4 + 10);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_ix_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmp_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_d_16 = new opcode() {
        public void handler() {
            long src = get_DY();
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmp_a_16 = new opcode() {
        public void handler() {
            long src = get_AY();
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_a_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmp_ai_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_16(EA_AI());
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }

        }
    };
    public static opcode m68000_cmp_pi_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_16(EA_PI_16());
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_pi_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmp_pd_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_16(EA_PD_16());
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(4 + 6);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_pd_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmp_di_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_16(EA_DI());
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmp_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_al_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_16(EA_AL());
            long dst = get_DX();
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "cmp_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmp_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmp_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_d_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_a_32 = new opcode() {
        public void handler() {
            long src = get_AY();
            long dst = get_AX();
            long res = MASK_OUT_ABOVE_32(dst - src);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_32(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_32(src, dst, res);
            USE_CLKS(6);
            if (m68klog != null) {
                fprintf(m68klog, "cmpa_a_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpa_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpa_i_32 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_32();
            long dst = get_AX();
            long res = MASK_OUT_ABOVE_32(dst - src);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_32(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_32(src, dst, res);
            USE_CLKS(6 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "cmpa_i_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpi_d_8 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_8();
            long dst = get_DY();
            long res = MASK_OUT_ABOVE_8(dst - src);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_8(src, dst, res);
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "cmpi_d_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpi_ai_8 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_8();
            long dst = m68ki_read_8(EA_AI());
            long res = MASK_OUT_ABOVE_8(dst - src);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_8(src, dst, res);
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "cmpi_ai_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpi_pi_8 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_8();
            long dst = m68ki_read_8(EA_PI_8());
            long res = MASK_OUT_ABOVE_8(dst - src);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_8(src, dst, res);
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "cmpi_pi_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpi_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_di_8 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_8();
            long dst = m68ki_read_8(EA_DI());
            long res = MASK_OUT_ABOVE_8(dst - src);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_8(src, dst, res);
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "cmpi_di_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpi_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cmpi_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cmpi_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_d_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_16();
            long dst = get_DY();
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "cmpi_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpi_ai_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_16();
            long dst = m68ki_read_16(EA_AI());
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "cmpi_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpi_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_di_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_16();
            long dst = m68ki_read_16(EA_DI());
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "cmpi_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpi_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_al_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_16();
            long dst = m68ki_read_16(EA_AL());
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(8 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "cmpi_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_cmpi_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cmpi_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_d_32 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_32();
            long dst = get_DY();
            long res = MASK_OUT_ABOVE_32(dst - src);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_32(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_32(src, dst, res);
            USE_CLKS(14);
            if (m68klog != null) {
                fprintf(m68klog, "cmpi_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpi_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpi_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cmpi_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cmpi_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpm_8_ax7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpm_8_ay7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpm_8_axy7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpm_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_cmpm_16 = new opcode() { /*RECHECK*/

        public void handler() {
            long src = m68ki_read_16((m68k_cpu.ar[(int) (get_CPU_IR() & 7)] += 2) - 2);
            long dst = m68ki_read_16((m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] += 2) - 2);
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "cmpm_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_cmpm_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cpbcc = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cpdbcc = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cpgen = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cpscc = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_cptrapcc = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbt = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbf = new opcode() {
        public void handler() {
            long d_reg = get_DY();
            long res = MASK_OUT_ABOVE_16(d_reg - 1);
            set_DY(MASK_OUT_BELOW_16(d_reg) | res);
            if (res != 0xffffL) {
                m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                USE_CLKS(10);
                if (m68klog != null) {
                    fprintf(m68klog, "dbf1 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(14);
            if (m68klog != null) {
                fprintf(m68klog, "dbf2 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_dbhi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbls = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbcc = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbcs = new opcode() {/*RECHECK*/

        public void handler() {
            if (CONDITION_NOT_CS()) {
                long d_reg = get_DY();
                long res = MASK_OUT_ABOVE_16(d_reg - 1);

                set_DY(MASK_OUT_BELOW_16(d_reg) | res);
                if (res != 0xffffL) {
                    m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                    USE_CLKS(10);
                    if (m68klog != null) {
                        fprintf(m68klog, "dbcs(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                    }
                    return;
                }
                set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
                USE_CLKS(14);
                if (m68klog != null) {
                    fprintf(m68klog, "dbcs(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "dbcs(3) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_dbne = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbeq = new opcode() {/*RECHECK*/

        public void handler() {
            if (CONDITION_NOT_EQ()) {
                long d_reg = get_DY();
                long res = MASK_OUT_ABOVE_16(d_reg - 1);

                set_DY(MASK_OUT_BELOW_16(d_reg) | res);
                if (res != 0xffffL) {
                    m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                    USE_CLKS(10);
                    if (m68klog != null) {
                        fprintf(m68klog, "dbeq(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                    }
                    return;
                }
                set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
                USE_CLKS(14);
                if (m68klog != null) {
                    fprintf(m68klog, "dbeq(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "dbeq(3) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_dbvc = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbvs = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbpl = new opcode() {/*RECHECK*/

        public void handler() {
            if (CONDITION_NOT_PL()) {
                long d_reg = get_DY();
                long res = MASK_OUT_ABOVE_16(d_reg - 1);

                set_DY(MASK_OUT_BELOW_16(d_reg) | res);
                if (res != 0xffffL) {
                    m68ki_branch_word(m68ki_read_16(get_CPU_PC()));
                    USE_CLKS(10);
                    if (m68klog != null) {
                        fprintf(m68klog, "dbpl(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                    }
                    return;
                }
                set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
                USE_CLKS(14);
                if (m68klog != null) {
                    fprintf(m68klog, "dbpl(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "dbpl(3) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_dbmi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbge = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dblt = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dbgt = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_dble = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_d_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divs_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_d_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_divu_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_divl_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_d_8 = new opcode() {
        public void handler() {
            set_DY(get_DY() ^ MASK_OUT_ABOVE_8(get_DX()));
            long res = MASK_OUT_ABOVE_8(get_DY());

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "eor_d_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_eor_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_di_8 = new opcode() {
        public void handler() {
            long ea = EA_DI();
            long res = MASK_OUT_ABOVE_8(get_DX() ^ m68ki_read_8(ea));

            m68ki_write_8(ea, res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "eor_di_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_eor_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_d_16 = new opcode() {
        public void handler() {
            set_DY(get_DY() ^ MASK_OUT_ABOVE_16(get_DX()));
            long res = MASK_OUT_ABOVE_16(get_DY());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "eor_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_eor_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eor_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_to_ccr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_to_sr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_d_16 = new opcode() {
        public void handler() {
            set_DY(get_DY() ^ m68ki_read_imm_16());
            long res = MASK_OUT_ABOVE_16(get_DY());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "eori_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_eori_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_di_16 = new opcode() {
        public void handler() {
            long tmp = m68ki_read_imm_16();
            long ea = EA_DI();
            long res = tmp ^ m68ki_read_16(ea);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "eori_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_eori_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_eori_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_exg_dd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_exg_aa = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_exg_da = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ext_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ext_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_extb = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_illegal = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_jmp_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_jmp_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_jmp_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_jmp_aw = new opcode() {
        public void handler() {
            m68ki_branch_long(EA_AW());
            USE_CLKS(0 + 10);
            if (m68klog != null) {
                fprintf(m68klog, "jmp_aw :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_jmp_al = new opcode() {
        public void handler() {
            m68ki_branch_long(EA_AL());
            USE_CLKS(0 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "jmp_al :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_jmp_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_jmp_pcix = new opcode() {
        public void handler() {
            m68ki_branch_long(EA_PCIX());
            USE_CLKS(0 + 14);
            if (m68klog != null) {
                fprintf(m68klog, "jmp_pcix :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_jsr_ai = new opcode() {
        public void handler() {
            long ea = EA_AI();
            m68ki_push_32(get_CPU_PC());
            m68ki_branch_long(ea);
            USE_CLKS(0 + 16);
            if (m68klog != null) {
                fprintf(m68klog, "jsr_ai :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_jsr_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_jsr_ix = new opcode() {
        public void handler() {
            long ea = EA_IX();
            m68ki_push_32(get_CPU_PC());
            m68ki_branch_long(ea);
            USE_CLKS(0 + 22);
            if (m68klog != null) {
                fprintf(m68klog, "jsr_ix :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_jsr_aw = new opcode() {
        public void handler() {
            long ea = EA_AW();
            m68ki_push_32(get_CPU_PC());
            m68ki_branch_long(ea);
            USE_CLKS(0 + 18);
            if (m68klog != null) {
                fprintf(m68klog, "jsr_aw :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_jsr_al = new opcode() {
        public void handler() {
            long ea = EA_AL();
            m68ki_push_32(get_CPU_PC());
            m68ki_branch_long(ea);
            USE_CLKS(0 + 20);
            if (m68klog != null) {
                fprintf(m68klog, "jsr_al :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_jsr_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_jsr_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lea_di = new opcode() {
        public void handler() {
            set_AX(EA_DI());
            USE_CLKS(0 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "lea_di :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_lea_ix = new opcode() {
        public void handler() {
            set_AX(EA_IX());
            USE_CLKS(0 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "lea_ix :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_lea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lea_al = new opcode() {
        public void handler() {
            set_AX(EA_AL());
            USE_CLKS(0 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "lea_al :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_lea_pcdi = new opcode() { /*RECHECK*/

        public void handler() {
            //long old_pc = (CPU_PC+=2) - 2;
            set_CPU_PC((get_CPU_PC() + 2) & 0xFFFFFFFFL);
            long old_pc = get_CPU_PC() - 2;
            long ea = old_pc + MAKE_INT_16(m68ki_read_16(old_pc));
            set_AX(ea);
            USE_CLKS(0 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "lea_pcdi :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_lea_pcix = new opcode() {
        public void handler() {
            set_AX(EA_PCIX());
            USE_CLKS(0 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "lea_pcix :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_link_16_a7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_link_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_link_32_a7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_link_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_s_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_s_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long src = MASK_OUT_ABOVE_16(d_dst);
            long res = src >>> shift;

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = 0;
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = (src >>> (shift - 1)) & 1;
            m68k_cpu.c_flag = (src >>> (shift - 1)) & 1;
            m68k_cpu.v_flag = 0;
            USE_CLKS((int) ((shift << 1) + 6));
            if (m68klog != null) {
                fprintf(m68klog, "lsr_s_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_lsr_s_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_r_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_r_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_r_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_ea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_ea_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_ea_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_ea_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_ea_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_ea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsr_ea_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_s_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_s_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long src = MASK_OUT_ABOVE_16(d_dst);
            long res = MASK_OUT_ABOVE_16(src << shift);

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = (src >>> (16 - shift)) & 1;
            m68k_cpu.c_flag = (src >>> (16 - shift)) & 1;
            m68k_cpu.v_flag = 0;
            USE_CLKS((int) ((shift << 1) + 6));
            if (m68klog != null) {
                fprintf(m68klog, "lsl_s_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_lsl_s_32 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long src = d_dst;
            long res = MASK_OUT_ABOVE_32(src << shift);

            set_DY(res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = (src >>> (32 - shift)) & 1;
            m68k_cpu.c_flag = (src >>> (32 - shift)) & 1;
            m68k_cpu.v_flag = 0;
            USE_CLKS((int) ((shift << 1) + 8));
            if (m68klog != null) {
                fprintf(m68klog, "lsl_s_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_lsl_r_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_r_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_r_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_ea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_ea_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_ea_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_ea_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_ea_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_ea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_lsl_ea_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_d_8 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_8(get_DY());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_d_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_pi_8 = new opcode() {
        public void handler() {
            long res = m68ki_read_8(EA_PI_8());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_pi_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_di_8 = new opcode() {
        public void handler() {
            long res = m68ki_read_8(EA_DI());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_di_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_i_8 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_8();
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_i_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_d_8 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_8(get_DY());
            long ea_dst = get_AX();

            m68ki_write_8(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "move_ai_d_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_d_8 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_8(get_DY());
            long ea_dst = m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)]++;

            m68ki_write_8(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "move_pi_d_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pi_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi7_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd7_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_d_8 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_8(get_DY());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_8(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_d_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_pi_8 = new opcode() {
        public void handler() {
            long res = m68ki_read_8(EA_PI_8());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_8(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_pi_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_d_16 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_16(get_DY());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_ai_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AI());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_pi_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_PI_16());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_pi_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_di_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_DI());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_ix_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_IX());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 10);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_ix_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_al_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AL());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_pcix_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_PCIX());
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 10);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_pcix_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_i_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_16();
            long d_dst = get_DX();

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_i_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_d_16 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_16(get_DY());
            long ea_dst = get_AX();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "move_ai_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_ai_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AI());
            long ea_dst = get_AX();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_ai_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_pi_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_PI_16());
            long ea_dst = get_AX();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_ai_pi_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_di_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_DI());
            long ea_dst = get_AX();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_ai_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_al_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AL());
            long ea_dst = get_AX();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "move_ai_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_i_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_16();
            long ea_dst = get_AX();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_ai_i_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pi_d_16 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_16(get_DY());
            long ea_dst = (m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] += 2) - 2;

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "move_pi_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pi_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pi_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_PI_16());
            long ea_dst = (m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] += 2) - 2;

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_pi_pi_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pi_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_al_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AL());
            long ea_dst = (m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] += 2) - 2;

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "move_pi_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pi_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_i_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_16();
            long ea_dst = (m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] += 2) - 2;

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_pi_i_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pd_d_16 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_16(get_DY());
            long ea_dst = m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] -= 2;

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "move_pd_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pd_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_d_16 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_16(get_DY());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_ai_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AI());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_pi_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_PI_16());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_pi_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_di_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_DI());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_i_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_16();
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_i_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ix_d_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_d_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_d_16 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_16(get_DY());
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_al_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_ai_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AI());
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_al_pi_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_PI_16());
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_pi_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_al_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_di_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_DI());
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_al_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_al_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AL());
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_al_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_i_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_16();
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_16(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_i_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_d_32 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_32(get_DY());
            set_DX(res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_ai_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_32(EA_AI());
            set_DX(res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_ai_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_pi_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_32(EA_PI_32());
            set_DX(res);
            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_pi_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_di_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_32(EA_DI());
            set_DX(res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_di_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_al_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_32(EA_AL());
            set_DX(res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 16);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_al_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_dd_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_dd_i_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_32();
            set_DX(res);
            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_dd_i_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_a_32 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_32(get_AY());
            long ea_dst = get_AX();

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "move_ai_a_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ai_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ai_i_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_32();
            long ea_dst = get_AX();

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_ai_i_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pi_d_32 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_32(get_DY());
            long ea_dst = (m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] += 4) - 4;

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "move_pi_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pi_a_32 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_32(get_AY());
            long ea_dst = (m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] += 4) - 4;

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12);
            if (m68klog != null) {
                fprintf(m68klog, "move_pi_a_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pi_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pi_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_32(EA_PI_32());
            long ea_dst = (m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] += 4) - 4;

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_pi_pi_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pi_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pi_i_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_32();
            long ea_dst = (m68k_cpu.ar[(int) ((get_CPU_IR() >>> 9) & 7)] += 4) - 4;

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_pi_i_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_pd_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_pd_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_d_32 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_32(get_DY());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_a_32 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_32(get_AY());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_a_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_pi_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_32(EA_PI_32());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_pi_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_al_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_32(EA_AL());
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16 + 16);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_al_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_di_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_di_i_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_32();
            long ea_dst = get_AX() + MAKE_INT_16(m68ki_read_imm_16());

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(16 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_di_i_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_ix_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_ix_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_aw_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_d_32 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_32(get_DY());
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(20);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_al_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_al_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_32(EA_AL());
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(20 + 16);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_al_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_al_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_al_pcix_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_32(EA_PCIX());
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(20 + 14);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_pcix_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_al_i_32 = new opcode() {
        public void handler() {
            long res = m68ki_read_imm_32();
            long ea_dst = m68ki_read_imm_32();

            m68ki_write_32(ea_dst, res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(20 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "move_al_i_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_d_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_ix_16 = new opcode() {
        public void handler() {
            set_AX(MAKE_INT_16(m68ki_read_16(EA_IX())));
            USE_CLKS(4 + 10);
            if (m68klog != null) {
                fprintf(m68klog, "movea_ix_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_i_16 = new opcode() {
        public void handler() {
            set_AX(MAKE_INT_16(m68ki_read_imm_16()));
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "movea_i_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_a_32 = new opcode() {
        public void handler() {
            set_AX(MASK_OUT_ABOVE_32(get_AY()));
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "movea_a_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_ai_32 = new opcode() {
        public void handler() {
            set_AX(m68ki_read_32(EA_AI()));
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "movea_ai_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_pi_32 = new opcode() {
        public void handler() {
            set_AX(m68ki_read_32(EA_PI_32()));
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "movea_pi_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_di_32 = new opcode() {
        public void handler() {
            set_AX(m68ki_read_32(EA_DI()));
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "movea_di_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_ix_32 = new opcode() {
        public void handler() {
            set_AX(m68ki_read_32(EA_IX()));
            USE_CLKS(4 + 14);
            if (m68klog != null) {
                fprintf(m68klog, "movea_ix_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_al_32 = new opcode() {
        public void handler() {
            set_AX(m68ki_read_32(EA_AL()));
            USE_CLKS(4 + 16);
            if (m68klog != null) {
                fprintf(m68klog, "movea_al_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movea_pcix_32 = new opcode() {
        public void handler() {
            set_AX(m68ki_read_32(EA_PCIX()));
            USE_CLKS(4 + 14);
            if (m68klog != null) {
                fprintf(m68klog, "movea_pcix_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movea_i_32 = new opcode() {
        public void handler() {
            set_AX(m68ki_read_imm_32());
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "movea_i_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68010_move_fr_ccr_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_move_fr_ccr_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_move_fr_ccr_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_move_fr_ccr_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_move_fr_ccr_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_move_fr_ccr_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_move_fr_ccr_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_move_fr_ccr_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_ccr_i = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_fr_sr_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_fr_sr_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_fr_sr_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_fr_sr_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_fr_sr_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_fr_sr_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_fr_sr_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_fr_sr_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_sr_i = new opcode() {
        public void handler() {
            long new_sr = m68ki_read_imm_16();
            if (get_CPU_S() != 0L) {
                m68ki_set_sr(new_sr);
                USE_CLKS(12 + 4);
                if (m68klog != null) {
                    fprintf(m68klog, "move_to_sr_i(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            m68ki_exception(EXCEPTION_PRIVILEGE_VIOLATION);
            if (m68klog != null) {
                fprintf(m68klog, "move_to_sr_i(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_move_fr_usp = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_move_to_usp = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_movec_cr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_movec_rc = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_pd_32 = new opcode() {
        public void handler() {
            long i = 0;
            long register_list = m68ki_read_imm_16();
            long ea = get_AY();
            long count = 0;

            for (; i < 16; i++) {
                if ((register_list & (1 << i)) != 0) {
                    ea -= 4;
                    //m68ki_write_32(ea,  * (m68k_movem_pd_table[i]));
                    if (i == 0) {
                        m68ki_write_32(ea, get_CPU_A()[7]);
                    }
                    if (i == 1) {
                        m68ki_write_32(ea, get_CPU_A()[6]);
                    }
                    if (i == 2) {
                        m68ki_write_32(ea, get_CPU_A()[5]);
                    }
                    if (i == 3) {
                        m68ki_write_32(ea, get_CPU_A()[4]);
                    }
                    if (i == 4) {
                        m68ki_write_32(ea, get_CPU_A()[3]);
                    }
                    if (i == 5) {
                        m68ki_write_32(ea, get_CPU_A()[2]);
                    }
                    if (i == 6) {
                        m68ki_write_32(ea, get_CPU_A()[1]);
                    }
                    if (i == 7) {
                        m68ki_write_32(ea, get_CPU_A()[0]);
                    }
                    if (i == 8) {
                        m68ki_write_32(ea, get_CPU_D()[7]);
                    }
                    if (i == 9) {
                        m68ki_write_32(ea, get_CPU_D()[6]);
                    }
                    if (i == 10) {
                        m68ki_write_32(ea, get_CPU_D()[5]);
                    }
                    if (i == 11) {
                        m68ki_write_32(ea, get_CPU_D()[4]);
                    }
                    if (i == 12) {
                        m68ki_write_32(ea, get_CPU_D()[3]);
                    }
                    if (i == 13) {
                        m68ki_write_32(ea, get_CPU_D()[2]);
                    }
                    if (i == 14) {
                        m68ki_write_32(ea, get_CPU_D()[1]);
                    }
                    if (i == 15) {
                        m68ki_write_32(ea, get_CPU_D()[0]);
                    }
                    if (m68klog != null) {
                        fprintf(m68klog, "move_pd_32 : reg_list:%d,ea:%d,read32:%d\n", register_list, ea, m68ki_read_32(ea));
                    }
                    count++;
                }
            }
            set_AY(ea);
            /* ASG: changed from (count << 4) to (count << 3) */
            USE_CLKS((int) ((count << 3) + 8));
            if (m68klog != null) {
                fprintf(m68klog, "movem_pd_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movem_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_pi_32 = new opcode() {
        public void handler() {
            long i = 0;
            long register_list = m68ki_read_imm_16();
            long ea = get_AY();
            long count = 0;

            for (; i < 16; i++) {
                if ((register_list & (1 << i)) != 0) {
                    //*(m68k_movem_pi_table[i]) = m68ki_read_32(ea);
                    if (i == 0) {
                        set_CPU_D(0, m68ki_read_32(ea));
                    }
                    if (i == 1) {
                        set_CPU_D(1, m68ki_read_32(ea));
                    }
                    if (i == 2) {
                        set_CPU_D(2, m68ki_read_32(ea));
                    }
                    if (i == 3) {
                        set_CPU_D(3, m68ki_read_32(ea));
                    }
                    if (i == 4) {
                        set_CPU_D(4, m68ki_read_32(ea));
                    }
                    if (i == 5) {
                        set_CPU_D(5, m68ki_read_32(ea));
                    }
                    if (i == 6) {
                        set_CPU_D(6, m68ki_read_32(ea));
                    }
                    if (i == 7) {
                        set_CPU_D(7, m68ki_read_32(ea));
                    }
                    if (i == 8) {
                        set_CPU_A(0, m68ki_read_32(ea));
                    }
                    if (i == 9) {
                        set_CPU_A(1, m68ki_read_32(ea));
                    }
                    if (i == 10) {
                        set_CPU_A(2, m68ki_read_32(ea));
                    }
                    if (i == 11) {
                        set_CPU_A(3, m68ki_read_32(ea));
                    }
                    if (i == 12) {
                        set_CPU_A(4, m68ki_read_32(ea));
                    }
                    if (i == 13) {
                        set_CPU_A(5, m68ki_read_32(ea));
                    }
                    if (i == 14) {
                        set_CPU_A(6, m68ki_read_32(ea));
                    }
                    if (i == 15) {
                        set_CPU_A(7, m68ki_read_32(ea));
                    }
                    ea += 4;
                    count++;
                }
            }
            set_AY(ea);
            /* ASG: changed from (count << 4) to (count << 3) */
            USE_CLKS((int) ((count << 3) + 12));
            if (m68klog != null) {
                fprintf(m68klog, "movem_pi_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movem_re_ai_16 = new opcode() {
        public void handler() {
            long i = 0;
            long register_list = m68ki_read_imm_16();
            long ea = EA_AI();
            long count = 0;

            for (; i < 16; i++) {
                if ((register_list & (1 << i)) != 0) {
                    //m68ki_write_16(ea,  * (m68k_movem_pi_table[i]));
                    if (i == 0) {
                        m68ki_write_16(ea, get_CPU_D()[0]);
                    }
                    if (i == 1) {
                        m68ki_write_16(ea, get_CPU_D()[1]);
                    }
                    if (i == 2) {
                        m68ki_write_16(ea, get_CPU_D()[2]);
                    }
                    if (i == 3) {
                        m68ki_write_16(ea, get_CPU_D()[3]);
                    }
                    if (i == 4) {
                        m68ki_write_16(ea, get_CPU_D()[4]);
                    }
                    if (i == 5) {
                        m68ki_write_16(ea, get_CPU_D()[5]);
                    }
                    if (i == 6) {
                        m68ki_write_16(ea, get_CPU_D()[6]);
                    }
                    if (i == 7) {
                        m68ki_write_16(ea, get_CPU_D()[7]);
                    }
                    if (i == 8) {
                        m68ki_write_16(ea, get_CPU_A()[0]);
                    }
                    if (i == 9) {
                        m68ki_write_16(ea, get_CPU_A()[1]);
                    }
                    if (i == 10) {
                        m68ki_write_16(ea, get_CPU_A()[2]);
                    }
                    if (i == 11) {
                        m68ki_write_16(ea, get_CPU_A()[3]);
                    }
                    if (i == 12) {
                        m68ki_write_16(ea, get_CPU_A()[4]);
                    }
                    if (i == 13) {
                        m68ki_write_16(ea, get_CPU_A()[5]);
                    }
                    if (i == 14) {
                        m68ki_write_16(ea, get_CPU_A()[6]);
                    }
                    if (i == 15) {
                        m68ki_write_16(ea, get_CPU_A()[7]);
                    }
                    ea += 2;
                    count++;
                }
            }
            USE_CLKS((int) ((count << 2) + 4 + 4));
            if (m68klog != null) {
                fprintf(m68klog, "movem_re_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movem_re_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_re_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_re_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_re_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_re_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_re_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_re_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_re_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_re_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_ai_16 = new opcode() {
        public void handler() {
            long i = 0;
            long register_list = m68ki_read_imm_16();
            long ea = EA_AI();
            long count = 0;

            for (; i < 16; i++) {
                if ((register_list & (1 << i)) != 0) {
                    // * (m68k_movem_pi_table[i]) = MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea)));
                    if (i == 0) {
                        set_CPU_D(0, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 1) {
                        set_CPU_D(1, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 2) {
                        set_CPU_D(2, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 3) {
                        set_CPU_D(3, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 4) {
                        set_CPU_D(4, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 5) {
                        set_CPU_D(5, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 6) {
                        set_CPU_D(6, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 7) {
                        set_CPU_D(7, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 8) {
                        set_CPU_A(0, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 9) {
                        set_CPU_A(1, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 10) {
                        set_CPU_A(2, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 11) {
                        set_CPU_A(3, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 12) {
                        set_CPU_A(4, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 13) {
                        set_CPU_A(5, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 14) {
                        set_CPU_A(6, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 15) {
                        set_CPU_A(7, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    ea += 2;
                    count++;
                }
            }
            USE_CLKS((int) ((count << 2) + 8 + 4));
        }
    };
    public static opcode m68000_movem_er_di_16 = new opcode() {
        public void handler() {
            long i = 0;
            long register_list = m68ki_read_imm_16();
            long ea = EA_DI();
            long count = 0;

            for (; i < 16; i++) {
                if ((register_list & (1 << i)) != 0) {
                    //*(m68k_movem_pi_table[i]) = MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea)));
                    if (i == 0) {
                        set_CPU_D(0, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 1) {
                        set_CPU_D(1, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 2) {
                        set_CPU_D(2, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 3) {
                        set_CPU_D(3, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 4) {
                        set_CPU_D(4, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 5) {
                        set_CPU_D(5, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 6) {
                        set_CPU_D(6, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 7) {
                        set_CPU_D(7, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 8) {
                        set_CPU_A(0, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 9) {
                        set_CPU_A(1, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 10) {
                        set_CPU_A(2, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 11) {
                        set_CPU_A(3, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 12) {
                        set_CPU_A(4, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 13) {
                        set_CPU_A(5, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 14) {
                        set_CPU_A(6, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 15) {
                        set_CPU_A(7, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    ea += 2;
                    count++;
                }
            }
            USE_CLKS((int) ((count << 2) + 8 + 8));
            if (m68klog != null) {
                fprintf(m68klog, "movem_er_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movem_er_ix_16 = new opcode() {
        public void handler() {
            long i = 0;
            long register_list = m68ki_read_imm_16();
            long ea = EA_IX();
            long count = 0;

            for (; i < 16; i++) {
                if ((register_list & (1 << i)) != 0) {
                    //* (m68k_movem_pi_table[i]) = MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea)));
                    if (i == 0) {
                        set_CPU_D(0, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 1) {
                        set_CPU_D(1, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 2) {
                        set_CPU_D(2, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 3) {
                        set_CPU_D(3, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 4) {
                        set_CPU_D(4, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 5) {
                        set_CPU_D(5, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 6) {
                        set_CPU_D(6, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 7) {
                        set_CPU_D(7, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 8) {
                        set_CPU_A(0, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 9) {
                        set_CPU_A(1, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 10) {
                        set_CPU_A(2, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 11) {
                        set_CPU_A(3, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 12) {
                        set_CPU_A(4, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 13) {
                        set_CPU_A(5, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 14) {
                        set_CPU_A(6, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    if (i == 15) {
                        set_CPU_A(7, MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_16(ea))));
                    }
                    ea += 2;
                    count++;
                }
            }
            USE_CLKS((int) ((count << 2) + 8 + 10));
            if (m68klog != null) {
                fprintf(m68klog, "movem_er_ix_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movem_er_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movem_er_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movep_er_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movep_er_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_movep_re_16 = new opcode() { /*RECHECK*/

        public void handler() {
            long ea = get_AY() + MAKE_INT_16(MASK_OUT_ABOVE_16(m68ki_read_imm_16()));
            long src = get_DX();

            m68ki_write_8(ea, MASK_OUT_ABOVE_8(src >>> 8));
            m68ki_write_8(ea += 2, MASK_OUT_ABOVE_8(src));
            USE_CLKS(16);
            if (m68klog != null) {
                fprintf(m68klog, "movep_re_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_movep_re_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_moves_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_moveq = new opcode() {
        public void handler() {
            set_DX(MAKE_INT_8(MASK_OUT_ABOVE_8(get_CPU_IR())));
            long res = get_DX();

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "moveq :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_muls_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long res = MAKE_INT_16(get_DY()) * MAKE_INT_16(MASK_OUT_ABOVE_16(d_dst));

            set_DX(res);

            m68k_cpu.not_z_flag = res;
            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(54);
            if (m68klog != null) {
                fprintf(m68klog, "muls_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_muls_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_muls_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_muls_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_muls_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_muls_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_muls_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_muls_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_muls_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_muls_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_muls_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long res = MASK_OUT_ABOVE_16(get_DY()) * MASK_OUT_ABOVE_16(d_dst);

            set_DX(res);

            m68k_cpu.not_z_flag = res;
            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(54);
            if (m68klog != null) {
                fprintf(m68klog, "mulu_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_mulu_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_mulu_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_mull_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nbcd_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(-dst);

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = GET_MSB_16(dst & res);
            m68k_cpu.c_flag = res != 0L ? 1L : 0L;
            m68k_cpu.x_flag = res != 0L ? 1L : 0L;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "neg_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_neg_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_neg_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_d_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_negx_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_nop = new opcode() {
        public void handler() {
            USE_CLKS(4);
        }
    };
    public static opcode m68000_not_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long res = MASK_OUT_ABOVE_16(~d_dst);

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "not_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_not_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_not_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_d_16 = new opcode() {
        public void handler() {
            set_DX(get_DX() | MASK_OUT_ABOVE_16(get_DY()));
            long res = MASK_OUT_ABOVE_16(get_DX());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "or_er_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_or_er_ai_16 = new opcode() {
        public void handler() {
            set_DX(get_DX() | m68ki_read_16(EA_AI()));
            long res = MASK_OUT_ABOVE_16(get_DX());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "or_er_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_or_er_pi_16 = new opcode() {
        public void handler() {
            set_DX(get_DX() | m68ki_read_16(EA_PI_16()));
            long res = MASK_OUT_ABOVE_16(get_DX());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "or_er_pi_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_or_er_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_al_16 = new opcode() {
        public void handler() {
            set_DX(get_DX() | m68ki_read_16(EA_AL()));
            long res = MASK_OUT_ABOVE_16(get_DX());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "or_er_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_or_er_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_er_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_di_16 = new opcode() {
        public void handler() {
            long ea = EA_DI();
            long res = MASK_OUT_ABOVE_16(get_DX() | m68ki_read_16(ea));

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "ori_re_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_or_re_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_or_re_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_to_ccr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_to_sr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_d_16 = new opcode() {
        public void handler() {
            set_DY(get_DY() | m68ki_read_imm_16());
            long res = MASK_OUT_ABOVE_16(get_DY());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "ori_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_ori_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_di_16 = new opcode() {
        public void handler() {
            long tmp = m68ki_read_imm_16();
            long ea = EA_DI();
            long res = MASK_OUT_ABOVE_16(tmp | m68ki_read_16(ea));

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "ori_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_ori_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_al_16 = new opcode() {
        public void handler() {
            long tmp = m68ki_read_imm_16();
            long ea = EA_AL();
            long res = MASK_OUT_ABOVE_16(tmp | m68ki_read_16(ea));

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(12 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "ori_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_ori_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ori_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_pack_rr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_pack_mm_ax7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_pack_mm_ay7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_pack_mm_axy7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_pack_mm = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_pea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_pea_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_pea_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_pea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_pea_al = new opcode() {
        public void handler() {
            long ea = EA_AL();
            m68ki_push_32(ea);
            USE_CLKS(0 + 20);
            if (m68klog != null) {
                fprintf(m68klog, "pea_al :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_pea_pcdi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_pea_pcix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rst = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_s_8 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long orig_shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long shift = orig_shift & 7;
            long src = MASK_OUT_ABOVE_8(d_dst);
            long res = ROR_8(src, shift);

            set_DY(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = (src >>> ((shift - 1) & 7)) & 1;
            m68k_cpu.v_flag = 0;
            USE_CLKS((int) ((orig_shift << 1) + 6));
            if (m68klog != null) {
                fprintf(m68klog, "ror_s_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_ror_s_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long src = MASK_OUT_ABOVE_16(d_dst);
            long res = ROR_16(src, shift);

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = (src >> (shift - 1)) & 1;
            m68k_cpu.v_flag = 0;
            USE_CLKS((int) ((shift << 1) + 6));
            if (m68klog != null) {
                fprintf(m68klog, "ror_s_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_ror_s_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_r_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_r_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_r_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_ea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_ea_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_ea_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_ea_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_ea_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_ea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_ror_ea_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_s_8 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long orig_shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long shift = orig_shift & 7;
            long src = MASK_OUT_ABOVE_8(d_dst);
            long res = MASK_OUT_ABOVE_8(ROL_8(src, shift));

            set_DY(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = (src >>> (8 - orig_shift)) & 1;
            m68k_cpu.v_flag = 0;
            USE_CLKS((int) ((orig_shift << 1) + 6));
            if (m68klog != null) {
                fprintf(m68klog, "rol_s_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_rol_s_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long src = MASK_OUT_ABOVE_16(d_dst);
            long res = MASK_OUT_ABOVE_16(ROL_16(src, shift));

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = (src >>> (16 - shift)) & 1;
            m68k_cpu.v_flag = 0;
            USE_CLKS((int) ((shift << 1) + 6));
            if (m68klog != null) {
                fprintf(m68klog, "rol_s_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_rol_s_32 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long shift = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long src = MASK_OUT_ABOVE_32(d_dst);
            long res = MASK_OUT_ABOVE_32(ROL_32(src, shift));

            set_DY(res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.c_flag = (src >> (32 - shift)) & 1;
            m68k_cpu.v_flag = 0;
            USE_CLKS((int) ((shift << 1) + 8));
            if (m68klog != null) {
                fprintf(m68klog, "rol_s_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_rol_r_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_r_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_r_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_ea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_ea_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_ea_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_ea_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_ea_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_ea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rol_ea_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_s_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_s_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_s_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_r_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_r_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_r_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_ea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_ea_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_ea_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_ea_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_ea_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_ea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxr_ea_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_s_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_s_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_s_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_r_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_r_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_r_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_ea_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_ea_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_ea_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_ea_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_ea_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_ea_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_roxl_ea_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68010_rtd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rte = new opcode() { /*RECHECK*/

        public void handler() {
            long new_sr;
            long new_pc;
            long format_word;

            if (get_CPU_S() != 0) {
                new_sr = m68ki_pull_16();
                new_pc = m68ki_pull_32();
                m68ki_branch_long(new_pc);
                if ((get_CPU_MODE() & CPU_MODE_010_PLUS) == 0) {
                    m68ki_set_sr(new_sr);
                    USE_CLKS(20);
                    if (m68klog != null) {
                        fprintf(m68klog, "rte(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                    }
                    return;
                }
                format_word = (m68ki_pull_16() >>> 12) & 0xf;
                m68ki_set_sr(new_sr);
                /* I'm ignoring code 8 (bus error and address error) */
                if (format_word != 0) /* Generate a new program counter from the format error vector */ {
                    m68ki_set_pc(m68ki_read_32((EXCEPTION_FORMAT_ERROR << 2) + get_CPU_VBR()));
                }
                USE_CLKS(20);
                if (m68klog != null) {
                    fprintf(m68klog, "rte(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            m68ki_exception(EXCEPTION_PRIVILEGE_VIOLATION);
            if (m68klog != null) {
                fprintf(m68klog, "rte(3) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_rtm = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rtr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_rts = new opcode() {
        public void handler() {
            m68ki_branch_long(m68ki_pull_32());
            USE_CLKS(16);
            if (m68klog != null) {
                fprintf(m68klog, "rts :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_sbcd_rr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sbcd_mm_ax7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sbcd_mm_ay7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sbcd_mm_axy7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sbcd_mm = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_st_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sf_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_shi_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sls_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scc_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scs_d = new opcode() {
        public void handler() {
            if (CONDITION_CS()) {
                set_DY(get_DY() | 0xffL);
                USE_CLKS(6);
                if (m68klog != null) {
                    fprintf(m68klog, "scs_d(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_DY(get_DY() & 0xffffff00L);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "scs_d(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_scs_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scs_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scs_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scs_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scs_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scs_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scs_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scs_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_scs_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sne_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_seq_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svc_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_svs_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_spl_d = new opcode() {
        public void handler() {
            if (CONDITION_PL()) {
                set_DY(get_DY() | 0xffL);
                USE_CLKS(6);
                if (m68klog != null) {
                    fprintf(m68klog, "spl_d(1) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
                }
                return;
            }
            set_DY(get_DY() & 0xffffff00L);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "spl_d(2) :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_spl_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_spl_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_spl_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_spl_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_spl_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_spl_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_spl_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_spl_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_spl_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_smi_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sge_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_slt_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sgt_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sle_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_stop = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_d_8 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = get_DY();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_8(dst - src);

            set_DX(MASK_OUT_BELOW_8(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_8(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_8(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_8(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "sub_er_d_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_sub_er_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_i_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = get_DY();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(dst - src);

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "sub_er_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_sub_er_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_ai_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = m68ki_read_16(EA_AI());
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(dst - src);

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "sub_er_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_sub_er_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_di_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = m68ki_read_16(EA_DI());
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(dst - src);

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "sub_er_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_sub_er_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_al_16 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = m68ki_read_16(EA_AL());
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(dst - src);

            set_DX(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "sub_er_al_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_sub_er_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_d_32 = new opcode() {
        public void handler() {
            long d_dst = get_DX();
            long src = get_DY();
            long dst = d_dst;
            set_DX(MASK_OUT_ABOVE_32(dst - src));
            long res = get_DX();

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_32(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_32(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_32(src, dst, res);
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "sub_er_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_sub_er_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_er_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_di_16 = new opcode() {
        public void handler() {
            long ea = EA_DI();
            long src = get_DX();
            long dst = m68ki_read_16(ea);
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "sub_re_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_sub_re_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_sub_re_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_d_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_di_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_i_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_d_32 = new opcode() {
        public void handler() {
            long a_dst = get_AX();

            set_AX(MASK_OUT_ABOVE_32(a_dst - get_DY()));
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "suba_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_suba_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_suba_i_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long src = m68ki_read_imm_16();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(dst - src);

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "subi_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_subi_ai_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_di_16 = new opcode() {
        public void handler() {
            long src = m68ki_read_imm_16();
            long ea = EA_DI();
            long dst = m68ki_read_16(ea);
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68ki_write_16(ea, res);
            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(12 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "subi_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_subi_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_al_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_d_32 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long src = m68ki_read_imm_32();
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_32(dst - src);

            set_DY(res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_32(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_32(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_32(src, dst, res);
            USE_CLKS(16);
            if (m68klog != null) {
                fprintf(m68klog, "subi_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_subi_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subi_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_d_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_ai_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_d_16 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_16(dst - src);

            set_DY(MASK_OUT_BELOW_16(d_dst) | res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "subq_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_subq_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_ai_16 = new opcode() {
        public void handler() {
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long ea = EA_AI();
            long dst = m68ki_read_16(ea);
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(8 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "subq_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_subq_pi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_di_16 = new opcode() {
        public void handler() {
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long ea = EA_DI();
            long dst = m68ki_read_16(ea);
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(8 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "subq_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_subq_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_al_16 = new opcode() {
        public void handler() {
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long ea = EA_AL();
            long dst = m68ki_read_16(ea);
            long res = MASK_OUT_ABOVE_16(dst - src);

            m68ki_write_16(ea, res);

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_16(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_16(src, dst, res);
            USE_CLKS(8 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "subq_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_subq_d_32 = new opcode() {
        public void handler() {
            long d_dst = get_DY();
            long src = (((get_CPU_IR() >>> 9) - 1) & 7) + 1;
            long dst = d_dst;
            long res = MASK_OUT_ABOVE_32(dst - src);

            set_DY(res);

            m68k_cpu.n_flag = GET_MSB_32(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.x_flag = CFLAG_SUB_32(src, dst, res);
            m68k_cpu.c_flag = CFLAG_SUB_32(src, dst, res);
            m68k_cpu.v_flag = VFLAG_SUB_32(src, dst, res);
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "subq_d_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_subq_a_32 = new opcode() {
        public void handler() {
            long a_dst = get_AY();

            set_AY(MASK_OUT_ABOVE_32(a_dst - ((((get_CPU_IR() >>> 9) - 1) & 7) + 1)));
            USE_CLKS(8);
            if (m68klog != null) {
                fprintf(m68klog, "subq_a_32 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_subq_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subq_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subx_rr_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subx_rr_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subx_rr_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subx_mm_8_ax7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subx_mm_8_ay7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subx_mm_8_axy7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subx_mm_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subx_mm_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_subx_mm_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_swap = new opcode() { /*RECHECK*/

        public void handler() {
            //long *d_dst =  & DY;

            set_DY(get_DY() ^ (get_DY() >>> 16) & 0x0000ffffL);
            set_DY(get_DY() ^ (get_DY() << 16) & 0xffff0000L);
            set_DY(get_DY() ^ (get_DY() >>> 16) & 0x0000ffffL);

            m68k_cpu.n_flag = GET_MSB_32(get_DY());
            m68k_cpu.not_z_flag = get_DY();
            m68k_cpu.c_flag = 0;
            m68k_cpu.v_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "swap :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }

        }
    };
    public static opcode m68000_tas_d = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tas_ai = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tas_pi = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tas_pi7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tas_pd = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tas_pd7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tas_di = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tas_ix = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tas_aw = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tas_al = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_trap = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapt_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapt_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapt_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapf_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapf_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapf_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_traphi_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_traphi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_traphi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapls_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapls_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapls_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapcc_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapcc_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapcc_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapcs_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapcs_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapcs_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapne_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapne_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapne_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapeq_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapeq_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapeq_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapvc_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapvc_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapvc_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapvs_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapvs_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapvs_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trappl_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trappl_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trappl_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapmi_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapmi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapmi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapge_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapge_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapge_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_traplt_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_traplt_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_traplt_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapgt_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapgt_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_trapgt_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_traple_0 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_traple_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_traple_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_trapv = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_d_8 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_8(get_DY());

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "tst_d_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_tst_ai_8 = new opcode() {
        public void handler() {
            long ea = EA_AI();
            long res = m68ki_read_8(ea);

            m68k_cpu.n_flag = GET_MSB_8(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "tst_ai_8 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_tst_pi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_pi7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_pd_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_pd7_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_di_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_ix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_aw_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_al_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_tst_pcdi_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_tst_pcix_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_tst_imm_8 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_d_16 = new opcode() {
        public void handler() {
            long res = MASK_OUT_ABOVE_16(get_DY());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4);
            if (m68klog != null) {
                fprintf(m68klog, "tst_d_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }

        }
    };
    public static opcode m68020_tst_a_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_ai_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AI());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "tst_ai_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_tst_pi_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_PI_16());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 4);
            if (m68klog != null) {
                fprintf(m68klog, "tst_pi_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_tst_pd_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_di_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_DI());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 8);
            if (m68klog != null) {
                fprintf(m68klog, "tst_di_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68000_tst_ix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_aw_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_al_16 = new opcode() {
        public void handler() {
            long res = m68ki_read_16(EA_AL());

            m68k_cpu.n_flag = GET_MSB_16(res);
            m68k_cpu.not_z_flag = res;
            m68k_cpu.v_flag = 0;
            m68k_cpu.c_flag = 0;
            USE_CLKS(4 + 12);
            if (m68klog != null) {
                fprintf(m68klog, "tst_al_16 :PC:%d,PPC:%d,mode:%d,dr0:%d,dr1:%d,dr2:%d,dr3:%d,dr4:%d,dr5:%d,dr6:%d,dr7:%d,ar0:%d,ar1:%d,ar2:%d,ar3:%d,ar4:%d,ar5:%d,ar6:%d,ar7:%d,sp0:%d,sp1:%d,sp2:%d,sp3:%d,vbr:%d,sfc:%d,dfc:%d,cacr:%d,caar:%d,ir:%d,t1:%d,t0:%d,s:%d,m:%d,x:%d,n:%d,nz:%d,v:%d,c:%d,intm:%d,ints:%d,stop:%d,halt:%d,intc:%d,prefa:%d,prefd:%d\n", m68k_cpu.pc, m68k_cpu.ppc, m68k_cpu.mode, m68k_cpu.dr[0], m68k_cpu.dr[1], m68k_cpu.dr[2], m68k_cpu.dr[3], m68k_cpu.dr[4], m68k_cpu.dr[5], m68k_cpu.dr[6], m68k_cpu.dr[7], m68k_cpu.ar[0], m68k_cpu.ar[1], m68k_cpu.ar[2], m68k_cpu.ar[3], m68k_cpu.ar[4], m68k_cpu.ar[5], m68k_cpu.ar[6], m68k_cpu.ar[7], m68k_cpu.sp[0], m68k_cpu.sp[1], m68k_cpu.sp[2], m68k_cpu.sp[3], m68k_cpu.vbr, m68k_cpu.sfc, m68k_cpu.dfc, m68k_cpu.cacr, m68k_cpu.caar, m68k_cpu.ir, m68k_cpu.t1_flag, m68k_cpu.t0_flag, m68k_cpu.s_flag, m68k_cpu.m_flag, m68k_cpu.x_flag, m68k_cpu.n_flag, m68k_cpu.not_z_flag, m68k_cpu.v_flag, m68k_cpu.c_flag, m68k_cpu.int_mask, m68k_cpu.int_state, m68k_cpu.stopped, m68k_cpu.halted, m68k_cpu.int_cycles, m68k_cpu.pref_addr, m68k_cpu.pref_data);
            }
        }
    };
    public static opcode m68020_tst_pcdi_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_tst_pcix_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_tst_imm_16 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_d_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_tst_a_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_ai_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_pi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_pd_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_di_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_ix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_aw_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_tst_al_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_tst_pcdi_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_tst_pcix_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_tst_imm_32 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_unlk_a7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68000_unlk = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_unpk_rr = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_unpk_mm_ax7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_unpk_mm_ay7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_unpk_mm_axy7 = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
    public static opcode m68020_unpk_mm = new opcode() {
        public void handler() {
            if (m68klog != null) {
                fclose(m68klog);
            }
            throw new UnsupportedOperationException("Unimplemented");
        }
    };
}
