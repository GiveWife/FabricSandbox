package net.givewife.additions.registry.keybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class VelocityKeybind extends CustomKeybind {

    public VelocityKeybind(String name, String category, InputUtil.Type type, int key) {
        super(name, category, type, key);
    }

    @Override
    public void run(MinecraftClient client) {



    }

}
