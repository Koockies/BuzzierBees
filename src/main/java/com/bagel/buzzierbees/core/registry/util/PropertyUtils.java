package com.bagel.buzzierbees.core.registry.util;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class PropertyUtils {
	public static final Block.Properties FLOWER = Block.Properties.create(Material.PLANTS).func_226896_b_().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT);
	public static final Block.Properties CANDLE = Block.Properties.create(Material.CORAL).hardnessAndResistance(0.0F).sound(SoundType.WOOD);
	public static final Block.Properties POT    = Block.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance().func_226896_b_();
}
