package game;

import java.util.List;
import graphics.ImageLoader;
import graphics.Sprite;
import graphics.Texture;

public final class Janitor extends Enemy{
    private Sprite idle_sprite;
    private Sprite run_sprite;
    private Sprite currentSprite;
    
    GameObject target;

    public Janitor(Vector2D startpos){
        super(startpos, new Vector2D(3, 3), 200);
        idle_sprite = new Sprite("BIRDMANIDLE", 16, 16, 5, 0.2 );
        run_sprite = new Sprite("BIRDMANRUNS", 16, 16, 7, 0.05);
        currentSprite = idle_sprite;
        
        
    }
    public void setTarget(GameObject target){
        this.target = target;
    }
    
    
    @Override
    protected final void queueTextures(List<Texture> out) {
        out.add(currentSprite.getCurrentFrame());
    }
    
   private void move(Vector2D direction, double delta){
    position.x += direction.x * speed * delta;
    position.y += direction.y * speed * delta;
   }


    @Override
    public void update(double delta){
        Vector2D diff = new Vector2D(target.position.x - position.x, target.position.y - position.y);
        if (diff.getLength() > 20){
            diff.normalize();
            move(diff, delta);

            currentSprite = run_sprite;
        }
        else {
            currentSprite = idle_sprite;
        }
        currentSprite.update(delta);
    }
}


