package input;
public class KeyState {
    public boolean pressed,justpressed,justreleased;
    boolean releasePending;
    long releaseNanos;
    long releaseWhen;

    public KeyState(){}


    public KeyState carryOver(){
        KeyState n = new KeyState();
        n.pressed = pressed;
        n.releasePending = releasePending;
        n.releaseNanos = releaseNanos;
        n.releaseWhen = releaseWhen;
        return n;
    }
}
