package xyz.tcbuildmc.minecraft.carpet.mixin.rule.stackingPotionDuration;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

@Mixin(AreaEffectCloudEntity.class)
public abstract class AreaEffectCloudEntityMixin {
    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z"
            )
    )
    private boolean cta$stackingPotionDuration$lingeringPotion$addEffect(LivingEntity instance, StatusEffectInstance statusEffectInstance, Entity entity) {
        if (CarpetTubeAdditionSettings.stackingPotionDuration == CarpetTubeAdditionSettings.StackingPotionDurationOption.LINGERING_POTION ||
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
