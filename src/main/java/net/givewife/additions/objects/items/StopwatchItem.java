package net.givewife.additions.objects.items;

import net.givewife.additions.objects.templates.NbtCooldownItem;
import net.givewife.additions.util.Pos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class StopwatchItem extends NbtCooldownItem {

    public StopwatchItem(String tag, int cooldown, String name, Item.Settings settings) {
        super(tag, cooldown, name, settings);
    }

    @Override
    public void function(ItemStack stack, World world, Entity entity) {
        if(!help.isPlayer(entity)) return;
        PlayerEntity user = (PlayerEntity) entity;

        Pos p = new Pos(user);
        List<HostileEntity> list = new ArrayList<HostileEntity>();
        world.collectEntitiesByType(TypeFilter.instanceOf(HostileEntity.class), new Box(p.x()-20, p.y()-20, p.z()-20, p.x()+20, p.y()+20, p.z()+20), (ent) -> (ent.isAngryAt(user)), list, 20);
        System.out.println(">? list: " + list.size());

        for(HostileEntity host : list) {

            host.setTarget(null);
            host.setAttacking(false);

        }
    }

}
