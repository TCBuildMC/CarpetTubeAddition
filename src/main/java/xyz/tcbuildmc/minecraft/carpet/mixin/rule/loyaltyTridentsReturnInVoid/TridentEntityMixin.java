package xyz.tcbuildmc.minecraft.carpet.mixin.rule.loyaltyTridentsReturnInVoid;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 <a href="https://github.com/Lunaar-SMP/lunaar-carpet-addons">Lunaar Carpet Addons</a>
 */
@Mixin(TridentEntity.class)
public abstract class TridentEntityMixin extends PersistentProjectileEntity {
    @Shadow
    @Final
    private static TrackedData<Byte> LOYALTY;

    public TridentEntityMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void tickInVoid() {
        int level = getDataTracker().get(LOYALTY);

        if (CarpetTubeAdditionSettings.loyaltyTridentsReturnInVoid && level > 0) {
            setNoClip(true);
            return;
        }

        super.tickInVoid();
    }
}
