package net.givewife.additions.registry.keybinds;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.mob.ZombieEntity;

public class BlindingKeybind extends CustomKeybind {

    public BlindingKeybind(String name, String category, InputUtil.Type type, int key) {
        super(name, category, type, key);
    }

    @Override
    public void run(MinecraftClient client) {

    }

}
