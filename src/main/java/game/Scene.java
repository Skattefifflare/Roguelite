package game;
import java.util.ArrayList;
import utils.*;
import input.InputHandler;
import graphics.RenderHandler;
import input.KeyActions;
import game.enemies.*;
import input.mouse.*;

public final class Scene {
    private RenderHandler rh;

    private ArrayList<Updateable> updateables;
    private ArrayList<Renderable> renderables;
    private ArrayList<Enemy> enemies;

    private Player player;

    public Scene(RenderHandler rh){
        this.rh = rh;

        updateables = new ArrayList<>();
        renderables = new ArrayList<>();
        enemies = new ArrayList<>();

        InputHandler.init(rh.getPanel());
        MouseHandler.init(rh.getPanel());

        setKeyBinds();
        createObjects();
    }
    public void update(double delta){
        InputHandler.takeSnapshot();
        MouseHandler.takeSnapshot();

        for (var u : updateables){
            u.update(delta);
        }
    }

    private void setKeyBinds(){
        InputHandler.bindkey("A", KeyActions.moveLeft);
        InputHandler.bindkey("D", KeyActions.moveRight);
        InputHandler.bindkey("W", KeyActions.moveUp);
        InputHandler.bindkey("S", KeyActions.moveDown);
    }

    private void createObjects(){
        player = new Player();
        sort(player);

        var janitor = new Janitor(new Vector2D(300, 300));
        janitor.setTarget(player);
        sort(janitor);
    }
    private void sort(Object object){
        if (object instanceof Updateable u) {updateables.add(u);}
        if (object instanceof Renderable r) {renderables.add(r); rh.queue(r); }
        if (object instanceof Enemy e) {enemies.add(e);}
    }
}
