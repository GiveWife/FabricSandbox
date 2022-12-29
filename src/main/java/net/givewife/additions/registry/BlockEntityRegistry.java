package net.givewife.additions.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.givewife.additions.Main;
import net.givewife.additions.objects.blockentity.ProjectorBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockEntityRegistry {

    public final BlockEntityType<ProjectorBlockEntity> PROJECTOR_BLOCK_ENTITY;

    public BlockEntityRegistry() {

        PROJECTOR_BLOCK_ENTITY = Registry.register(

                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Main.MODID, "projector_block_entity"),
                FabricBlockEntityTypeBuilder.create(ProjectorBlockEntity::new, BlockRegistry.PROJECTOR_1).build()

        );

    }

}