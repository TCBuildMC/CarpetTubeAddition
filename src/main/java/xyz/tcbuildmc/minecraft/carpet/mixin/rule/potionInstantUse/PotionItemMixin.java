package xyz.tcbuildmc.minecraft.carpet.mixin.rule.potionInstantUse;

import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 <a href="https://github.com/whoImT/carpet-addons">whoImT/carpet-addons</a>
 */
@Mixin(PotionItem.class)
public abstract class PotionItemMixin {
    @Inject(
            method = "getMaxUseTime",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cta$potionInstantUse$setUseTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (CarpetTubeAdditionSettings.potionInstantUse) {
            cir.setReturnValue(1);
            cir.cancel();
        }
    }
}
