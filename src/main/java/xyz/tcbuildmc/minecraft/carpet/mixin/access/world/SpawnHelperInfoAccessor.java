package xyz.tcbuildmc.minecraft.carpet.mixin.access.world;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SpawnHelper.Info.class)
public interface SpawnHelperInfoAccessor {
    @Invoker("isBelowCap")
    boolean isMobCapNotFull(SpawnGroup group
            //#if MC >= 11800
            , ChunkPos chunkPos
            //#endif
    );
}
