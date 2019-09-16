package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;

public class BlockPrismarine extends Block
{
    public static final PropertyEnum<BlockPrismarine.EnumType> VARIANT = PropertyEnum.<BlockPrismarine.EnumType>create("variant", BlockPrismarine.EnumType.class);
    public static final int ROUGH_META = BlockPrismarine.EnumType.ROUGH.getMetadata();
    public static final int BRICKS_META = BlockPrismarine.EnumType.BRICKS.getMetadata();
    public static final int DARK_META = BlockPrismarine.EnumType.DARK.getMetadata();

    public BlockPrismarine()
    {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPrismarine.EnumType.ROUGH));
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }

    /**
     * Gets the localized name of this block. Used for the statistics page.
     */
    public String getLocalizedName()
    {
        return I18n.translateToLocal(this.getTranslationKey() + "." + BlockPrismarine.EnumType.ROUGH.getTranslationKey() + ".name");
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     * @deprecated call via {@link IBlockState#getMapColor(IBlockAccess,BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.getValue(VARIANT) == BlockPrismarine.EnumType.ROUGH ? MapColor.CYAN : MapColor.DIAMOND;
    }

    /**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return ((BlockPrismarine.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockPrismarine.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {VARIANT});
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockPrismarine.EnumType.byMetadata(meta));
    }

    public void func_149666_a(CreativeTabs p_149666_1_, NonNullList<ItemStack> p_149666_2_)
    {
        p_149666_2_.add(new ItemStack(this, 1, ROUGH_META));
        p_149666_2_.add(new ItemStack(this, 1, BRICKS_META));
        p_149666_2_.add(new ItemStack(this, 1, DARK_META));
    }

    public static enum EnumType implements IStringSerializable
    {
        ROUGH(0, "prismarine", "rough"),
        BRICKS(1, "prismarine_bricks", "bricks"),
        DARK(2, "dark_prismarine", "dark");

        private static final BlockPrismarine.EnumType[] META_LOOKUP = new BlockPrismarine.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String translationKey;

        private EnumType(int meta, String name, String unlocalizedName)
        {
            this.meta = meta;
            this.name = name;
            this.translationKey = unlocalizedName;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockPrismarine.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getTranslationKey()
        {
            return this.translationKey;
        }

        static {
            for (BlockPrismarine.EnumType blockprismarine$enumtype : values())
            {
                META_LOOKUP[blockprismarine$enumtype.getMetadata()] = blockprismarine$enumtype;
            }
        }
    }
}
