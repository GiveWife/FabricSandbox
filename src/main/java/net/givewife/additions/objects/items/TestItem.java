package net.givewife.additions.objects.items;

import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.objects.templates.NbtCooldownItem;
import net.givewife.additions.objects.templates.NbtItem;
import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.effects.EffectBox;
import net.givewife.additions.particles.effects.EffectPlayer;
import net.givewife.additions.particles.effects.EffectSimpleBox;
import net.givewife.additions.util.NbtHelper;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.positions.player.BodyLocations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
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


}
