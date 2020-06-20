package io.github.ashley1227.unbadhitboxes.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FernBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = FernBlock.class, priority = 1)
public class FernBlockMixin {
	@Shadow protected static VoxelShape SHAPE;

	@Overwrite
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		Vec3d vec3d = state.getOffsetPos(view, pos);
		return SHAPE.offset(vec3d.x, vec3d.y, vec3d.z);
	}

}
