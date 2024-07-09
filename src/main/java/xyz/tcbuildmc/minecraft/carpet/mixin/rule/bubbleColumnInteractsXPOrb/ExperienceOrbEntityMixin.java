package xyz.tcbuildmc.minecraft.carpet.mixin.rule.bubbleColumnInteractsXPOrb;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.mixin.access.entity.ExperienceOrbAccessor;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbEntityMixin {
    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ExperienceOrbEntity;applyWaterMovement()V"
            )
    )
    private void cta$bubbleColumnInteractsXPOrb$MoveInWater(ExperienceOrbEntity instance) {
        if (CarpetTubeAdditionSettings.xpOrbColumnInteraction) {
            Vec3d velocity = instance.getVelocity();
            instance.setVelocity(
                    velocity.x * (double) 0.99f,
                    velocity.y + (double) (velocity.y < (double) 0.06f ? 5.0E-4f : 0.0f),
                    velocity.z * (double) 0.99f);

            return;
        }

        ((ExperienceOrbAccessor) instance).invokeApplyWaterMovement();
    }
}
