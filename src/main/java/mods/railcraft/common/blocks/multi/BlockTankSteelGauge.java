/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2017
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/

package mods.railcraft.common.blocks.multi;

import mods.railcraft.common.items.Metal;
import mods.railcraft.common.items.RailcraftItems;
import mods.railcraft.common.plugins.forge.WorldPlugin;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static mods.railcraft.common.blocks.multi.BlockTankIronGauge.POSITION;

/**
 *
 */
public class BlockTankSteelGauge extends BlockTankMetal {

    public BlockTankSteelGauge() {
        super(Material.GLASS);
        setSoundType(SoundType.GLASS);
        setDefaultState(getDefaultState().withProperty(POSITION, BlockTankIronGauge.ColumnPosition.SINGLE));
        fullBlock = false;
        lightOpacity = 0;
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, COLOR, POSITION);
    }

    @Override
    public void defineRecipes() {
        super.defineRecipes();
        addRecipe("GPG",
                "PGP",
                "GPG",
                'G', Blocks.GLASS_PANE,
                'P', RailcraftItems.PLATE, Metal.STEEL);
    }

    @Override
    public Class<? extends TileEntity> getTileClass(IBlockState state) {
        return TileTankSteelGauge.class;
    }

    @Override
    public TileMultiBlock<?, ?, ?> createTileEntity(World world, IBlockState state) {
        return new TileTankSteelGauge<>();
    }

    @Override
    public Tuple<Integer, Integer> getTextureDimensions() {
        return new Tuple<>(1, 5);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing side) {
        return WorldPlugin.getBlock(access, pos.offset(side)) != this && super.shouldSideBeRendered(state, access, pos, side);
    }
}
