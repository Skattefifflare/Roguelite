package graphics;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import utils.Vector2D;
import utils.Renderable;;


public final class RenderHandler {
    private List<Renderable> objects;
    private RenderPanel panel;
    public RenderPanel getPanel(){return panel;}

    public RenderHandler() {
        objects = new ArrayList<>();
        panel = new RenderPanel(this);
    }


    public void queue(Renderable object){
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
        for (var obj : objects) {

            for (var texture : obj.getTextures()){
                double scaleX = obj.getScale().x * texture.scale.x;
                double scaleY = obj.getScale().y * texture.scale.y;
                Vector2D scalePiv = texture.scalePivot;
                Vector2D rotPiv = texture.rotationPivot;
                rotPiv = new Vector2D(
                    scalePiv.x + scaleX * (rotPiv.x - scalePiv.x),
                    scalePiv.y + scaleY * (rotPiv.y - scalePiv.y)
                );

                transform.setToIdentity();

                transform.translate(
                    Math.round(obj.getPosition().x + texture.offset.x),
                    Math.round(obj.getPosition().y + texture.offset.y)
                );
                transform.translate(rotPiv.x, rotPiv.y);
                transform.rotate(texture.getRotation());
                transform.translate(-rotPiv.x, -rotPiv.y);

                transform.translate(scalePiv.x, scalePiv.y);
                transform.scale(scaleX, scaleY);
                transform.translate(-scalePiv.x, -scalePiv.y);

                graphics.drawImage(texture.image, transform,  null);
            }
        }
    }

}
