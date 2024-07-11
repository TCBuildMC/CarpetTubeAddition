package xyz.tcbuildmc.minecraft.carpet.util.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackUtils {
    public static boolean is(ItemStack stack, Item target) {
        //#if MC >= 11700
        return stack.isOf(target);
        //#else
        //$$ return stack.isItemEqual(target.getDefaultStack());
        //#endif
    }
}
