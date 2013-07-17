
package drivers;

import static mame.driverH.*;
import static mame.memoryH.*;
import static mame.commonH.*;
import static mame.inputport.*;
import static mame.drawgfxH.*;
import static vidhrdw.generic.*;
import static vidhrdw.pengo.*;
import static mame.sndintrfH.*;
import static sound.namcoH.*;
import static sound.namco.*;
import static mame.cpuintrf.*;
import static mame.common.*;
import static arcadeflex.input.*;
import static machine.pacman.*;
import static mame.inputportH.*;
import static mame.inputH.*;
import static arcadeflex.libc.*;

public class pacman {
    /*TODO*///static void alibaba_sound_w(int offset, int data)
    /*TODO*///{
    /*TODO*///	/* since the sound region in Ali Baba is not contiguous, translate the
    /*TODO*///	   offset into the 0-0x1f range */
    /*TODO*/// 	if (offset < 0x10)
    /*TODO*///		pengo_sound_w(offset, data);
    /*TODO*///	else if (offset < 0x20)
    /*TODO*///		spriteram_2[offset - 0x10] = data;
    /*TODO*///	else
    /*TODO*///		pengo_sound_w(offset - 0x10, data);
    /*TODO*///}
    /*TODO*///
    /*TODO*///
    /*TODO*///static int alibaba_mystery_1_r(int offset)
    /*TODO*///{
    /*TODO*///	// The return value determines what the mystery item is.  Each bit corresponds
    /*TODO*///	// to a question mark
    /*TODO*///
    /*TODO*///	return rand() & 0x0f;
    /*TODO*///}
    /*TODO*///
    /*TODO*///static int alibaba_mystery_2_r(int offset)
    /*TODO*///{
    /*TODO*///	static int mystery = 0;
    /*TODO*///
    /*TODO*///	// The single bit return value determines when the mystery is lit up.
    /*TODO*///	// This is certainly wrong
    /*TODO*///
    /*TODO*///	mystery++;
    /*TODO*///	return (mystery >> 10) & 1;
    /*TODO*///}
    /*TODO*///
    /*TODO*///
    /*TODO*///static void pacman_coin_lockout_global_w(int offset, int data)
    /*TODO*///{
    /*TODO*///	coin_lockout_global_w(offset, ~data & 0x01);
    /*TODO*///}
    /*TODO*///
    /*TODO*///\
    	static MemoryReadAddress readmem[] =
	{
		new MemoryReadAddress( 0x0000, 0x3fff, MRA_ROM ),
		new MemoryReadAddress( 0x4000, 0x47ff, MRA_RAM ),	/* video and color RAM */
		new MemoryReadAddress( 0x4c00, 0x4fff, MRA_RAM ),	/* including sprite codes at 4ff0-4fff */
		new MemoryReadAddress( 0x5000, 0x503f, input_port_0_r ),	/* IN0 */
		new MemoryReadAddress( 0x5040, 0x507f, input_port_1_r ),	/* IN1 */
		new MemoryReadAddress( 0x5080, 0x50bf, input_port_2_r ),	/* DSW1 */
		new MemoryReadAddress( 0x50c0, 0x50ff, input_port_3_r ),	/* DSW2 */
		new MemoryReadAddress( 0x8000, 0xbfff, MRA_ROM ),	/* Ms. Pac-Man / Ponpoko only */
		new MemoryReadAddress( -1 )	/* end of table */
	};
	static MemoryWriteAddress writemem[] =
	{
		new MemoryWriteAddress( 0x0000, 0x3fff, MWA_ROM ),
		new MemoryWriteAddress( 0x4000, 0x43ff, videoram_w, videoram, videoram_size ),
		new MemoryWriteAddress( 0x4400, 0x47ff, colorram_w, colorram ),
		new MemoryWriteAddress( 0x4c00, 0x4fef, MWA_RAM ),
		new MemoryWriteAddress( 0x4ff0, 0x4fff, MWA_RAM, spriteram, spriteram_size ),
		new MemoryWriteAddress( 0x5000, 0x5000, interrupt_enable_w ),
		new MemoryWriteAddress( 0x5001, 0x5001, pengo_sound_enable_w ),
		new MemoryWriteAddress( 0x5002, 0x5002, MWA_NOP ),
		new MemoryWriteAddress( 0x5003, 0x5003, pengo_flipscreen_w ),
	 	new MemoryWriteAddress( 0x5004, 0x5005, osd_led_w ),
	// 	new MemoryWriteAddress( 0x5006, 0x5006, pacman_coin_lockout_global_w ),	this breaks many games
	 	new MemoryWriteAddress( 0x5007, 0x5007, coin_counter_w ),
		new MemoryWriteAddress( 0x5040, 0x505f, pengo_sound_w, namco_soundregs ),
		new MemoryWriteAddress( 0x5060, 0x506f, MWA_RAM, spriteram_2 ),
		new MemoryWriteAddress( 0x50c0, 0x50c0, watchdog_reset_w ),
		new MemoryWriteAddress( 0x8000, 0xbfff, MWA_ROM ),	/* Ms. Pac-Man / Ponpoko only */
		new MemoryWriteAddress( 0xc000, 0xc3ff, videoram_w ), /* mirror address for video ram, */
		new MemoryWriteAddress( 0xc400, 0xc7ef, colorram_w ), /* used to display HIGH SCORE and CREDITS */
		new MemoryWriteAddress( 0xffff, 0xffff, MWA_NOP ),	/* Eyes writes to this location to simplify code */
		new MemoryWriteAddress( -1 )	/* end of table */
	};
    /*TODO*///};
    /*TODO*///
    /*TODO*///
    /*TODO*///static struct MemoryReadAddress alibaba_readmem[] =
    /*TODO*///	{
    /*TODO*///	{ 0x0000, 0x3fff, MRA_ROM },
    /*TODO*///	{ 0x4000, 0x47ff, MRA_RAM },	/* video and color RAM */
    /*TODO*///	{ 0x4c00, 0x4fff, MRA_RAM },	/* including sprite codes at 4ef0-4eff */
    /*TODO*///	{ 0x5000, 0x503f, input_port_0_r },	/* IN0 */
    /*TODO*///	{ 0x5040, 0x507f, input_port_1_r },	/* IN1 */
    /*TODO*///	{ 0x5080, 0x50bf, input_port_2_r },	/* DSW1 */
    /*TODO*///	{ 0x50c0, 0x50c0, alibaba_mystery_1_r },
    /*TODO*///	{ 0x50c1, 0x50c1, alibaba_mystery_2_r },
    /*TODO*///	{ 0x8000, 0x8fff, MRA_ROM },
    /*TODO*///	{ 0x9000, 0x93ff, MRA_RAM },
    /*TODO*///	{ 0xa000, 0xa7ff, MRA_ROM },
    /*TODO*///	{ -1 }	/* end of table */
    /*TODO*///};
    /*TODO*///
    /*TODO*///static struct MemoryWriteAddress alibaba_writemem[] =
    /*TODO*///{
    /*TODO*///	{ 0x0000, 0x3fff, MWA_ROM },
    /*TODO*///	{ 0x4000, 0x43ff, videoram_w, &videoram, &videoram_size },
    /*TODO*///	{ 0x4400, 0x47ff, colorram_w, &colorram },
    /*TODO*///	{ 0x4ef0, 0x4eff, MWA_RAM, &spriteram, &spriteram_size },
    /*TODO*///	{ 0x4c00, 0x4fff, MWA_RAM },
    /*TODO*///	{ 0x5000, 0x5000, watchdog_reset_w },
    /*TODO*/// 	{ 0x5004, 0x5005, osd_led_w },
    /*TODO*/// 	{ 0x5006, 0x5006, pacman_coin_lockout_global_w },
    /*TODO*/// 	{ 0x5007, 0x5007, coin_counter_w },
    /*TODO*///	{ 0x5040, 0x506f, alibaba_sound_w, &pengo_soundregs },  /* the sound region is not contiguous */
    /*TODO*///	{ 0x5060, 0x506f, MWA_RAM, &spriteram_2 }, /* actually at 5050-505f, here to point to free RAM */
    /*TODO*///	{ 0x50c0, 0x50c0, pengo_sound_enable_w },
    /*TODO*///	{ 0x50c1, 0x50c1, pengo_flipscreen_w },
    /*TODO*///	{ 0x50c2, 0x50c2, interrupt_enable_w },
    /*TODO*///	{ 0x8000, 0x8fff, MWA_ROM },
    /*TODO*///	{ 0x9000, 0x93ff, MWA_RAM },
    /*TODO*///	{ 0xa000, 0xa7ff, MWA_ROM },
    /*TODO*///	{ 0xc000, 0xc3ff, videoram_w }, /* mirror address for video ram, */
    /*TODO*///	{ 0xc400, 0xc7ef, colorram_w }, /* used to display HIGH SCORE and CREDITS */
    /*TODO*///	{ -1 }	/* end of table */
    /*TODO*///};
    /*TODO*///
    /*TODO*///
    /*TODO*///
    static IOWritePort writeport[] =
    {
	new IOWritePort( 0x00, 0x00, interrupt_vector_w ),	/* Pac-Man only */
	new IOWritePort( -1 )	/* end of table */
    };
    /*TODO*///
    /*TODO*///
    /*TODO*///static struct IOWritePort vanvan_writeport[] =
    /*TODO*///{
    /*TODO*///	{ 0x01, 0x01, SN76496_0_w },
    /*TODO*///	{ 0x02, 0x02, SN76496_1_w },
    /*TODO*///	{ -1 }
    /*TODO*///};
    /*TODO*///
    /*TODO*///static struct IOWritePort dremshpr_writeport[] =
    /*TODO*///{
    /*TODO*///	{ 0x06, 0x06, AY8910_write_port_0_w },
    /*TODO*///	{ 0x07, 0x07, AY8910_control_port_0_w },
    /*TODO*///	{ -1 }
    /*TODO*///};
    /*TODO*///
    /*TODO*///
    /*TODO*///static struct MemoryReadAddress theglob_readmem[] =
    /*TODO*///{
    /*TODO*///	{ 0x0000, 0x3fff, MRA_BANK1 },
    /*TODO*///	{ 0x4000, 0x47ff, MRA_RAM },	/* video and color RAM */
    /*TODO*///	{ 0x4c00, 0x4fff, MRA_RAM },	/* including sprite codes at 4ff0-4fff */
    /*TODO*///	{ 0x5000, 0x503f, input_port_0_r },	/* IN0 */
    /*TODO*///	{ 0x5040, 0x507f, input_port_1_r },	/* IN1 */
    /*TODO*///	{ 0x5080, 0x50bf, input_port_2_r },	/* DSW1 */
    /*TODO*///	{ 0x50c0, 0x50ff, input_port_3_r },	/* DSW2 */
    /*TODO*///	{ -1 }	/* end of table */
    /*TODO*///};
    /*TODO*///
    /*TODO*///static struct IOReadPort theglob_readport[] =
    /*TODO*///{
    /*TODO*///	{ 0x00, 0xff, theglob_decrypt_rom },	/* Switch protection logic */
    /*TODO*///	{ -1 }	/* end of table */
    /*TODO*///};
    /*TODO*///
    /*TODO*///
    static InputPortPtr input_ports_pacman = new InputPortPtr(){ public void handler() {
    	PORT_START();	/* IN0 */
    	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY );
    	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY );
    	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY );
    	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY );
    	PORT_BITX(    0x10, 0x10, IPT_DIPSWITCH_NAME | IPF_CHEAT, "Rack Test", KEYCODE_F1, IP_JOY_NONE );
    	PORT_DIPSETTING(    0x10, DEF_STR( "Off" ) );
    	PORT_DIPSETTING(    0x00, DEF_STR( "On" ) );
    	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 );
    	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_COIN2 );
    	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN3 );
    
    	PORT_START();	/* IN1 */
    	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL );
    	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL );
    	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL );
    	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL );
    	PORT_SERVICE( 0x10, IP_ACTIVE_LOW );
    	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 );
    	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 );
    	PORT_DIPNAME(0x80, 0x80, DEF_STR( "Cabinet" ) );
    	PORT_DIPSETTING(   0x80, DEF_STR( "Upright" ) );
    	PORT_DIPSETTING(   0x00, DEF_STR( "Cocktail" ) );
    
    	PORT_START();	/* DSW 1 */
    	PORT_DIPNAME( 0x03, 0x01, DEF_STR( "Coinage" ) );
    	PORT_DIPSETTING(    0x03, DEF_STR( "2C_1C" ) );
    	PORT_DIPSETTING(    0x01, DEF_STR( "1C_1C" ) );
    	PORT_DIPSETTING(    0x02, DEF_STR( "1C_2C" ) );
    	PORT_DIPSETTING(    0x00, DEF_STR( "Free_Play" ) );
    	PORT_DIPNAME( 0x0c, 0x08, DEF_STR( "Lives" ) );
    	PORT_DIPSETTING(    0x00, "1" );
    	PORT_DIPSETTING(    0x04, "2" );
    	PORT_DIPSETTING(    0x08, "3" );
    	PORT_DIPSETTING(    0x0c, "5" );
    	PORT_DIPNAME( 0x30, 0x00, DEF_STR( "Bonus_Life" ) );
    	PORT_DIPSETTING(    0x00, "10000" );
    	PORT_DIPSETTING(    0x10, "15000" );
    	PORT_DIPSETTING(    0x20, "20000" );
    	PORT_DIPSETTING(    0x30, "None" );
    	PORT_DIPNAME( 0x40, 0x40, DEF_STR( "Difficulty" ) );
    	PORT_DIPSETTING(    0x40, "Normal" );
    	PORT_DIPSETTING(    0x00, "Hard" );
    	PORT_DIPNAME( 0x80, 0x80, "Ghost Names" );
    	PORT_DIPSETTING(    0x80, "Normal" );
    	PORT_DIPSETTING(    0x00, "Alternate" );
    
    	PORT_START();	/* DSW 2 */
    	PORT_BIT( 0xff, IP_ACTIVE_HIGH, IPT_UNUSED );
    
    	PORT_START();	/* FAKE */
    	/* This fake input port is used to get the status of the fire button */
    	/* and activate the speedup cheat if it is. */
    	PORT_BITX(    0x01, 0x00, IPT_DIPSWITCH_NAME | IPF_CHEAT, "Speedup Cheat", KEYCODE_LCONTROL, JOYCODE_1_BUTTON1 );
    	PORT_DIPSETTING(    0x00, DEF_STR( "Off" ) );
    	PORT_DIPSETTING(    0x01, DEF_STR( "On" ) );
        INPUT_PORTS_END();
    }};
    /* Ms. Pac-Man input ports are identical to Pac-Man, the only difference is */
    /* the missing Ghost Names dip switch. */
    static InputPortPtr input_ports_mspacman = new InputPortPtr(){ public void handler() {
    	PORT_START();	/* IN0 */
    	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY );
    	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY );
    	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY );
    	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY );
    	PORT_BITX(    0x10, 0x10, IPT_DIPSWITCH_NAME | IPF_CHEAT, "Rack Test", KEYCODE_F1, IP_JOY_NONE );
    	PORT_DIPSETTING(    0x10, DEF_STR( "Off" ) );
    	PORT_DIPSETTING(    0x00, DEF_STR( "On" ) );
    	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 );
    	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_COIN2 );
    	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN3 );
    
    	PORT_START();	/* IN1 */
    	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL );
    	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL );
    	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL );
    	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL );
    	PORT_SERVICE( 0x10, IP_ACTIVE_LOW );
    	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 );
    	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 );
    	PORT_DIPNAME( 0x80, 0x80, DEF_STR( "Cabinet" ) );
    	PORT_DIPSETTING(    0x80, DEF_STR( "Upright" ) );
    	PORT_DIPSETTING(    0x00, DEF_STR( "Cocktail" ) );
    
    	PORT_START();	/* DSW 1 */
    	PORT_DIPNAME( 0x03, 0x01, DEF_STR( "Coinage" ) );
    	PORT_DIPSETTING(    0x03, DEF_STR( "2C_1C" ) );
    	PORT_DIPSETTING(    0x01, DEF_STR( "1C_1C" ) );
    	PORT_DIPSETTING(    0x02, DEF_STR( "1C_2C" ) );
    	PORT_DIPSETTING(    0x00, DEF_STR( "Free_Play" ) );
    	PORT_DIPNAME( 0x0c, 0x08, DEF_STR( "Lives" ) );
    	PORT_DIPSETTING(    0x00, "1" );
    	PORT_DIPSETTING(    0x04, "2" );
    	PORT_DIPSETTING(    0x08, "3" );
    	PORT_DIPSETTING(    0x0c, "5" );
    	PORT_DIPNAME( 0x30, 0x00, DEF_STR( "Bonus_Life" ) );
    	PORT_DIPSETTING(    0x00, "10000" );
    	PORT_DIPSETTING(    0x10, "15000" );
    	PORT_DIPSETTING(    0x20, "20000" );
    	PORT_DIPSETTING(    0x30, "None" );
    	PORT_DIPNAME( 0x40, 0x40, DEF_STR( "Difficulty" ) );
    	PORT_DIPSETTING(    0x40, "Normal" );
    	PORT_DIPSETTING(    0x00, "Hard" );
    	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_UNUSED );
    
    	PORT_START();	/* DSW 2 */
    	PORT_BIT( 0xff, IP_ACTIVE_HIGH, IPT_UNUSED );
    
    	PORT_START();	/* FAKE */
    	/* This fake input port is used to get the status of the fire button */
    	/* and activate the speedup cheat if it is. */
    	PORT_BITX(    0x01, 0x00, IPT_DIPSWITCH_NAME | IPF_CHEAT, "Speedup Cheat", KEYCODE_LCONTROL, JOYCODE_1_BUTTON1 );
    	PORT_DIPSETTING(    0x00, DEF_STR( "Off" ) );
    	PORT_DIPSETTING(    0x01, DEF_STR( "On" ) );
        INPUT_PORTS_END();
    }};
    
    /*TODO*///INPUT_PORTS_START( maketrax )
    /*TODO*///	PORT_START	/* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_DIPNAME( 0x10, 0x00, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x10, DEF_STR( Cocktail ) )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN3 )
    /*TODO*///
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_HIGH, IPT_UNUSED )  /* Protection */
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_HIGH, IPT_UNUSED )  /* Protection */
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x03, 0x01, DEF_STR( Coinage ) )
    /*TODO*///	PORT_DIPSETTING(    0x03, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Free_Play ) )
    /*TODO*///	PORT_DIPNAME( 0x0c, 0x00, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x04, "4" )
    /*TODO*///	PORT_DIPSETTING(    0x08, "5" )
    /*TODO*///	PORT_DIPSETTING(    0x0c, "6" )
    /*TODO*///	PORT_DIPNAME( 0x10, 0x10, "First Pattern" )
    /*TODO*///	PORT_DIPSETTING(    0x10, "Easy" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "Hard" )
    /*TODO*///	PORT_DIPNAME( 0x20, 0x20, "Teleport Holes" )
    /*TODO*///	PORT_DIPSETTING(    0x20, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*/// 	PORT_BIT( 0xc0, IP_ACTIVE_HIGH, IPT_UNUSED )  /* Protection */
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///	PORT_BIT( 0xff, IP_ACTIVE_HIGH, IPT_UNUSED )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///INPUT_PORTS_START( mbrush )
    /*TODO*///	PORT_START	/* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_DIPNAME( 0x10, 0x00, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x10, DEF_STR( Cocktail ) )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN3 )
    /*TODO*///
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_HIGH, IPT_UNUSED )  /* Protection in Make Trax */
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_HIGH, IPT_UNUSED )  /* Protection in Make Trax */
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x03, 0x01, DEF_STR( Coinage ) )
    /*TODO*///	PORT_DIPSETTING(    0x03, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Free_Play ) )
    /*TODO*///	PORT_DIPNAME( 0x0c, 0x08, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, "1" )
    /*TODO*///	PORT_DIPSETTING(    0x04, "2" )
    /*TODO*///	PORT_DIPSETTING(    0x08, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x0c, "4" )
    /*TODO*///	PORT_DIPNAME( 0x10, 0x10, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x10, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x20, 0x20, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x20, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_BIT( 0xc0, IP_ACTIVE_HIGH, IPT_UNUSED )  /* Protection in Make Trax */
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///	PORT_BIT( 0xff, IP_ACTIVE_HIGH, IPT_UNUSED )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///INPUT_PORTS_START( paintrlr )
    /*TODO*///	PORT_START	/* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_DIPNAME( 0x10, 0x00, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x10, DEF_STR( Cocktail ) )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN3 )
    /*TODO*///
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_HIGH, IPT_UNUSED )  /* Protection in Make Trax */
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_HIGH, IPT_UNUSED )  /* Protection in Make Trax */
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x03, 0x01, DEF_STR( Coinage ) )
    /*TODO*///	PORT_DIPSETTING(    0x03, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Free_Play ) )
    /*TODO*///	PORT_DIPNAME( 0x0c, 0x00, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x04, "4" )
    /*TODO*///	PORT_DIPSETTING(    0x08, "5" )
    /*TODO*///	PORT_DIPSETTING(    0x0c, "6" )
    /*TODO*///	PORT_DIPNAME( 0x10, 0x10, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x10, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x20, 0x20, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x20, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_BIT( 0xc0, IP_ACTIVE_HIGH, IPT_UNUSED )  /* Protection in Make Trax */
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///	PORT_BIT( 0xff, IP_ACTIVE_HIGH, IPT_UNUSED )
    /*TODO*///INPUT_PORTS_END
    	static InputPortPtr input_ports_ponpoko = new InputPortPtr(){ public void handler() { 
		PORT_START(); 	/* IN0 */
		PORT_BIT( 0x01, IP_ACTIVE_HIGH, IPT_JOYSTICK_UP    | IPF_8WAY );
		PORT_BIT( 0x02, IP_ACTIVE_HIGH, IPT_JOYSTICK_LEFT  | IPF_8WAY );
		PORT_BIT( 0x04, IP_ACTIVE_HIGH, IPT_JOYSTICK_RIGHT | IPF_8WAY );
		PORT_BIT( 0x08, IP_ACTIVE_HIGH, IPT_JOYSTICK_DOWN  | IPF_8WAY );
		PORT_BIT( 0x10, IP_ACTIVE_HIGH, IPT_BUTTON1 );
		PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 );
		PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_COIN2 );
		PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN3 );
	
		/* The 2nd player controls are used even in upright mode */
		PORT_START(); 	/* IN1 */
		PORT_BIT( 0x01, IP_ACTIVE_HIGH, IPT_JOYSTICK_UP    | IPF_8WAY | IPF_PLAYER2 );
		PORT_BIT( 0x02, IP_ACTIVE_HIGH, IPT_JOYSTICK_LEFT  | IPF_8WAY | IPF_PLAYER2 );
		PORT_BIT( 0x04, IP_ACTIVE_HIGH, IPT_JOYSTICK_RIGHT | IPF_8WAY | IPF_PLAYER2 );
		PORT_BIT( 0x08, IP_ACTIVE_HIGH, IPT_JOYSTICK_DOWN  | IPF_8WAY | IPF_PLAYER2 );
		PORT_BIT( 0x10, IP_ACTIVE_HIGH, IPT_BUTTON1 | IPF_PLAYER2 );
		PORT_BIT( 0x20, IP_ACTIVE_HIGH, IPT_START1 );
		PORT_BIT( 0x40, IP_ACTIVE_HIGH, IPT_START2 );
		PORT_BIT( 0x80, IP_ACTIVE_HIGH, IPT_UNUSED );
	
		PORT_START(); 	/* DSW 1 */
		PORT_DIPNAME( 0x03, 0x01, DEF_STR( "Bonus_Life" ));
		PORT_DIPSETTING(    0x01, "10000" );
		PORT_DIPSETTING(    0x02, "30000" );
		PORT_DIPSETTING(    0x03, "50000" );
		PORT_DIPSETTING(    0x00, "None" );
		PORT_DIPNAME( 0x0c, 0x00, DEF_STR( "Unknown" ));
		PORT_DIPSETTING(    0x00, "0" );
		PORT_DIPSETTING(    0x04, "1" );
		PORT_DIPSETTING(    0x08, "2" );
		PORT_DIPSETTING(    0x0c, "3" );
		PORT_DIPNAME( 0x30, 0x20, DEF_STR( "Lives" ));
		PORT_DIPSETTING(    0x00, "2" );
		PORT_DIPSETTING(    0x10, "3" );
		PORT_DIPSETTING(    0x20, "4" );
		PORT_DIPSETTING(    0x30, "5" );
		PORT_DIPNAME( 0x40, 0x40, DEF_STR( "Cabinet" ));
		PORT_DIPSETTING(    0x40, DEF_STR( "Upright" ));
		PORT_DIPSETTING(    0x00, DEF_STR( "Cocktail" ));
		PORT_DIPNAME( 0x80, 0x80, DEF_STR( "Unknown" ));
		PORT_DIPSETTING(    0x80, DEF_STR( "Off" ));
		PORT_DIPSETTING(    0x00, DEF_STR( "On" ));
	
		PORT_START(); 	/* DSW 2 */
		PORT_DIPNAME( 0x0f, 0x01, DEF_STR( "Coinage" ));
		PORT_DIPSETTING(    0x04, "A 3/1 B 3/1" );
		PORT_DIPSETTING(    0x0e, "A 3/1 B 1/2" );
		PORT_DIPSETTING(    0x0f, "A 3/1 B 1/4" );
		PORT_DIPSETTING(    0x02, "A 2/1 B 2/1" );
		PORT_DIPSETTING(    0x0d, "A 2/1 B 1/1" );
		PORT_DIPSETTING(    0x07, "A 2/1 B 1/3" );
		PORT_DIPSETTING(    0x0b, "A 2/1 B 1/5" );
		PORT_DIPSETTING(    0x0c, "A 2/1 B 1/6" );
		PORT_DIPSETTING(    0x01, "A 1/1 B 1/1" );
		PORT_DIPSETTING(    0x06, "A 1/1 B 4/5" );
		PORT_DIPSETTING(    0x05, "A 1/1 B 2/3" );
		PORT_DIPSETTING(    0x0a, "A 1/1 B 1/3" );
		PORT_DIPSETTING(    0x08, "A 1/1 B 1/5" );
		PORT_DIPSETTING(    0x09, "A 1/1 B 1/6" );
		PORT_DIPSETTING(    0x03, "A 1/2 B 1/2" );
		PORT_DIPSETTING(    0x00, DEF_STR( "Free_Play" ));
		PORT_DIPNAME( 0x10, 0x10, DEF_STR( "Unknown" )); /* Most likely unused */
		PORT_DIPSETTING(    0x10, DEF_STR( "Off" ));
		PORT_DIPSETTING(    0x00, DEF_STR( "On" ));
		PORT_DIPNAME( 0x20, 0x20, DEF_STR( "Unknown" ));  /* Most likely unused */
		PORT_DIPSETTING(    0x20, DEF_STR( "Off" ));
		PORT_DIPSETTING(    0x00, DEF_STR( "On" ));
		PORT_DIPNAME( 0x40, 0x00, DEF_STR( "Demo_Sounds" ));
		PORT_DIPSETTING(    0x40, DEF_STR( "Off" ));
		PORT_DIPSETTING(    0x00, DEF_STR( "On" ));
		PORT_DIPNAME( 0x80, 0x80, DEF_STR( "Unknown" )); /* Most likely unused */
		PORT_DIPSETTING(    0x80, DEF_STR( "Off" ));
		PORT_DIPSETTING(    0x00, DEF_STR( "On" ));
                INPUT_PORTS_END(); 
        }}; 
  
    /*TODO*///INPUT_PORTS_START( eyes )
    /*TODO*///	PORT_START  /* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_SERVICE( 0x10, IP_ACTIVE_LOW )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_TILT )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_BUTTON1 | IPF_COCKTAIL )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x03, 0x03, DEF_STR( Coinage ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x03, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Free_Play ) )
    /*TODO*///	PORT_DIPNAME( 0x0c, 0x08, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x0c, "2" )
    /*TODO*///	PORT_DIPSETTING(    0x08, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x04, "4" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "5" )
    /*TODO*///	PORT_DIPNAME( 0x30, 0x30, DEF_STR( Bonus_Life ) )
    /*TODO*///	PORT_DIPSETTING(    0x30, "50000" )
    /*TODO*///	PORT_DIPSETTING(    0x20, "75000" )
    /*TODO*///	PORT_DIPSETTING(    0x10, "100000" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "125000" )
    /*TODO*///	PORT_DIPNAME( 0x40, 0x40, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Cocktail ) )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x80, DEF_STR( Unknown ) )  /* Not accessed */
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///	PORT_BIT( 0xff, IP_ACTIVE_HIGH, IPT_UNUSED )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///INPUT_PORTS_START( mrtnt )
    /*TODO*///	PORT_START  /* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_SERVICE( 0x10, IP_ACTIVE_LOW )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_TILT )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_BUTTON1 | IPF_COCKTAIL )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x03, 0x03, DEF_STR( Coinage ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x03, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Free_Play ) )
    /*TODO*///	PORT_DIPNAME( 0x0c, 0x08, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x0c, "2" )
    /*TODO*///	PORT_DIPSETTING(    0x08, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x04, "4" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "5" )
    /*TODO*///	PORT_DIPNAME( 0x30, 0x30, DEF_STR( Bonus_Life ) )
    /*TODO*///	PORT_DIPSETTING(    0x30, "75000" )
    /*TODO*///	PORT_DIPSETTING(    0x20, "100000" )
    /*TODO*///	PORT_DIPSETTING(    0x10, "125000" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "150000" )
    /*TODO*///	PORT_DIPNAME( 0x40, 0x40, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Cocktail ) )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x80, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///	PORT_BIT( 0xff, IP_ACTIVE_HIGH, IPT_UNUSED )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///INPUT_PORTS_START( lizwiz )
    /*TODO*///	PORT_START	/* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_8WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_8WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_8WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_8WAY )
    /*TODO*///	PORT_SERVICE( 0x10, IP_ACTIVE_LOW )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_TILT )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_8WAY | IPF_PLAYER2 )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_8WAY | IPF_PLAYER2 )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_8WAY | IPF_PLAYER2 )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_8WAY | IPF_PLAYER2 )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_BUTTON1 | IPF_PLAYER2 )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x03, 0x03, DEF_STR( Coinage ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x03, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Free_Play ) )
    /*TODO*///	PORT_DIPNAME( 0x0c, 0x08, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x0c, "2" )
    /*TODO*///	PORT_DIPSETTING(    0x08, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x04, "4" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "5" )
    /*TODO*///	PORT_DIPNAME( 0x30, 0x30, DEF_STR( Bonus_Life ) )
    /*TODO*///	PORT_DIPSETTING(    0x30, "75000" )
    /*TODO*///	PORT_DIPSETTING(    0x20, "100000" )
    /*TODO*///	PORT_DIPSETTING(    0x10, "125000" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "150000" )
    /*TODO*///	PORT_DIPNAME( 0x40, 0x40, DEF_STR( Difficulty ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, "Normal" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "Hard" )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x80, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///	PORT_BIT( 0xff, IP_ACTIVE_HIGH, IPT_UNUSED )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///INPUT_PORTS_START( theglob )
    /*TODO*///	PORT_START	/* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_UNKNOWN )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_UNKNOWN )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_BUTTON2 | IPF_COCKTAIL )
    /*TODO*///
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_BUTTON1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_BUTTON2 )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x80, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Cocktail ) )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x03, 0x03, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x03, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x02, "4" )
    /*TODO*///	PORT_DIPSETTING(    0x01, "5" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "6" )
    /*TODO*///	PORT_DIPNAME( 0x1c, 0x1c, DEF_STR( Difficulty ) )
    /*TODO*///	PORT_DIPSETTING(    0x1c, "Easiest" )
    /*TODO*///	PORT_DIPSETTING(    0x18, "Very Easy" )
    /*TODO*///	PORT_DIPSETTING(    0x14, "Easy" )
    /*TODO*///	PORT_DIPSETTING(    0x10, "Normal" )
    /*TODO*///	PORT_DIPSETTING(    0x0c, "Difficult" )
    /*TODO*///	PORT_DIPSETTING(    0x08, "Very Difficult" )
    /*TODO*///	PORT_DIPSETTING(    0x04, "Very Hard" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "Hardest" )
    /*TODO*///	PORT_DIPNAME( 0x20, 0x00, DEF_STR( Demo_Sounds ) )
    /*TODO*///	PORT_DIPSETTING(    0x20, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x40, 0x40, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x80, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///	PORT_BIT( 0xff, IP_ACTIVE_HIGH, IPT_UNUSED )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///INPUT_PORTS_START( vanvan )
    /*TODO*///	PORT_START	/* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_UNKNOWN )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///
    /*TODO*///	/* The 2nd player controls are used even in upright mode */
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_UNKNOWN )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x01, 0x00, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( Cocktail ) )
    /*TODO*///	PORT_DIPNAME( 0x02, 0x02, DEF_STR( Flip_Screen ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x04, 0x04, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x04, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x08, 0x08, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x08, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x30, 0x30, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x30, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x20, "4" )
    /*TODO*///	PORT_DIPSETTING(    0x10, "5" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "6" )
    /*TODO*///	PORT_DIPNAME( 0x40, 0x40, DEF_STR( Coin_A ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x80, DEF_STR( Coin_B ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( 1C_3C ) )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///	PORT_DIPNAME( 0x01, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( On ) )
    /*TODO*///	PORT_BITX(    0x02, 0x00, IPT_DIPSWITCH_NAME | IPF_CHEAT, "Invulnerability", KEYCODE_F1, IP_JOY_NONE )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x04, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x04, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x08, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x08, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x10, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x10, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x20, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x20, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x40, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( On ) )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///INPUT_PORTS_START( vanvans )
    /*TODO*///	PORT_START	/* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_UNKNOWN )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///
    /*TODO*///	/* The 2nd player controls are used even in upright mode */
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_UNKNOWN )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x01, 0x00, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( Cocktail ) )
    /*TODO*///	PORT_DIPNAME( 0x02, 0x02, DEF_STR( Flip_Screen ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x04, 0x04, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x04, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x08, 0x08, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x08, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x30, 0x30, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x30, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x20, "4" )
    /*TODO*///	PORT_DIPSETTING(    0x10, "5" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "6" )
    /*TODO*///	PORT_DIPNAME( 0xc0, 0xc0, DEF_STR( Coinage ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0xc0, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, DEF_STR( 1C_3C ) )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///	PORT_DIPNAME( 0x01, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( On ) )
    /*TODO*///	PORT_BITX(    0x02, 0x00, IPT_DIPSWITCH_NAME | IPF_CHEAT, "Invulnerability", KEYCODE_F1, IP_JOY_NONE )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x04, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x04, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x08, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x08, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x10, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x10, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x20, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x20, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x40, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x00, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( On ) )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///INPUT_PORTS_START( dremshpr )
    /*TODO*///	PORT_START	/* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_UNKNOWN )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_UNKNOWN )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x01, 0x01, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Cocktail ) )
    /*TODO*///	PORT_DIPNAME( 0x02, 0x02, DEF_STR( Flip_Screen ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///	PORT_DIPNAME( 0x0c, 0x0c, DEF_STR( Bonus_Life ) )
    /*TODO*///	PORT_DIPSETTING(    0x08, "30000" )
    /*TODO*///	PORT_DIPSETTING(    0x04, "50000" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "70000" )
    /*TODO*///	PORT_DIPSETTING(    0x0c, "None" )
    /*TODO*///	PORT_DIPNAME( 0x30, 0x30, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x30, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x20, "4" )
    /*TODO*///	PORT_DIPSETTING(    0x10, "5" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "6" )
    /*TODO*///	PORT_DIPNAME( 0xc0, 0xc0, DEF_STR( Coinage ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0xc0, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, DEF_STR( 1C_3C ) )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 2 */
    /*TODO*///  //PORT_BITX(    0x01, 0x00, IPT_DIPSWITCH_NAME | IPF_CHEAT, "Invulnerability", IP_KEY_NONE, IP_JOY_NONE )
    /*TODO*///  //PORT_DIPSETTING(    0x00, DEF_STR( Off ) )		/* turning this on crashes puts the */
    /*TODO*///  //PORT_DIPSETTING(    0x01, DEF_STR( On ) )       /* emulated machine in an infinite loop once in a while */
    /*TODO*/////	PORT_DIPNAME( 0xff, 0x00, DEF_STR( Unused ) )
    /*TODO*///	PORT_BIT( 0xfe, IP_ACTIVE_LOW, IPT_UNUSED )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///INPUT_PORTS_START( alibaba )
    /*TODO*///	PORT_START	/* IN0 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY )
    /*TODO*///	PORT_BITX(0x10, 0x10, IPT_DIPSWITCH_NAME | IPF_CHEAT, "Rack Test", KEYCODE_F1, IP_JOY_NONE )
    /*TODO*///	PORT_DIPSETTING(0x10, DEF_STR( Off ) )
    /*TODO*///	PORT_DIPSETTING(0x00, DEF_STR( On ) )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_COIN1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_BUTTON1 )
    /*TODO*///	PORT_BIT( 0x80, IP_ACTIVE_LOW, IPT_COIN2 )
    /*TODO*///
    /*TODO*///	PORT_START	/* IN1 */
    /*TODO*///	PORT_BIT( 0x01, IP_ACTIVE_LOW, IPT_JOYSTICK_UP    | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x02, IP_ACTIVE_LOW, IPT_JOYSTICK_LEFT  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x04, IP_ACTIVE_LOW, IPT_JOYSTICK_RIGHT | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x08, IP_ACTIVE_LOW, IPT_JOYSTICK_DOWN  | IPF_4WAY | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x10, IP_ACTIVE_LOW, IPT_BUTTON1 | IPF_COCKTAIL )
    /*TODO*///	PORT_BIT( 0x20, IP_ACTIVE_LOW, IPT_START1 )
    /*TODO*///	PORT_BIT( 0x40, IP_ACTIVE_LOW, IPT_START2 )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x80, DEF_STR( Cabinet ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( Upright ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Cocktail ) )
    /*TODO*///
    /*TODO*///	PORT_START	/* DSW 1 */
    /*TODO*///	PORT_DIPNAME( 0x03, 0x01, DEF_STR( Coinage ) )
    /*TODO*///	PORT_DIPSETTING(    0x03, DEF_STR( 2C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x01, DEF_STR( 1C_1C ) )
    /*TODO*///	PORT_DIPSETTING(    0x02, DEF_STR( 1C_2C ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( Free_Play ) )
    /*TODO*///	PORT_DIPNAME( 0x0c, 0x08, DEF_STR( Lives ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, "1" )
    /*TODO*///	PORT_DIPSETTING(    0x04, "2" )
    /*TODO*///	PORT_DIPSETTING(    0x08, "3" )
    /*TODO*///	PORT_DIPSETTING(    0x0c, "5" )
    /*TODO*///	PORT_DIPNAME( 0x30, 0x00, DEF_STR( Bonus_Life ) )
    /*TODO*///	PORT_DIPSETTING(    0x00, "10000" )
    /*TODO*///	PORT_DIPSETTING(    0x10, "15000" )
    /*TODO*///	PORT_DIPSETTING(    0x20, "20000" )
    /*TODO*///	PORT_DIPSETTING(    0x30, "None" )
    /*TODO*///	PORT_DIPNAME( 0x40, 0x40, DEF_STR( Difficulty ) )
    /*TODO*///	PORT_DIPSETTING(    0x40, "Normal" )
    /*TODO*///	PORT_DIPSETTING(    0x00, "Hard" )
    /*TODO*///	PORT_DIPNAME( 0x80, 0x80, DEF_STR( Unknown ) )
    /*TODO*///	PORT_DIPSETTING(    0x80, DEF_STR( Off) )
    /*TODO*///	PORT_DIPSETTING(    0x00, DEF_STR( On ) )
    /*TODO*///INPUT_PORTS_END
    /*TODO*///
    /*TODO*///
	static GfxLayout tilelayout = new GfxLayout
	(
		8,8,	/* 8*8 characters */
	    256,    /* 256 characters */
	    2,  /* 2 bits per pixel */
	    new int[] { 0, 4 },   /* the two bitplanes for 4 pixels are packed into one byte */
	    new int[] { 8*8+0, 8*8+1, 8*8+2, 8*8+3, 0, 1, 2, 3 }, /* bits are packed in groups of four */
	    new int[] { 0*8, 1*8, 2*8, 3*8, 4*8, 5*8, 6*8, 7*8 },
	    16*8    /* every char takes 16 bytes */
	);
	
	
	static GfxLayout spritelayout = new GfxLayout
	(
		16,16,	/* 16*16 sprites */
		64,	/* 64 sprites */
		2,	/* 2 bits per pixel */
		new int[] { 0, 4 },	/* the two bitplanes for 4 pixels are packed into one byte */
		new int[] { 8*8, 8*8+1, 8*8+2, 8*8+3, 16*8+0, 16*8+1, 16*8+2, 16*8+3,
				24*8+0, 24*8+1, 24*8+2, 24*8+3, 0, 1, 2, 3 },
		new int[] { 0*8, 1*8, 2*8, 3*8, 4*8, 5*8, 6*8, 7*8,
				32*8, 33*8, 34*8, 35*8, 36*8, 37*8, 38*8, 39*8 },
		64*8	/* every sprite takes 64 bytes */
	);
	
	
	static GfxDecodeInfo gfxdecodeinfo[] =
	{
		new GfxDecodeInfo( REGION_GFX1, 0, tilelayout,   0, 32 ),
		new GfxDecodeInfo( REGION_GFX2, 0, spritelayout, 0, 32 ),
		new GfxDecodeInfo( -1 ) /* end of array */
	};
    /*TODO*///
    /*TODO*///
    static namco_interface namco_interface = new namco_interface
    (
    	3072000/32,	/* sample rate */
    	3,			/* number of voices */
    	100,		/* playback volume */
   	REGION_SOUND1	/* memory region */
    );
    /*TODO*///
    /*TODO*///static struct SN76496interface sn76496_interface =
    /*TODO*///{
    /*TODO*///	2,
    /*TODO*///	{ 1789750, 1789750 },	/* 1.78975 Mhz ? */
    /*TODO*///	{ 75, 75 }
    /*TODO*///};
    /*TODO*///
    /*TODO*///static struct AY8910interface dremshpr_ay8910_interface =
    /*TODO*///{
    /*TODO*///	1,	/* 1 chip */
    /*TODO*///	14318000/8,	/* 1.78975 MHz ??? */
    /*TODO*///	{ 50 },
    /*TODO*///	{ 0 },
    /*TODO*///	{ 0 },
    /*TODO*///	{ 0 },
    /*TODO*///	{ 0 }
    /*TODO*///};
    /*TODO*///
    /*TODO*///
    	static MachineDriver machine_driver_pacman = new MachineDriver
	(
		/* basic machine hardware */
		new MachineCPU[] {
			new MachineCPU(
				CPU_Z80,
				18432000/6,	/* 3.072 Mhz */
				readmem,writemem,null,writeport,
				pacman_interrupt,1
			)
		},
		60, 2500,	/* frames per second, vblank duration */
		1,	/* single CPU, no need for interleaving */
		pacman_init_machine,
	
		/* video hardware */
		36*8, 28*8, new rectangle( 0*8, 36*8-1, 0*8, 28*8-1 ),
		gfxdecodeinfo,
		16, 4*32,
		pacman_vh_convert_color_prom,
	
		VIDEO_TYPE_RASTER | VIDEO_SUPPORTS_DIRTY,
		null,
		pacman_vh_start,
		generic_vh_stop,
		pengo_vh_screenrefresh,
	
		/* sound hardware */
		0,0,0,0,
		new MachineSound[] {
                    new MachineSound
                    (	
			SOUND_NAMCO,
			namco_interface
                    )
		}
	);

    /*TODO*///
    /*TODO*///static struct MachineDriver machine_driver_theglob =
    /*TODO*///{
    /*TODO*///	/* basic machine hardware */
    /*TODO*///	{
    /*TODO*///		{
    /*TODO*///			CPU_Z80,
    /*TODO*///			18432000/6,	/* 3.072 Mhz */
    /*TODO*///			theglob_readmem,writemem,theglob_readport,writeport,
    /*TODO*///			pacman_interrupt,1
    /*TODO*///		}
    /*TODO*///	},
    /*TODO*///	60, 2500,	/* frames per second, vblank duration */
    /*TODO*///	1,	/* single CPU, no need for interleaving */
    /*TODO*///	theglob_init_machine,
    /*TODO*///
    /*TODO*///	/* video hardware */
    /*TODO*///	36*8, 28*8, { 0*8, 36*8-1, 0*8, 28*8-1 },
    /*TODO*///	gfxdecodeinfo,
    /*TODO*///	16, 4*32,
    /*TODO*///	pacman_vh_convert_color_prom,
    /*TODO*///
    /*TODO*///	VIDEO_TYPE_RASTER,
    /*TODO*///	0,
    /*TODO*///	pacman_vh_start,
    /*TODO*///	generic_vh_stop,
    /*TODO*///	pengo_vh_screenrefresh,
    /*TODO*///
    /*TODO*///	/* sound hardware */
    /*TODO*///	0,0,0,0,
    /*TODO*///	{
    /*TODO*///		{
    /*TODO*///			SOUND_NAMCO,
    /*TODO*///			&namco_interface
    /*TODO*///		}
    /*TODO*///	}
    /*TODO*///};
    /*TODO*///
    /*TODO*///static struct MachineDriver machine_driver_vanvan =
    /*TODO*///{
    /*TODO*///	/* basic machine hardware */
    /*TODO*///	{
    /*TODO*///		{
    /*TODO*///			CPU_Z80,
    /*TODO*///			18432000/6,	/* 3.072 Mhz */
    /*TODO*///			readmem,writemem,0,vanvan_writeport,
    /*TODO*///			nmi_interrupt,1
    /*TODO*///		}
    /*TODO*///	},
    /*TODO*///	60, 2500,	/* frames per second, vblank duration */
    /*TODO*///	1,	/* single CPU, no need for interleaving */
    /*TODO*///	0,
    /*TODO*///
    /*TODO*///	/* video hardware */
    /*TODO*///	36*8, 28*8, { 0*8, 36*8-1, 0*8, 28*8-1 },
    /*TODO*///	gfxdecodeinfo,
    /*TODO*///	16, 4*32,
    /*TODO*///	pacman_vh_convert_color_prom,
    /*TODO*///
    /*TODO*///	VIDEO_TYPE_RASTER | VIDEO_SUPPORTS_DIRTY,
    /*TODO*///	0,
    /*TODO*///	pacman_vh_start,
    /*TODO*///	generic_vh_stop,
    /*TODO*///	pengo_vh_screenrefresh,
    /*TODO*///
    /*TODO*///	/* sound hardware */
    /*TODO*///	0,0,0,0,
    /*TODO*///	{
    /*TODO*///		{
    /*TODO*///			SOUND_SN76496,
    /*TODO*///			&sn76496_interface
    /*TODO*///		}
    /*TODO*///	}
    /*TODO*///};
    /*TODO*///
    /*TODO*///static struct MachineDriver machine_driver_dremshpr =
    /*TODO*///{
    /*TODO*///	/* basic machine hardware */
    /*TODO*///	{
    /*TODO*///		{
    /*TODO*///			CPU_Z80,
    /*TODO*///			18432000/6,	/* 3.072 Mhz */
    /*TODO*///			readmem,writemem,0,dremshpr_writeport,
    /*TODO*///			nmi_interrupt,1
    /*TODO*///		}
    /*TODO*///	},
    /*TODO*///	60, 2500,	/* frames per second, vblank duration */
    /*TODO*///	1,	/* single CPU, no need for interleaving */
    /*TODO*///	0,
    /*TODO*///
    /*TODO*///	/* video hardware */
    /*TODO*///	36*8, 28*8, { 0*8, 36*8-1, 0*8, 28*8-1 },
    /*TODO*///	gfxdecodeinfo,
    /*TODO*///	16, 4*32,
    /*TODO*///	pacman_vh_convert_color_prom,
    /*TODO*///
    /*TODO*///	VIDEO_TYPE_RASTER | VIDEO_SUPPORTS_DIRTY,
    /*TODO*///	0,
    /*TODO*///	pacman_vh_start,
    /*TODO*///	generic_vh_stop,
    /*TODO*///	pengo_vh_screenrefresh,
    /*TODO*///
    /*TODO*///	/* sound hardware */
    /*TODO*///	0,0,0,0,
    /*TODO*///	{
    /*TODO*///		{
    /*TODO*///			SOUND_AY8910,
    /*TODO*///			&dremshpr_ay8910_interface
    /*TODO*///		}
    /*TODO*///	}
    /*TODO*///};
    /*TODO*///
    /*TODO*///static struct MachineDriver machine_driver_alibaba =
    /*TODO*///{
    /*TODO*///	/* basic machine hardware */
    /*TODO*///	{
    /*TODO*///		{
    /*TODO*///			CPU_Z80,
    /*TODO*///			18432000/6,	/* 3.072 Mhz */
    /*TODO*///			alibaba_readmem,alibaba_writemem,0,0,
    /*TODO*///			interrupt,1
    /*TODO*///		}
    /*TODO*///	},
    /*TODO*///	60, 2500,	/* frames per second, vblank duration */
    /*TODO*///	1,	/* single CPU, no need for interleaving */
    /*TODO*///	0,
    /*TODO*///
    /*TODO*///	/* video hardware */
    /*TODO*///	36*8, 28*8, { 0*8, 36*8-1, 0*8, 28*8-1 },
    /*TODO*///	gfxdecodeinfo,
    /*TODO*///	16, 4*32,
    /*TODO*///	pacman_vh_convert_color_prom,
    /*TODO*///
    /*TODO*///	VIDEO_TYPE_RASTER | VIDEO_SUPPORTS_DIRTY,
    /*TODO*///	0,
    /*TODO*///	pacman_vh_start,
    /*TODO*///	generic_vh_stop,
    /*TODO*///	pengo_vh_screenrefresh,
    /*TODO*///
    /*TODO*///	/* sound hardware */
    /*TODO*///	0,0,0,0,
    /*TODO*///	{
    /*TODO*///		{
    /*TODO*///			SOUND_NAMCO,
    /*TODO*///			&namco_interface
    /*TODO*///		}
    /*TODO*///	}
    /*TODO*///};
    /*TODO*///
    /*TODO*////***************************************************************************
    /*TODO*///
    /*TODO*///  Game driver(s)
    /*TODO*///
    /*TODO*///***************************************************************************/
	static RomLoadPtr rom_pacman = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "namcopac.6e",  0x0000, 0x1000, 0xfee263b3 );
		ROM_LOAD( "namcopac.6f",  0x1000, 0x1000, 0x39d1fc83 );
		ROM_LOAD( "namcopac.6h",  0x2000, 0x1000, 0x02083b03 );
		ROM_LOAD( "namcopac.6j",  0x3000, 0x1000, 0x7a36fe55 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5e",    0x0000, 0x1000, 0x0c944964 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5f",    0x0000, 0x1000, 0x958fedf9 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_npacmod = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "namcopac.6e",  0x0000, 0x1000, 0xfee263b3 );
		ROM_LOAD( "namcopac.6f",  0x1000, 0x1000, 0x39d1fc83 );
		ROM_LOAD( "namcopac.6h",  0x2000, 0x1000, 0x02083b03 );
		ROM_LOAD( "npacmod.6j",   0x3000, 0x1000, 0x7d98d5f5 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5e",    0x0000, 0x1000, 0x0c944964 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5f",    0x0000, 0x1000, 0x958fedf9 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_pacmanjp = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "pacman.6e",    0x0000, 0x1000, 0xc1e6ab10 );
		ROM_LOAD( "pacman.6f",    0x1000, 0x1000, 0x1a6fb2d4 );
		ROM_LOAD( "pacman.6h",    0x2000, 0x1000, 0xbcdd1beb );
		ROM_LOAD( "prg7",         0x3000, 0x0800, 0xb6289b26 );
		ROM_LOAD( "prg8",         0x3800, 0x0800, 0x17a88c13 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "chg1",         0x0000, 0x0800, 0x2066a0b7 );
		ROM_LOAD( "chg2",         0x0800, 0x0800, 0x3591b89d );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5f",    0x0000, 0x1000, 0x958fedf9 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_pacmanm = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "pacman.6e",    0x0000, 0x1000, 0xc1e6ab10 );
		ROM_LOAD( "pacman.6f",    0x1000, 0x1000, 0x1a6fb2d4 );
		ROM_LOAD( "pacman.6h",    0x2000, 0x1000, 0xbcdd1beb );
		ROM_LOAD( "pacman.6j",    0x3000, 0x1000, 0x817d94e3 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5e",    0x0000, 0x1000, 0x0c944964 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5f",    0x0000, 0x1000, 0x958fedf9 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_pacmod = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "pacmanh.6e",   0x0000, 0x1000, 0x3b2ec270 );
		ROM_LOAD( "pacman.6f",    0x1000, 0x1000, 0x1a6fb2d4 );
		ROM_LOAD( "pacmanh.6h",   0x2000, 0x1000, 0x18811780 );
		ROM_LOAD( "pacmanh.6j",   0x3000, 0x1000, 0x5c96a733 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacmanh.5e",   0x0000, 0x1000, 0x299fb17a );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5f",    0x0000, 0x1000, 0x958fedf9 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_hangly = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "hangly.6e",    0x0000, 0x1000, 0x5fe8610a );
		ROM_LOAD( "hangly.6f",    0x1000, 0x1000, 0x73726586 );
		ROM_LOAD( "hangly.6h",    0x2000, 0x1000, 0x4e7ef99f );
		ROM_LOAD( "hangly.6j",    0x3000, 0x1000, 0x7f4147e6 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5e",    0x0000, 0x1000, 0x0c944964 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5f",    0x0000, 0x1000, 0x958fedf9 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_hangly2 = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "hangly.6e",    0x0000, 0x1000, 0x5fe8610a );
		ROM_LOAD( "hangly2.6f",   0x1000, 0x0800, 0x5ba228bb );
		ROM_LOAD( "hangly2.6m",   0x1800, 0x0800, 0xbaf5461e );
		ROM_LOAD( "hangly.6h",    0x2000, 0x1000, 0x4e7ef99f );
		ROM_LOAD( "hangly2.6j",   0x3000, 0x0800, 0x51305374 );
		ROM_LOAD( "hangly2.6p",   0x3800, 0x0800, 0x427c9d4d );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacmanh.5e",   0x0000, 0x1000, 0x299fb17a );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5f",    0x0000, 0x1000, 0x958fedf9 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_puckman = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "puckman.6e",   0x0000, 0x1000, 0xa8ae23c5 );
		ROM_LOAD( "pacman.6f",    0x1000, 0x1000, 0x1a6fb2d4 );
		ROM_LOAD( "puckman.6h",   0x2000, 0x1000, 0x197443f8 );
		ROM_LOAD( "puckman.6j",   0x3000, 0x1000, 0x2e64a3ba );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5e",    0x0000, 0x1000, 0x0c944964 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5f",    0x0000, 0x1000, 0x958fedf9 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_pacheart = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );    /* 64k for code */
		ROM_LOAD( "pacheart.pg1", 0x0000, 0x0800, 0xd844b679 );
		ROM_LOAD( "pacheart.pg2", 0x0800, 0x0800, 0xb9152a38 );
		ROM_LOAD( "pacheart.pg3", 0x1000, 0x0800, 0x7d177853 );
		ROM_LOAD( "pacheart.pg4", 0x1800, 0x0800, 0x842d6574 );
		ROM_LOAD( "pacheart.pg5", 0x2000, 0x0800, 0x9045a44c );
		ROM_LOAD( "pacheart.pg6", 0x2800, 0x0800, 0x888f3c3e );
		ROM_LOAD( "pacheart.pg7", 0x3000, 0x0800, 0xf5265c10 );
		ROM_LOAD( "pacheart.pg8", 0x3800, 0x0800, 0x1a21a381 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacheart.ch1", 0x0000, 0x0800, 0xc62bbabf );
		ROM_LOAD( "chg2",         0x0800, 0x0800, 0x3591b89d );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacheart.ch3", 0x0000, 0x0800, 0xca8c184c );
		ROM_LOAD( "pacheart.ch4", 0x0800, 0x0800, 0x1b1d9096 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 ); /* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_piranha = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "pr1.cpu",      0x0000, 0x1000, 0xbc5ad024 );
		ROM_LOAD( "pacman.6f",    0x1000, 0x1000, 0x1a6fb2d4 );
		ROM_LOAD( "pr3.cpu",      0x2000, 0x1000, 0x473c379d );
		ROM_LOAD( "pr4.cpu",      0x3000, 0x1000, 0x63fbf895 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pr5.cpu",      0x0000, 0x0800, 0x3fc4030c );
		ROM_LOAD( "pr7.cpu",      0x0800, 0x0800, 0x30b9a010 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pr6.cpu",      0x0000, 0x0800, 0xf3e9c9d5 );
		ROM_LOAD( "pr8.cpu",      0x0800, 0x0800, 0x133d720d );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_pacplus = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "pacplus.6e",   0x0000, 0x1000, 0xd611ef68 );
		ROM_LOAD( "pacplus.6f",   0x1000, 0x1000, 0xc7207556 );
		ROM_LOAD( "pacplus.6h",   0x2000, 0x1000, 0xae379430 );
		ROM_LOAD( "pacplus.6j",   0x3000, 0x1000, 0x5a6dff7b );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacplus.5e",   0x0000, 0x1000, 0x022c35da );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacplus.5f",   0x0000, 0x1000, 0x4de65cdd );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "pacplus.7f",   0x0000, 0x0020, 0x063dd53a );
		ROM_LOAD( "pacplus.4a",   0x0020, 0x0100, 0xe271a166 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_mspacman = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "boot1",        0x0000, 0x1000, 0xd16b31b7 );
		ROM_LOAD( "boot2",        0x1000, 0x1000, 0x0d32de5e );
		ROM_LOAD( "boot3",        0x2000, 0x1000, 0x1821ee0b );
		ROM_LOAD( "boot4",        0x3000, 0x1000, 0x165a9dd8 );
		ROM_LOAD( "boot5",        0x8000, 0x1000, 0x8c3e6de6 );
		ROM_LOAD( "boot6",        0x9000, 0x1000, 0x368cb165 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5e",           0x0000, 0x1000, 0x5c281d01 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5f",           0x0000, 0x1000, 0x615af909 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_mspacatk = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "boot1",        0x0000, 0x1000, 0xd16b31b7 );
		ROM_LOAD( "mspacatk.2",   0x1000, 0x1000, 0x0af09d31 );
		ROM_LOAD( "boot3",        0x2000, 0x1000, 0x1821ee0b );
		ROM_LOAD( "boot4",        0x3000, 0x1000, 0x165a9dd8 );
		ROM_LOAD( "mspacatk.5",   0x8000, 0x1000, 0xe6e06954 );
		ROM_LOAD( "mspacatk.6",   0x9000, 0x1000, 0x3b5db308 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5e",           0x0000, 0x1000, 0x5c281d01 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5f",           0x0000, 0x1000, 0x615af909 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_pacgal = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "boot1",        0x0000, 0x1000, 0xd16b31b7 );
		ROM_LOAD( "boot2",        0x1000, 0x1000, 0x0d32de5e );
		ROM_LOAD( "pacman.7fh",   0x2000, 0x1000, 0x513f4d5c );
		ROM_LOAD( "pacman.7hj",   0x3000, 0x1000, 0x70694c8e );
		ROM_LOAD( "boot5",        0x8000, 0x1000, 0x8c3e6de6 );
		ROM_LOAD( "boot6",        0x9000, 0x1000, 0x368cb165 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5e",           0x0000, 0x1000, 0x5c281d01 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "pacman.5ef",   0x0000, 0x0800, 0x65a3ee71 );
		ROM_LOAD( "pacman.5hj",   0x0800, 0x0800, 0x50c7477d );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s129.4a",    0x0020, 0x0100, 0x63efb927 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_crush = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 2*0x10000, REGION_CPU1 );/* 64k for code + 64k for opcode copy to hack protection */
		ROM_LOAD( "crushkrl.6e",  0x0000, 0x1000, 0xa8dd8f54 );
		ROM_LOAD( "crushkrl.6f",  0x1000, 0x1000, 0x91387299 );
		ROM_LOAD( "crushkrl.6h",  0x2000, 0x1000, 0xd4455f27 );
		ROM_LOAD( "crushkrl.6j",  0x3000, 0x1000, 0xd59fc251 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "maketrax.5e",  0x0000, 0x1000, 0x91bad2da );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "maketrax.5f",  0x0000, 0x1000, 0xaea79f55 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "crush.4a",     0x0020, 0x0100, 0x2bc5d339 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_crush2 = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "tp1",          0x0000, 0x0800, 0xf276592e );
		ROM_LOAD( "tp5a",         0x0800, 0x0800, 0x3d302abe );
		ROM_LOAD( "tp2",          0x1000, 0x0800, 0x25f42e70 );
		ROM_LOAD( "tp6",          0x1800, 0x0800, 0x98279cbe );
		ROM_LOAD( "tp3",          0x2000, 0x0800, 0x8377b4cb );
		ROM_LOAD( "tp7",          0x2800, 0x0800, 0xd8e76c8c );
		ROM_LOAD( "tp4",          0x3000, 0x0800, 0x90b28fa3 );
		ROM_LOAD( "tp8",          0x3800, 0x0800, 0x10854e1b );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "tpa",          0x0000, 0x0800, 0xc7617198 );
		ROM_LOAD( "tpc",          0x0800, 0x0800, 0xe129d76a );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "tpb",          0x0000, 0x0800, 0xd1899f05 );
		ROM_LOAD( "tpd",          0x0800, 0x0800, 0xd35d1caf );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "crush.4a",     0x0020, 0x0100, 0x2bc5d339 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_crush3 = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "unkmol.4e",    0x0000, 0x0800, 0x49150ddf );
		ROM_LOAD( "unkmol.6e",    0x0800, 0x0800, 0x21f47e17 );
		ROM_LOAD( "unkmol.4f",    0x1000, 0x0800, 0x9b6dd592 );
		ROM_LOAD( "unkmol.6f",    0x1800, 0x0800, 0x755c1452 );
		ROM_LOAD( "unkmol.4h",    0x2000, 0x0800, 0xed30a312 );
		ROM_LOAD( "unkmol.6h",    0x2800, 0x0800, 0xfe4bb0eb );
		ROM_LOAD( "unkmol.4j",    0x3000, 0x0800, 0x072b91c9 );
		ROM_LOAD( "unkmol.6j",    0x3800, 0x0800, 0x66fba07d );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "unkmol.5e",    0x0000, 0x0800, 0x338880a0 );
		ROM_LOAD( "unkmol.5h",    0x0800, 0x0800, 0x4ce9c81f );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "unkmol.5f",    0x0000, 0x0800, 0x752e3780 );
		ROM_LOAD( "unkmol.5j",    0x0800, 0x0800, 0x6e00d2ac );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "crush.4a",     0x0020, 0x0100, 0x2bc5d339 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_maketrax = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 2*0x10000, REGION_CPU1 );/* 64k for code + 64k for opcode copy to hack protection */
		ROM_LOAD( "maketrax.6e",  0x0000, 0x1000, 0x0150fb4a );
		ROM_LOAD( "maketrax.6f",  0x1000, 0x1000, 0x77531691 );
		ROM_LOAD( "maketrax.6h",  0x2000, 0x1000, 0xa2cdc51e );
		ROM_LOAD( "maketrax.6j",  0x3000, 0x1000, 0x0b4b5e0a );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "maketrax.5e",  0x0000, 0x1000, 0x91bad2da );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "maketrax.5f",  0x0000, 0x1000, 0xaea79f55 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "crush.4a",     0x0020, 0x0100, 0x2bc5d339 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_mbrush = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "mbrush.6e",    0x0000, 0x1000, 0x750fbff7 );
		ROM_LOAD( "mbrush.6f",    0x1000, 0x1000, 0x27eb4299 );
		ROM_LOAD( "mbrush.6h",    0x2000, 0x1000, 0xd297108e );
		ROM_LOAD( "mbrush.6j",    0x3000, 0x1000, 0x6fd719d0 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "tpa",          0x0000, 0x0800, 0xc7617198 );
		ROM_LOAD( "mbrush.5h",    0x0800, 0x0800, 0xc15b6967 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "mbrush.5f",    0x0000, 0x0800, 0xd5bc5cb8 ); /* copyright sign was removed */
		ROM_LOAD( "tpd",          0x0800, 0x0800, 0xd35d1caf );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "crush.4a",     0x0020, 0x0100, 0x2bc5d339 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_paintrlr = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "paintrlr.1",   0x0000, 0x0800, 0x556d20b5 );
		ROM_LOAD( "paintrlr.5",   0x0800, 0x0800, 0x4598a965 );
		ROM_LOAD( "paintrlr.2",   0x1000, 0x0800, 0x2da29c81 );
		ROM_LOAD( "paintrlr.6",   0x1800, 0x0800, 0x1f561c54 );
		ROM_LOAD( "paintrlr.3",   0x2000, 0x0800, 0xe695b785 );
		ROM_LOAD( "paintrlr.7",   0x2800, 0x0800, 0x00e6eec0 );
		ROM_LOAD( "paintrlr.4",   0x3000, 0x0800, 0x0fd5884b );
		ROM_LOAD( "paintrlr.8",   0x3800, 0x0800, 0x4900114a );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "tpa",          0x0000, 0x0800, 0xc7617198 );
		ROM_LOAD( "mbrush.5h",    0x0800, 0x0800, 0xc15b6967 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "mbrush.5f",    0x0000, 0x0800, 0xd5bc5cb8 ); /* copyright sign was removed */
		ROM_LOAD( "tpd",          0x0800, 0x0800, 0xd35d1caf );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "crush.4a",     0x0020, 0x0100, 0x2bc5d339 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_ponpoko = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "ppokoj1.bin",  0x0000, 0x1000, 0xffa3c004 );
		ROM_LOAD( "ppokoj2.bin",  0x1000, 0x1000, 0x4a496866 );
		ROM_LOAD( "ppokoj3.bin",  0x2000, 0x1000, 0x17da6ca3 );
		ROM_LOAD( "ppokoj4.bin",  0x3000, 0x1000, 0x9d39a565 );
		ROM_LOAD( "ppoko5.bin",   0x8000, 0x1000, 0x54ca3d7d );
		ROM_LOAD( "ppoko6.bin",   0x9000, 0x1000, 0x3055c7e0 );
		ROM_LOAD( "ppoko7.bin",   0xa000, 0x1000, 0x3cbe47ca );
		ROM_LOAD( "ppokoj8.bin",  0xb000, 0x1000, 0x04b63fc6 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "ppoko9.bin",   0x0000, 0x1000, 0xb73e1a06 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "ppoko10.bin",  0x0000, 0x1000, 0x62069b5d );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_ponpokov = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "ppoko1.bin",   0x0000, 0x1000, 0x49077667 );
		ROM_LOAD( "ppoko2.bin",   0x1000, 0x1000, 0x5101781a );
		ROM_LOAD( "ppoko3.bin",   0x2000, 0x1000, 0xd790ed22 );
		ROM_LOAD( "ppoko4.bin",   0x3000, 0x1000, 0x4e449069 );
		ROM_LOAD( "ppoko5.bin",   0x8000, 0x1000, 0x54ca3d7d );
		ROM_LOAD( "ppoko6.bin",   0x9000, 0x1000, 0x3055c7e0 );
		ROM_LOAD( "ppoko7.bin",   0xa000, 0x1000, 0x3cbe47ca );
		ROM_LOAD( "ppoko8.bin",   0xb000, 0x1000, 0xb39be27d );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "ppoko9.bin",   0x0000, 0x1000, 0xb73e1a06 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "ppoko10.bin",  0x0000, 0x1000, 0x62069b5d );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s126.4a",    0x0020, 0x0100, 0x3eb3a8e4 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_eyes = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "d7",           0x0000, 0x1000, 0x3b09ac89 );
		ROM_LOAD( "e7",           0x1000, 0x1000, 0x97096855 );
		ROM_LOAD( "f7",           0x2000, 0x1000, 0x731e294e );
		ROM_LOAD( "h7",           0x3000, 0x1000, 0x22f7a719 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "d5",           0x0000, 0x1000, 0xd6af0030 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "e5",           0x0000, 0x1000, 0xa42b5201 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s129.4a",    0x0020, 0x0100, 0xd8d78829 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_eyes2 = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "g38201.7d",    0x0000, 0x1000, 0x2cda7185 );
		ROM_LOAD( "g38202.7e",    0x1000, 0x1000, 0xb9fe4f59 );
		ROM_LOAD( "g38203.7f",    0x2000, 0x1000, 0xd618ba66 );
		ROM_LOAD( "g38204.7h",    0x3000, 0x1000, 0xcf038276 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "g38205.5d",    0x0000, 0x1000, 0x03b1b4c7 ); /* this one has a (c) sign */
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "e5",           0x0000, 0x1000, 0xa42b5201 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "82s123.7f",    0x0000, 0x0020, 0x2fc650bd );
		ROM_LOAD( "82s129.4a",    0x0020, 0x0100, 0xd8d78829 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_mrtnt = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "tnt.1",        0x0000, 0x1000, 0x0e836586 );
		ROM_LOAD( "tnt.2",        0x1000, 0x1000, 0x779c4c5b );
		ROM_LOAD( "tnt.3",        0x2000, 0x1000, 0xad6fc688 );
		ROM_LOAD( "tnt.4",        0x3000, 0x1000, 0xd77557b3 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "tnt.5",        0x0000, 0x1000, 0x3038cc0e );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "tnt.6",        0x0000, 0x1000, 0x97634d8b );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "mrtnt08.bin",  0x0000, 0x0020, 0x00000000 );
		ROM_LOAD( "mrtnt04.bin",  0x0020, 0x0100, 0x00000000 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m"  ,  0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_lizwiz = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "6e.cpu",       0x0000, 0x1000, 0x32bc1990 );
		ROM_LOAD( "6f.cpu",       0x1000, 0x1000, 0xef24b414 );
		ROM_LOAD( "6h.cpu",       0x2000, 0x1000, 0x30bed83d );
		ROM_LOAD( "6j.cpu",       0x3000, 0x1000, 0xdd09baeb );
		ROM_LOAD( "wiza",         0x8000, 0x1000, 0xf6dea3a6 );
		ROM_LOAD( "wizb",         0x9000, 0x1000, 0xf27fb5a8 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5e.cpu",       0x0000, 0x1000, 0x45059e73 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5f.cpu",       0x0000, 0x1000, 0xd2469717 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "7f.cpu",       0x0000, 0x0020, 0x7549a947 );
		ROM_LOAD( "4a.cpu",       0x0020, 0x0100, 0x5fdca536 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m"  ,  0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_theglob = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x20000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "glob.u2",      0x0000, 0x2000, 0x829d0bea );
		ROM_LOAD( "glob.u3",      0x2000, 0x2000, 0x31de6628 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "glob.5e",      0x0000, 0x1000, 0x53688260 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "glob.5f",      0x0000, 0x1000, 0x051f59c7 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "glob.7f",      0x0000, 0x0020, 0x1f617527 );
		ROM_LOAD( "glob.4a",      0x0020, 0x0100, 0x28faa769 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m"  ,  0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_beastf = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x20000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "bf-u2.bin",    0x0000, 0x2000, 0x3afc517b );
		ROM_LOAD( "bf-u3.bin",    0x2000, 0x2000, 0x8dbd76d0 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "beastf.5e",    0x0000, 0x1000, 0x5654dc34 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "beastf.5f",    0x0000, 0x1000, 0x1b30ca61 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "glob.7f",      0x0000, 0x0020, 0x1f617527 );
		ROM_LOAD( "glob.4a",      0x0020, 0x0100, 0x28faa769 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m"  ,  0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_jumpshot = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "6e",           0x0000, 0x1000, 0xf00def9a );
		ROM_LOAD( "6f",           0x1000, 0x1000, 0xf70deae2 );
		ROM_LOAD( "6h",           0x2000, 0x1000, 0x894d6f68 );
		ROM_LOAD( "6j",           0x3000, 0x1000, 0xf15a108a );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5e",           0x0000, 0x1000, 0xd9fa90f5 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5f",           0x0000, 0x1000, 0x2ec711c1 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "prom.7f",      0x0000, 0x0020, 0x872b42f3 );
		ROM_LOAD( "prom.4a",      0x0020, 0x0100, 0x0399f39f );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
	
	static RomLoadPtr rom_vanvan = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "van1.bin",	  0x0000, 0x1000, 0x00f48295 );
		ROM_LOAD( "van-2.51",     0x1000, 0x1000, 0xdf58e1cb );
		ROM_LOAD( "van-3.52",     0x2000, 0x1000, 0x15571e24 );
		ROM_LOAD( "van4.bin",     0x3000, 0x1000, 0xf8b37ed5 );
		ROM_LOAD( "van5.bin",     0x8000, 0x1000, 0xb8c1e089 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "van-20.18",    0x0000, 0x1000, 0x60efbe66 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "van-21.19",    0x0000, 0x1000, 0x5dd53723 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "6331-1.6",     0x0000, 0x0020, 0xce1d9503 );
		ROM_LOAD( "6301-1.37",    0x0020, 0x0100, 0x4b803d9f );
	ROM_END(); }}; 
	
	static RomLoadPtr rom_vanvans = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "van-1.50",     0x0000, 0x1000, 0xcf1b2df0 );
		ROM_LOAD( "van-2.51",     0x1000, 0x1000, 0xdf58e1cb );
		ROM_LOAD( "van-3.52",     0x2000, 0x1000, 0x15571e24 );
		ROM_LOAD( "van-4.53",     0x3000, 0x1000, 0xb724cbe0 );
		ROM_LOAD( "van-5.39",     0x8000, 0x1000, 0xdb67414c );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "van-20.18",    0x0000, 0x1000, 0x60efbe66 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "van-21.19",    0x0000, 0x1000, 0x5dd53723 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "6331-1.6",     0x0000, 0x0020, 0xce1d9503 );
		ROM_LOAD( "6301-1.37",    0x0020, 0x0100, 0x4b803d9f );
	ROM_END(); }}; 
	
	static RomLoadPtr rom_dremshpr = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "red_1.50",	  0x0000, 0x1000, 0x830c6361 );
		ROM_LOAD( "red_2.51",     0x1000, 0x1000, 0xd22551cc );
		ROM_LOAD( "red_3.52",     0x2000, 0x1000, 0x0713a34a );
		ROM_LOAD( "red_4.53",     0x3000, 0x1000, 0xf38bcaaa );
		ROM_LOAD( "red_5.39",     0x8000, 0x1000, 0x6a382267 );
		ROM_LOAD( "red_6.40",     0x9000, 0x1000, 0x4cf8b121 );
		ROM_LOAD( "red_7.41",     0xa000, 0x1000, 0xbd4fc4ba );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "red-20.18",    0x0000, 0x1000, 0x2d6698dc );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "red-21.19",    0x0000, 0x1000, 0x38c9ce9b );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "6331-1.6",     0x0000, 0x0020, 0xce1d9503 );
		ROM_LOAD( "6301-1.37",    0x0020, 0x0100, 0x39d6fb5c );
	ROM_END(); }}; 
	
	static RomLoadPtr rom_alibaba = new RomLoadPtr(){ public void handler(){ 
		ROM_REGION( 0x10000, REGION_CPU1 );/* 64k for code */
		ROM_LOAD( "6e",           0x0000, 0x1000, 0x38d701aa );
		ROM_LOAD( "6f",           0x1000, 0x1000, 0x3d0e35f3 );
		ROM_LOAD( "6h",           0x2000, 0x1000, 0x823bee89 );
		ROM_LOAD( "6k",           0x3000, 0x1000, 0x474d032f );
		ROM_LOAD( "6l",           0x8000, 0x1000, 0x5ab315c1 );
		ROM_LOAD( "6m",           0xa000, 0x0800, 0x438d0357 );
	
		ROM_REGION( 0x1000, REGION_GFX1 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5e",           0x0000, 0x0800, 0x85bcb8f8 );
		ROM_LOAD( "5h",           0x0800, 0x0800, 0x38e50862 );
	
		ROM_REGION( 0x1000, REGION_GFX2 | REGIONFLAG_DISPOSE );
		ROM_LOAD( "5f",           0x0000, 0x0800, 0xb5715c86 );
		ROM_LOAD( "5k",           0x0800, 0x0800, 0x713086b3 );
	
		ROM_REGION( 0x0120, REGION_PROMS );
		ROM_LOAD( "alibaba.7f",   0x0000, 0x0020, 0x00000000 ); /* missing */
		ROM_LOAD( "alibaba.4a",   0x0020, 0x0100, 0x00000000 );
	
		ROM_REGION( 0x0200, REGION_SOUND1 );/* sound PROMs */
		ROM_LOAD( "82s126.1m",    0x0000, 0x0100, 0xa9cc86bf );
		ROM_LOAD( "82s126.3m",    0x0100, 0x0100, 0x77245b66 );/* timing - not used */
	ROM_END(); }}; 
    /*TODO*///
    /*TODO*///
    /*TODO*///
    /*TODO*///static int maketrax_special_port2_r(int offset)
    /*TODO*///{
    /*TODO*///	int pc,data;
    /*TODO*///
    /*TODO*///	pc = cpu_getpreviouspc();
    /*TODO*///
    /*TODO*///	data = input_port_2_r(offset);
    /*TODO*///
    /*TODO*///	if ((pc == 0x1973) || (pc == 0x2389)) return data | 0x40;
    /*TODO*///
    /*TODO*///	switch (offset)
    /*TODO*///	{
    /*TODO*///		case 0x01:
    /*TODO*///		case 0x04:
    /*TODO*///			data |= 0x40; break;
    /*TODO*///		case 0x05:
    /*TODO*///			data |= 0xc0; break;
    /*TODO*///		default:
    /*TODO*///			data &= 0x3f; break;
    /*TODO*///	}
    /*TODO*///
    /*TODO*///	return data;
    /*TODO*///}
    /*TODO*///
    /*TODO*///static int maketrax_special_port3_r(int offset)
    /*TODO*///{
    /*TODO*///	int pc;
    /*TODO*///
    /*TODO*///	pc = cpu_getpreviouspc();
    /*TODO*///
    /*TODO*///	if ( pc == 0x040e) return 0x20;
    /*TODO*///
    /*TODO*///	if ((pc == 0x115e) || (pc == 0x3ae2)) return 0x00;
    /*TODO*///
    /*TODO*///	switch (offset)
    /*TODO*///	{
    /*TODO*///		case 0x00:
    /*TODO*///			return 0x1f;
    /*TODO*///		case 0x09:
    /*TODO*///			return 0x30;
    /*TODO*///		case 0x0c:
    /*TODO*///			return 0x00;
    /*TODO*///		default:
    /*TODO*///			return 0x20;
    /*TODO*///	}
    /*TODO*///}
    /*TODO*///
    /*TODO*///static void maketrax_rom_decode(void)
    /*TODO*///{
    /*TODO*///	unsigned char *rom = memory_region(REGION_CPU1);
    /*TODO*///	int diff = memory_region_length(REGION_CPU1) / 2;
    /*TODO*///
    /*TODO*///
    /*TODO*///	/* patch protection using a copy of the opcodes so ROM checksum */
    /*TODO*///	/* tests will not fail */
    /*TODO*///	memory_set_opcode_base(0,rom+diff);
    /*TODO*///
    /*TODO*///	memcpy(rom+diff,rom,diff);
    /*TODO*///
    /*TODO*///	rom[0x0415 + diff] = 0xc9;
    /*TODO*///	rom[0x1978 + diff] = 0x18;
    /*TODO*///	rom[0x238e + diff] = 0xc9;
    /*TODO*///	rom[0x3ae5 + diff] = 0xe6;
    /*TODO*///	rom[0x3ae7 + diff] = 0x00;
    /*TODO*///	rom[0x3ae8 + diff] = 0xc9;
    /*TODO*///	rom[0x3aed + diff] = 0x86;
    /*TODO*///	rom[0x3aee + diff] = 0xc0;
    /*TODO*///	rom[0x3aef + diff] = 0xb0;
    /*TODO*///}
    /*TODO*///
    /*TODO*///static void init_maketrax(void)
    /*TODO*///{
    /*TODO*///	/* set up protection handlers */
    /*TODO*///	install_mem_read_handler(0, 0x5080, 0x50bf, maketrax_special_port2_r);
    /*TODO*///	install_mem_read_handler(0, 0x50c0, 0x50ff, maketrax_special_port3_r);
    /*TODO*///
    /*TODO*///	maketrax_rom_decode();
    /*TODO*///}
    public static InitDriverPtr init_ponpoko = new InitDriverPtr(){ public void handler()
    {
        int i, j;
        UBytePtr RAM= new UBytePtr();
        //UBytePtr temp = new UBytePtr();
        char temp;
        /* The gfx data is swapped wrt the other Pac-Man hardware games. */
    	/* Here we revert it to the usual format. */
    
    	/* Characters */
        RAM = memory_region(REGION_GFX1);
        for (i = 0;i < memory_region_length(REGION_GFX1);i += 0x10)
    	{
    		for (j = 0; j < 8; j++)
    		{
    			temp          = RAM.read(i+j+0x08);
                        RAM.write(i+j+0x08,RAM.read(i+j+0x00));
                        RAM.write(i+j+0x00,temp);
    		}
    	}
        /* Sprites */
    	RAM = memory_region(REGION_GFX2);
    	for (i = 0;i < memory_region_length(REGION_GFX2);i += 0x20)
    	{
    		for (j = 0; j < 8; j++)
    		{
                    temp = RAM.read(i+j+0x18);
                    RAM.write(i+j+0x18,RAM.read(i+j+0x10));
    		    RAM.write(i+j+0x10,RAM.read(i+j+0x08));
    		    RAM.write(i+j+0x08,RAM.read(i+j+0x00));
    	            RAM.write(i+j+0x00,temp);
    		}
    	}
    }};
   
    /*TODO*///static void eyes_decode(unsigned char *data)
    /*TODO*///{
    /*TODO*///	int j;
    /*TODO*///	unsigned char swapbuffer[8];
    /*TODO*///
    /*TODO*///	for (j = 0; j < 8; j++)
    /*TODO*///	{
    /*TODO*///		swapbuffer[j] = data[(j >> 2) + (j & 2) + ((j & 1) << 2)];
    /*TODO*///	}
    /*TODO*///
    /*TODO*///	for (j = 0; j < 8; j++)
    /*TODO*///	{
    /*TODO*///		char ch = swapbuffer[j];
    /*TODO*///
    /*TODO*///		data[j] = (ch & 0x80) | ((ch & 0x10) << 2) |
    /*TODO*///					 (ch & 0x20) | ((ch & 0x40) >> 2) | (ch & 0x0f);
    /*TODO*///	}
    /*TODO*///}
    /*TODO*///
    /*TODO*///static void init_eyes(void)
    /*TODO*///{
    /*TODO*///	int i;
    /*TODO*///	unsigned char *RAM;
    /*TODO*///
    /*TODO*///	/* CPU ROMs */
    /*TODO*///
    /*TODO*///	/* Data lines D3 and D5 swapped */
    /*TODO*///	RAM = memory_region(REGION_CPU1);
    /*TODO*///	for (i = 0; i < 0x4000; i++)
    /*TODO*///	{
    /*TODO*///		RAM[i] =  (RAM[i] & 0xc0) | ((RAM[i] & 0x08) << 2) |
    /*TODO*///				  (RAM[i] & 0x10) | ((RAM[i] & 0x20) >> 2) | (RAM[i] & 0x07);
    /*TODO*///	}
    /*TODO*///
    /*TODO*///
    /*TODO*///	/* Graphics ROMs */
    /*TODO*///
    /*TODO*///	/* Data lines D4 and D6 and address lines A0 and A2 are swapped */
    /*TODO*///	RAM = memory_region(REGION_GFX1);
    /*TODO*///	for (i = 0;i < memory_region_length(REGION_GFX1);i += 8)
    /*TODO*///		eyes_decode(&RAM[i]);
    /*TODO*///	RAM = memory_region(REGION_GFX2);
    /*TODO*///	for (i = 0;i < memory_region_length(REGION_GFX2);i += 8)
    /*TODO*///		eyes_decode(&RAM[i]);
    /*TODO*///}
    /*TODO*///
    /*TODO*///
    /*TODO*///static void init_pacplus(void)
    /*TODO*///{
    /*TODO*///	pacplus_decode();
    /*TODO*///}
    /*TODO*///
                                                                                          /*          rom       parent          machine                   inp                  init */
    public static GameDriver driver_pacman   = new GameDriver("1980","pacman"  , "pacman.java", rom_pacman,   null         ,   machine_driver_pacman,   input_ports_pacman,   null, ROT90, "Namco", "PuckMan (Japan set 1)" );
    public static GameDriver driver_pacmanjp = new GameDriver("1980","pacmanjp", "pacman.java", rom_pacmanjp, driver_pacman,   machine_driver_pacman,   input_ports_pacman,   null, ROT90, "Namco", "PuckMan (Japan set 2)" );
    public static GameDriver driver_pacmanm  = new GameDriver("1980","pacmanm" , "pacman.java", rom_pacmanm,  driver_pacman,   machine_driver_pacman,   input_ports_pacman,   null, ROT90, "[Namco] (Midway license)", "Pac-Man (Midway)" );
    public static GameDriver driver_npacmod  = new GameDriver("1981","npacmod" , "pacman.java", rom_npacmod,  driver_pacman,   machine_driver_pacman,   input_ports_pacman,   null, ROT90, "Namco", "PuckMan (harder?)" );
    public static GameDriver driver_pacmod   = new GameDriver("1981","pacmod"  , "pacman.java", rom_pacmod,   driver_pacman,   machine_driver_pacman,   input_ports_pacman,   null, ROT90, "[Namco] (Midway license)", "Pac-Man (Midway, harder)" );
    public static GameDriver driver_hangly   = new GameDriver("1981","hangly"  , "pacman.java", rom_hangly,   driver_pacman,   machine_driver_pacman,   input_ports_pacman,   null, ROT90, "hack", "Hangly-Man (set 1)" );
    public static GameDriver driver_hangly2  = new GameDriver("1981","hangly2" , "pacman.java", rom_hangly2,  driver_pacman,   machine_driver_pacman,   input_ports_pacman,   null, ROT90, "hack", "Hangly-Man (set 2)" );
    public static GameDriver driver_puckman  = new GameDriver("1980","puckman" , "pacman.java", rom_puckman,  driver_pacman,   machine_driver_pacman,   input_ports_pacman,   null, ROT90, "hack", "New Puck-X" );
    public static GameDriver driver_pacheart = new GameDriver("1981","pacheart", "pacman.java", rom_pacheart, driver_pacman,   machine_driver_pacman,   input_ports_pacman,   null, ROT90, "hack", "Pac-Man (Hearts)" );
    public static GameDriver driver_piranha  = new GameDriver("1981","piranha" , "pacman.java", rom_piranha,  driver_pacman,   machine_driver_pacman,   input_ports_mspacman, null, ROT90, "hack", "Piranha" );
    /*TODO*///GAME( 1982, pacplus,  0,        pacman,   pacman,   pacplus,  ROT90,  "[Namco] (Midway license)", "Pac-Man Plus" )
    public static GameDriver driver_mspacman = new GameDriver("1981", "mspacman","pacman.java", rom_mspacman, null,            machine_driver_pacman,   input_ports_mspacman, null, ROT90,  "bootleg", "Ms. Pac-Man" );
    public static GameDriver driver_mspacatk = new GameDriver("1981", "mspacatk","pacman.java", rom_mspacatk, driver_mspacman, machine_driver_pacman,   input_ports_mspacman, null, ROT90,  "hack", "Ms. Pac-Man Plus" );
    public static GameDriver driver_pacgal   = new GameDriver("1981", "pacgal"  ,"pacman.java", rom_pacgal,   driver_mspacman, machine_driver_pacman,   input_ports_mspacman, null, ROT90,  "hack", "Pac-Gal" );
    /*TODO*///GAME( 1981, crush,    0,        pacman,   maketrax, maketrax, ROT90,  "Kural Samno Electric", "Crush Roller (Kural Samno)" )
    /*TODO*///GAME( 1981, crush2,   crush,    pacman,   maketrax, 0,        ROT90,  "Kural Esco Electric", "Crush Roller (Kural Esco - bootleg?)" )
    /*TODO*///GAME( 1981, crush3,   crush,    pacman,   maketrax, eyes,     ROT90,  "Kural Electric", "Crush Roller (Kural - bootleg?)" )
    /*TODO*///GAME( 1981, maketrax, crush,    pacman,   maketrax, maketrax, ROT270, "[Kural] (Williams license)", "Make Trax" )
    /*TODO*///GAME( 1981, mbrush,   crush,    pacman,   mbrush,   0,        ROT90,  "bootleg", "Magic Brush" )
    /*TODO*///GAME( 1981, paintrlr, crush,    pacman,   paintrlr, 0,        ROT90,  "bootleg", "Paint Roller" )
    public static GameDriver driver_ponpoko  = new GameDriver("1982","ponpoko", "pacman.java", rom_ponpoko,  null,            machine_driver_pacman,   input_ports_ponpoko,  init_ponpoko,  ROT0,   "Sigma Ent. Inc.", "Ponpoko" );
    public static GameDriver driver_ponpokov = new GameDriver("1982","ponpokov","pacman.java", rom_ponpokov, driver_ponpoko,  machine_driver_pacman,   input_ports_ponpoko,  init_ponpoko,  ROT0,   "Sigma Ent. Inc. (Venture Line license)", "Ponpoko (Venture Line)" );
    /*TODO*///GAME( 1982, eyes,     0,        pacman,   eyes,     eyes,     ROT90,  "Digitrex Techstar (Rock-ola license)", "Eyes (Digitrex Techstar)" )
    /*TODO*///GAME( 1982, eyes2,    eyes,     pacman,   eyes,     eyes,     ROT90,  "Techstar Inc. (Rock-ola license)", "Eyes (Techstar Inc.)" )
    /*TODO*///GAME( 1983, mrtnt,    0,        pacman,   mrtnt,    eyes,     ROT90,  "Telko", "Mr. TNT" )
    /*TODO*///GAME( 1985, lizwiz,   0,        pacman,   lizwiz,   0,        ROT90,  "Techstar (Sunn license)", "Lizard Wizard" )
    /*TODO*///GAME( 1983, theglob,  0,        theglob,  theglob,  0,        ROT90,  "Epos Corporation", "The Glob" )
    /*TODO*///GAME( 1984, beastf,   theglob,  theglob,  theglob,  0,        ROT90,  "Epos Corporation", "Beastie Feastie" )
    /*TODO*///GAMEX(????, jumpshot, 0,        pacman,   pacman,   0,        ROT90,  "<unknown>", "Jump Shot", GAME_NOT_WORKING )	/* not working, encrypted */
    /*TODO*///GAME( 1982, dremshpr, 0,        dremshpr, dremshpr, 0,        ROT270, "Sanritsu", "Dream Shopper" )
    /*TODO*///GAME( 1983, vanvan,   0,        vanvan,   vanvan,   0,        ROT270, "Karateco", "Van Van Car" )
    /*TODO*///GAME( 1983, vanvans,  vanvan,   vanvan,   vanvans,  0,        ROT270, "Sanritsu", "Van Van Car (Sanritsu)" )
    /*TODO*///GAME( 1982, alibaba,  0,        alibaba,  alibaba,  0,        ROT90,  "Sega", "Ali Baba and 40 Thieves" )
    /*TODO*///    
}
