
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Containers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.mcreator.createnuclearpower.procedures.TurnLidFluidProcedure;
import net.mcreator.createnuclearpower.init.CreateNuclearPowerModBlocks;
import net.mcreator.createnuclearpower.block.entity.TurnLidBlockEntity;

import java.util.Random;
import java.util.List;
import java.util.Collections;

public class TurnLidBlock extends Block implements EntityBlock {
	public static final DirectionProperty FACING = DirectionalBlock.FACING;

	public TurnLidBlock() {
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
			default -> Shapes.or(box(0, 0, 0, 16, 16, 16), box(0, 17.46912, -10.48599, 16, 19.46912, 10.07776), box(0, 17.46912, 5.92224, 16, 19.46912, 26.48599), box(-10.48599, 17.46912, 0, 10.07776, 19.46912, 16),
					box(5.92224, 17.46912, 0, 26.48599, 19.46912, 16), box(19, 8, 10, 21, 10, 22), box(24, 0, 6, 26, 2, 26), box(22, 4, 8, 24, 6, 24), box(-6, 8, -5, 6, 10, -3), box(-10, 0, -10, 10, 2, -8), box(-8, 4, -8, 8, 6, -6),
					box(-5, 8, 10, -3, 10, 22), box(-10, 0, 6, -8, 2, 26), box(-8, 4, 8, -6, 6, 24), box(10, 0, -1, 30, 2, 1), box(12, 4, 2, 28, 6, 4), box(14, 8, 5, 26, 10, 7));
			case NORTH -> Shapes.or(box(0, 0, 0, 16, 16, 16), box(0, 17.46912, 5.92224, 16, 19.46912, 26.48599), box(0, 17.46912, -10.48599, 16, 19.46912, 10.07776), box(5.92224, 17.46912, 0, 26.48599, 19.46912, 16),
					box(-10.48599, 17.46912, 0, 10.07776, 19.46912, 16), box(-5, 8, -6, -3, 10, 6), box(-10, 0, -10, -8, 2, 10), box(-8, 4, -8, -6, 6, 8), box(10, 8, 19, 22, 10, 21), box(6, 0, 24, 26, 2, 26), box(8, 4, 22, 24, 6, 24),
					box(19, 8, -6, 21, 10, 6), box(24, 0, -10, 26, 2, 10), box(22, 4, -8, 24, 6, 8), box(-14, 0, 15, 6, 2, 17), box(-12, 4, 12, 4, 6, 14), box(-10, 8, 9, 2, 10, 11));
			case EAST -> Shapes.or(box(0, 0, 0, 16, 16, 16), box(-10.48599, 17.46912, 0, 10.07776, 19.46912, 16), box(5.92224, 17.46912, 0, 26.48599, 19.46912, 16), box(0, 17.46912, 5.92224, 16, 19.46912, 26.48599),
					box(0, 17.46912, -10.48599, 16, 19.46912, 10.07776), box(10, 8, -5, 22, 10, -3), box(6, 0, -10, 26, 2, -8), box(8, 4, -8, 24, 6, -6), box(-5, 8, 10, -3, 10, 22), box(-10, 0, 6, -8, 2, 26), box(-8, 4, 8, -6, 6, 24),
					box(10, 8, 19, 22, 10, 21), box(6, 0, 24, 26, 2, 26), box(8, 4, 22, 24, 6, 24), box(-1, 0, -14, 1, 2, 6), box(2, 4, -12, 4, 6, 4), box(5, 8, -10, 7, 10, 2));
			case WEST -> Shapes.or(box(0, 0, 0, 16, 16, 16), box(5.92224, 17.46912, 0, 26.48599, 19.46912, 16), box(-10.48599, 17.46912, 0, 10.07776, 19.46912, 16), box(0, 17.46912, -10.48599, 16, 19.46912, 10.07776),
					box(0, 17.46912, 5.92224, 16, 19.46912, 26.48599), box(-6, 8, 19, 6, 10, 21), box(-10, 0, 24, 10, 2, 26), box(-8, 4, 22, 8, 6, 24), box(19, 8, -6, 21, 10, 6), box(24, 0, -10, 26, 2, 10), box(22, 4, -8, 24, 6, 8),
					box(-6, 8, -5, 6, 10, -3), box(-10, 0, -10, 10, 2, -8), box(-8, 4, -8, 8, 6, -6), box(15, 0, 10, 17, 2, 30), box(12, 4, 12, 14, 6, 28), box(9, 8, 14, 11, 10, 26));
			case UP -> Shapes.or(box(0, 0, 0, 16, 16, 16), box(0, -10.48599, 17.46912, 16, 10.07776, 19.46912), box(0, 5.92224, 17.46912, 16, 26.48599, 19.46912), box(5.92224, 0, 17.46912, 26.48599, 16, 19.46912),
					box(-10.48599, 0, 17.46912, 10.07776, 16, 19.46912), box(-5, 10, 8, -3, 22, 10), box(-10, 6, 0, -8, 26, 2), box(-8, 8, 4, -6, 24, 6), box(10, -5, 8, 22, -3, 10), box(6, -10, 0, 26, -8, 2), box(8, -8, 4, 24, -6, 6),
					box(19, 10, 8, 21, 22, 10), box(24, 6, 0, 26, 26, 2), box(22, 8, 4, 24, 24, 6), box(-14, -1, 0, 6, 1, 2), box(-12, 2, 4, 4, 4, 6), box(-10, 5, 8, 2, 7, 10));
			case DOWN -> Shapes.or(box(0, 0, 0, 16, 16, 16), box(0, 5.92224, -3.46912, 16, 26.48599, -1.46912), box(0, -10.48599, -3.46912, 16, 10.07776, -1.46912), box(5.92224, 0, -3.46912, 26.48599, 16, -1.46912),
					box(-10.48599, 0, -3.46912, 10.07776, 16, -1.46912), box(-5, -6, 6, -3, 6, 8), box(-10, -10, 14, -8, 10, 16), box(-8, -8, 10, -6, 8, 12), box(10, 19, 6, 22, 21, 8), box(6, 24, 14, 26, 26, 16), box(8, 22, 10, 24, 24, 12),
					box(19, -6, 6, 21, 6, 8), box(24, -10, 14, 26, 10, 16), box(22, -8, 10, 24, 8, 12), box(-14, 15, 14, 6, 17, 16), box(-12, 12, 10, 4, 14, 12), box(-10, 9, 6, 2, 11, 8));
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

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		world.scheduleTick(pos, this, 10);
	}

	@Override
	public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, Random random) {
		super.tick(blockstate, world, pos, random);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		TurnLidFluidProcedure.execute(world, x, y, z);
		world.scheduleTick(pos, this, 10);
	}

	@Override
	public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
		BlockEntity tileEntity = worldIn.getBlockEntity(pos);
		return tileEntity instanceof MenuProvider menuProvider ? menuProvider : null;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new TurnLidBlockEntity(pos, state);
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
		super.triggerEvent(state, world, pos, eventID, eventParam);
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof TurnLidBlockEntity be) {
				Containers.dropContents(world, pos, be);
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof TurnLidBlockEntity be)
			return AbstractContainerMenu.getRedstoneSignalFromContainer(be);
		else
			return 0;
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerRenderLayer() {
		ItemBlockRenderTypes.setRenderLayer(CreateNuclearPowerModBlocks.TURN_LID.get(), renderType -> renderType == RenderType.cutout());
	}
}
