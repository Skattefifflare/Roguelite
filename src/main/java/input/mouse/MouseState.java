package input.mouse;

import java.util.EnumMap;
import game.Vector2D;

class MouseState {
    static class MouseButtonState {
        boolean clicked, justClicked, justReleased;
        int eventX, eventY; // the position of the last event
    }

    final EnumMap<MouseButtons, MouseButtonState> buttonMap = new EnumMap<>(MouseButtons.class);

    Vector2D pos = Vector2D.zero();
    double delta_scroll; // amount of scroll that happened from last frame. downscroll is positive

    MouseState(){
        for (MouseButtons mb : MouseButtons.values()) {
            buttonMap.put(mb, new MouseButtonState());
        }
    }


    MouseState carryOver() {
        MouseState n = new MouseState();
        n.pos = new Vector2D(pos.x, pos.y);

        buttonMap.forEach((key, value) -> {
            MouseButtonState state = n.buttonMap.get(key);
            state.clicked = value.clicked;
            state.eventX = value.eventX;
            state.eventY = value.eventY;
        });
        return n;
    }

}
