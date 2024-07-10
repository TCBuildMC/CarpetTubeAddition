package xyz.tcbuildmc.minecraft.carpet.mixin.rule.stonecuttorDoDamage;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 Carpet Addons Not Found
 */
@Mixin(StonecutterBlock.class)
public abstract class StonecuttorBlockMixin extends Block {
    public StonecuttorBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world,
                            BlockPos pos,
                            //#if MC >= 11700
                            BlockState state,
                            //#endif
                            Entity entity) {

        if (!world.isClient() && world.isReceivingRedstonePower(pos) && CarpetTubeAdditionSettings.stonecuttorDoDamage != 0.0f) {
            if (CarpetTubeAdditionSettings.stonecuttorDoDamage < 0.0f && entity instanceof LivingEntity) {
                ((LivingEntity) entity).heal(-CarpetTubeAdditionSettings.stonecuttorDoDamage);

                return;
            }

            entity.damage(
                    //#if MC >= 11900
                    entity.getDamageSources().generic(),
                    //#else
                    //$$ DamageSource.GENERIC,
                    //#endif
                    CarpetTubeAdditionSettings.stonecuttorDoDamage);

            return;
        }

        super.onSteppedOn(world,
                pos,
                //#if MC >= 11700
                state,
                //#endif
                entity);
    }
}
