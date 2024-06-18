package net.luxiepotato.tutorialmod.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.luxiepotato.tutorialmod.item.ModItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModLootTableModifiers {
    private static final Identifier JUNGLE_TEMPLE_ID =
            new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier CREEPER_ID =
            new Identifier("minecraft", "entities/creeper");

    private static final Identifier SUSPICIOUS_SAND_DESERT_TEMPLE_ID =
            new Identifier("minecraft", "archaeology/desert_pyramid");


    public static void modifyLootRables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (JUNGLE_TEMPLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModItems.METAL_DETECTOR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                tableBuilder.pool(poolBuilder.build());
            }

            if (CREEPER_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f))
                        .with(ItemEntry.builder(ModItems.COAL_BRIQUETTE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());

                // Add to original pools
                tableBuilder.pool(poolBuilder.build());
            }
        });

        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, original, source) -> {
            if (SUSPICIOUS_SAND_DESERT_TEMPLE_ID.equals(id)) {
                // get original file's entries object of first pool
                List<LootPoolEntry> entriesList = new ArrayList<>(List.of(original.pools[0].entries));
                // create and add additional item entries to the pool
                entriesList.add(ItemEntry.builder(ModItems.METAL_DETECTOR).build());
                entriesList.add(ItemEntry.builder(ModItems.COAL_BRIQUETTE).build());

                // recreate the pool object
                LootPool.Builder poolBuilder = LootPool.builder().with(entriesList);
                // build a new table to replace the original with
                return LootTable.builder().pool(poolBuilder).build();
            }

            // not replacing anything
            return null;
        });
    }
}
