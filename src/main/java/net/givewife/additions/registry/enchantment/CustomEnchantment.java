package net.givewife.additions.registry.enchantment;

import net.givewife.additions.Main;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;

public abstract class CustomEnchantment extends Enchantment {

    private String id;

    public CustomEnchantment(String id, Enchantment.Rarity rarity, EnchantmentTarget target, EquipmentSlot[] slots) {
        super(rarity, target, slots);
        this.id = id;
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public Identifier getIdentifier() {
        return new Identifier(Main.MODID, this.id);
    }

}
