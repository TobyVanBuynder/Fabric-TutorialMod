package net.luxiepotato.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.luxiepotato.tutorialmod.TutorialMod;
import net.luxiepotato.tutorialmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup RUBY_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(TutorialMod.MOD_ID, "ruby"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.tutorialmod.ruby"))
                    .icon(() -> new ItemStack(ModItems.RUBY))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.RUBY);
                        entries.add(ModItems.RAW_RUBY);

                        entries.add(ModItems.COAL_BRIQUETTE);

                        entries.add(ModItems.METAL_DETECTOR);

                        entries.add(ModItems.TOMATO);


                        entries.add(ModBlocks.RUBY_BLOCK);
                        entries.add(ModBlocks.RAW_RUBY_BLOCK);

                        entries.add(ModBlocks.RUBY_ORE);
                        entries.add(ModBlocks.DEEPSLATE_RUBY_ORE);
                        entries.add(ModBlocks.NETHER_RUBY_ORE);
                        entries.add(ModBlocks.END_STONE_RUBY_ORE);

                        entries.add(ModBlocks.SOUND_BLOCK);
                    })
                    .build()
    );


    public static void registerItemGroups() {
        TutorialMod.LOGGER.info("Registering mod item groups for " + TutorialMod.MOD_ID);
    }
}
