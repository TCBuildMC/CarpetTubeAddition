package xyz.tcbuildmc.minecraft.carpet.mixin.access.entity;

import net.minecraft.entity.ExperienceOrbEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ExperienceOrbEntity.class)
public interface ExperienceOrbAccessor {
    @Invoker("applyWaterMovement")
    void invokeApplyWaterMovement();
}
