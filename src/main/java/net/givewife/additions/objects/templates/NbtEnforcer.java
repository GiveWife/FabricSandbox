package net.givewife.additions.objects.templates;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public interface NbtEnforcer {

    public void initNbt(ItemStack stack);

    public NbtCompound getDefault();


}
