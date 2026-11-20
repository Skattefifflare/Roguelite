package graphics;
import java.awt.image.BufferedImage;
import game.Vector2D;

public class Texture {
    public BufferedImage image;
    public Vector2D offset;
    public Vector2D scale;
    public Vector2D scalePivot;

    // rotation är INTE implementerat i Renderhandler (11/19)
    private double rotation;
    private Vector2D rotationPivot;

    public Texture(BufferedImage image) {
        this.image = image;
        offset = new Vector2D(0, 0);
        scale = new Vector2D(1,1);
    }
    public Texture(BufferedImage image, Vector2D offset) {
        this.image = image;
        this.offset = offset;
        scale = new Vector2D(1, 1);
    }
    public Texture(BufferedImage image, Vector2D offset, Vector2D scale, double rotation) {
        this.image = image;
        this.offset = offset;
        this.scale = scale;
        this.rotation = rotation;
    }

    public double getRotation(){
        return rotation;
    }
    public void setRotation(double radians){
        rotation = radians % 2 * Math.PI;
    }
    public void rotate(double radians) {
        rotation += radians;
        rotation %= 2*Math.PI;
    }
}
