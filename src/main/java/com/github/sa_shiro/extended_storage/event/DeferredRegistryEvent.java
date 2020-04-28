package com.github.sa_shiro.extended_storage.event;

import com.github.sa_shiro.extended_storage.ExtendedStorage;
import com.github.sa_shiro.extended_storage.block.*;
import com.github.sa_shiro.extended_storage.container.*;
import com.github.sa_shiro.extended_storage.tileentity.*;
import com.github.sa_shiro.extended_storage.util.ItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DeferredRegistryEvent {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, ExtendedStorage.MOD_ID);
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPE = new DeferredRegister<>(ForgeRegistries.CONTAINERS, ExtendedStorage.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ExtendedStorage.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, ExtendedStorage.MOD_ID);
    // Blocks
    public static final RegistryObject<Block> IRON_CHEST = BLOCKS.register(
            "iron_chest", () -> new IronChestBlock(Material.IRON, SoundType.METAL, 10.0F, 1.0F, 1));
    public static final RegistryObject<Block> GOLD_CHEST = BLOCKS.register(
            "gold_chest", () -> new GoldChestBlock(Material.IRON, SoundType.METAL, 15.5F, 5.0F, 1));
    public static final RegistryObject<Block> DIAMOND_CHEST = BLOCKS.register(
            "diamond_chest", () -> new DiamondChestBlock(Material.IRON, SoundType.METAL, 20.0F, 10.0F, 2));
    public static final RegistryObject<Block> OBSIDIAN_CHEST = BLOCKS.register(
            "obsidian_chest", () -> new ObsidianChestBlock(Material.IRON, SoundType.METAL, 25.5F, 5000.0F, 3));
    public static final RegistryObject<Block> EMERALD_CHEST = BLOCKS.register(
            "emerald_chest", () -> new EmeraldChestBlock(Material.IRON, SoundType.METAL, 30.0F, 500.0F, 3));
    public static final RegistryObject<Block> DRAGON_CHEST = BLOCKS.register(
            "dragon_chest", () -> new DragonChestBlock(Material.IRON, SoundType.METAL, 35.5F, 1000000000.0F, 3));
    // Tile Entities
    public static final RegistryObject<TileEntityType<IronChestTileEntity>> IRON_CHEST_TILE_ENTITY = TILE_ENTITY_TYPE.register(
            "iron_chest", () -> TileEntityType.Builder.create(IronChestTileEntity::new, IRON_CHEST.get()).build(null));
    public static final RegistryObject<TileEntityType<GoldChestTileEntity>> GOLD_CHEST_TILE_ENTITY = TILE_ENTITY_TYPE.register(
            "gold_chest", () -> TileEntityType.Builder.create(GoldChestTileEntity::new, GOLD_CHEST.get()).build(null));
    public static final RegistryObject<TileEntityType<DiamondChestTileEntity>> DIAMOND_CHEST_TILE_ENTITY = TILE_ENTITY_TYPE.register(
            "diamond_chest", () -> TileEntityType.Builder.create(DiamondChestTileEntity::new, DIAMOND_CHEST.get()).build(null));
    public static final RegistryObject<TileEntityType<ObsidianChestTileEntity>> OBSIDIAN_CHEST_TILE_ENTITY = TILE_ENTITY_TYPE.register(
            "obsidian_chest", () -> TileEntityType.Builder.create(ObsidianChestTileEntity::new, OBSIDIAN_CHEST.get()).build(null));
    public static final RegistryObject<TileEntityType<EmeraldChestTileEntity>> EMERALD_CHEST_TILE_ENTITY = TILE_ENTITY_TYPE.register(
            "emerald_chest", () -> TileEntityType.Builder.create(EmeraldChestTileEntity::new, EMERALD_CHEST.get()).build(null));
    public static final RegistryObject<TileEntityType<DragonChestTileEntity>> DRAGON_CHEST_TILE_ENTITY = TILE_ENTITY_TYPE.register(
            "dragon_chest", () -> TileEntityType.Builder.create(DragonChestTileEntity::new, DRAGON_CHEST.get()).build(null));
    // Containers
    public static final RegistryObject<ContainerType<IronChestContainer>> IRON_CHEST_CONTAINER = CONTAINER_TYPE.register(
            "iron_chest", () -> IForgeContainerType.create(IronChestContainer::new));
    public static final RegistryObject<ContainerType<GoldChestContainer>> GOLD_CHEST_CONTAINER = CONTAINER_TYPE.register(
            "gold_chest", () -> IForgeContainerType.create(GoldChestContainer::new));
    public static final RegistryObject<ContainerType<DiamondChestContainer>> DIAMOND_CHEST_CONTAINER = CONTAINER_TYPE.register(
            "diamond_chest", () -> IForgeContainerType.create(DiamondChestContainer::new));
    public static final RegistryObject<ContainerType<ObsidianChestContainer>> OBSIDIAN_CHEST_CONTAINER = CONTAINER_TYPE.register(
            "obsidian_chest", () -> IForgeContainerType.create(ObsidianChestContainer::new));
    public static final RegistryObject<ContainerType<EmeraldChestContainer>> EMERALD_CHEST_CONTAINER = CONTAINER_TYPE.register(
            "emerald_chest", () -> IForgeContainerType.create(EmeraldChestContainer::new));
    public static final RegistryObject<ContainerType<DragonChestContainer>> DRAGON_CHEST_CONTAINER = CONTAINER_TYPE.register(
            "dragon_chest", () -> IForgeContainerType.create(DragonChestContainer::new));
    private static final Item.Properties props = new Item.Properties().group(ItemGroup.itemGroup);
    // Items
    public static final RegistryObject<Item> IRON_CHEST_ITEM = ITEMS.register("iron_chest", () -> new BlockItem(IRON_CHEST.get(), props));
    public static final RegistryObject<Item> GOLD_CHEST_ITEM = ITEMS.register("gold_chest", () -> new BlockItem(GOLD_CHEST.get(), props));
    public static final RegistryObject<Item> DIAMOND_CHEST_ITEM = ITEMS.register("diamond_chest", () -> new BlockItem(DIAMOND_CHEST.get(), props));
    public static final RegistryObject<Item> OBSIDIAN_CHEST_ITEM = ITEMS.register("obsidian_chest", () -> new BlockItem(OBSIDIAN_CHEST.get(), props));
    public static final RegistryObject<Item> EMERALD_CHEST_ITEM = ITEMS.register("emerald_chest", () -> new BlockItem(EMERALD_CHEST.get(), props));
    public static final RegistryObject<Item> DRAGON_CHEST_ITEM = ITEMS.register("dragon_chest", () -> new BlockItem(DRAGON_CHEST.get(), props));
}
