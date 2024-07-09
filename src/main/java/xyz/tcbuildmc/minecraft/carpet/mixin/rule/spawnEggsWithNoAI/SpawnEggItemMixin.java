package xyz.tcbuildmc.minecraft.carpet.mixin.rule.spawnEggsWithNoAI;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(SpawnEggItem.class)
public abstract class SpawnEggItemMixin {
    @Redirect(
            method = "useOnBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityType;spawnFromItemStack(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/SpawnReason;ZZ)Lnet/minecraft/entity/Entity;"
            )
    )
    private Entity cta$spawnEggsWithNoAI$useOnBlockAndSpawn(EntityType<?> instance, ServerWorld world, ItemStack stack, PlayerEntity player, BlockPos pos, SpawnReason spawnReason, boolean alignPosition, boolean invertY) {
        Entity entity = instance.spawnFromItemStack(world, stack, player, pos, spawnReason, alignPosition, invertY);

        return cta$spawnEggsWithNoAI$createEntity(entity);
    }

    @Redirect(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityType;spawnFromItemStack(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/SpawnReason;ZZ)Lnet/minecraft/entity/Entity;"
            )
    )
    private Entity cta$spawnEggsWithNoAI$useAndSpawn(EntityType<?> instance, ServerWorld world, ItemStack stack, PlayerEntity player, BlockPos pos, SpawnReason spawnReason, boolean alignPosition, boolean invertY) {
        Entity entity = instance.spawnFromItemStack(world, stack, player, pos, spawnReason, alignPosition, invertY);

        return cta$spawnEggsWithNoAI$createEntity(entity);
    }

    @Unique
    private Entity cta$spawnEggsWithNoAI$createEntity(Entity instance) {
        if (CarpetTubeAdditionSettings.spawnEggsWithNoAI && instance instanceof MobEntity) {
            MobEntity mob = (MobEntity) instance;
            mob.setAiDisabled(true);
            return mob;
        }

        return instance;
    }
}
