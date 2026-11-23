package input.mouse;
import game.Vector2D;


public class MouseFacade {
    private static volatile MouseState state = new MouseState();

    static void setState(MouseState snapshot){ state = snapshot;}


    public static boolean clicked(MouseButtons button){return state.buttonMap.get(button).clicked;}
    public static boolean justClicked(MouseButtons button){return state.buttonMap.get(button).justClicked;}
    public static boolean justReleased(MouseButtons button){return state.buttonMap.get(button).justReleased;}
    public static Vector2D getEventPos(MouseButtons button){return new Vector2D(state.buttonMap.get(button).eventX, state.buttonMap.get(button).eventY);}
    public static Vector2D getMousePos(){return state.pos;}
    public static double getScroll(){return state.delta_scroll;}
}
