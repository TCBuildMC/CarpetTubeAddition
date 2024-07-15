package xyz.tcbuildmc.minecraft.carpet.mixin.rule.replaceableFlowersInPot;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

//#if MC >= 11700
import net.minecraft.world.event.GameEvent;
//#endif

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

import java.util.Map;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotBlockMixin {
    //#if MC >= 11700
    @Shadow
    @Final
    private static Map<Block, Block> CONTENT_TO_POTTED;

    @Shadow public abstract Block getContent();

    @Inject(
            method = "onUse",
            at = @At(
                    value = "TAIL",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true
    )
    private void cta$replaceableFlowersInPot$onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(player.getActiveHand());
        Item item = stack.getItem();
        Block newPot = ((item instanceof BlockItem) ? CONTENT_TO_POTTED.getOrDefault(((BlockItem) item).getBlock(), Blocks.AIR) : Blocks.AIR);

        if (CarpetTubeAdditionSettings.replaceableFlowersInPot &&
                newPot instanceof FlowerPotBlock &&
                !((FlowerPotBlock) newPot).getContent().equals(this.getContent())) {

            cir.setReturnValue(ActionResult.SUCCESS);

            world.setBlockState(pos, newPot.getDefaultState(), 3);
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);

            if (player.isCreative()) {
                return;
            }

            stack.decrement(1);
            ItemStack dropStack = new ItemStack(this.getContent().asItem(), 1);
            if (stack.isEmpty()) {
                player.setStackInHand(player.getActiveHand(), dropStack);
            } else if (!player.giveItemStack(dropStack)) {
                player.dropStack(dropStack);
            }
        }
    }
    //#endif
}
