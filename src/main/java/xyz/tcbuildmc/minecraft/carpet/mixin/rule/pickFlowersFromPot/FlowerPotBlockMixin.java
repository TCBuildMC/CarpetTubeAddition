package xyz.tcbuildmc.minecraft.carpet.mixin.rule.pickFlowersFromPot;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//#if MC >= 11700
import net.minecraft.world.event.GameEvent;
//#endif

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotBlockMixin {
    //#if MC >= 11700
    @Inject(
            method = "onUse",
            at = @At(
                    value = "TAIL"
            ),
            cancellable = true
    )
    private void cta$pickFlowersFromPot$onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        Block potBlock = state.getBlock();
        if (CarpetTubeAdditionSettings.pickFlowersFromPot && potBlock instanceof FlowerPotBlock &&
                !((FlowerPotBlock) potBlock).getContent().equals(Blocks.AIR)) {

            Block content = ((FlowerPotBlock) potBlock).getContent();

            world.setBlockState(pos, Blocks.FLOWER_POT.getDefaultState(), Block.NOTIFY_ALL);
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

            cir.setReturnValue(ActionResult.SUCCESS);
            if (player.isCreative()) {
                return;
            }

            ItemStack contentStack = new ItemStack(content.asItem(), 1);
            if (player.getStackInHand(player.getActiveHand()).isEmpty()) {
                player.setStackInHand(player.getActiveHand(), contentStack);
            } else if (!player.giveItemStack(contentStack)) {
                player.dropStack(contentStack);
            }
        }
    }
    //#endif
}
