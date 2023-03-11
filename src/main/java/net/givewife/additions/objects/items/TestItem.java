package net.givewife.additions.objects.items;

import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.particles.effects.EffectSingle;
import net.givewife.additions.particles.printer.ParticleFigure;
import net.givewife.additions.particles.printer.figures.GlowstoneFigure;
import net.givewife.additions.particles.printer.figures.GravelFigure;
import net.givewife.additions.particles.printer.figures.ObsidianFigure;
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

        ParticleFigure[] figures = new ParticleFigure[] {
                new ObsidianFigure(pos.north(2).west(2)),
                new GravelFigure(pos.north(2)),
                new GlowstoneFigure(pos.north(2).east(2))
        };

        for(ParticleFigure i : figures)
            i.print(world);




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
