import javax.swing.*;
import game.*;
import graphics.*;
import input.InputHandler;
import java.lang.reflect.*;
import input.KeyActions;
import input.mouse.MouseHandler;

public class Game {
    RenderHandler rh;
    Player player;

    public Game() throws InterruptedException, InvocationTargetException {
        System.setProperty("sun.java2d.opengl", "false");
        System.setProperty("sun.java2d.d3d", "false");
        SwingUtilities.invokeAndWait(() -> {
            JFrame window = new JFrame("My Application");

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(800, 600);
            window.setLocationRelativeTo(null);

            rh = new RenderHandler();
            window.add(rh.getPanel());
            window.setVisible(true);
        });
    }
    public void run(){
        initialize();
        gameLoop();
    }

    private void initialize(){
        player = new Player();
        rh.queue(player);

        InputHandler.init(rh.getPanel());
        InputHandler.bindkey("A", KeyActions.moveLeft);
        InputHandler.bindkey("D", KeyActions.moveRight);
        InputHandler.bindkey("W", KeyActions.moveUp);
        InputHandler.bindkey("S", KeyActions.moveDown);

        MouseHandler.init(rh.getPanel());
    }

    private void update(double delta) {
        player.update(delta);
    }
    private void draw(){
        rh.draw();

    }

    private void gameLoop() {
        long last = System.nanoTime();
        while(true) {
            long now = System.nanoTime();
            double delta = (now - last) / 1_000_000_000.0;
            last = now;

            InputHandler.takeSnapshot();
            MouseHandler.takeSnapshot();
            update(delta);
            draw();
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
