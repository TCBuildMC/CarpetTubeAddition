package xyz.tcbuildmc.minecraft.carpet.mixin.rule.growableFungus;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FungusBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.mixin.helper.AbstractBlockMixin;

/**
 * 移植自 JankAddons
 */
@Mixin(FungusBlock.class)
public abstract class FungusBlockMixin extends AbstractBlockMixin {
    @Shadow
    public abstract void grow(ServerWorld world, Random random, BlockPos pos, BlockState state);

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void cta$growableFungus$enableRandomTick(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Block nylium, AbstractBlock.Settings settings, CallbackInfo ci) {
        this.randomTicks = true;
    }

    @Override
    public void onRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (CarpetTubeAdditionSettings.growableFungus &&
                random.nextInt(CarpetTubeAdditionSettings.fungusGrowthChance) == 0 &&
                world.getLightLevel(pos) >= CarpetTubeAdditionSettings.fungusGrowthMinLightLevel) {

            this.grow(world, random, pos, state);
        }
    }
}
