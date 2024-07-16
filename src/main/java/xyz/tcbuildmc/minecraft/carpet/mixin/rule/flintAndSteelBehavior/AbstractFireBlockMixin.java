package xyz.tcbuildmc.minecraft.carpet.mixin.rule.flintAndSteelBehavior;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 <a href="https://github.com/whoImT/carpet-addons">whoImT/carpet-addons</a>
 */
@Mixin(AbstractFireBlock.class)
public abstract class AbstractFireBlockMixin {
    @Inject(
            //#if MC >= 11700
            method = "canPlaceAt",
            //#else
            //$$ method = "method_30032",
            //#endif
            at = @At("HEAD"),
            cancellable = true
    )
    private static void cta$flintAndSteelBehavior$activates(World world, BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (CarpetTubeAdditionSettings.flintAndSteelBehavior == CarpetTubeAdditionSettings.FlintAndSteelBehaviors.TRUE &&
                world.isAir(pos)) {

            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
