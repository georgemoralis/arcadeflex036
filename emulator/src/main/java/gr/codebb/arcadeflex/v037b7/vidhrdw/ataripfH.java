package gr.codebb.arcadeflex.v037b7.vidhrdw;

public class ataripfH {
    /*##########################################################################

            ataripf.h

            Common playfield management functions for Atari raster games.

    ##########################################################################*/

/*TODO*///#ifndef __ATARIPF__
/*TODO*///#define __ATARIPF__

    /*##########################################################################
            CONSTANTS
    ##########################################################################*/

    /* maximum number of playfields */
    public static final int ATARIPF_MAX			= 2;

/*TODO*////* overrendering constants */
/*TODO*///#define OVERRENDER_BEGIN	0
/*TODO*///#define OVERRENDER_QUERY	1
/*TODO*///#define OVERRENDER_FINISH	2
/*TODO*///
/*TODO*////* return results for OVERRENDER_BEGIN case */
/*TODO*///#define OVERRENDER_NONE		0
/*TODO*///#define OVERRENDER_SOME		1
/*TODO*///#define OVERRENDER_ALL		2
/*TODO*///
/*TODO*////* return results for OVERRENDER_QUERY case */
/*TODO*///#define OVERRENDER_NO		0
/*TODO*///#define OVERRENDER_YES		1
/*TODO*///
/*TODO*////* latch masks */
/*TODO*///#define LATCHMASK_NONE		0x0000
/*TODO*///#define LATCHMASK_MSB		0xff00
/*TODO*///#define LATCHMASK_LSB		0x00ff

    /* base granularity for all playfield gfx */
    public static final int ATARIPF_BASE_GRANULARITY_SHIFT	= 3;
    public static final int ATARIPF_BASE_GRANULARITY		= (1 << ATARIPF_BASE_GRANULARITY_SHIFT);



/*TODO*////*##########################################################################
/*TODO*///	TYPES & STRUCTURES
/*TODO*///##########################################################################*/
/*TODO*///
/*TODO*////* description of the playfield */
/*TODO*///struct ataripf_desc
/*TODO*///{
/*TODO*///	UINT8				gfxindex;			/* index to which gfx system */
/*TODO*///	UINT8				cols, rows;			/* size of the playfield in tiles (x,y) */
/*TODO*///	UINT8				xmult, ymult;		/* tile_index = y * ymult + x * xmult (xmult,ymult) */
/*TODO*///
/*TODO*///	UINT16				palettebase;		/* index of palette base */
/*TODO*///	UINT16				maxcolors;			/* maximum number of colors */
/*TODO*///	UINT8				shadowxor;			/* color XOR for shadow effect (if any) */
/*TODO*///	UINT16				latchmask;			/* latch mask */
/*TODO*///	UINT32				transpens;			/* transparent pen mask */
/*TODO*///
/*TODO*///	UINT32				tilemask;			/* tile data index mask */
/*TODO*///	UINT32				colormask;			/* tile data color mask */
/*TODO*///	UINT32				hflipmask;			/* tile data hflip mask */
/*TODO*///	UINT32				vflipmask;			/* tile data hflip mask */
/*TODO*///	UINT32				prioritymask;		/* tile data priority mask */
/*TODO*///};


    /* description of pen usage for up to 256 pens */
    public static final int ATARIPF_USAGE_WORDS		= 8;			/* 8*32 bits = 256 bits total */
    public static class ataripf_usage
    {
            int[] bits = new int[ATARIPF_USAGE_WORDS];
    };


/*TODO*////* data used for overrendering */
/*TODO*///struct ataripf_overrender_data
/*TODO*///{
/*TODO*///	/* these are passed in to ataripf_overrender */
/*TODO*///	struct osd_bitmap *	bitmap;				/* bitmap we're drawing to */
/*TODO*///	struct rectangle 	clip;				/* clip region to overrender with */
/*TODO*///	UINT32				mousage;			/* motion object pen usage */
/*TODO*///	UINT32				mocolor;			/* motion object color */
/*TODO*///	UINT32				mopriority;			/* motion object priority */
/*TODO*///
/*TODO*///	/* these are filled in for the callback's usage */
/*TODO*///	struct ataripf_usage *pfusage;			/* playfield tile pen usage */
/*TODO*///	UINT32				pfcolor;			/* playfield tile color */
/*TODO*///	UINT32				pfpriority;			/* playfield tile priority */
/*TODO*///
/*TODO*///	/* these can be modified by the callback, along with pfcolor, above */
/*TODO*///	int					drawmode;			/* how should the tile be drawn */
/*TODO*///	int					drawpens;			/* which pens? */
/*TODO*///	int					maskpens;			/* mask pens */
/*TODO*///};
/*TODO*///
/*TODO*///
/*TODO*////* overrendering callback function */
/*TODO*///typedef int (*ataripf_overrender_cb)(struct ataripf_overrender_data *data, int stage);
/*TODO*///
/*TODO*///
/*TODO*///
/*TODO*////*##########################################################################
/*TODO*///	MACROS
/*TODO*///##########################################################################*/
/*TODO*///
/*TODO*////* accessors for the lookup table */
/*TODO*///#define ATARIPF_LOOKUP_DATABITS				8
/*TODO*///#define ATARIPF_LOOKUP_DATAMASK				((1 << ATARIPF_LOOKUP_DATABITS) - 1)
/*TODO*///#define ATARIPF_LOOKUP_CODEMASK				(0xffff ^ ATARIPF_LOOKUP_DATAMASK)
/*TODO*///
/*TODO*///#define ATARIPF_LOOKUP_CODE(lookup,data)	(((lookup) & ATARIPF_LOOKUP_CODEMASK) | ((data) & ATARIPF_LOOKUP_DATAMASK))
/*TODO*///#define ATARIPF_LOOKUP_COLOR(lookup)		(((lookup) >> 16) & 0xff)
/*TODO*///#define ATARIPF_LOOKUP_HFLIP(lookup)		(((lookup) >> 24) & 1)
/*TODO*///#define ATARIPF_LOOKUP_VFLIP(lookup)		(((lookup) >> 25) & 1)
/*TODO*///#define ATARIPF_LOOKUP_PRIORITY(lookup)		(((lookup) >> 26) & 7)
/*TODO*///#define ATARIPF_LOOKUP_GFX(lookup)			(((lookup) >> 29) & 7)
/*TODO*///
/*TODO*///#define ATARIPF_LOOKUP_ENTRY(gfxindex, code, color, hflip, vflip, priority)	\
/*TODO*///			(((gfxindex) & 7) << 29) |										\
/*TODO*///			(((priority) & 7) << 26) |										\
/*TODO*///			(((vflip) & 1) << 25) |											\
/*TODO*///			(((hflip) & 1) << 24) |											\
/*TODO*///			(((color) & 0xff) << 16) |										\
/*TODO*///			(((code) % Machine.gfx[gfxindex].total_elements) & ATARIPF_LOOKUP_CODEMASK)
/*TODO*///
/*TODO*///#define ATARIPF_LOOKUP_SET_CODE(data,code)		((data) = ((data) & ~ATARIPF_LOOKUP_CODEMASK) | ((code) & ATARIPF_LOOKUP_CODEMASK))
/*TODO*///#define ATARIPF_LOOKUP_SET_COLOR(data,color)	((data) = ((data) & ~0x00ff0000) | (((color) << 16) & 0x00ff0000))
/*TODO*///#define ATARIPF_LOOKUP_SET_HFLIP(data,hflip)	((data) = ((data) & ~0x01000000) | (((hflip) << 24) & 0x01000000))
/*TODO*///#define ATARIPF_LOOKUP_SET_VFLIP(data,vflip)	((data) = ((data) & ~0x02000000) | (((vflip) << 25) & 0x02000000))
/*TODO*///#define ATARIPF_LOOKUP_SET_PRIORITY(data,pri)	((data) = ((data) & ~0x1c000000) | (((pri) << 26) & 0x1c000000))
/*TODO*///#define ATARIPF_LOOKUP_SET_GFX(data,gfx)		((data) = ((data) & ~0xe0000000) | (((gfx) << 29) & 0xe0000000))
/*TODO*///
/*TODO*///
/*TODO*///
/*TODO*////*##########################################################################
/*TODO*///	FUNCTION PROTOTYPES
/*TODO*///##########################################################################*/
/*TODO*///
/*TODO*////* preinitialization */
/*TODO*///void ataripf_blend_gfx(int gfx0, int gfx1, int mask0, int mask1);
/*TODO*///
/*TODO*////* setup/shutdown */
/*TODO*///int ataripf_init(int map, const struct ataripf_desc *desc);
/*TODO*///UINT32 *ataripf_get_lookup(int map, int *size);
/*TODO*///
/*TODO*////* core processing */
/*TODO*///void ataripf_invalidate(int map);
/*TODO*///void ataripf_mark_palette(int map);
/*TODO*///void ataripf_render(int map, struct osd_bitmap *bitmap);
/*TODO*///void ataripf_overrender(int map, ataripf_overrender_cb callback, struct ataripf_overrender_data *data);
/*TODO*///
/*TODO*////* atrribute setters */
/*TODO*///void ataripf_set_bankbits(int map, int bankbits, int scanline);
/*TODO*///void ataripf_set_xscroll(int map, int xscroll, int scanline);
/*TODO*///void ataripf_set_yscroll(int map, int yscroll, int scanline);
/*TODO*///void ataripf_set_latch(int map, int latch);
/*TODO*///void ataripf_set_latch_lo(int latch);
/*TODO*///void ataripf_set_latch_hi(int latch);
/*TODO*///void ataripf_set_transparent_pens(int map, int pens);
/*TODO*///
/*TODO*////* atrribute getters */
/*TODO*///int ataripf_get_bankbits(int map);
/*TODO*///int ataripf_get_xscroll(int map);
/*TODO*///int ataripf_get_yscroll(int map);
/*TODO*///UINT32 *ataripf_get_vram(int map);
/*TODO*///
/*TODO*////* write handlers */
/*TODO*///WRITE16_HANDLER( ataripf_0_simple_w );
/*TODO*///WRITE16_HANDLER( ataripf_0_latched_w );
/*TODO*///WRITE16_HANDLER( ataripf_0_upper_msb_w );
/*TODO*///WRITE16_HANDLER( ataripf_0_upper_lsb_w );
/*TODO*///WRITE16_HANDLER( ataripf_0_large_w );
/*TODO*///WRITE16_HANDLER( ataripf_0_split_w );
/*TODO*///
/*TODO*///WRITE16_HANDLER( ataripf_1_simple_w );
/*TODO*///WRITE16_HANDLER( ataripf_1_latched_w );
/*TODO*///
/*TODO*///WRITE16_HANDLER( ataripf_01_upper_lsb_msb_w );
/*TODO*///
/*TODO*///WRITE32_HANDLER( ataripf_0_split32_w );
/*TODO*///
/*TODO*///
/*TODO*///
/*TODO*////*##########################################################################
/*TODO*///	GLOBAL VARIABLES
/*TODO*///##########################################################################*/
/*TODO*///
/*TODO*///extern data16_t *ataripf_0_base;
/*TODO*///extern data16_t *ataripf_0_upper;
/*TODO*///extern data16_t *ataripf_1_base;
/*TODO*///extern data16_t *ataripf_1_upper;
/*TODO*///
/*TODO*///extern data32_t *ataripf_0_base32;
/*TODO*///
/*TODO*///
/*TODO*///#endif
    
}
