package game;
import java.awt.image.BufferedImage;
import input.InputFacade;
import graphics.Sprite;
import input.KeyActions;


public class Player extends GameObject {
    private Sprite idle_sprite;
    private Sprite run_sprite;
    private Sprite currentSprite;

    private static int scale = 5;

    private int speed = 250;
    public Player(){
        super(500, 500, scale, scale);
        idle_sprite = new Sprite("BIRDMANIDLE", 16, 16, 5, 0.2 );
        run_sprite = new Sprite("BIRDMANRUNS", 16, 16, 7, 0.05);

        currentSprite = idle_sprite;
    }

    @Override
    public BufferedImage getTexture(){
        return currentSprite.getCurrentFrame();
    }


    public void update(double delta) {
        var direction = getDirection();

        move(direction, delta);

        if (direction.getLength() != 0) {
            updateSprite(delta, run_sprite);
        }
        else{
            updateSprite(delta, idle_sprite);
        }
        if (direction.x < 0){
            scaleX = -scale;
        }
        else if (direction.x > 0) {
            scaleX = scale;
        }
    }

    private Vector2D getDirection(){
        Vector2D direction = new Vector2D(
            (InputFacade.pressed(KeyActions.moveRight) ? 1 : 0) - (InputFacade.pressed(KeyActions.moveLeft) ? 1 : 0),
            (InputFacade.pressed(KeyActions.moveDown) ? 1 : 0) - (InputFacade.pressed(KeyActions.moveUp) ? 1 : 0)
        );
        direction.normalize();
        return direction;
    }
    private void move(Vector2D direction, double delta){
        x += direction.x * speed * delta;
        y += direction.y * speed * delta;
    }

    private void updateSprite(double delta, Sprite new_sprite){
        if (new_sprite == null || currentSprite == new_sprite) {
            currentSprite.update(delta);
            return;
        }

        currentSprite.reset();
        currentSprite = new_sprite;
        currentSprite.update(delta);
    }
}
