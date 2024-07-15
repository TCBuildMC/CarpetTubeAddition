package xyz.tcbuildmc.minecraft.carpet.mixin.rule.impalingAffectsMobsInWaterOrRain;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
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
@Mixin(MobEntity.class)
public abstract class MobEntityMixin {
    //#if MC < 12100
    @Redirect(
            method = "tryAttack",
            at = @At(
                    value = "INVOKE",

                    //#if MC >= 12000
                    target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityType;)F"
                    //#else
                    //$$ target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F"
                    //#endif
            )
    )
    private float cta$impalingAffectsMobsInWaterOrRain$mob$getDamage(
            ItemStack stack,
            //#if MC >= 12000
            EntityType<?> entityType,
            //#else
            //$$ EntityGroup group,
            //#endif
            Entity target
    ) {
        if (CarpetTubeAdditionSettings.impalingAffectsMobsInWaterOrRain) {
            return DamageUtils.getDamage(stack, (LivingEntity) target);
        }

        return EnchantmentHelper.getAttackDamage(stack,
                //#if MC >= 12000
                target.getType()
                //#else
                //$$ group
                //#endif
        );
    }
    //#endif
}
