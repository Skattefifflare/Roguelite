package graphics;
import java.awt.image.BufferedImage;


public class Texture {
    public BufferedImage image;
    public double offsetX;
    public double offsetY;
    public double scaleX = 1;
    public double scaleY = 1;

    // rotation är INTE implementerat i Renderhandler (11/19)
    private double rotation;

    public Texture(BufferedImage image) {
        this.image = image;
    }

    public Texture(BufferedImage image, double offsetX, double offsetY) {
        this.image = image;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public Texture(BufferedImage image, double offsetX, double offsetY, double scaleX, double scaleY, double rotation) {
        this.image = image;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
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
