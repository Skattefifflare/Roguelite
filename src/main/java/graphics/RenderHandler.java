package graphics;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import game.GameObject;


public class RenderHandler {
    private List<GameObject> objects;
    public RenderPanel panel;

    public RenderHandler() {
        objects = new ArrayList<>();
        panel = new RenderPanel(this);
    }
    public void queue(GameObject object){
        objects.add(object);
    }

    public void draw() {
        panel.repaint();
    }

    public void render(Graphics2D graphics) {
        graphics.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_OFF
        );
        graphics.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
        );
        AffineTransform transform = new AffineTransform();
        graphics.setColor(new Color(32, 25, 35));
        graphics.fillRect(0, 0, 1000, 1000);
        for (var o : objects) {

            var texture = o.getTexture();

            //Note till framtiden: ordning av transform-operationer spelar roll.
            // det går att rotera och scalea runt separata pivots.

            transform.setToIdentity();
            transform.translate(texture.getWidth()/2 + Math.round(o.x), texture.getHeight() + Math.round(o.y));
            transform.scale(o.scaleX, o.scaleY);
            transform.translate(-texture.getWidth() / 2.0, -texture.getHeight());

            graphics.drawImage(texture, transform,  null);

        }
    }

}
