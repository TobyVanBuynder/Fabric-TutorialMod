package net.luxiepotato.tutorialmod.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.luxiepotato.tutorialmod.TutorialMod;
import net.luxiepotato.tutorialmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {

    public static final RegistryKey<PointOfInterestType> SOUND_POI_KEY = poiKey("soundpoi");
    public static final PointOfInterestType SOUND_POI = registerPoi("soundpoi", ModBlocks.SOUND_BLOCK);
    public static final VillagerProfession SOUND_MASTER = registerProfession("sound_master", SOUND_POI_KEY);

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> poiType) {
        return Registry.register(
                Registries.VILLAGER_PROFESSION, new Identifier(TutorialMod.MOD_ID, name),
                new VillagerProfession(name,
                        entry -> entry.matchesKey(poiType), entry -> entry.matchesKey(poiType),
                        ImmutableSet.of(), ImmutableSet.of(),
                        SoundEvents.ENTITY_VILLAGER_WORK_SHEPHERD
                )
        );
    }

    private static PointOfInterestType registerPoi(String name, Block block) {
        return PointOfInterestHelper.register(new Identifier(TutorialMod.MOD_ID, name),
                1, 1, block);
    }

    public static RegistryKey<PointOfInterestType> poiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, new Identifier(TutorialMod.MOD_ID, name));
    }

    public static void registerVillagers() {
        TutorialMod.LOGGER.info("Registering Villagers for " + TutorialMod.MOD_ID);
    }
}
