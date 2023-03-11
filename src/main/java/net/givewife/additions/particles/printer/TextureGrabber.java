package net.givewife.additions.particles.printer;

import com.ibm.icu.text.MessagePattern;
import net.givewife.additions.mixins.NativeImageAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.Map;

public class TextureGrabber {

    private SpriteIdentifier id;
    private boolean debug;

    public TextureGrabber(String modelName) {
        this.id = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, new Identifier("minecraft:block/" + modelName));
    }

    public float[][] getRgb() {
        float[][] rgbs = new float[16*16][3];
        Sprite sprite = id.getSprite();
        NativeImage im = ((NativeImageAccessor) sprite.getContents()).getImage();
        int counter = 0;

        for(int i = 0; i < im.getHeight(); i++) {
            for(int j = 0; j < im.getWidth(); j++) {
                Color color = new Color(im.getColor(i, j));
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();

                rgbs[counter][0] = (float) ((float) blue / 255);
                rgbs[counter][1] = (float) ((float) green / 255);
                rgbs[counter][2] = (float) ((float) red / 255);

                counter++;
            }
        }

        return rgbs;
    }

    private String getPrint(int... i) {

        String s = "[";

        for(int x = 0; x < i.length; x++) {

            s += Integer.toString(i[x]);
            if(x < i.length - 1) s += ", ";

        }
        s += "]";

        return s;

    }

    private String getPrint(float... i) {

        String s = "[";

        for(int x = 0; x < i.length; x++) {

            s += Float.toString(i[x]);
            if(x < i.length - 1) s += ", ";

        }
        s += "]";

        return s;

    }

}
