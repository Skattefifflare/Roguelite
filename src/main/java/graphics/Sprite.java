package graphics;


public class Sprite {
    private Texture[] frames;
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
    public Texture getCurrentFrame(){
        return frames[(int)(timer/frameTime)];
    }
    public void reset(){
        timer = 0;
    }
    public Texture getFrame(int index){
        return frames[index];
    }
}
