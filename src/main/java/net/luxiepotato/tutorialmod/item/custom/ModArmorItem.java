package net.luxiepotato.tutorialmod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.luxiepotato.tutorialmod.item.ModArmorMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Map;

public class ModArmorItem extends ArmorItem {

    public static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
            .put(ModArmorMaterial.RUBY, new StatusEffectInstance(
                    StatusEffects.HASTE, 400, 1,
                    false, false, true
            )).build();

    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player && hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        boolean hasFullArmor = true;

        // Corresponding slots:
        // helmet = 3, chestplate = 2, leggings = 1, boots = 0
        for (int a = 0; a < 4; a++) {
            if (player.getInventory().getArmorStack(a).isEmpty()) {
                hasFullArmor = false;
                break;
            }
        }

        return hasFullArmor;
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial armorMaterial = entry.getKey();
            StatusEffectInstance statusEffectInstance = entry.getValue();

            if (hasCorrectArmorOn(armorMaterial, player)) {
                setStatusEffectForMaterial(armorMaterial, statusEffectInstance, player);
            }
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial armorMaterial, PlayerEntity player) {
        boolean hasCorrectArmor = true;

        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                hasCorrectArmor = false;
                break;
            }
        }

        if (hasCorrectArmor) {
            // Corresponding slots:
            // helmet = 3, chestplate = 2, leggings = 1, boots = 0
            for (int a = 0; a < 4; a++) {
                if (!((ArmorItem) player.getInventory().getArmorStack(a).getItem()).getMaterial().equals(armorMaterial)) {
                    hasCorrectArmor = false;
                    break;
                }
            }
        }

        return hasCorrectArmor;
    }

    private void setStatusEffectForMaterial(ArmorMaterial material, StatusEffectInstance statusEffectInstance, PlayerEntity player) {
        // creates a new instance every time it runs out
        // can be done better with infinite
        // or ModStatusEffects or something
        boolean hasStatusEffectAlready = player.hasStatusEffect(statusEffectInstance.getEffectType());
        if (!hasStatusEffectAlready) {
            player.addStatusEffect(new StatusEffectInstance(statusEffectInstance));
        }
    }
}
