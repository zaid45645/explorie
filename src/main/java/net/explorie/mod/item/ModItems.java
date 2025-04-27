package net.explorie.mod.item;

import java.util.function.Function;

import net.explorie.mod.item.custom.OreFinderItem;
import net.explorie.mod.item.custom.StructureFinderItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("zaidexpo", name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }
    public static final Item ORE_MAP_FRAGMENTS = register("ore_map_fragments", Item::new, new Item.Settings());
    public static final Item STRUCTURE_MAP_FRAGMENTS = register("structure_map_fragments", Item::new, new Item.Settings());
    public static final Item EXPO_MAP = register("expo_map", StructureFinderItem::new, new Item.Settings().maxDamage(32));
    public static final  Item ORE_MAP = register("ore_map", OreFinderItem::new, new Item.Settings().maxDamage(32));

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS)
                .register((itemGroup) -> itemGroup.add(ModItems.EXPO_MAP));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.add(ModItems.ORE_MAP));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemGroup) -> itemGroup.add(ModItems.ORE_MAP_FRAGMENTS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemGroup) -> itemGroup.add(ModItems.STRUCTURE_MAP_FRAGMENTS));
    }
}

