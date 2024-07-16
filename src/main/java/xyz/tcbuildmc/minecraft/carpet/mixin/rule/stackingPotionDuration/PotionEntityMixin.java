package xyz.tcbuildmc.minecraft.carpet.mixin.rule.stackingPotionDuration;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

@Mixin(PotionEntity.class)
public abstract class PotionEntityMixin {
    /**
     * 移植自 <a href="https://github.com/whoImT/carpet-addons">whoImT/carpet-addons</a>
     */
    @Redirect(
            method = "applySplashPotion",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z"
            )
    )
    private boolean cta$stackingPotionDuration$splashPotion$addEffect(LivingEntity instance, StatusEffectInstance statusEffectInstance, Entity entity) {
        if (CarpetTubeAdditionSettings.stackingPotionDuration == CarpetTubeAdditionSettings.StackingPotionDurationOption.SPLASH_POTION ||
                CarpetTubeAdditionSettings.stackingPotionDuration == CarpetTubeAdditionSettings.StackingPotionDurationOption.TRUE) {

            StatusEffectInstance oldEffect = instance.getStatusEffect(statusEffectInstance.getEffectType());

            int oldDuration = 0;
            if (oldEffect != null && oldEffect.getAmplifier() == statusEffectInstance.getAmplifier()) {
                oldDuration = oldEffect.getDuration();
            }

            StatusEffectInstance newEffect = new StatusEffectInstance(statusEffectInstance.getEffectType(),
                    statusEffectInstance.getDuration() + oldDuration, statusEffectInstance.getAmplifier());

            return instance.addStatusEffect(newEffect, entity);
        }

        return instance.addStatusEffect(statusEffectInstance, entity);
    }
}
