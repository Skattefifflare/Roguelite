package game;
import java.util.List;
import graphics.Texture;

public interface Renderable {
    public List<Texture> getTextures();
    public Vector2D getPosition();
    public Vector2D getScale();
}
