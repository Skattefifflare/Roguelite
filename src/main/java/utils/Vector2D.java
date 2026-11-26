package utils;


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

    public static Vector2D zero() {return new Vector2D(0,0);}
    public static Vector2D one() {return new Vector2D(1,1);}

    public Vector2D copy(){
        return new Vector2D(x, y);
    }
}
