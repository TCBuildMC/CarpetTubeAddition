package xyz.tcbuildmc.minecraft.carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;

//#if MC >= 11900
import net.minecraft.command.CommandRegistryAccess;
//#endif

import net.minecraft.server.command.ServerCommandSource;

public class CarpetTubeAddition implements CarpetExtension {
    private static final CarpetTubeAddition INSTANCE = new CarpetTubeAddition();

    public static CarpetTubeAddition getInstance() {
        return INSTANCE;
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetTubeAdditionSettings.class);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher
            //#if MC >= 11900
            , CommandRegistryAccess commandBuildContext
            //#endif
    ) {

    }

    @Override
    public String version() {
        return CarpetTubeAdditionMod.MOD_ID;
    }
}
