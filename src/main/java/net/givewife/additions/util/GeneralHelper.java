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

import java.util.Random;

import static java.text.ChoiceFormat.nextDouble;

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
    public Entity raycastInDirection(MinecraftClient client, float tickDelta, Vec3d direction) {;

        Entity entity = client.getCameraEntity();

        if (entity == null || client.world == null) {
            return null;
        }

        double reachDistance = client.interactionManager.getReachDistance() + 50;//Change this to extend the reach
        HitResult target = raycast(entity, reachDistance, tickDelta, true, client.cameraEntity.getCameraPosVec(0f));

        Vec3d cameraPos = entity.getCameraPosVec(tickDelta);

        if (target != null) {
            reachDistance = target.getPos().squaredDistanceTo(cameraPos);
        }

        Vec3d vec3d3 = cameraPos.add(entity.getRotationVec(0f).multiply(reachDistance));
        Box box = entity
                .getBoundingBox()
                .stretch(entity.getRotationVec(1.0F).multiply(reachDistance))
                .expand(1.0D, 1.0D, 1.0D);
        EntityHitResult entityHitResult = ProjectileUtil.raycast(
                entity,
                cameraPos,
                vec3d3,
                box,
                (entityx) -> !entityx.isSpectator(),
                reachDistance
        );

        if (entityHitResult == null) {
            //System.out.println("Hit result?");
            return null;
        } else {
            target = entityHitResult;
        }

        Entity entity2 = entityHitResult.getEntity();
        Vec3d vec3d4 = entityHitResult.getPos();

        if (target != null) {
            target = entityHitResult;
            if (entity2 instanceof LivingEntity || entity2 instanceof ItemFrameEntity) {
                client.targetedEntity = entity2;
            }
        }

        return entityHitResult.getEntity();
    }

    /**
     * Returns a hitresult from the raycast starting from the given entity
     */
    public HitResult raycast(Entity from, double distance, float tickDelta, boolean includeFluids, Vec3d direction) {
        Vec3d rot = from.getRotationVec(0f).multiply(distance);

        return from.world.raycast(
                new RaycastContext(
                        from.getCameraPosVec(tickDelta),
                        rot,
                        RaycastContext.ShapeType.OUTLINE,
                        includeFluids ? RaycastContext.FluidHandling.ANY : RaycastContext.FluidHandling.NONE,
                        from
                ));
    }


    /**
     * Returns an integer between the given values.
     */
    public static int getRand(int max, int low) {
        Random rand = new Random();
        return rand.nextInt(max-low) + low;
    }

    public static double getRadial(double low, double max) {
        Random rand = new Random();
        double gen = rand.nextDouble(max-low) + low;
        return gen;
    }

    public String intToString(int[] arr) {
        String s = "[";
        for(int i = 0; i < arr.length; i++) {
            s += Integer.toString(arr[i]);
            if(i + 1 < arr.length) s += ", ";
        }
        s += "]";
        return s;
    }


}
