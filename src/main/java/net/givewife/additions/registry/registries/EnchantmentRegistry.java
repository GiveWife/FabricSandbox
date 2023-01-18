package net.givewife.additions.registry.registries;

import net.givewife.additions.enchantment.CustomEnchantment;
import net.givewife.additions.enchantment.XpEnchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EnchantmentRegistry {

    public static CustomEnchantment XP = new XpEnchantment("xp");

    public static void register() {
        Registry.register(Registries.ENCHANTMENT, XP.getIdentifier(), XP);
    }

}
