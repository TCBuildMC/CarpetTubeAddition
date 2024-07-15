package xyz.tcbuildmc.minecraft.carpet.mixin.rule.drownedUseEnchantedTridents;

//#if MC >= 12000
import net.minecraft.component.type.ItemEnchantmentsComponent;
//#else
//$$ import net.minecraft.enchantment.LoyaltyEnchantment;
//#endif

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 移植自 <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">Lunaar Carpet Addons</a>
 */
@Mixin(DrownedEntity.class)
public abstract class DrownedEntityMixin {
    @Redirect(
            method = "shootAt",
            at = @At(
                    value = "NEW",
                    target = "net/minecraft/entity/projectile/TridentEntity"
            )
    )
    private TridentEntity cta$drownedUseEnchantedTridents$shootTrident(World world, LivingEntity owner, ItemStack stack) {
        if (CarpetTubeAdditionSettings.drownedUseEnchantedTridents) {
            //#if MC >= 12000
            ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(
                    EnchantmentHelper.getEnchantments(owner.getStackInHand(Hand.MAIN_HAND)));

            builder.remove(entry -> entry.value().equals(Enchantments.LOYALTY));
            EnchantmentHelper.set(stack, builder.build());
            //#else
            //$$ Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack).entrySet().stream()
            //$$         .filter(entry -> !(entry.getKey() instanceof LoyaltyEnchantment))
            //$$         .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            //$$
            //$$ EnchantmentHelper.set(enchantments, stack);
            //#endif
        }

        return new TridentEntity(world, owner, stack);
    }
}
