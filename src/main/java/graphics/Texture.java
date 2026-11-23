package graphics;
import java.awt.image.BufferedImage;
import game.Vector2D;

public class Texture {
    public BufferedImage image;
    public Vector2D offset;
    public Vector2D scale;
    public Vector2D scalePivot;
    private double rotation;
    public Vector2D rotationPivot;

    public Texture(BufferedImage image) {
        this(image, Vector2D.zero());
    }
    public Texture(BufferedImage image, Vector2D offset) {
        this(image, offset, Vector2D.one());
    }
    public Texture(BufferedImage image, Vector2D offset, Vector2D scale) {
        this(image, offset, scale, new Vector2D(image.getWidth()/2, image.getHeight()));
    }
    public Texture(BufferedImage image, Vector2D offset, Vector2D scale, Vector2D scalePivot) {
        this(image, offset, scale, scalePivot, 0);
    }
    public Texture(BufferedImage image, Vector2D offset, Vector2D scale, Vector2D scalePivot, double rotation) {
        this(image, offset, scale, scalePivot, rotation, new Vector2D(image.getWidth()/2, image.getHeight()/2));
    }
    public Texture(BufferedImage image, Vector2D offset, Vector2D scale, Vector2D scalePivot, double rotation, Vector2D rotationPivot) {
        this.image = image;
        this.offset = offset;
        this.scale = scale;
        this.scalePivot = scalePivot;
        this.rotation = rotation;
        this.rotationPivot = rotationPivot;
    }

    public double getRotation() {return rotation;}
    public void setRotation(double radians) {rotation = radians % (2 * Math.PI);}
    public void rotate(double radians) {rotation = (rotation + radians % (2*Math.PI));}
}
