package net.givewife.additions.data;

import net.givewife.additions.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.PlayerRemoveS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

public class ServerState extends PersistentState {

    public HashMap<UUID, PlayerState> players = new HashMap<>();
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {

        NbtCompound playersNbtCompound = new NbtCompound();
        players.forEach((UUID, playerState) -> {

            NbtCompound playerStateNbt = new NbtCompound();
            playerStateNbt.putBoolean(PlayerState.TAG, playerState.hasJumped);

            playersNbtCompound.put(String.valueOf(UUID), playerStateNbt);

        });

        nbt.put("players", playersNbtCompound);

        return nbt;

    }

    public static ServerState createFromNbt(NbtCompound tag) {

        ServerState serverState = new ServerState();
        NbtCompound playersTag = tag.getCompound(PlayerState.COMPOUND_TAG);

        playersTag.getKeys().forEach(key -> {
            PlayerState playerState = new PlayerState();

            playerState.hasJumped = playersTag.getCompound(key).getBoolean(PlayerState.TAG);

            UUID uuid = UUID.fromString(key);
            serverState.players.put(uuid, playerState);
        });

        return serverState;

    }

    public static ServerState getServerState(MinecraftServer server) {
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        ServerState serverState = persistentStateManager.getOrCreate(
                ServerState::createFromNbt,
                ServerState::new,
                Main.MODID);

        serverState.markDirty(); // YOU MUST DO THIS!!!! Or data wont be saved correctly.

        return serverState;
    }

    public static PlayerState getPlayerState(LivingEntity player) {
        ServerState serverState = getServerState(player.world.getServer());

        // Either get the player by the uuid, or we don't have data for him yet, make a new player state
        PlayerState playerState = serverState.players.computeIfAbsent(player.getUuid(), uuid -> new PlayerState());

        return playerState;
    }

}
