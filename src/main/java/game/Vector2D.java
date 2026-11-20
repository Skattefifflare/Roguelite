package game;


public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }


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
