package xyz.tcbuildmc.minecraft.carpet.mixin.access.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.ObserverBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ObserverBlock.class)
public interface ObServerBlockAccessor {
    @Invoker("scheduledTick")
    void invokeTick(BlockState state, ServerWorld world, BlockPos pos, Random random);
}
