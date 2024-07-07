package xyz.tcbuildmc.minecraft.carpet;

import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

//#if MC >= 11700
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//#else
//$$ import org.apache.logging.log4j.LogManager;
//$$ import org.apache.logging.log4j.Logger;
//#endif

public class CarpetTubeAdditionMod implements ModInitializer {
    public static final String MOD_ID = "carpet-tube-addition";
    public static final String MOD_NAME = "CarpetTubeAddition";
    public static final String VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();

    //#if MC >= 11700
    public static final Logger LOGGER = LoggerFactory.getLogger("Carpet Tube Addition");
    //#else
    //$$ public static final Logger LOGGER = LogManager.getLogger("Carpet Tube Addition");
    //#endif

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(CarpetTubeAddition.getInstance());
    }
}
