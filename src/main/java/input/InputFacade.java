package input;
import java.util.EnumMap;
import java.util.Map;


public class InputFacade {
    private static volatile Map<KeyActions, KeyState> keymap = new EnumMap<>(KeyActions.class);

    static void setKeyMap(Map<KeyActions, KeyState> snapshot) {
        keymap = snapshot;
    }


    public static boolean pressed(KeyActions action){
        return keymap.get(action).pressed;
    }
    public static boolean justpressed(KeyActions action){
        return keymap.get(action).justpressed;
    }
    public static boolean justreleased(KeyActions action){
        return keymap.get(action).justreleased;
    }
}
