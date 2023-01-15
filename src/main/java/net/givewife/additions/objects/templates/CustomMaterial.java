package net.givewife.additions.objects.templates;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CustomMaterial implements ToolMaterial {

    private int durability, enchant, mininglevel;
    private float miningspeed, attackdamage;
    private Item repair;

    public CustomMaterial(int durability, int enchant, int mininglevel, float miningspeed, float attackdamage, Item repair) {
        this.durability = durability;
        this.enchant = enchant;
        this.mininglevel = mininglevel;
        this.miningspeed = miningspeed;
        this.attackdamage = attackdamage;
        this.repair = repair;
    }

    @Override
    public int getDurability() {
        return this.durability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningspeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackdamage;
    }

    @Override
    public int getMiningLevel() {
        return this.mininglevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchant;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(repair);
    }

}
