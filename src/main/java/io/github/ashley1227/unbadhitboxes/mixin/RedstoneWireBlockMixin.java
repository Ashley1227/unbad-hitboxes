package io.github.ashley1227.unbadhitboxes.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(RedstoneWireBlock.class)
public class RedstoneWireBlockMixin {

	/**
	 * Bottom part
	 */
	@Shadow private static VoxelShape field_24413;

	/**
	 * Side
	 */
	@Shadow private static final Map<Direction, VoxelShape> field_24414;

	/**
	 * Up
	 */
	@Shadow private static final Map<Direction, VoxelShape> field_24415;

	static {
		field_24413 = Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 1.0d, 16.0d);

		field_24414 = Maps.newEnumMap(
				ImmutableMap.of(
						Direction.NORTH,
						Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 1.0d, 16.0d),
						Direction.SOUTH,
						Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 1.0d, 16.0d),
						Direction.EAST,
						Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 1.0d, 16.0d),
						Direction.WEST,
						Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 1.0d, 16.0d)
				)
		);

		field_24415 = Maps.newEnumMap(
				ImmutableMap.of(
						Direction.NORTH,
						VoxelShapes.union(
								field_24414.get(Direction.NORTH),
								Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 16.0d, 1.0d)
						),
						Direction.SOUTH,
						VoxelShapes.union(
								field_24414.get(Direction.SOUTH),
								Block.createCuboidShape(0.0d, 0.0d, 15.0d, 16.0d, 16.0d, 16.0d)
						),
						Direction.EAST,
						VoxelShapes.union(
								field_24414.get(Direction.EAST),
								Block.createCuboidShape(15.0d, 0.0d, 0.0d, 16.0d, 16.0d, 16.0d)
						),
						Direction.WEST,
						VoxelShapes.union(
								field_24414.get(Direction.WEST),
								Block.createCuboidShape(0.0d, 0.0d, 0.0d, 1.0d, 16.0d, 16.0d)
						)
				)
		);
	}
}
