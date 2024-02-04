
package net.mcreator.createnuclearpower.block;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.mcreator.createnuclearpower.init.CreateNuclearPowerModBlocks;

import java.util.List;
import java.util.Collections;

public class TurnDownBlock extends Block {
	public static final DirectionProperty FACING = DirectionalBlock.FACING;

	public TurnDownBlock() {
		super(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(0, 0, 0, 16, 2, 16), box(16, 0, 0, 18, 18, 16), box(0, 0, 16, 16, 18, 18), box(-1.84776, -0.76537, 0, 0.15224, 17.23463, 16), box(0, 6.21989, -3.23721, 16, 24.21989, -1.23721),
					box(16.12132, 13.86446, 13.61474, 18.12132, 15.86446, 25.61474), box(14.12132, 5.86446, 16.61474, 16.12132, 7.86446, 22.61474), box(16.3721, 13.86446, -9.36396, 18.3721, 15.86446, 2.63604),
					box(14.3721, 5.86446, -6.36396, 16.3721, 7.86446, -0.36396), box(-9.36396, 13.86446, -2.3721, 2.63604, 15.86446, -0.3721), box(-6.36396, 5.86446, -0.3721, -0.36396, 7.86446, 1.6279),
					box(-2.3721, 13.86446, 13.36396, -0.3721, 15.86446, 25.36396), box(-0.3721, 5.86446, 16.36396, 1.6279, 7.86446, 22.36396));
			case NORTH -> Shapes.or(box(0, 0, 0, 16, 2, 16), box(-2, 0, 0, 0, 18, 16), box(0, 0, -2, 16, 18, 0), box(15.84776, -0.76537, 0, 17.84776, 17.23463, 16), box(0, 6.21989, 17.23721, 16, 24.21989, 19.23721),
					box(-2.12132, 13.86446, -9.61474, -0.12132, 15.86446, 2.38526), box(-0.12132, 5.86446, -6.61474, 1.87868, 7.86446, -0.61474), box(-2.3721, 13.86446, 13.36396, -0.3721, 15.86446, 25.36396),
					box(-0.3721, 5.86446, 16.36396, 1.6279, 7.86446, 22.36396), box(13.36396, 13.86446, 16.3721, 25.36396, 15.86446, 18.3721), box(16.36396, 5.86446, 14.3721, 22.36396, 7.86446, 16.3721),
					box(16.3721, 13.86446, -9.36396, 18.3721, 15.86446, 2.63604), box(14.3721, 5.86446, -6.36396, 16.3721, 7.86446, -0.36396));
			case EAST -> Shapes.or(box(0, 0, 0, 16, 2, 16), box(0, 0, -2, 16, 18, 0), box(16, 0, 0, 18, 18, 16), box(0, -0.76537, 15.84776, 16, 17.23463, 17.84776), box(-3.23721, 6.21989, 0, -1.23721, 24.21989, 16),
					box(13.61474, 13.86446, -2.12132, 25.61474, 15.86446, -0.12132), box(16.61474, 5.86446, -0.12132, 22.61474, 7.86446, 1.87868), box(-9.36396, 13.86446, -2.3721, 2.63604, 15.86446, -0.3721),
					box(-6.36396, 5.86446, -0.3721, -0.36396, 7.86446, 1.6279), box(-2.3721, 13.86446, 13.36396, -0.3721, 15.86446, 25.36396), box(-0.3721, 5.86446, 16.36396, 1.6279, 7.86446, 22.36396),
					box(13.36396, 13.86446, 16.3721, 25.36396, 15.86446, 18.3721), box(16.36396, 5.86446, 14.3721, 22.36396, 7.86446, 16.3721));
			case WEST -> Shapes.or(box(0, 0, 0, 16, 2, 16), box(0, 0, 16, 16, 18, 18), box(-2, 0, 0, 0, 18, 16), box(0, -0.76537, -1.84776, 16, 17.23463, 0.15224), box(17.23721, 6.21989, 0, 19.23721, 24.21989, 16),
					box(-9.61474, 13.86446, 16.12132, 2.38526, 15.86446, 18.12132), box(-6.61474, 5.86446, 14.12132, -0.61474, 7.86446, 16.12132), box(13.36396, 13.86446, 16.3721, 25.36396, 15.86446, 18.3721),
					box(16.36396, 5.86446, 14.3721, 22.36396, 7.86446, 16.3721), box(16.3721, 13.86446, -9.36396, 18.3721, 15.86446, 2.63604), box(14.3721, 5.86446, -6.36396, 16.3721, 7.86446, -0.36396),
					box(-9.36396, 13.86446, -2.3721, 2.63604, 15.86446, -0.3721), box(-6.36396, 5.86446, -0.3721, -0.36396, 7.86446, 1.6279));
			case UP -> Shapes.or(box(0, 0, 0, 16, 16, 2), box(-2, 0, 0, 0, 16, 18), box(0, 16, 0, 16, 18, 18), box(15.84776, 0, -0.76537, 17.84776, 16, 17.23463), box(0, -3.23721, 6.21989, 16, -1.23721, 24.21989),
					box(-2.12132, 13.61474, 13.86446, -0.12132, 25.61474, 15.86446), box(-0.12132, 16.61474, 5.86446, 1.87868, 22.61474, 7.86446), box(-2.3721, -9.36396, 13.86446, -0.3721, 2.63604, 15.86446),
					box(-0.3721, -6.36396, 5.86446, 1.6279, -0.36396, 7.86446), box(13.36396, -2.3721, 13.86446, 25.36396, -0.3721, 15.86446), box(16.36396, -0.3721, 5.86446, 22.36396, 1.6279, 7.86446),
					box(16.3721, 13.36396, 13.86446, 18.3721, 25.36396, 15.86446), box(14.3721, 16.36396, 5.86446, 16.3721, 22.36396, 7.86446));
			case DOWN -> Shapes.or(box(0, 0, 14, 16, 16, 16), box(-2, 0, -2, 0, 16, 16), box(0, -2, -2, 16, 0, 16), box(15.84776, 0, -1.23463, 17.84776, 16, 16.76537), box(0, 17.23721, -8.21989, 16, 19.23721, 9.78011),
					box(-2.12132, -9.61474, 0.13554, -0.12132, 2.38526, 2.13554), box(-0.12132, -6.61474, 8.13554, 1.87868, -0.61474, 10.13554), box(-2.3721, 13.36396, 0.13554, -0.3721, 25.36396, 2.13554),
					box(-0.3721, 16.36396, 8.13554, 1.6279, 22.36396, 10.13554), box(13.36396, 16.3721, 0.13554, 25.36396, 18.3721, 2.13554), box(16.36396, 14.3721, 8.13554, 22.36396, 16.3721, 10.13554),
					box(16.3721, -9.36396, 0.13554, 18.3721, 2.63604, 2.13554), box(14.3721, -6.36396, 8.13554, 16.3721, -0.36396, 10.13554));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getClickedFace());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderLayer() {
		ItemBlockRenderTypes.setRenderLayer(CreateNuclearPowerModBlocks.TURN_DOWN.get(), renderType -> renderType == RenderType.cutout());
	}
}
