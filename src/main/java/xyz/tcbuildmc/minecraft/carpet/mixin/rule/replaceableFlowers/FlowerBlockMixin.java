package xyz.tcbuildmc.minecraft.carpet.mixin.rule.replaceableFlowers;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.PlantBlock;

//#if MC >= 11900
import net.minecraft.block.SuspiciousStewIngredient;
//#endif

import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.util.item.ItemStackUtils;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(FlowerBlock.class)
public abstract class FlowerBlockMixin extends PlantBlock
    //#if MC >= 11900
        implements SuspiciousStewIngredient
    //#endif
{
    public FlowerBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if (cta$replaceableFlowers$checkSettings()) {
            return context.getStack().isEmpty() || !ItemStackUtils.is(context.getStack(), this.asItem());
        }

        return super.canReplace(state, context);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (cta$replaceableFlowers$checkSettings() && !newState.isAir() && !moved) {
            dropStack(world, pos, new ItemStack(this.asItem(), 1));
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Unique
    private boolean cta$replaceableFlowers$checkSettings() {
        return CarpetTubeAdditionSettings.replaceableFlowers == CarpetTubeAdditionSettings.ReplaceableFlowersOptions.SMALL_FLOWERS_ONLY ||
                CarpetTubeAdditionSettings.replaceableFlowers == CarpetTubeAdditionSettings.ReplaceableFlowersOptions.TRUE;
    }
}
