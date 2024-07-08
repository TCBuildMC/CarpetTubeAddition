package xyz.tcbuildmc.minecraft.carpet.mixin.rule.chunkLoader;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;
import xyz.tcbuildmc.minecraft.carpet.util.world.ChunkUtils;

/**
 * 移植自 <a href="https://github.com/GC-server-CN/NoteBlockChunkLoader">GC-server-CN/NoteBlockChunkLoader</a>
 */
@Mixin(NoteBlock.class)
public abstract class NoteBlockMixin {
    @Shadow
    @Final
    public static IntProperty NOTE;

    @Inject(
            method = "playNote",
            at = @At("HEAD")
    )
    private void cta$chunkLoader$noteblock_play(Entity entity, BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        BlockPos downPos = pos.down();
        BlockState downState = world.getBlockState(downPos);

        BlockPos upPos = pos.up();
        BlockState upState = world.getBlockState(upPos);

        if (CarpetTubeAdditionSettings.noteBlockChunkLoader &&
                upState.isOf(Blocks.SMOOTH_QUARTZ_SLAB) &&
                downState.isOf(Blocks.BEACON) &&
                world.isReceivingRedstonePower(pos) &&
                state.get(NOTE) == 24 &&
                world instanceof ServerWorld) {

            ChunkUtils.loadChunk((ServerWorld) world, ChunkUtils.NOTE_BLOCK, ChunkUtils.fromPos(pos));
        }
    }
}
