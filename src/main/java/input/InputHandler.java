package input;
import javax.swing.*;

import java.util.HashMap;


public class InputHandler {
    private static volatile InputHandler instance;

    private JComponent comp;
    private HashMap<String, KeyState> snapshotMap;
    private HashMap<String, KeyState> asyncMap;

    private static final long same_event_ms = 4; // necessary for linux
    private static final long repeat_window_nanos = 5_000_000; // necessary for linux


    private InputHandler(JComponent comp){
        this.comp = comp;
        snapshotMap = new HashMap<String, KeyState>();
        asyncMap = new HashMap<String, KeyState>();
    }
    public static void init(JComponent comp){
        if (instance != null) throw new IllegalStateException("InputHandler already initialized");
            instance = new InputHandler(comp);
    }

    public static void takeSnapshot(){
        synchronized (instance){
            long now = System.nanoTime();
            instance.asyncMap.values().forEach(s -> {
                    if (s.releasePending && now - s.releaseNanos > repeat_window_nanos) {
                        s.releasePending = false;
                        s.pressed = false;
                        s.justreleased = true;
                    }
                });

            var nextAsync = new HashMap<String, KeyState>();

            instance.asyncMap.forEach((key, value) -> {
                nextAsync.put(key, value.carryOver());
                value.pressed |= value.justpressed;
            });

            instance.snapshotMap = instance.asyncMap;
            instance.asyncMap = nextAsync;
        }

    }

    public static void bindkey(String key, String action){
        InputMap im = instance.comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = instance.comp.getActionMap();
        im.put(KeyStroke.getKeyStroke(key), action);
        im.put(KeyStroke.getKeyStroke("released "+key), "released "+action);

        am.put(action, new UpdateKeyAction(instance, action, true));
        am.put("released "+action, new UpdateKeyAction(instance, action, false));

        synchronized (instance) {
            instance.asyncMap.putIfAbsent(action, new KeyState());
            instance.snapshotMap.putIfAbsent(action, new KeyState());
        }
    }

    public static boolean pressed(String action){
        synchronized (instance) { return instance.snapshotMap.get(action).pressed; }
    }
    public static boolean justpressed(String action){
        synchronized (instance) { return instance.snapshotMap.get(action).justpressed; }
    }
    public static boolean justreleased(String action){
        synchronized (instance) { return instance.snapshotMap.get(action).justreleased; }
    }


    synchronized void register(String action, boolean down, long when) {
        var state = asyncMap.get(action);

        if (down){
            if (state.releasePending){
                state.releasePending = false;
                if (when - state.releaseWhen <= same_event_ms) return;
                state.pressed = false;
                state.justreleased = true;
            }
            if (!state.pressed) state.justpressed = true;
            state.pressed = true;
        }
        else {
            state.releasePending = true;
            state.releaseWhen = when;
            state.releaseNanos = System.nanoTime();
        }
    }
}
