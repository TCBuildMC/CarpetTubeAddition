package xyz.tcbuildmc.minecraft.carpet;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

//#if MC >= 11900
import carpet.api.settings.Validators;
//#else
//$$ import carpet.settings.Validator;
//#endif

public class CarpetTubeAdditionSettings {
    public static final String CTA = "CTA";
    public static final String PORT = "port";
    public static final String NOT_VANILLA = "not vanilla";

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Use flintAndSteel item to redstone machines.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static FlintAndSteelBehaviors flintAndSteelBehavior = FlintAndSteelBehaviors.FALSE;

    public enum FlintAndSteelBehaviors {
        FALSE,
        OBSERVER_ONLY,
        TRUE
    }

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "Fungus will grow by random ticks.",
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
            //$$ desc = "Pistons will move the ender chests. Requires `movableBlockEntities`.",
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
            //$$ desc = "Pistons will move the respawn anchors. Requires `movableBlockEntities`.",
            //$$ category = {RuleCategory.EXPERIMENTAL, CTA}
            //#endif
    )
    public static boolean movableRespawnAnchor = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.EXPERIMENTAL, CTA, NOT_VANILLA}
            //#else
            //$$ desc = "Pistons will move the end portal frame blocks. Requires `movableBlockEntities`.",
            //$$ category = {RuleCategory.EXPERIMENTAL, CTA}
            //#endif
    )
    public static boolean movableEndPortalFrame = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.EXPERIMENTAL, CTA, NOT_VANILLA}
            //#else
            //$$ desc = "Pistons will move the spawner blocks. Requires `movableBlockEntities`.",
            //$$ category = {RuleCategory.EXPERIMENTAL, CTA}
            //#endif
    )
    public static boolean movableSpawners = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Ender pearls will load chunks when it is flying.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean enderPearlChunkLoader = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, RuleCategory.CREATIVE, CTA, PORT}
            //#else
            //$$ desc = "Note blocks will load chunks when play it.",
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

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "All XP will be dropped when player's death.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#endif
    )
    public static boolean playerDeathDropAllXP = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#else
            //$$ desc = "End portal frame blocks will drop the eye on them when they have eye.",
            //$$ category = {RuleCategory.FEATURE, RuleCategory.SURVIVAL, CTA, PORT}
            //#endif
    )
    public static boolean toggleableEyeOnEndPortalFrame = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Right-click flower block with other flower to replace it.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static ReplaceableFlowersOptions replaceableFlowers = ReplaceableFlowersOptions.FALSE;

    public enum ReplaceableFlowersOptions {
        FALSE,
        SMALL_FLOWERS_ONLY,
        TALL_FLOWERS_ONLY,
        TRUE
    }

    //#if MC >= 11700
    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Right-click pot block with other flower to replace the flower inside.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean replaceableFlowersInPot = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Right-click pot block to take the flower inside.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean pickFlowersFromPot = false;
    //#endif

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Phantoms will not spawn for creative players.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean disablePhantomSpawningForCreative = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Phantoms will not spawn in mushroom island.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean disablePhantomSpawningInMushroomIsland = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Allows tridents thrown by drowned to use the enchantments on the trident held by said drowned.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean drownedUseEnchantedTridents = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Loyalty tridents will return to their owner when thrown into the void.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean loyaltyTridentsReturnInVoid = false;

    // FIXME 1.21
    //#if MC < 12100
    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Apply the Impaling enchantment on any mob that is in water or rain.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean impalingAffectsMobsInWaterOrRain = false;
    //#endif

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Phantoms will not spawn when mobcap is full.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean disablePhantomSpawningWhenMobCapIsFull = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT, RuleCategory.OPTIMIZATION},
            validators = {Validators.NonNegativeNumber.class},
            //#else
            //$$ desc = "Changes the distance projectiles check for collisions. If set to 0 all Blocks to the destination will be checked which is the Vanilla behaviour.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT, RuleCategory.OPTIMIZATION},
            //$$ validate = {Validator.NONNEGATIVE_NUMBER.class},
            //#endif
            options = {"0", "200"},
            strict = false
    )
    public static int projectileRaycastLength = 0;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Potions can be drunk instantly.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean potionInstantUse = false;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Combines the duration of potions.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
            //#endif
    )
    public static boolean stackingPotionDuration = false;
}
