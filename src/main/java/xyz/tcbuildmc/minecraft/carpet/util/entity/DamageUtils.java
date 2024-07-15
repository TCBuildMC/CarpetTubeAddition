package xyz.tcbuildmc.minecraft.carpet.util.entity;

//#if MC >= 12000
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.registry.entry.RegistryEntry;
//#else
//$$ import net.minecraft.enchantment.ImpalingEnchantment;
//$$ import net.minecraft.entity.EntityGroup;
//#endif

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.EntityTypeTags;
import org.apache.commons.lang3.mutable.MutableFloat;

import java.util.Map;

public class DamageUtils {
    public static float getDamage(ItemStack stack, LivingEntity target) {
        //#if MC >= 12100
        //$$ return 0.0f;
        //#else
        MutableFloat f = new MutableFloat();

        //#if MC >= 12000
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(
                EnchantmentHelper.getEnchantments(stack));

        for (RegistryEntry<Enchantment> registryEntry : builder.getEnchantments()) {
            if (registryEntry.value().equals(Enchantments.IMPALING)) {
                int level = builder.getLevel(registryEntry.value());

                if (target.getType().isIn(EntityTypeTags.AQUATIC) || target.isTouchingWaterOrRain()) {
                    f.add(level * 2.5f);
                }
            } else {
                f.add(EnchantmentHelper.getAttackDamage(stack, target.getType()));
            }
        }
        //#else
        //$$ for (Map.Entry<Enchantment, Integer> entry : EnchantmentHelper.get(stack).entrySet()) {
        //$$     Enchantment enchantment = entry.getKey();
        //$$     int level = entry.getValue();
        //$$
        //$$     if (enchantment instanceof ImpalingEnchantment) {
        //$$         if (target.getGroup().equals(EntityGroup.AQUATIC) || target.isTouchingWaterOrRain()) {
        //$$             f.add(level * 2.5f);
        //$$         }
        //$$     } else {
        //$$         f.add(enchantment.getAttackDamage(level, target.getGroup()));
        //$$     }
        //$$ }
        //#endif

        return f.floatValue();
        //#endif
    }
}
