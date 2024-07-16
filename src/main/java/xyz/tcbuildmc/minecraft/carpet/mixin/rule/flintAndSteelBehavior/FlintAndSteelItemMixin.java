package xyz.tcbuildmc.minecraft.carpet.mixin.rule.flintAndSteelBehavior;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ObserverBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.mixin.access.block.ObServerBlockAccessor;

/**
 * 移植自 Carpet MCT Addition
 */
@Mixin(FlintAndSteelItem.class)
public abstract class FlintAndSteelItemMixin {
    @Inject(
            method = "useOnBlock",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cta$flintAndSteelBehavior$useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (CarpetTubeAdditionSettings.flintAndSteelBehavior == CarpetTubeAdditionSettings.FlintAndSteelBehaviors.OBSERVER_ONLY) {
            World world = context.getWorld();

            if (!world.isClient()) {
                PlayerEntity player = context.getPlayer();
                BlockPos blockPos = context.getBlockPos();
                BlockState blockState = world.getBlockState(blockPos);

                if (blockState.isOf(Blocks.OBSERVER) && player != null && !player.isSneaking()) {
                    ((ObServerBlockAccessor) (ObserverBlock) blockState.getBlock()).invokeTick(
                            blockState, ((ServerWorld) context.getWorld()), blockPos, context.getWorld().getRandom());

                    cir.setReturnValue(ActionResult.SUCCESS);
                }
            } else {
                cir.setReturnValue(ActionResult.PASS);
            }
        }
    }
}
