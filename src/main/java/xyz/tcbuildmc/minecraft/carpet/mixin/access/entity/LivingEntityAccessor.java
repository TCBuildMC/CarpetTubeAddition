package xyz.tcbuildmc.minecraft.carpet.mixin.access.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityAccessor {
    //#if MC < 11900
    //$$ @Invoker("getXpToDrop")
    //$$ int invokeGetXp(PlayerEntity player);
    //#endif
}
