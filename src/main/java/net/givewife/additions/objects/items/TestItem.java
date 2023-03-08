package net.givewife.additions.objects.items;

import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.particles.customparticles.figures.NetherPortalAssem;
import net.givewife.additions.util.positions.Pos;
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

        NetherPortalAssem figure = new NetherPortalAssem(new Pos(user).east(3), world);







        return super.use(world, user, hand);
    }
}
