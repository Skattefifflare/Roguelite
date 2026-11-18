package graphics;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;


public class ImageLoader {
    private static BufferedImage loadImage(String name) {
        try (var stream = ImageLoader.class.getResourceAsStream("/images/" + name + ".png")) { //to make path relative to ImageLoader
            if (stream == null) {
                System.err.println("Missing resource: /images/" + name + ".png");
                return null;
            }
            return ImageIO.read(stream);
        } catch (IOException e) {
            System.err.println("Could not read " + name);
            return null;
        }
    }
    public static Texture loadTexture(String name){
        return new Texture(loadImage(name));
    }

    public static Texture[] loadTileMap(String name, int tileWidth, int tileHeight, int tileCount) {
        BufferedImage tilemap = loadImage(name);
        if (tilemap == null) {
            return null;
        }
        Texture[] tiles = new Texture[tileCount];

        int columns = tilemap.getWidth() / tileWidth;
        System.out.println(tilemap.getWidth() + " : " + tileWidth);
        for (int i = 0; i < tileCount; i++) {
            int x = (i % columns) * tileWidth;
            int y = (i / columns) * tileHeight;

            tiles[i] = new Texture(tilemap.getSubimage(x, y, tileWidth, tileHeight));
        }

        return tiles;
    }
}
