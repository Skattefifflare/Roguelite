package graphics;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;


public class ImageLoader {
    public static BufferedImage loadImage(String name) {
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
    public static BufferedImage[] loadTileMap(String name, int tileWidth, int tileHeight, int tileCount) {
        BufferedImage tilemap = loadImage(name);
        if (tilemap == null) {
            return null;
        }
        BufferedImage[] tiles = new BufferedImage[tileCount];

        int columns = tilemap.getWidth() / tileWidth;
        System.out.println(tilemap.getWidth() + " : " + tileWidth);
        for (int i = 0; i < tileCount; i++) {
            int x = (i % columns) * tileWidth;
            int y = (i / columns) * tileHeight;

            tiles[i] = tilemap.getSubimage(x, y, tileWidth, tileHeight);
        }

        return tiles;
    }
}
