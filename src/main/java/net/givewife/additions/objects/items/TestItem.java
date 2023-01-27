package net.givewife.additions.objects.items;

import net.givewife.additions.objects.blocks.ProjectorBlock;
import net.givewife.additions.objects.templates.CustomSettings;
import net.givewife.additions.objects.templates.NbtItem;
import net.givewife.additions.particles.CustomEffect;
import net.givewife.additions.particles.effects.EffectPlayer;
import net.givewife.additions.particles.effects.EffectTrace;
import net.givewife.additions.registry.registries.BlockRegistry;
import net.givewife.additions.util.NbtHelper;
import net.givewife.additions.util.positions.Pos;
import net.givewife.additions.util.PositionUtilities;
import net.givewife.additions.util.positions.VecSurface;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestItem extends ModItem {

    private final String USE = "use";
    private final String COUNT = "count";
    private final int USETIME = 200;
    //Added nbt again because transferring this pre NbtItem template is more painful
    private NbtHelper nbt = new NbtHelper();

    public TestItem(String name) {
        super(name, CustomSettings.getDefaultSettings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if(world.isClient)
        print(world, player);
        return super.use(world, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(world.isClient && entity instanceof PlayerEntity && selected) {


        }
    }

    private void print(World world, PlayerEntity player) {
        //VecSurface sur = new VecSurface(new Pos(user), new Pos(user).north(50).east().up().up(), 100, 500);
        CustomEffect effect = new EffectPlayer(player);
        effect.run(world);
        //player.sendMessage(Text.literal(String.valueOf("Body Yaw: " + Double.toString(player.bodyYaw) + " ; Radians: " + Double.toString(Math.toRadians(player.bodyYaw)))), true);

    }
}
