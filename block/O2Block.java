
package net.mcreator.createnuclearpower.block;

import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.LiquidBlock;

import net.mcreator.createnuclearpower.init.CreateNuclearPowerModFluids;

public class O2Block extends LiquidBlock {
	public O2Block() {
		super(() -> (FlowingFluid) CreateNuclearPowerModFluids.O_2.get(), BlockBehaviour.Properties.of(Material.WATER).strength(100f)

		);
	}
}
