package graphics;
import java.awt.image.*;

public class Sprite {
    private BufferedImage[] frames;
    private double frameTime;
    private double timer;


    public Sprite(String name, int tileWidth, int tileHeight, int tileCount, double frameTime) {
        frames = ImageLoader.loadTileMap(name, tileWidth, tileHeight, tileCount);
        this.frameTime = frameTime;
    }

    public void update(double delta){
        timer += delta;
        timer %= (frameTime * frames.length);
    }
    public BufferedImage getCurrentFrame(){
        return frames[(int)(timer/frameTime)];
    }
    public void reset(){
        timer = 0;
    }
}
