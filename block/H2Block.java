
package net.mcreator.createnuclearpower.block;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.LiquidBlock;

import net.mcreator.createnuclearpower.init.CreateNuclearPowerModFluids;

public class H2Block extends LiquidBlock {
	public H2Block() {
		super(() -> (FlowingFluid) CreateNuclearPowerModFluids.H_2.get(), BlockBehaviour.Properties.of(Material.WATER).strength(100f)

		);
	}
}
