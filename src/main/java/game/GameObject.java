package game;
import java.util.ArrayList;
import java.util.List;
import graphics.Texture;
import utils.Renderable;
import utils.Vector2D;


public abstract class GameObject implements Renderable{

    public Vector2D position;
    @Override
    public final Vector2D getPosition() {return position;}

    public Vector2D scale;
    @Override
    public final Vector2D getScale() {return scale;}

    private final List<Texture> queuedTextures;

    public GameObject(Vector2D position, Vector2D scale){
        this.position = position;
        this.scale = scale;
        queuedTextures = new ArrayList<>();
    }

    @Override
    public final List<Texture> getTextures(){
        queuedTextures.clear();
        queueTextures(queuedTextures);
        return queuedTextures;
    }
    protected abstract void queueTextures(List<Texture> out);



}
