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
            categories = {RuleCategory.FEATURE, CTA, PORT}
            //#else
            //$$ desc = "Make fungus grow by random tick.",
            //$$ category = {RuleCategory.FEATURE, CTA, PORT}
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
}
