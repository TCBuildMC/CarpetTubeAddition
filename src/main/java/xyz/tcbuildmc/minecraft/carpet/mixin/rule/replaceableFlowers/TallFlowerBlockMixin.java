package xyz.tcbuildmc.minecraft.carpet.mixin.rule.replaceableFlowers;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.util.item.ItemStackUtils;

@Mixin(TallFlowerBlock.class)
public abstract class TallFlowerBlockMixin extends TallPlantBlock implements Fertilizable {
    public TallFlowerBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if (cta$replaceableFlowers$checkSettings()) {
            return context.getStack().isEmpty() || !ItemStackUtils.is(context.getStack(), this.asItem());
        }

        return false;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.get(TallFlowerBlock.HALF) == DoubleBlockHalf.LOWER && !newState.isAir()) {
            dropStack(world, pos, new ItemStack(this.asItem(), 1));
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Unique
    private boolean cta$replaceableFlowers$checkSettings() {
        return CarpetTubeAdditionSettings.replaceableFlowers == CarpetTubeAdditionSettings.ReplaceableFlowersOptions.TALL_FLOWERS_ONLY ||
                CarpetTubeAdditionSettings.replaceableFlowers == CarpetTubeAdditionSettings.ReplaceableFlowersOptions.TRUE;
    }
}
