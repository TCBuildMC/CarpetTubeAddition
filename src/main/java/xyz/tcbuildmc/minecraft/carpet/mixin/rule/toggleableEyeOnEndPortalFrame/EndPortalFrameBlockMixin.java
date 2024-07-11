package xyz.tcbuildmc.minecraft.carpet.mixin.rule.toggleableEyeOnEndPortalFrame;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalFrameBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(EndPortalFrameBlock.class)
public abstract class EndPortalFrameBlockMixin extends Block {
    @Shadow
    @Final
    public static BooleanProperty EYE;

    public EndPortalFrameBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(
            //#if MC >= 12000
            BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit
            //#else
            //$$ BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit
            //#endif
    ) {
        if (CarpetTubeAdditionSettings.toggleableEyeOnEndPortalFrame && state.get(EYE)) {
            BlockState newState = state.with(EndPortalFrameBlock.EYE, false);
            world.setBlockState(pos, newState);

            cta$toggleableEyeOnEndPortalFrame$removeEndPortalBlocks(world, pos);

            dropStack(world, pos.up(), new ItemStack(Items.ENDER_EYE, 1));
            return ActionResult.success(world.isClient());
        }

        return ActionResult.PASS;
    }

    @Unique
    private void cta$toggleableEyeOnEndPortalFrame$removeEndPortalBlocks(World world, BlockPos pos) {
        if (!world.getBlockState(pos).isOf(Blocks.END_PORTAL)) {
            return;
        }

        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        cta$toggleableEyeOnEndPortalFrame$removeEndPortalBlocks(world, pos.east());
        cta$toggleableEyeOnEndPortalFrame$removeEndPortalBlocks(world, pos.south());
        cta$toggleableEyeOnEndPortalFrame$removeEndPortalBlocks(world, pos.west());
        cta$toggleableEyeOnEndPortalFrame$removeEndPortalBlocks(world, pos.north());
    }
}
