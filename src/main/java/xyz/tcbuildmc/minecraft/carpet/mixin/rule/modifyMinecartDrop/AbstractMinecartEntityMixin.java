package xyz.tcbuildmc.minecraft.carpet.mixin.rule.modifyMinecartDrop;

import net.minecraft.entity.vehicle.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 Carpet LAS Addition
 */
@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin {
    //#if MC < 11900
    //$$ @Redirect(
    //$$         method = "dropItems",
    //$$         at = @At(
    //$$                 value = "NEW",
    //$$                 target = "net/minecraft/item/ItemStack"
    //$$         )
    //$$ )
    //$$ private ItemStack cta$modifyMinecartDrop$newItemStack(ItemConvertible item) {
    //$$     if (CarpetTubeAdditionSettings.modifyMinecartDrop) {
    //$$         AbstractMinecartEntity minecart = (AbstractMinecartEntity) (Object) this;
    //$$
    //$$         if (minecart instanceof ChestMinecartEntity) {
    //$$             return new ItemStack(Items.CHEST_MINECART);
    //$$         } else if (minecart instanceof HopperMinecartEntity) {
    //$$             return new ItemStack(Items.HOPPER_MINECART);
    //$$         } else if (minecart instanceof FurnaceMinecartEntity) {
    //$$             return new ItemStack(Items.FURNACE_MINECART);
    //$$         } else if (minecart instanceof CommandBlockMinecartEntity) {
    //$$            return new ItemStack(Items.COMMAND_BLOCK_MINECART);
    //$$         } else if (minecart instanceof TntMinecartEntity) {
    //$$             return new ItemStack(Items.TNT_MINECART);
    //$$         }
    //$$     }
    //$$
    //$$     return new ItemStack(item);
    //$$ }
    //#endif
}
