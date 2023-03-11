package net.givewife.additions.objects.items;

import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.particles.effects.EffectSingle;
import net.givewife.additions.particles.printer.figures.NetherPortalAssem;
import net.givewife.additions.particles.printer.figures.NetherReactorAssem;
import net.givewife.additions.registry.registries.ParticleRegistry;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.player.BodyLocations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestItem extends ModItem {

    private final String KEY = "key";
    private final int COOLDOWN = 40;

    //String tag, int cooldown, boolean switchAble, String name,
    public TestItem(String name) {
        super(name, CustomSettings.getDefaultSettings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if(!world.isClient) return super.use(world, user, hand);
        Pos pos = new Pos(user);

        //ObsidianFigure obsidianFigure = new ObsidianFigure(pos.north(2), true, true);
        //obsidianFigure.print(world);

        NetherReactorAssem portal = new NetherReactorAssem(pos.north(4), world);


        user.getItemCooldownManager().set(this, 20);

        return super.use(world, user, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if(selected && world.isClient && entity instanceof PlayerEntity) {

            PlayerEntity player = (PlayerEntity) entity;

            BodyLocations loc = new BodyLocations();

            EffectSingle s = new EffectSingle(loc.getPosTopLeft(new Pos(player), player.bodyYaw), ParticleRegistry.HIGHLIGHT);
            s.run(world);

            s = new EffectSingle(loc.getPosTopLeftBack(new Pos(player), player.bodyYaw), ParticleRegistry.HIGHLIGHT);
            s.run(world);


        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
