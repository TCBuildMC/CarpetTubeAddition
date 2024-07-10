package xyz.tcbuildmc.minecraft.carpet;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

public class CarpetTubeAdditionSettings {
    public static final String CTA = "CTA";
    public static final String PORT = "port";
    public static final String NOT_VANILLA = "not vanilla";

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Use flintAndSteel item to activate observer block.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean flintAndSteelActivatesObserver = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "Make fungus able to grow by random tick.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#endif
    )
    public static boolean growableFungus = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT},
            //#else
            //$$ desc = "Chance for fungus growth. Also see `growableFungus`.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT},
            //#endif

            options = {"0", "144"},
            strict = false
    )
    public static int fungusGrowthChance = 144;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT},
            //#else
            //$$ desc = "Minimum light level for fungus growth (Include it). Also see `growableFungus`.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT},
            //#endif

            options = {"1", "7", "8", "14", "15"},
            strict = false
    )
    public static int fungusGrowthMinLightLevel = 8;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "Use shears item to get the real tall plant items.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#endif
    )
    public static boolean harvestableTallPlant = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.EXPERIMENTAL, CTA, PORT, NOT_VANILLA}
            //#else
            //$$ desc = "Make pistons able to move enderchests. Requires `movableBlockEntities`.",
            //$$ category = {RuleCategory.EXPERIMENTAL, CTA, PORT}
            //#endif
    )
    public static boolean movableEnderChest = false;

    //#if MC >= 11900
    @Rule(
            categories = {RuleCategory.EXPERIMENTAL, RuleCategory.SURVIVAL, CTA}
    )
    public static boolean movableReinforcedDeepslate = false;
    //#endif

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.EXPERIMENTAL, CTA}
            //#else
            //$$ desc = "Make pistons able to move respawn anchors. Requires `movableBlockEntities`.",
            //$$ category = {RuleCategory.EXPERIMENTAL, CTA}
            //#endif
    )
    public static boolean movableRespawnAnchor = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.EXPERIMENTAL, CTA, NOT_VANILLA}
            //#else
            //$$ desc = "Make pistons able to move end portal frame blocks. Requires `movableBlockEntities`.",
            //$$ category = {RuleCategory.EXPERIMENTAL, CTA}
            //#endif
    )
    public static boolean movableEndPortalFrame = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.EXPERIMENTAL, CTA, NOT_VANILLA}
            //#else
            //$$ desc = "Make pistons able to move spawner blocks. Requires `movableBlockEntities`.",
            //$$ category = {RuleCategory.EXPERIMENTAL, CTA}
            //#endif
    )
    public static boolean movableSpawners = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Make ender pearls able to load chunks when it is flying.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean enderPearlChunkLoader = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, RuleCategory.CREATIVE, CTA, PORT}
            //#else
            //$$ desc = "Make note blocks able to load chunks when play it.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, RuleCategory.CREATIVE, CTA, PORT}
            //#endif
    )
    public static boolean noteBlockChunkLoader = false;

    //#if MC < 11900
    //$$ @Rule(
    //$$         desc = "Minecart will drop itself (Exclude items inside).",
    //$$         category = {RuleCategory.SURVIVAL, RuleCategory.CREATIVE, CTA, PORT}
    //$$ )
    //$$ public static boolean modifyMinecartDrop = false;
    //#endif

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.CREATIVE, CTA, PORT}
            //#else
            //$$ desc = "One hit to kill entities! (Only Creative mode)",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.CREATIVE, CTA, PORT}
            //#endif
    )
    public static boolean creativeOneHitKill = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "Bubble columns will push or pull XP orb entities like with other entities and items.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#endif
    )
    public static boolean xpOrbColumnInteraction = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "Endermen will not be angry when you see them.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#endif
    )
    public static boolean passiveEnderman = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Endermen will only spawn in the end.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean endermanOnlySpawnInTheEnd = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Spawn mobs with spawn egg items will not have AI.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean spawnEggsWithNoAI = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT},
            //#else
            //$$ desc = "Entities will be hurt/heal when stepping on the redstone-powered stonecuttor blocks.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT},
            //#endif

            options = {"-1", "0", "1"},
            strict = false
    )
    public static float stonecuttorDoDamage = 0.0f;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "Mobs will not spawn in overworld.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#endif
    )
    public static boolean disableMobSpawningOverworld = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "Mobs will not spawn in nether.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#endif
    )
    public static boolean disableMobSpawningNether = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "Mobs will not spawn in the end.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#endif
    )
    public static boolean disableMobSpawningTheEnd = false;
}
