package net.luxiepotato.tutorialmod.item.custom;

import net.luxiepotato.tutorialmod.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();

        if (!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            boolean foundBlock = false;

            for (int i = 0; i < positionClicked.getY() + 64; i++) {
                BlockPos currentBlockPosition = positionClicked.down(i);
                BlockState currentBlockState = context.getWorld().getBlockState(currentBlockPosition);

                if (isValuableBlock(currentBlockState)) {
                    outputValuableCoordinates(currentBlockPosition, player, currentBlockState.getBlock());
                    foundBlock = true;
                }
            }

            if (!foundBlock) {
                player.sendMessage(Text.literal("No valuable block found"));
            }
        }

        context.getStack().damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));

        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " at " + blockPos.toShortString()), false);
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isIn(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.metal_detector.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
