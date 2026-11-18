package graphics;
import java.awt.image.BufferedImage;


public class Texture {
    private BufferedImage image;
    public BufferedImage image(){return image;}
    private double offsetX;
    public double offsetX(){return offsetX;}
    private double offsetY;
    public double offsetY(){return offsetY;}
    private double scaleX = 1;
    public double scaleX(){return scaleX;}
    private double scaleY = 1;
    public double scaleY(){return scaleY;}
    private double rotation;
    public double roation(){return rotation;}


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



}
