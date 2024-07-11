package xyz.tcbuildmc.minecraft.carpet.mixin.rule.harvestableTallPlant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.util.item.ItemStackUtils;

/**
 * 移植自 JankAddons
 */
@Mixin(TallPlantBlock.class)
public abstract class TallPlantBlockMixin {
    @Redirect(
            method = "onBreak",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/TallPlantBlock;dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V"
            )
    )
    private void cta$harvestableTallPlant$onBreakPlant(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack itemStack) {
        if (CarpetTubeAdditionSettings.harvestableTallPlant &&
                cta$harvestableTallPlant$isValidItem(itemStack) &&
                cta$harvestableTallPlant$isValidBlock(state)) {

            Block.dropStack(world, pos, state.getBlock().asItem().getDefaultStack());
        } else {
            Block.dropStacks(state, world, pos, blockEntity, entity, itemStack);
        }
    }

    @Unique
    private boolean cta$harvestableTallPlant$isValidItem(ItemStack stack) {
        return ItemStackUtils.is(stack, Items.SHEARS);
    }

    @Unique
    private boolean cta$harvestableTallPlant$isValidBlock(BlockState state) {
        return state.isOf(Blocks.TALL_GRASS) || state.isOf(Blocks.LARGE_FERN);
    }
}
