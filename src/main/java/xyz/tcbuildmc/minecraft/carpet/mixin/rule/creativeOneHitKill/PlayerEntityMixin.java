package xyz.tcbuildmc.minecraft.carpet.mixin.rule.creativeOneHitKill;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.mixin.access.entity.EntityAccessor;

import java.util.Arrays;
import java.util.List;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    @Final
    private PlayerAbilities abilities;

    @Unique
    private final List<EntityType<?>> excludedEntityTypes = Arrays.asList(
            EntityType.PLAYER,
            EntityType.ITEM_FRAME
            //#if MC >= 11700
            , EntityType.GLOW_ITEM_FRAME
            //#endif
    );

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;handleAttack(Lnet/minecraft/entity/Entity;)Z",
                    shift = At.Shift.BY,
                    by = -2
            ),
            cancellable = true
    )
    private void cta$creativeOneHitKill$onAttack(Entity target, CallbackInfo ci) {
        if (CarpetTubeAdditionSettings.creativeOneHitKill) {
            World world = ((EntityAccessor) this).getWorld();

            if (!world.isClient() &&
                    this.abilities.creativeMode &&
                    EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(target) &&
                    excludedEntityTypes.contains(target.getType())) {

                if (target instanceof EnderDragonPart) {
                    EnderDragonEntity dragon = ((EnderDragonPart) target).owner;
                    for (EnderDragonPart part : dragon.getBodyParts()) {
                        part.kill();
                    }

                    dragon.kill();
                } else {
                    target.kill();
                }

                ci.cancel();
            }
        }
    }
}
