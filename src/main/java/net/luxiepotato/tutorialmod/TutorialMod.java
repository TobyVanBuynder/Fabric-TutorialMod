package net.luxiepotato.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.luxiepotato.tutorialmod.block.ModBlocks;
import net.luxiepotato.tutorialmod.item.ModItemGroups;
import net.luxiepotato.tutorialmod.item.ModItems;
import net.luxiepotato.tutorialmod.sound.ModSounds;
import net.luxiepotato.tutorialmod.util.ModCustomTrades;
import net.luxiepotato.tutorialmod.util.ModLootTableModifiers;
import net.luxiepotato.tutorialmod.villager.ModVillagers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModLootTableModifiers.modifyLootRables();

		ModCustomTrades.registerCustomTrades();
		ModVillagers.registerVillagers();

		ModSounds.registerSounds();

		// TODO: Add to separate class
		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200);
	}
}