package game;


public class Vector2D {
    protected double x;
    protected double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){return x;}
    public double getY(){return y;}

    public void normalize(){
        double length = Math.sqrt(x * x + y * y);

        if (length != 0) {
            x /= length;
            y /= length;
        }
    }
    public double getLength(){
        return Math.sqrt(x * x + y * y);
    }
}
