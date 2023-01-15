package net.givewife.additions.objects.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.givewife.additions.objects.templates.CustomMaterial;
import net.givewife.additions.objects.templates.ToolTemplate;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;

public class SuperTool extends ToolTemplate {

    //(String name, TagKey<Block> mineable, CustomMaterial material, float attackSpeed, float attackDamage, Item.Settings settings) {
    public SuperTool(String name) {
        super(name, BlockTags.AXE_MINEABLE, new CustomMaterial(1300, 2, 2, 4.0F, 0.0F, Items.OBSIDIAN), 1.0F, 1.0F, new Settings().maxCount(1));
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 30.0F;
    }

    @Override
    public NbtCompound getDefault() {
        NbtCompound compound = new NbtCompound();
        return compound;
    }

    @Override
    public void initNbt(ItemStack stack) {
        stack.setNbt(getDefault());
    }


}
