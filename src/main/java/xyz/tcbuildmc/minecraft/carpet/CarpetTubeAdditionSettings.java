package xyz.tcbuildmc.minecraft.carpet;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

public class CarpetTubeAdditionSettings {
    public static final String CTA = "CTA";
    public static final String PORT = "PORT";

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
            //$$ desc = "Make fungus grow by random tick.",
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

            options = {"0", "144"}
    )
    public static int fungusGrowthChance = 144;

    @Rule(
            //#if MC >= 11900
            categories = {RuleCategory.FEATURE, CTA, PORT},
            //#else
            //$$ desc = "Minimum light level for fungus growth (Include it). Also see `growableFungus`.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT},
            //#endif

            options = {"1", "7", "8", "14", "15"}
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
            categories = {RuleCategory.EXPERIMENTAL, CTA, PORT}
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
}
