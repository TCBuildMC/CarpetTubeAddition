package xyz.tcbuildmc.minecraft.carpet.mixin.rule.passiveEnderman;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin {
    @Inject(
            method = "setTarget",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cta$passiveEnderman$setTarget(LivingEntity target, CallbackInfo ci) {
        if (CarpetTubeAdditionSettings.passiveEnderman && target instanceof PlayerEntity) {
            ci.cancel();
        }
    }
}
