package input.mouse;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;


class MouseHandler extends MouseAdapter {

    private static volatile MouseHandler instance;
    private MouseState asyncState = new MouseState();

    private MouseHandler(){}
    public static void init(JComponent component){
        if (instance != null) throw new IllegalStateException("MouseHandler already initialized.");
        instance = new MouseHandler();
        component.addMouseListener(instance);
        component.addMouseMotionListener(instance);
        component.addMouseWheelListener(instance);
    }

    public static void takeSnapshot() {
        synchronized (instance) {
            MouseState next = instance.asyncState.carryOver();
            instance.asyncState.buttonMap.values().forEach(value -> value.clicked |= value.justClicked);

            MouseFacade.setState(instance.asyncState);
            instance.asyncState = next;
        }
    }

    private static MouseButtons map(int awtButton){
        return switch (awtButton){
            case MouseEvent.BUTTON1 -> MouseButtons.left;
            case MouseEvent.BUTTON2 -> MouseButtons.middle;
            case MouseEvent.BUTTON3 -> MouseButtons.right;
            default -> null;
        };
    }

    private void updatePosition(MouseEvent event){
        asyncState.pos.x = event.getX();
        asyncState.pos.y = event.getY();
    }



    @Override
    public synchronized void mousePressed(MouseEvent e) {
        updatePosition(e);
        MouseButtons b = map(e.getButton());
        if (b == null) return;
        MouseState.MouseButtonState s = asyncState.buttonMap.get(b);
        if (!s.clicked) s.justClicked = true;
        s.clicked = true;
        s.eventX = e.getX();
        s.eventY = e.getY();
    }

    @Override
    public synchronized void mouseReleased(MouseEvent e) {
        updatePosition(e);
        MouseButtons b = map(e.getButton());
        if (b == null) return;
        MouseState.MouseButtonState s = asyncState.buttonMap.get(b);
        if (s.clicked) s.justReleased = true;
        s.clicked = false;
        s.eventX = e.getX();
        s.eventY = e.getY();
    }

    @Override
    public synchronized void mouseMoved(MouseEvent e) {
        updatePosition(e);
    }

    @Override
    public synchronized void mouseDragged(MouseEvent e) {
        updatePosition(e);
    }

    @Override
    public synchronized void mouseWheelMoved(MouseWheelEvent e) {
        updatePosition(e);
        asyncState.delta_scroll += e.getPreciseWheelRotation();
    }

}
