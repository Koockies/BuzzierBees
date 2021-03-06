package com.bagel.buzzierbees.core.registry;

import com.bagel.buzzierbees.client.render.HiveBoatRenderer;
import com.bagel.buzzierbees.client.render.HoneySlimeRenderer;
import com.bagel.buzzierbees.common.entities.HiveBoatEntity;
import com.bagel.buzzierbees.common.entities.HoneySlimeEntity;
import com.bagel.buzzierbees.core.BuzzierBees;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities
{
	public static EntityType<HoneySlimeEntity>  HONEY_SLIME;
	public static EntityType<HiveBoatEntity>        BOAT;
	
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
    {
        ModEntities.HONEY_SLIME = EntityType.Builder.<HoneySlimeEntity>create(HoneySlimeEntity::new, EntityClassification.CREATURE).size(1.02F, 1.02F).build("buzzierbees:honey_slime");
        ModEntities.HONEY_SLIME.setRegistryName("honey_slime");

        ForgeRegistries.ENTITIES.register(ModEntities.HONEY_SLIME);
        
        BOAT = EntityType.Builder.<HiveBoatEntity>create(HiveBoatEntity::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).size(1.375f, 0.5625f).build(BuzzierBees.MODID + ":boat");
        BOAT.setRegistryName("boat");
        //ForgeRegistries.ENTITIES.register(BOAT); - dont forget to change bee soup to boat
    }

    public static <T extends Entity> EntityType<T> createEntity(EntityType.IFactory<T> factory, EntityClassification classification, String name, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        ResourceLocation location = new ResourceLocation("biomesoplenty", name);
        EntityType<T> type = EntityType.Builder.<T>create(factory, classification).setTrackingRange(trackingRange).setUpdateInterval(updateFrequency).setShouldReceiveVelocityUpdates(sendsVelocityUpdates).build(location.toString());
        type.setRegistryName(name);
        ForgeRegistries.ENTITIES.register(type);
        return type;
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends HoneySlimeEntity>)ModEntities.HONEY_SLIME, HoneySlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler((EntityType<? extends HiveBoatEntity>)ModEntities.BOAT, HiveBoatRenderer::new);
    }
    
    public static void addEntitySpawns() {
    	//Condition Registry
		EntitySpawnPlacementRegistry.register(ModEntities.HONEY_SLIME, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HoneySlimeEntity::honeySlimeCondition);

		//Spawn Registry
		Biomes.FLOWER_FOREST.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntities.HONEY_SLIME, 20, 1, 2));
	}
}
