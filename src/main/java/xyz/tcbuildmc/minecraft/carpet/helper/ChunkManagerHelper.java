package xyz.tcbuildmc.minecraft.carpet.helper;

import net.minecraft.world.SpawnHelper;

public class ChunkManagerHelper {
    private static SpawnHelper.Info info;

    public static SpawnHelper.Info getInfo() {
        return ChunkManagerHelper.info;
    }

    public static void setInfo(SpawnHelper.Info info) {
        ChunkManagerHelper.info = info;
    }
}
