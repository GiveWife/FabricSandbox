package net.givewife.additions.particles.printer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ImageConverter {
    
    public ImageConverter(String path) throws IOException {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        TextureManager manager = minecraftClient.getTextureManager();

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        File runDir;

        String[] args = FabricLoader.getInstance().getLaunchArguments(false);
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("--assetsDir"))
                run(args[i+1]);
        }

        /*
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        //System.out.println(Arrays.toString(directories));
            */
    }

    private void run(String path) throws IOException {

        Path dir = Paths.get(path);

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
    
}
