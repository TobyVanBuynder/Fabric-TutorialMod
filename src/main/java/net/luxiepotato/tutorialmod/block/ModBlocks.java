package net.luxiepotato.tutorialmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.luxiepotato.tutorialmod.TutorialMod;
import net.luxiepotato.tutorialmod.block.custom.CornCropBlock;
import net.luxiepotato.tutorialmod.block.custom.SoundBlock;
import net.luxiepotato.tutorialmod.block.custom.TomatoCropBlock;
import net.luxiepotato.tutorialmod.sound.ModSounds;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block RUBY_BLOCK = registerBlockWithItem("ruby_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block RAW_RUBY_BLOCK = registerBlockWithItem("raw_ruby_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block RUBY_ORE = registerBlockWithItem("ruby_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f),
                    UniformIntProvider.create(2, 5)));
    public static final Block DEEPSLATE_RUBY_ORE = registerBlockWithItem("deepslate_ruby_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(4f),
                    UniformIntProvider.create(2, 5)));
    public static final Block NETHER_RUBY_ORE = registerBlockWithItem("nether_ruby_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHERRACK).strength(1.5f),
                    UniformIntProvider.create(2, 5)));
    public static final Block END_STONE_RUBY_ORE = registerBlockWithItem("end_stone_ruby_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.END_STONE).strength(4f),
                    UniformIntProvider.create(4, 7)));

    public static final Block SOUND_BLOCK = registerBlockWithItem("sound_block",
            new SoundBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(ModSounds.SOUND_BLOCK_SOUNDS)));

    public static final Block RUBY_STAIRS = registerBlockWithItem("ruby_stairs",
            new StairsBlock(RUBY_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block RUBY_SLAB = registerBlockWithItem("ruby_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block RUBY_BUTTON = registerBlockWithItem("ruby_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK), BlockSetType.IRON, 10, true));
    public static final Block RUBY_PRESSURE_PLATE = registerBlockWithItem("ruby_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copyOf(Blocks.IRON_BLOCK), BlockSetType.IRON));

    public static final Block RUBY_FENCE = registerBlockWithItem("ruby_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block RUBY_FENCE_GATE = registerBlockWithItem("ruby_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK), WoodType.ACACIA));
    public static final Block RUBY_WALL = registerBlockWithItem("ruby_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));

    public static final Block RUBY_DOOR = registerBlockWithItem("ruby_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque(), BlockSetType.IRON));
    public static final Block RUBY_TRAPDOOR = registerBlockWithItem("ruby_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.IRON_TRAPDOOR), BlockSetType.IRON));

    public static final Block TOMATO_CROP = registerBlock("tomato_crop",
            new TomatoCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block CORN_CROP = registerBlock("corn_crop",
            new CornCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block DAHLIA = registerBlockWithItem("dahlia",
            new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 10,
                    FabricBlockSettings.copyOf(Blocks.ALLIUM).nonOpaque().noCollision()));
    public static final Block POTTED_DAHLIA = registerBlock("potted_dahlia",
            new FlowerPotBlock(DAHLIA, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));



    private static Block registerBlockWithItem(String name, Block block) {
        registerBlockItem(name, block);
        return registerBlock(name, block);
    }

    private static Block registerBlock(String name, Block block) {
        return Registry.register(
                Registries.BLOCK,
                new Identifier(TutorialMod.MOD_ID, name),
                block
        );
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(
                Registries.ITEM,
                new Identifier(TutorialMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings())
        );
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering mod blocks for mod " + TutorialMod.MOD_ID);
    }
}
