package xyz.tcbuildmc.minecraft.carpet.mixin.rule.moreMovableBlocks;

import carpet.CarpetSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 JankAddons
 */
@Mixin(PistonBlock.class)
public abstract class PistonBlockMixin {
    @Inject(
            method = "isMovable",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void cta$moreMovableBlocks$checkMoveableBlock(BlockState state, World world, BlockPos pos, Direction direction, boolean canBreak, Direction pistonDir, CallbackInfoReturnable<Boolean> cir) {
        if (CarpetSettings.movableBlockEntities) {
            if (CarpetTubeAdditionSettings.movableEnderChest && state.isOf(Blocks.ENDER_CHEST)) {
                cir.setReturnValue(true);
            }

            if (CarpetTubeAdditionSettings.movableRespawnAnchor && state.isOf(Blocks.RESPAWN_ANCHOR)) {
                cir.setReturnValue(true);
            }
        }

        //#if MC >= 11900
        if (CarpetTubeAdditionSettings.movableReinforcedDeepslate && state.isOf(Blocks.REINFORCED_DEEPSLATE)) {
            cir.setReturnValue(true);
        }
        //#endif
    }
}
