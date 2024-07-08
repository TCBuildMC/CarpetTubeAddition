package xyz.tcbuildmc.minecraft.carpet.util.world;

import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Comparator;

public class ChunkUtils {
    public static final ChunkTicketType<ChunkPos> END_PEARL = register("end_pearl", 2);
    public static final ChunkTicketType<ChunkPos> NOTE_BLOCK = register("note_block", 300);

    public static void loadChunk(ServerWorld world, ChunkTicketType<ChunkPos> ticket, ChunkPos pos) {
        world.getChunkManager().addTicket(ticket, pos, 3, pos);
    }

    public static ChunkPos fromPos(BlockPos pos) {
        return new ChunkPos(pos.getX() >> 4, pos.getZ() >> 4);
    }

    public static ChunkPos fromPos(Vec3d pos) {
        return new ChunkPos(MathHelper.floor(pos.getX()) >> 4, MathHelper.floor(pos.getZ()) >> 4);
    }

    public static ChunkPos withVelocity(Vec3d pos, Vec3d velocity) {
        return new ChunkPos(MathHelper.floor(pos.getX() + velocity.getX()) >> 4, MathHelper.floor(pos.getZ() + velocity.getZ()));
    }

    public static ChunkTicketType<ChunkPos> register(String id, int time) {
        return ChunkTicketType.create(id, Comparator.comparingLong(ChunkPos::toLong), time);
    }
}
