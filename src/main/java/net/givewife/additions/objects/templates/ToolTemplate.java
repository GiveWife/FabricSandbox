package net.givewife.additions.objects.templates;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.givewife.additions.Main;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public abstract class ToolTemplate extends MiningToolItem implements NbtEnforcer {

    private String name;

    public ToolTemplate(String name, TagKey<Block> mineable, CustomMaterial material, float attackSpeed, float attackDamage, Item.Settings settings) {
        super(attackDamage, attackSpeed, material, mineable, settings);
        this.name = name;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!stack.hasNbt()) initNbt(stack);
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    public Identifier getIdentifier() {
        return new Identifier(Main.MODID, name);
    }

}
