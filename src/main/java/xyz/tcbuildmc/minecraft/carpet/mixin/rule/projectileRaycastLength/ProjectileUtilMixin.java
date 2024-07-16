package xyz.tcbuildmc.minecraft.carpet.mixin.rule.projectileRaycastLength;

import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.tcbuildmc.minecraft.carpet.CarpetTubeAdditionSettings;

/**
 * 移植自 <a href="https://github.com/whoImT/carpet-addons">whoImT/carpet-addons</a>
 */
@Mixin(ProjectileUtil.class)
public abstract class ProjectileUtilMixin {
    @Redirect(
            //#if MC >= 11700
            method = "getCollision(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/entity/Entity;Ljava/util/function/Predicate;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/world/World;FLnet/minecraft/world/RaycastContext$ShapeType;)Lnet/minecraft/util/hit/HitResult;",
            //#else
            //$$ method = "getCollision",
            //#endif
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/math/Vec3d;add(Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec3d;"
            )
    )
    private static Vec3d cta$projectileRaycastLength$setLength(Vec3d instance, Vec3d pos) {
        if (CarpetTubeAdditionSettings.projectileRaycastLength > 0 &&
                pos.length() > CarpetTubeAdditionSettings.projectileRaycastLength) {

            return instance.add(pos.normalize().multiply(CarpetTubeAdditionSettings.projectileRaycastLength));
        }

        return instance.add(pos);
    }
}
