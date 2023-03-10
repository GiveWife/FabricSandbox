package net.givewife.additions.particles.printer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.TextureUtil;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.resource.metadata.TextureResourceMetadata;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.ResourceTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.include.com.google.common.io.Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ImageConverter {
    
    public ImageConverter() throws IOException {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextureManager manager = minecraftClient.getTextureManager();

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        File testPath = new File(System.getProperty("user.dir"), "mods");

        getPng(testPath);


    }

    private void run(String path) throws IOException {

        File base = new File(path, "versions");
        base = new File(base, "1.19.3");

        Path dir = base.toPath();

        FileWriter writer = new FileWriter(dir.toFile());
        //Reading the image
        File file= new File(dir.toString());
        BufferedImage img = ImageIO.read(file);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Retrieving contents of a pixel
                int pixel = img.getRGB(x,y);
                //Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                //Retrieving the R G B values
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                writer.append(red+":");
                writer.append(green+":");
                writer.append(blue+"");
                writer.append("\n");
                writer.flush();
            }
        }
        writer.close();

    }

    private void getPng(File path) throws IOException {

        ResourceManager manager = MinecraftClient.getInstance().getResourceManager();
        TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();

        Identifier id = Registries.BLOCK.getId(Blocks.OBSIDIAN);
        Registries.BLOCK.getEntry(Blocks.OBSIDIAN).getKey().get().getRegistry().getPath();
        System.out.println("id: " + id.toString());
        System.out.println("path:"  + Registries.BLOCK.getId(Blocks.OBSIDIAN).getPath() + "; ");
        AbstractTexture t = textureManager.getTexture(id);
        t.bindTexture();

        try {
            //Resource resource = manager.getResourceOrThrow(id);
            ResourceTexture resource = new ResourceTexture(id);
            Resource r = manager.getResourceOrThrow(id);
            NativeImage image = NativeImage.read(manager.open(id));
        } catch(FileNotFoundException e) {
            System.out.println("Not found?");
        }

        //, int textureId, int scales, int width, int height
        //Path path = Paths.get(Registries.BLOCK.getId(Blocks.OBSIDIAN).getPath());
        //TextureUtil.writeAsPNG(path.toPath(), "texture_test", t.getGlId(), 1, 16, 16);

        //TextureUtil.readResource(manager.open(id));
        load(manager, id);

    }

    public void load(ResourceManager resourceManager, Identifier id) {
        try {

            NativeImage nativeImage;
            Resource resource = resourceManager.getResourceOrThrow(id);
            try (InputStream inputStream = resource.getInputStream();){
                nativeImage = NativeImage.read(inputStream);
                System.out.println("Image!");
            }
            TextureResourceMetadata textureResourceMetadata = null;
            try {
                textureResourceMetadata = resource.getMetadata().decode(TextureResourceMetadata.READER).orElse(null);
            }
            catch (RuntimeException runtimeException) {
                System.out.println("hi, exception!");
            }
            System.out.println("native image!");
        }
        catch (IOException iOException) {
            System.out.println("exception");
        }
    }
}
