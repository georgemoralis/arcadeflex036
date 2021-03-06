/***************************************************************************

							-= Back Street Soccer =-

					driver by	Luca Elia (eliavit@unina.it)


	This game has only sprites, of a peculiar type:

	there is a region of memory where 32 pages of 32x32 tile codes can
	be written like a tilemap made of 32 pages of 256x256 pixels.

	Sprites are rectangular regions of *tiles* fetched from there and
	sent to the	screen.


Offset:			Value:

0x00000 +

	0000.w		fedc ---- ---- ----		Source Page (Low Bits)
				---- ba98 ---- ----		Source Column (Bit 8 = Sprite Flip X)
				---- ---- 7654 3210		Screen Y Position

	0002.w		fedc ---- ---- ----		Tile Bank
				---- ba-- ---- ----
				---- --9- ---- ----		Source Page (High Bit)
				---- ---8 7654 3210		Screen X Position

0x10000 +

	0000.w		-

	0002.w		fedc ba98 ---- ----
				---- ---- 76-- ----		Sprite Size:
										00 . 16 x 16	(2x4  tiles)
										01 . 32 x 32	(4x4  tiles)
										10 . 16 x 256	(2x32 tiles)
										11 . 32 x 256	(4x32 tiles)
				---- ---- --54 ----
				---- ---- ---- 3210		Source Row


***************************************************************************/

/*
 * ported to v0.37b7
 * using automatic conversion tool v0.01
 */ 
package gr.codebb.arcadeflex.v037b7.vidhrdw;

import static gr.codebb.arcadeflex.common.PtrLib.*;
import static gr.codebb.arcadeflex.common.libc.cstring.memset;
import static gr.codebb.arcadeflex.v036.mame.commonH.*;
import static gr.codebb.arcadeflex.v036.mame.drawgfx.*;
import static gr.codebb.arcadeflex.v036.mame.driverH.*;
import static gr.codebb.arcadeflex.v036.mame.mame.Machine;
import gr.codebb.arcadeflex.v036.mame.osdependH.osd_bitmap;
import static gr.codebb.arcadeflex.v036.mame.tilemapC.*;
import static gr.codebb.arcadeflex.v036.mame.tilemapH.*;
import static gr.codebb.arcadeflex.v037b7.mame.palette.*;
import static gr.codebb.arcadeflex.v037b7.mame.paletteH.*;
import static gr.codebb.arcadeflex.v036.mame.memoryH.*;
import static gr.codebb.arcadeflex.v037b7.vidhrdw.generic.*;
import static gr.codebb.arcadeflex.v037b7.mame.drawgfxH.*;
import static gr.codebb.arcadeflex.v036.mame.common.bitmap_alloc;
import static gr.codebb.arcadeflex.v036.mame.sndintrf.*;
import static gr.codebb.arcadeflex.v037b7.mame.cpuintrf.*;
import static gr.codebb.arcadeflex.v036.platform.osdepend.logerror;
import static gr.codebb.arcadeflex.v036.platform.video.osd_clearbitmap;

public class bssoccer
{
	
	
	/* Variables and functions only used here */
	
	/* Variables that driver has access to */
	
	public static UBytePtr bssoccer_vregs=new UBytePtr();
	static int flipscreen;
	
	/* Variables and functions defined in driver */
	
	
	public static WriteHandlerPtr bssoccer_vregs_w = new WriteHandlerPtr() {public void handler(int offset, int data)
	{
/*TODO*///	#ifdef MAME_DEBUG
/*TODO*///	#if 0
/*TODO*///		char buf[80];
/*TODO*///		COMBINE_WORD_MEM(&bssoccer_vregs[offset], data);
/*TODO*///		sprintf(buf,"%04X %04X %04X ", READ_WORD(&bssoccer_vregs[0x0]), READ_WORD(&bssoccer_vregs[0x2]), READ_WORD(&bssoccer_vregs[0x4]) );
/*TODO*///		usrintf_showmessage(buf);
/*TODO*///	#endif
/*TODO*///	#endif
	
		switch (offset)
		{
			case 0x00:	soundlatch_w.handler(0,data & 0xff);	break;
			case 0x02:	flipscreen = data & 1;			break;	// other bits are used!
			case 0x04:	break;	// bits 4&0 used!
			case 0x06:	break;	// lev 1 ack
			case 0x08:	break;	// lev 2 ack
			default:	logerror("CPU#0 PC %06X - Written vreg %02X <- %02X\n", cpu_get_pc(), offset, data);
		}
	} };
	
	
	static void bssoccer_draw_sprites(osd_bitmap bitmap)
	{
		int offs;
	
		int max_x	=	Machine.drv.screen_width  - 8;
		int max_y	=	Machine.drv.screen_height - 8;
	
		for ( offs = 0xfc00; offs < 0x10000 ; offs += 4 )
		{
			int	srcpg, srcx,srcy, dimx,dimy;
			int tile_x, tile_xinc, tile_xstart;
			int tile_y, tile_yinc;
			int dx, dy;
			int flipx, y0;
	
			int y		=	spriteram.READ_WORD( offs + 0 );
			int x		=	spriteram.READ_WORD( offs + 2 );
			int dim		=	spriteram.READ_WORD( offs + 0 + 0x10000 );
	
			int bank	=	(x >> 12) & 0xf;
	
			srcpg	=	((y & 0xf000) >> 12) + ((x & 0x0200) >> 5);	// src page
			srcx	=	((y   >> 8) & 0xf) * 2;						// src col
			srcy	=	((dim >> 0) & 0xf) * 2;						// src row
	
			switch ( (dim >> 4) & 0xf )
			{
				case 0x0:	dimx = 2;	dimy =  2;	y0 = 0x100;	break;
				case 0x4:	dimx = 4;	dimy =  4;	y0 = 0x100;	break;
				case 0x8:	dimx = 2;	dimy = 32;	y0 = 0x130;	break;
				case 0xc:	dimx = 4;	dimy = 32;	y0 = 0x120;	break;
				default:	dimx = 0;	dimy = 0;	y0 = 0;
			}
	
			if (dimx==4)	{ flipx = srcx & 2;		srcx &= ~2; }
			else			{ flipx = 0; }
	
			x = (x & 0xff) - (x & 0x100);
			y = (y0 - (y & 0xff) - dimy*8 ) & 0xff;
	
			if (flipx != 0)	{ tile_xstart = dimx-1;	tile_xinc = -1; }
			else		{ tile_xstart = 0;		tile_xinc = +1; }
	
			tile_y = 0;		tile_yinc = +1;
	
			for (dy = 0; dy < dimy * 8; dy += 8)
			{
				tile_x = tile_xstart;
	
				for (dx = 0; dx < dimx * 8; dx += 8)
				{
					int addr	=	( (srcpg * 0x20 * 0x20) +
									  ((srcx + tile_x) & 0x1f) * 0x20 +
	  								  ((srcy + tile_y) & 0x1f) ) * 2;
	
					int tile	=	spriteram.READ_WORD( addr);
					int attr	=	spriteram.READ_WORD( addr + 0x10000);
	
					int sx		=	x + dx;
					int sy		=	(y + dy) & 0xff;
	
					int tile_flipx	=	tile & 0x4000;
					int tile_flipy	=	tile & 0x8000;
	
					if (flipx != 0)	tile_flipx = tile_flipx!=0?0:1;
	
					if (flipscreen != 0)
					{
						sx = max_x - sx;
						sy = max_y - sy;
						tile_flipx = tile_flipx!=0?0:1;
						tile_flipy = tile_flipy!=0?0:1;
					}
	
					drawgfx(bitmap,Machine.gfx[0],
							(tile & 0x3fff) + bank*0x4000,
							attr,
							tile_flipx, tile_flipy,
							sx,sy,
							Machine.visible_area,TRANSPARENCY_PEN,15);
	
					tile_x += tile_xinc;
				}
	
				tile_y += tile_yinc;
			}
	
		}
	
	}
	
	
	static void bssoccer_mark_sprite_colors()
	{
		int offs,i,col;
                int[] colmask=new int[0x100];
		int count = 0;
	
		int[] pen_usage         =	Machine.gfx[0].pen_usage;
		int total_elements	=	Machine.gfx[0].total_elements;
		int color_codes_start	=	Machine.drv.gfxdecodeinfo[0].color_codes_start;
		int total_color_codes	=	Machine.drv.gfxdecodeinfo[0].total_color_codes;
	
		int xmin = Machine.visible_area.min_x;
		int xmax = Machine.visible_area.max_x;
		int ymin = Machine.visible_area.min_y;
		int ymax = Machine.visible_area.max_y;
	
		memset(colmask, 0, colmask.length);
	
		for ( offs = 0xfc00; offs < 0x10000 ; offs += 4 )
		{
			int	srcpg, srcx,srcy, dimx,dimy;
			int tile_x, tile_xinc, tile_xstart;
			int tile_y, tile_yinc;
			int dx, dy;
			int flipx, y0;
	
			int y		=	spriteram.READ_WORD( offs + 0 );
			int x		=	spriteram.READ_WORD( offs + 2 );
			int dim		=	spriteram.READ_WORD( offs + 0 + 0x10000 );
	
			int bank	=	(x >> 12) & 0xf;
	
			srcpg	=	((y & 0xf000) >> 12) + ((x & 0x0200) >> 5);	// src page
			srcx	=	((y   >> 8) & 0xf) * 2;						// src col
			srcy	=	((dim >> 0) & 0xf) * 2;						// src row
	
			switch ( (dim >> 4) & 0xf )
			{
				case 0x0:	dimx = 2;	dimy =  2;	y0 = 0x100;	break;
				case 0x4:	dimx = 4;	dimy =  4;	y0 = 0x100;	break;
				case 0x8:	dimx = 2;	dimy = 32;	y0 = 0x130;	break;
				case 0xc:	dimx = 4;	dimy = 32;	y0 = 0x120;	break;
				default:	dimx = 0;	dimy = 0;	y0 = 0;
			}
	
			if (dimx==4)	{ flipx = srcx & 2;		srcx &= ~2; }
			else			{ flipx = 0; }
	
			x = (x & 0xff) - (x & 0x100);
			y = (y0 - (y & 0xff) - dimy*8 ) & 0xff;
	
			if (flipx != 0)	{ tile_xstart = dimx-1;	tile_xinc = -1; }
			else		{ tile_xstart = 0;		tile_xinc = +1; }
	
			tile_y = 0;		tile_yinc = +1;
	
			/* Mark the pens used by the visible portion of this sprite */
	
			for (dy = 0; dy < dimy * 8; dy += 8)
			{
				tile_x = tile_xstart;
	
				for (dx = 0; dx < dimx * 8; dx += 8)
				{
					int addr	=	( (srcpg * 0x20 * 0x20) +
									  ((srcx + tile_x) & 0x1f) * 0x20 +
	  								  ((srcy + tile_y) & 0x1f) ) * 2;
	
					int tile	=	spriteram.READ_WORD( addr );
					int attr	=	spriteram.READ_WORD( addr + 0x10000 );
	
					int color	=	attr % total_color_codes;
	
					int sx		=	x + dx;
					int sy		=	(y + dy) & 0xff;
	
					tile = (tile & 0x3fff) + bank*0x4000;
	
					if (((sx+7) >= xmin) && (sx <= xmax) &&
						((sy+7) >= ymin) && (sy <= ymax))
						colmask[color] |= pen_usage[tile % total_elements];
	
					tile_x += tile_xinc;
				}
	
				tile_y += tile_yinc;
			}
	
		}
	
		for (col = 0; col < total_color_codes; col++)
		 for (i = 0; i < 15; i++)	// pen 15 is transparent
		  if ((colmask[col] & (1 << i)) != 0)
		  { palette_used_colors.write(16 * col + i + color_codes_start, PALETTE_COLOR_USED);
		    count++;	}
	
/*TODO*///	#if 0
/*TODO*///	{
/*TODO*///		char buf[80];
/*TODO*///		sprintf(buf,"%d",count);
/*TODO*///		usrintf_showmessage(buf);
/*TODO*///	}
/*TODO*///	#endif
	
	}
	
	
	
	
	/***************************************************************************
	
	
									Screen Drawing
	
	
	***************************************************************************/
	
	public static VhUpdatePtr bssoccer_vh_screenrefresh = new VhUpdatePtr() { public void handler(osd_bitmap bitmap,int full_refresh) 
	{
	
		/* Mark the sprites' colors */
	
		palette_init_used_colors();
		bssoccer_mark_sprite_colors();
		palette_recalc();
	
		/* Draw the sprites */
	
		osd_clearbitmap(Machine.scrbitmap);	/* I believe it's black */
		bssoccer_draw_sprites(bitmap);
	
	} };
}
