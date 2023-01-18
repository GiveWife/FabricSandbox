package net.givewife.additions.enchantment;

import net.givewife.additions.util.GeneralHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class XpEnchantment extends CustomEnchantment {

    public XpEnchantment(String s) {
        super(s, Enchantment.Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{ EquipmentSlot.MAINHAND });
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

        GeneralHelper.getRand(10, 2*level);


        super.onTargetDamaged(user, target, level);
    }

}
