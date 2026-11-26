package game;

import java.util.List;
import graphics.ImageLoader;
import graphics.Sprite;
import graphics.Texture;

public class Janitor extends Enemy{
    private Sprite idle_sprite;
    private Sprite run_sprite;
    private Sprite currentSprite;

    public Janitor(Vector2D startpos){
        super(startpos, new Vector2D(3, 3), 200);
        idle_sprite = new Sprite("BIRDMANIDLE", 16, 16, 5, 0.2 );
        run_sprite = new Sprite("BIRDMANRUNS", 16, 16, 7, 0.05);

        currentSprite = idle_sprite;
    }

    @Override
    protected final void queueTextures(List<Texture> out) {
        out.add(currentSprite.getCurrentFrame());
    }
    @Override 
    public void move (){
        
    }
}

