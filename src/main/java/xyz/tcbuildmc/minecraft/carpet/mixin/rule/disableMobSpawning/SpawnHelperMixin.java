package xyz.tcbuildmc.minecraft.carpet.mixin.rule.disableMobSpawning;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(SpawnHelper.class)
public abstract class SpawnHelperMixin {
    @Inject(
            method = "isAcceptableSpawnPosition",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void cta$disableMobSpawning(ServerWorld world, Chunk chunk, BlockPos.Mutable pos, double squaredDistance, CallbackInfoReturnable<Boolean> cir) {
        Identifier dimension = world.getRegistryKey().getValue();

        if (CarpetTubeAdditionSettings.disableMobSpawningOverworld && World.OVERWORLD.getValue().equals(dimension)) {
            cir.setReturnValue(false);
        }

        if (CarpetTubeAdditionSettings.disableMobSpawningNether && World.NETHER.getValue().equals(dimension)) {
            cir.setReturnValue(false);
        }

        if (CarpetTubeAdditionSettings.disableMobSpawningTheEnd && World.END.getValue().equals(dimension)) {
            cir.setReturnValue(false);
        }
    }
}
