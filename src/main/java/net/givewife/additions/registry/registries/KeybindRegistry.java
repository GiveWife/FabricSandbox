package net.givewife.additions.registry.registries;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.givewife.additions.registry.keybinds.CustomKeybind;
import net.givewife.additions.registry.keybinds.HealKeybind;
import net.givewife.additions.registry.keybinds.PushKeybind;
import net.givewife.additions.registry.keybinds.VelocityKeybind;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeybindRegistry {

    private CustomKeybind VELOCITY;
    private CustomKeybind HEAL;
    private CustomKeybind[] keybinds;

    public KeybindRegistry() {
        VELOCITY = new VelocityKeybind("velocity", "test", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V);
        //PUSH = new PushKeybind("push", "test", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B);
        HEAL = new HealKeybind("heal", "test", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H);
        keybinds = getKeybinds();
    }

    private CustomKeybind[] getKeybinds() {
        return new CustomKeybind[] {
            VELOCITY, HEAL
        };
    }

}
