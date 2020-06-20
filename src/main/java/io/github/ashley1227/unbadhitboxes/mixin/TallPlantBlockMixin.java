package io.github.ashley1227.unbadhitboxes.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = TallPlantBlock.class, priority = 1)
public abstract class TallPlantBlockMixin extends PlantBlock {

	private VoxelShape SHAPE = Block.createCuboidShape(1d, 0d, 1d, 15d, 16d, 15d);

	private TallPlantBlockMixin(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		Vec3d vec3d = state.getOffsetPos(view, pos);
		return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
	}
}
