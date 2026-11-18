package game;


public abstract class GameObject implements Renderable{

    public double x;
    public double y;
    public double scaleX;
    public double scaleY;

    public GameObject(double x, double y, double scaleX, double scaleY){
        this.x = x;
        this.y = y;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
}
