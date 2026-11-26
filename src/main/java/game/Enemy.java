package game;

public abstract class Enemy extends GameObject{
    protected final double speed;
    protected final Vector2D direction = new Vector2D(0, 0);
    protected boolean facingLeft = true; 

    public Enemy(Vector2D position, Vector2D scale, double speed) {
        super(position, scale);
        this.speed = speed;
    }
    public void setFacing(double x, double y) {
        direction.x = x;
        direction.y = y;
        if (x < 0) facingLeft = false;
    }
    public void update(double delta) {
    position.x += direction.x * speed * delta;
    position.y += direction.y * speed * delta;
    }
    public void track(GameObject target){
        Vector2D targetPos = target.position;
        Vector2D direction = new Vector2D(targetPos.x - position.x, targetPos.y - position.y);
        direction = direction.normalize();
        
    }
}

 
