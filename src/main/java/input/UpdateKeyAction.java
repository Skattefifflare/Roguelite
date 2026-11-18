package input;
import java.awt.event.ActionEvent;
import javax.swing.*;


final class UpdateKeyAction extends AbstractAction{
    private final InputHandler handler;
    private final KeyActions action;
    private final boolean down;
    UpdateKeyAction(InputHandler handler, KeyActions action, boolean down){
        this.handler = handler;
        this.action = action;
        this.down = down;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        handler.register(action, down, e.getWhen());
    }
}
