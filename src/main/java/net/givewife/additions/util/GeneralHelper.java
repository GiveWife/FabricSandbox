package net.givewife.additions.util;


import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class GeneralHelper {

    private String name;

    public GeneralHelper() {}

    public GeneralHelper(String source) {
        this.name = name;
    }

    public void print(String message) {
        System.out.println("[GH: " + name + "] " + message);
    }

    /**
     * Get held item from entity
     * returns 0 if the entity is not a PlayerEntity
     */
    public ItemStack heldstack(Entity entity) {
        if(entity instanceof PlayerEntity) {
            return ((PlayerEntity)entity).getMainHandStack();
        }
        return null;
    }

    /**
     * Determines whether the given entity is a player
     */
    public boolean isPlayer(Entity entity) {
        return (entity instanceof PlayerEntity);
    }

    /**
     * Determines whether the given stack is the same as the main hand stack
     */
    public boolean mainhandIs(PlayerEntity player, Item item) {
        return (player.getMainHandStack().getItem() == item);
    }

    /**
     * Determines whether the given stack is the same as the off hand stack
     */
    public boolean offhandIs(PlayerEntity player, Item item) {
        return (player.getOffHandStack().getItem() == item);
    }


    /**
     * Returns the entity which gets hit by the player's crosshair from given distance. Returns null if the hit is not an entity
     */
    public HitResult raycastInDirection(MinecraftClient client, float tickDelta, Vec3d direction) {
        Entity entity = client.getCameraEntity();

        System.out.println(" GH: entity: " + entity.getDisplayName());

        if (entity == null || client.world == null) {
            return null;
        }

        double reachDistance = client.interactionManager.getReachDistance() * 10;//Change this to extend the reach
        HitResult target = raycast(entity, reachDistance, tickDelta, false, client.cameraEntity.getRotationVec(tickDelta));

        System.out.println(" GH: target: " + target.getType());

        boolean tooFar = false;
        double extendedReach = reachDistance;
        if (client.interactionManager.hasExtendedReach()) {
            extendedReach = 6.0D;//Change this to extend the reach
            reachDistance = extendedReach;
        } else {
            if (reachDistance > 3.0D) {
                tooFar = true;
            }
        }

        Vec3d cameraPos = entity.getCameraPosVec(tickDelta);
        System.out.println(" GH: vec: " + cameraPos);

        extendedReach = extendedReach * extendedReach;
        if (target != null) {
            extendedReach = target.getPos().squaredDistanceTo(cameraPos);
        }

        Vec3d vec3d3 = cameraPos.add(direction.multiply(reachDistance));
        Box box = entity
                .getBoundingBox()
                .stretch(entity.getRotationVec(1.0F).multiply(reachDistance))
                .expand(1.0D, 1.0D, 1.0D);
        EntityHitResult entityHitResult = ProjectileUtil.raycast(
                entity,
                cameraPos,
                vec3d3,
                box,
                (entityx) -> !entityx.isSpectator() && entityx.collidedSoftly,
                extendedReach
        );

        if (entityHitResult == null) {
            return target;
        }

        Entity entity2 = entityHitResult.getEntity();
        Vec3d vec3d4 = entityHitResult.getPos();
        double g = cameraPos.squaredDistanceTo(vec3d4);
        if (tooFar && g > 9.0D) {
            return null;
        } else if (g < extendedReach || target == null) {
            target = entityHitResult;
            if (entity2 instanceof LivingEntity || entity2 instanceof ItemFrameEntity) {
                client.targetedEntity = entity2;
            }
        }

        return target;
    }

    /**
     * Returns a hitresult from the raycast starting from the given entity
     */
    public HitResult raycast(Entity from, double distance, float tickDelta, boolean includeFluids, Vec3d direction) {
        Vec3d end = from.getCameraPosVec(tickDelta).add(direction.multiply(distance));
        return from.world.raycast(
                new RaycastContext(
                        from.getCameraPosVec(tickDelta),
                        end,
                        RaycastContext.ShapeType.OUTLINE,
                        includeFluids ? RaycastContext.FluidHandling.ANY : RaycastContext.FluidHandling.NONE,
                        from
                ));
    }

}
