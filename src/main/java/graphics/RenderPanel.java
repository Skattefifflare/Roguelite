package graphics;

import javax.swing.*;
import java.awt.*;


class RenderPanel extends JPanel {
    RenderHandler renderhandler;
    public RenderPanel(RenderHandler renderhandler) {
        this.renderhandler = renderhandler;
        setBackground(Color.black);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics.create();

        try {
            renderhandler.render(graphics2D);
        }
        finally {
            graphics2D.dispose();
            Toolkit.getDefaultToolkit().sync();
        }
    }
}
