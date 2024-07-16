package xyz.tcbuildmc.minecraft.carpet.mixin.rule.stackingPotionDuration;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 <a href="https://github.com/whoImT/carpet-addons">whoImT/carpet-addons</a>
 */
@Mixin(PotionItem.class)
public abstract class PotionItemMixin {
    @Redirect(
            //#if MC >= 12000
            method = "method_57389", // lambda method
            //#else
            //$$ method = "finishUsing",
            //#endif
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"
            )
    )
    private static boolean cta$stackingPotionDuration$normalPotion$addEffect(LivingEntity instance, StatusEffectInstance effect) {
        if (CarpetTubeAdditionSettings.stackingPotionDuration == CarpetTubeAdditionSettings.StackingPotionDurationOption.NORMAL_POTION ||
                CarpetTubeAdditionSettings.stackingPotionDuration == CarpetTubeAdditionSettings.StackingPotionDurationOption.TRUE) {

            StatusEffectInstance oldEffect = instance.getStatusEffect(effect.getEffectType());

            int oldDuration = 0;
            if (oldEffect != null && oldEffect.getAmplifier() == effect.getAmplifier()) {
                oldDuration = oldEffect.getDuration();
            }

            StatusEffectInstance newEffect = new StatusEffectInstance(effect.getEffectType(),
                    effect.getDuration() + oldDuration, effect.getAmplifier());

            return instance.addStatusEffect(newEffect);
        }

        return instance.addStatusEffect(effect);
    }
}
