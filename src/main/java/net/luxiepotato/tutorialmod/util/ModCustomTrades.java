package net.luxiepotato.tutorialmod.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.luxiepotato.tutorialmod.item.ModItems;
import net.luxiepotato.tutorialmod.villager.ModVillagers;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class ModCustomTrades {
    public static void registerCustomTrades() {

        // FARMER LVL 1
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    factories.add(
                            (entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 3),
                                    new ItemStack(ModItems.TOMATO, 5),
                                    6, 5, 0.05f
                            )
                    );
                    factories.add(
                            (entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 4),
                                    new ItemStack(ModItems.TOMATO, 1),
                                    4, 7, 0.75f
                            )
                    );
                }
        );

        // FARMER LVL 2
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2,
                factories -> {
                    factories.add(
                            (entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 3),
                                    new ItemStack(Items.DIAMOND, 1),
                                    new ItemStack(ModItems.CORN, 5),
                                    6, 5, 0.05f
                            )
                    );
                    factories.add(
                            (entity, random) -> new TradeOffer(
                                    new ItemStack(Items.EMERALD, 2),
                                    new ItemStack(Items.GOLD_INGOT, 2),
                                    new ItemStack(ModItems.CORN_SEEDS, 1),
                                    4, 7, 0.75f
                            )
                    );
                }
        );

        // LIBRARIAN LVL 1
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1,
                factories -> factories.add(
                        (entity, random) -> new TradeOffer(
                                new ItemStack(ModItems.RUBY, 3),
                                EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.PIERCING, 1)),
                                6, 5, 0.05f
                        )
                )
        );

        // WANDERING TRADER LVL 1
        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> factories.add(
                        (entity, random) -> new TradeOffer(
                                new ItemStack(ModItems.RUBY, 3),
                                EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.PIERCING, 2)),
                                6, 5, 0.05f
                        )
                )
        );

        // SOUND MASTER LVL 1
        TradeOfferHelper.registerVillagerOffers(ModVillagers.SOUND_MASTER, 1,
                factories -> factories.add(
                        (entity, random) -> new TradeOffer(
                                new ItemStack(Items.EMERALD, 5),
                                new ItemStack(Items.REDSTONE, 2),
                                new ItemStack(Blocks.NOTE_BLOCK, 1),
                                6, 5, 0.5f
                        )
                )
        );
    }
}
