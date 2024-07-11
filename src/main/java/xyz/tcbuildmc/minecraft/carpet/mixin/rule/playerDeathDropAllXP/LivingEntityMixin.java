package xyz.tcbuildmc.minecraft.carpet.mixin.rule.playerDeathDropAllXP;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.mixin.access.entity.LivingEntityAccessor;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Redirect(
            method = "dropXp",
            at = @At(
                    value = "INVOKE",
                    //#if MC >= 12100
                    //$$ target = "Lnet/minecraft/entity/LivingEntity;getXpToDrop(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;)I"
                    //#elseif MC >= 11900
                    target = "Lnet/minecraft/entity/LivingEntity;getXpToDrop()I"
                    //#else
                    //$$ target = "Lnet/minecraft/entity/LivingEntity;getXpToDrop(Lnet/minecraft/entity/player/PlayerEntity;)I"
                    //#endif
            )
    )
    private int cta$playerDeathDropAllXP$getXp(
            //#if MC >= 12100
            //$$ LivingEntity instance, ServerWorld world, Entity entity
            //#elseif MC >= 11900
            LivingEntity instance
            //#else
            //$$ LivingEntity instance, PlayerEntity player
            //#endif
    ) {

        if (CarpetTubeAdditionSettings.playerDeathDropAllXP && instance instanceof PlayerEntity) {
            float xp = ((PlayerEntity) instance).experienceLevel + ((PlayerEntity) instance).experienceProgress;

            if (xp <= 16) {

                xp = (float) (Math.pow(xp, 2) + 6 * xp);
            } else if (xp < 32) {

                xp = (float) (2.5 * Math.pow(xp, 2) - 40.5 * xp + 360);
            } else {

                xp = (float) (4.5 * Math.pow(xp, 2) - 162.5 * xp + 2220);
            }

            return (int) Math.ceil(xp);
        }

        //#if MC >= 12100
        //$$ return instance.getXpToDrop(world, entity);
        //#elseif MC >= 11900
        return instance.getXpToDrop();
        //#else
        //$$ return ((LivingEntityAccessor) instance).invokeGetXp(player);
        //#endif
    }
}
