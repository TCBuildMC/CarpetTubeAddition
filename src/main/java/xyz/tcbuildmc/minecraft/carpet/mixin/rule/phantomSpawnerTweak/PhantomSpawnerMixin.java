package xyz.tcbuildmc.minecraft.carpet.mixin.rule.phantomSpawnerTweak;

import net.minecraft.registry.RegistryKey;

//#if MC >= 11800
import net.minecraft.registry.entry.RegistryEntry;
//#endif

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.spawner.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(PhantomSpawner.class)
public abstract class PhantomSpawnerMixin {
    @Redirect(
            method = "spawn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/world/ServerWorld;getPlayers()Ljava/util/List;"
            )
    )
    private List<ServerPlayerEntity> cta$phantomSpawnerTweak$getPlayers(ServerWorld instance) {
        List<ServerPlayerEntity> players = new ArrayList<>();
        for (ServerPlayerEntity player : instance.getPlayers()) {
            if (cta$phantomSpawnerTweak$canSpawn(instance, player)) {
                players.add(player);
            }
        }

        return players;
    }

    @Unique
    private boolean cta$phantomSpawnerTweak$canSpawn(ServerWorld world, ServerPlayerEntity player) {
        if (CarpetTubeAdditionSettings.disablePhantomSpawningForCreative && player.isCreative()) {
            return false;
        }

        BlockPos blockPos = player.getBlockPos();

        //#if MC >= 11800
        RegistryEntry<Biome> biomeEntry = world.getBiome(blockPos);
        RegistryKey<Biome> biomeKey = biomeEntry.getKey().orElse(null);
        //#else
        //$$ Biome biome = world.getBiome(blockPos);
        //#endif

        if (CarpetTubeAdditionSettings.disablePhantomSpawningInMushroomIsland &&
                //#if MC >= 11800
                biomeKey != null && biomeKey.equals(BiomeKeys.MUSHROOM_FIELDS)
                //#else
                //$$ biome.getCategory().equals(Biome.Category.MUSHROOM)
                //#endif
        ) {

            return false;
        }

        return true;
    }
}
