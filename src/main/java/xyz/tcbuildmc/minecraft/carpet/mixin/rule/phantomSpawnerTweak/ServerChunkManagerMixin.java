package xyz.tcbuildmc.minecraft.carpet.mixin.rule.phantomSpawnerTweak;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerChunkManager;

//#if MC >= 11800
import net.minecraft.world.SpawnDensityCapper;
//#endif

import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.helper.ChunkManagerHelper;

@Mixin(ServerChunkManager.class)
public abstract class ServerChunkManagerMixin {
    @Redirect(
            method = "tickChunks",
            at = @At(
                    value = "INVOKE",

                    //#if MC >= 11800
                    target = "Lnet/minecraft/world/SpawnHelper;setupSpawn(ILjava/lang/Iterable;Lnet/minecraft/world/SpawnHelper$ChunkSource;Lnet/minecraft/world/SpawnDensityCapper;)Lnet/minecraft/world/SpawnHelper$Info;"
                    //#else
                    //$$ target = "Lnet/minecraft/world/SpawnHelper;setupSpawn(ILjava/lang/Iterable;Lnet/minecraft/world/SpawnHelper$ChunkSource;)Lnet/minecraft/world/SpawnHelper$Info;"
                    //#endif
            )
    )
    private SpawnHelper.Info cta$phantomSpawnerTweak$disablePhantomSpawningWhenMobCapIsFull$infoGetter(
            int i,
            Iterable<Entity> iterable,
            SpawnHelper.ChunkSource chunkSource
            //#if MC >= 11800
            , SpawnDensityCapper spawnDensityCapper
            //#endif
    ) {
        SpawnHelper.Info info = SpawnHelper.setupSpawn(i,
                iterable,
                chunkSource
                //#if MC >= 11800
                , spawnDensityCapper
                //#endif
        );

        ChunkManagerHelper.setInfo(info);
        return info;
    }
}
