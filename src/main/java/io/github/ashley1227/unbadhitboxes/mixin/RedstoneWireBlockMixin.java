package io.github.ashley1227.unbadhitboxes.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.block.enums.WireConnection;
import net.minecraft.entity.EntityContext;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.Map;

@Mixin(value = RedstoneWireBlock.class, priority = 1)
public class RedstoneWireBlockMixin {
	@Shadow private static Map<Direction, EnumProperty<WireConnection>> DIRECTION_TO_WIRE_CONNECTION_PROPERTY;

	/**
	 * Bottom part
	 */
	private static VoxelShape field_24413;

	/**
	 * Side
	 */
	private static final Map<Direction, VoxelShape> field_24414;

	/**
	 * Up
	 */
	private static final Map<Direction, VoxelShape> field_24415;

	/**
	 * haha @Overwrite go brrr
	 */
	@Overwrite
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
		VoxelShape voxelShape = field_24413;
		Iterator var3 = Direction.Type.HORIZONTAL.iterator();

		while(var3.hasNext()) {
			Direction direction = (Direction)var3.next();
			WireConnection wireConnection = (WireConnection)state.get((Property)DIRECTION_TO_WIRE_CONNECTION_PROPERTY.get(direction));
			if (wireConnection == WireConnection.SIDE) {
				voxelShape = VoxelShapes.union(voxelShape, field_24414.get(direction));
			} else if (wireConnection == WireConnection.UP) {
				voxelShape = VoxelShapes.union(voxelShape, field_24415.get(direction));
			}
		}
		return voxelShape;
	}

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
