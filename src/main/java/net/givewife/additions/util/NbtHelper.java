package net.givewife.additions.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class NbtHelper {

    public NbtHelper() {
    }


    public boolean checkNbt(ItemStack stack) {
        if(!stack.hasNbt()) {
            System.out.println("NBT Helper: " + stack.getItem().getName() + " doesn't have an nbt compound");
            initNbt(stack);
        }
        return true;
    }

    public void initNbt(ItemStack stack) {
        stack.setNbt(new NbtCompound());
    }

    /**
     * Sets a string on the given tag. Incase of no Nbt Compound it will be created first.
     */
    public void setString(String tag, String value, ItemStack stack) {
        checkNbt(stack);
        NbtCompound nbt = stack.getNbt();
        nbt.putString(tag, value);
    }


    /**
     * Sets an integer on the given tag. Incase of no Nbt Compound it will be created first.
     */
    public void setInt(String tag, int value, ItemStack stack) {
        checkNbt(stack);
        NbtCompound nbt = stack.getNbt();
        nbt.putInt(tag, value);
    }

    /**
     * Sets an integer array on the given tag. Incase of no Nbt Compound it will be created first.
     */
    public void setIntArray(String tag, int[] value, ItemStack stack) {
        checkNbt(stack);
        NbtCompound nbt = stack.getNbt();
        nbt.putIntArray(tag, value);
    }

    /**
     * Returns the value at the given tag. If it doesn't exist it will be created and set to 0.
     * Incase there is no Nbt Compound, it will also be created at the beginning of the function.
     */
    public int getInt(String tag, ItemStack stack) {
        checkNbt(stack);
        NbtCompound nbt = stack.getNbt();
        if(nbt.contains(tag)) return nbt.getInt(tag);
        setInt(tag, 0, stack);
        return 0;
    }

    /**
     * Adds the given amount to the integer tag of the given stack.
     * If there is no Nbt Compound, it will be created. If the tag doesn't exist it will be created and set to 0.
     * The given amount will be added to the tag, if it exists or not.
     */
    public void addInt(String tag, int amount, ItemStack stack) {

        checkNbt(stack);
        NbtCompound nbt = stack.getNbt();

        if(nbt.contains(tag)) {
            int current = getInt(tag, stack);
            setInt(tag, current + amount, stack);
        } else {
            System.out.println("NBT Helper: " + stack.getItem().getName() + " did not have tag " + tag + ". Tag set to 0 + (" + amount + ") = " + amount);
            setInt(tag, amount, stack);
        }

    }

    /**
     * Returns a boolean that determines if the given check is equal to the value at the given tag.
     * If there is no Nbt Compound, it will be created.
     * If the tag doesn't exist it will NOT be created.
     */
    public boolean isStringEqual(String tag, String check, ItemStack stack) {
        checkNbt(stack);
        NbtCompound nbt = stack.getNbt();

        return (nbt.contains(tag) && nbt.getString(tag).equals(check));
    }

    /**
     * Returns a boolean that determines if the given check is equal to the value at the given tag.
     * If there is no Nbt Compound, it will be created.
     * If there is no tag, it will NOT be created.
     */
    public boolean isIntEqual(String tag, int check, ItemStack stack) {
        checkNbt(stack);
        NbtCompound nbt = stack.getNbt();

        return (nbt.contains(tag) && nbt.getInt(tag) == check);
    }

}
