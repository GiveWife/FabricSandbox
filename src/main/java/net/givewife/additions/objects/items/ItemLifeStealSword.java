package net.givewife.additions.objects.items;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.givewife.additions.registry.MessageRegistry;
import net.givewife.additions.util.NbtHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemLifeStealSword extends SwordItem {

    private NbtHelper helper = new NbtHelper();
    private final String ATTACKED_ENTITY = "attacked";
    private final String AMOUNT_HITS = "hits";
    private boolean TARGET_ONE = true;

    public ItemLifeStealSword() {
        super(ToolMaterials.IRON, 2, 4.0F, new Item.Settings().maxCount(1).maxDamage(1200));
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 10.0F;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        if(attacker.world.isClient) return super.postHit(stack, target, attacker);

        String UUID = target.getUuidAsString();
        System.out.println("Here1");

        //Helper function checks if tag exists and if values are the same. Assigns NBT if necessary
        if(helper.isStringEqual(ATTACKED_ENTITY, UUID, stack) || TARGET_ONE) {
            System.out.println("Here2");

            if(helper.isIntEqual(AMOUNT_HITS, 1, stack)) {
                System.out.println("Here3");

                helper.setInt(AMOUNT_HITS, 0, stack);
                System.out.println("Before? amount: " + 3.0F + ", uuid: " + attacker.getUuidAsString());


            } else {

                helper.addInt(AMOUNT_HITS, 1, stack);

            }

        } else {

            helper.setString(ATTACKED_ENTITY, UUID, stack);

        }

        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!stack.hasNbt()) {
            helper.initNbt(stack);
            helper.setString(ATTACKED_ENTITY, "", stack);
            helper.setInt(AMOUNT_HITS, 0, stack);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("heil"));
    }
}
