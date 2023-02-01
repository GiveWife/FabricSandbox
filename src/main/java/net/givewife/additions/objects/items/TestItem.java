package net.givewife.additions.objects.items;

import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.particles.effects.EffectSnake;
import net.givewife.additions.util.positions.Parabola;
import net.givewife.additions.util.positions.Pos;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
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
        Parabola par = new Parabola(pos, pos.north(4).west(4).down(), 2,100);

        EffectSnake snake = new EffectSnake(pos, 5, 4, world, 10);
        Parabola[] paras = snake.getJumps();

        for(int i = 0; i < paras.length; i++) {
            System.out.println("Paras reach: " + paras[i].getPrint());
            for(int j = 0; j < paras[i].getSteps(); j++) {
                Pos off = paras[i].offset(j);
                world.addParticle(ParticleTypes.END_ROD, off.x(), off.y(), off.z(), 0, 0, 0);
            }
        }
        user.getItemCooldownManager().set(this, 20);

        for(int j = 0; j < 100; j++) {
            Pos off = par.offset(j);
            world.addParticle(ParticleTypes.END_ROD, off.x(), off.y(), off.z(), 0, 0, 0);
        }



        return super.use(world, user, hand);
    }
}
