package game;
import java.util.ArrayList;
import java.util.List;
import graphics.Texture;


public abstract class GameObject implements Renderable{

    protected double x;
    protected double y;
    protected double scaleX;
    protected double scaleY;

    private final List<Texture> queuedTextures;

    public GameObject(double x, double y, double scaleX, double scaleY){
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;

        queuedTextures = new ArrayList<>();
    }


    public double getX() {return x;}
    public double getY() {return y;}
    public double getScaleX(){return scaleX;}
    public double getScaleY(){return scaleY;}


    @Override
    public final List<Texture> getTextures(){
        queuedTextures.clear();
        queueTextures(queuedTextures);
        return queuedTextures;
    }
    protected abstract void queueTextures(List<Texture> out);
}
