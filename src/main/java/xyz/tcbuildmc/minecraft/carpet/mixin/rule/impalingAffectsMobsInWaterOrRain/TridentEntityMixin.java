package xyz.tcbuildmc.minecraft.carpet.mixin.rule.impalingAffectsMobsInWaterOrRain;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.util.entity.DamageUtils;

//#if MC < 12000
//$$ import net.minecraft.entity.EntityGroup;
//#endif

/**
 * 移植自 <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">Lunaar Carpet Addons</a>
 */
@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin {
    //#if MC < 12100
    @Redirect(
            method = "onEntityHit",
            at = @At(
                    value = "INVOKE",

                    //#if MC >= 12000
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityType;)F"
                    //#else
                    //$$ target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F"
                    //#endif
            )
    )
    private float cta$impalingAffectsMobsInWaterOrRain$trident$getDamage(
            ItemStack stack,
            //#if MC >= 12000
            EntityType<?> entityType,
            //#else
            //$$ EntityGroup group,
            //#endif
            EntityHitResult hitResult)
    {
        if (CarpetTubeAdditionSettings.impalingAffectsMobsInWaterOrRain) {
            return DamageUtils.getDamage(stack, (LivingEntity) hitResult.getEntity());
        }

        return EnchantmentHelper.getAttackDamage(stack,
                //#if MC >= 12000
                hitResult.getEntity().getType()
                //#else
                //$$ group
                //#endif
        );
    }
    //#endif
}
