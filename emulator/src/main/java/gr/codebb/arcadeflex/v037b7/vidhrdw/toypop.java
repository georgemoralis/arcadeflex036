/***************************************************************************

  vidhrdw.c

  Functions to emulate the video hardware of the machine.

***************************************************************************/

/*
 * ported to v0.37b7
 * using automatic conversion tool v0.01
 */ 
package gr.codebb.arcadeflex.v037b7.vidhrdw;

import static gr.codebb.arcadeflex.common.PtrLib.*;
import static gr.codebb.arcadeflex.v036.mame.driverH.*;
import static gr.codebb.arcadeflex.v036.mame.common.*;
import static gr.codebb.arcadeflex.v036.mame.commonH.*;
import static gr.codebb.arcadeflex.v036.mame.drawgfx.*;
import static gr.codebb.arcadeflex.v037b7.mame.drawgfxH.*;
import static gr.codebb.arcadeflex.v036.mame.mame.Machine;
import static gr.codebb.arcadeflex.v037b7.mame.memoryH.*;
import static gr.codebb.arcadeflex.v036.mame.osdependH.*;
import static gr.codebb.arcadeflex.v036.mame.memoryH.COMBINE_WORD;
import static gr.codebb.arcadeflex.v037b7.vidhrdw.generic.*;
import static gr.codebb.arcadeflex.common.libc.cstring.memset;
import static gr.codebb.arcadeflex.v037b7.machine.toypop.toypop_sharedram_2;

public class toypop
{	
	static osd_bitmap bgbitmap;
	public static UBytePtr dirtybackground = new UBytePtr();
        public static UBytePtr bg_image = new UBytePtr();
	static int flipscreen;
	
	/***************************************************************************
	
	  Convert the color PROMs into a more useable format.
	
	  toypop has three 256x4 palette PROM and two 256x8 color lookup table PROMs
	  (one for characters, one for sprites).
	
	
	***************************************************************************/
	public static VhConvertColorPromPtr toypop_vh_convert_color_prom = new VhConvertColorPromPtr() { public void handler(char []palette, char []colortable, UBytePtr color_prom) 
	{
		int i;
	
		for (i = 0;i < 256;i++)
		{
			int bit0,bit1,bit2,bit3;
	
			// red component
			bit0 = (color_prom.read(i)>> 0) & 0x01;
			bit1 = (color_prom.read(i)>> 1) & 0x01;
			bit2 = (color_prom.read(i)>> 2) & 0x01;
			bit3 = (color_prom.read(i)>> 3) & 0x01;
			palette[3*i] = (char) (0x0e * bit0 + 0x1f * bit1 + 0x43 * bit2 + 0x8f * bit3);
			// green component
			bit0 = (color_prom.read(i+0x100)>> 0) & 0x01;
			bit1 = (color_prom.read(i+0x100)>> 1) & 0x01;
			bit2 = (color_prom.read(i+0x100)>> 2) & 0x01;
			bit3 = (color_prom.read(i+0x100)>> 3) & 0x01;
			palette[3*i + 1] = (char) (0x0e * bit0 + 0x1f * bit1 + 0x43 * bit2 + 0x8f * bit3);
			// blue component
			bit0 = (color_prom.read(i+0x200)>> 0) & 0x01;
			bit1 = (color_prom.read(i+0x200)>> 1) & 0x01;
			bit2 = (color_prom.read(i+0x200)>> 2) & 0x01;
			bit3 = (color_prom.read(i+0x200)>> 3) & 0x01;
			palette[3*i + 2] = (char) (0x0e * bit0 + 0x1f * bit1 + 0x43 * bit2 + 0x8f * bit3);
		}
	
		// characters
		for (i = 0;i < 256;i++)
			colortable[i] = (char) (color_prom.read(i + 0x300)| 0xf0);
	
		// sprites
		for (i = 256;i < Machine.drv.color_table_len;i++)
			colortable[i] = color_prom.read(i + 0x400);	// 0x500-5ff
	} };
	
	public static VhStartPtr toypop_vh_start = new VhStartPtr() { public int handler() 
	{
		if ((dirtybuffer = new char[videoram_size[0]]) == null)
			return 1;
		memset(dirtybuffer, 1, videoram_size[0]);
	
		if ((dirtybackground = new UBytePtr(videoram_size[0])) == null) {
			dirtybuffer = null;
			return 1;
		}
		memset(dirtybackground, 1, videoram_size[0]);
	
		if ((tmpbitmap = bitmap_alloc(36*8,28*8)) == null) {
			dirtybuffer=null;
			dirtybackground=null;
			return 1;
		}
		if ((bgbitmap = bitmap_alloc(36*8,28*8)) == null) {
			bitmap_free(tmpbitmap);
			dirtybuffer=null;
			dirtybackground=null;
			return 1;
		}
	
		return 0;
	} };
	
	public static VhStopPtr toypop_vh_stop = new VhStopPtr() { public void handler() 
	{
		dirtybuffer=null;
		dirtybackground=null;
		bitmap_free(tmpbitmap);
		bitmap_free(bgbitmap);
	
		dirtybuffer=null;
		dirtybackground=null;
		tmpbitmap=null;
		bgbitmap=null;
	} };
	
	public static ReadHandlerPtr toypop_background_r  = new ReadHandlerPtr() { public int handler(int offset)
	{
		return bg_image.READ_WORD(offset);
	} };
	
	public static WriteHandlerPtr toypop_background_w = new WriteHandlerPtr() {public void handler(int offset, int data)
	{
		int oldword = bg_image.READ_WORD(offset);
		int newword = COMBINE_WORD(oldword,data);
	
		if (oldword != newword) {
			bg_image.WRITE_WORD(offset,newword);
			dirtybackground.write(((offset % 288) >> 3) + ((offset / 2304) * 36), 1);
		}
	} };
	
	public static WriteHandlerPtr toypop_flipscreen_w = new WriteHandlerPtr() {public void handler(int offset, int data)
	{
		flipscreen = offset ^ 2;
	} };
	
	static void toypop_draw_sprite(osd_bitmap dest, int code, int color,
		int flipx,int flipy,int sx,int sy)
	{
		drawgfx(dest,Machine.gfx[1],code,color,flipx,flipy,sx,sy,Machine.visible_area,
			TRANSPARENCY_PEN,0);
	}
	
	public static VhUpdatePtr toypop_vh_screenrefresh = new VhUpdatePtr() { public void handler(osd_bitmap bitmap,int full_refresh) 
	{
		int offs;
		rectangle box=new rectangle();
	
		/* check if the background image has been modified */
		/* since last time and update it accordingly. */
		// (The background image changes only few times, so this loop is very fast)
		for (offs = 0;offs < 1008;offs++) {
			if (dirtybackground.read(offs) != 0) {
				int bx, by, palette_index;
	
				dirtybackground.write(offs, 0);
				box.min_x = (offs % 36) << 3;
				box.max_x = box.min_x + 7;
				box.min_y = (offs / 36) << 3;
				box.max_y = box.min_y + 7;
				// copy the background image from memory to bitmap
				palette_index = (toypop_sharedram_2.read(0x102)!=0 ? 0xe0 : 0x60);
				for (by = box.min_y;by <= box.max_y;by++)
					for (bx = box.min_x;bx <= box.max_x;bx++)
/*TODO*///	#ifdef LSB_FIRST
/*TODO*///						bgbitmap.line[by][bx] = Machine.pens[bg_image[(bx + by * 288) ^ 1] | palette_index];
/*TODO*///	#else
						bgbitmap.line[by].write(bx, Machine.pens[bg_image.read(bx + by * 288)] | palette_index);
/*TODO*///	#endif
				// mark the matching character as dirty
				// so the next loop will draw everything right
				switch ((offs >> 1) % 18) {
					case 0:	// the 2 columns at left
						dirtybuffer[(((offs % 36) + 30) << 5) + (offs / 36 + 2)] = 1;
						break;
					case 17:	// the 2 columns at right
						dirtybuffer[(((offs % 36) - 34) << 5) + (offs / 36 + 2)] = 1;
						break;
					default:	// the rest of the screen
						dirtybuffer[(offs % 36) - 2 + ((offs / 36 + 2) << 5)] = 1;
				}
			}
		}
	
		/* for every character in the Video RAM, check if it has been modified */
		/* since last time and update it accordingly. */
		for (offs = videoram_size[0] - 1;offs >= 0;offs--) {
			if (dirtybuffer[offs] != 0) {
				int sx,sy;
	
				dirtybuffer[offs] = 0;
				if (offs >= videoram_size[0] - 64) {
					// Draw the 2 columns at left
					sx = ((offs >> 5) - 30) << 3;
					sy = ((offs & 0x1f) - 2) << 3;
				} else if (offs < 64) {
					// Draw the 2 columns at right
					sx = ((offs >> 5) + 34) << 3;
					sy = ((offs & 0x1f) - 2) << 3;
				} else {
					// draw the rest of the screen
					sx = ((offs & 0x1f) + 2) << 3;
					sy = ((offs >> 5) - 2) << 3;
				}
				// draw the background
				box.min_x = sx;
				box.max_x = sx+7;
				box.min_y = sy;
				box.max_y = sy+7;
				copybitmap(tmpbitmap,bgbitmap,0,0,0,0,box,TRANSPARENCY_NONE,0);
				// draw the character
				drawgfx(tmpbitmap,Machine.gfx[0],
						videoram.read(offs),
						colorram.read(offs),
						0,0,sx,sy,
						null,TRANSPARENCY_PEN,0);
			}
		}
	
		/* copy the temporary bitmap to the screen */
		copybitmap(bitmap,tmpbitmap,flipscreen,flipscreen,0,0,Machine.visible_area,TRANSPARENCY_NONE,0);
	
		/* Draw the sprites. */
		for (offs = 0;offs < spriteram_size[0];offs += 2) {
			/* is it on? */
			if ((spriteram_2.read(offs)) != 0xe9) {
				int sprite = spriteram.read(offs);
				int color = spriteram.read(offs+1);
				int x = 343 - spriteram_2.read(offs+1)- 0x100 * (spriteram_3.read(offs+1)& 1);
				int y = spriteram_2.read(offs)- 9;
				int flipx = spriteram_3.read(offs)& 1;
				int flipy = spriteram_3.read(offs)& 2;
	
				if (flipscreen != 0) {
					flipx = flipx!=0?0:1;
					flipy = flipy!=0?0:1;
				}
	
				switch (spriteram_3.read(offs)& 0x0c)
				{
					case 0:		/* normal size */
						toypop_draw_sprite(bitmap,sprite,color,flipx,flipy,x,y);
						break;
	
					case 4:		/* 2x vertical */
						sprite &= ~1;
						if (flipy==0)
						{
							toypop_draw_sprite(bitmap,sprite,color,flipx,flipy,x,y);
							toypop_draw_sprite(bitmap,1+sprite,color,flipx,flipy,x,16+y);
						}
						else
						{
							toypop_draw_sprite(bitmap,sprite,color,flipx,flipy,x,16+y);
							toypop_draw_sprite(bitmap,1+sprite,color,flipx,flipy,x,y);
						}
						break;
	
					case 8:		/* 2x horizontal */
						sprite &= ~2;
						if (flipx==0)
						{
							toypop_draw_sprite(bitmap,2+sprite,color,flipx,flipy,x,y);
							toypop_draw_sprite(bitmap,sprite,color,flipx,flipy,16+x,y);
						}
						else
						{
							toypop_draw_sprite(bitmap,sprite,color,flipx,flipy,x,y);
							toypop_draw_sprite(bitmap,2+sprite,color,flipx,flipy,16+x,y);
						}
						break;
	
					case 12:		/* 2x both ways */
						sprite &= ~3;
						if (flipy==0 && flipx==0)
						{
							toypop_draw_sprite(bitmap,2+sprite,color,flipx,flipy,x-16,y+16);
							toypop_draw_sprite(bitmap,3+sprite,color,flipx,flipy,x,16+y);
							toypop_draw_sprite(bitmap,sprite,color,flipx,flipy,x-16,y);
							toypop_draw_sprite(bitmap,1+sprite,color,flipx,flipy,x,y);
						}
						else if (flipy!=0 && flipx!=0)
						{
							toypop_draw_sprite(bitmap,1+sprite,color,flipx,flipy,x-16,y+16);
							toypop_draw_sprite(bitmap,sprite,color,flipx,flipy,x,16+y);
							toypop_draw_sprite(bitmap,3+sprite,color,flipx,flipy,x-16,y);
							toypop_draw_sprite(bitmap,2+sprite,color,flipx,flipy,x,y);
						}
						else if (flipx != 0)
						{
							toypop_draw_sprite(bitmap,3+sprite,color,flipx,flipy,x-16,y+16);
							toypop_draw_sprite(bitmap,2+sprite,color,flipx,flipy,x,16+y);
							toypop_draw_sprite(bitmap,1+sprite,color,flipx,flipy,x-16,y);
							toypop_draw_sprite(bitmap,sprite,color,flipx,flipy,x,y);
						}
						else /* flipy */
						{
							toypop_draw_sprite(bitmap,sprite,color,flipx,flipy,x-16,y+16);
							toypop_draw_sprite(bitmap,1+sprite,color,flipx,flipy,x,16+y);
							toypop_draw_sprite(bitmap,2+sprite,color,flipx,flipy,x-16,y);
							toypop_draw_sprite(bitmap,3+sprite,color,flipx,flipy,x,y);
						}
						break;
				}
			}
		}
	} };
}
