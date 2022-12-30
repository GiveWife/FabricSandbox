package net.givewife.additions.registry.keybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class VelocityKeybind extends CustomKeybind {

    public VelocityKeybind(String name, String category, InputUtil.Type type, int key) {
        super(name, category, type, key);
    }

    @Override
    public void run(MinecraftClient client) {

        ClientPlayerEntity player = client.player;
        Vec3d vec = player.getRotationVec(0.0f);

        double camX = player.getRotationVec(1f).x;
        double camY = player.getRotationVec(1f).y;
        double camZ = player.getRotationVec(1f).z;

        double velocityAddedX = camX * 1.005F;
        double velocityAddedY = camY * 1.0001F;
        double velocityAddedZ = camZ * 1.05F;

        double currentVelocityX = player.getVelocity().x + velocityAddedX;
        double currentVelocityY = player.getVelocity().y + velocityAddedY;
        double currentVelocityZ = player.getVelocity().z + velocityAddedZ;

        player.setVelocity(currentVelocityX, currentVelocityY, currentVelocityZ);

    }

}
