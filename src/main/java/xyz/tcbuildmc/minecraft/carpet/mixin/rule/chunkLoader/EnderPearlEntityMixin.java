package xyz.tcbuildmc.minecraft.carpet.mixin.rule.chunkLoader;

import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.util.world.ChunkUtils;

/**
 * 移植自 Carpet LAS Addition
 */
@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlEntityMixin {
    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void cta$chunkLoader$enderPearl_tick(CallbackInfo ci) {
        if (CarpetTubeAdditionSettings.enderPearlChunkLoader) {
            EnderPearlEntity enderPearl = (EnderPearlEntity) (Object) this;
            if (enderPearl.getEntityWorld() instanceof ServerWorld) {
                Vec3d pos = enderPearl.getPos();
                Vec3d velocity = enderPearl.getVelocity();

                ChunkUtils.loadChunk((ServerWorld) enderPearl.getEntityWorld(), ChunkUtils.END_PEARL, ChunkUtils.fromPos(pos));
                ChunkUtils.loadChunk((ServerWorld) enderPearl.getEntityWorld(), ChunkUtils.END_PEARL, ChunkUtils.withVelocity(pos, velocity));
            }
        }
    }
}
