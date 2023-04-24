import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeybindingsDemo {
    World world;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 650;
    public static final int FPS = 60;
    JFrame frame;
    JLabel label;
    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;
    public KeybindingsDemo(){
        this.frame = new JFrame("Keybinding Demo");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(1024,768);
        this.frame.setLayout(null);

        this.label = new JLabel();
        this.label.setBackground(Color.red);
        this.label.setBounds(100, 100, 100, 100);
        this.label.setOpaque(true);

        this.upAction = new UpAction();
        this.downAction = new DownAction();
        this.leftAction = new LeftAction();
        this.rightAction = new RightAction();

        label.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        label.getActionMap().put("upAction", upAction);
        label.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        label.getActionMap().put("downAction", downAction);
        label.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        label.getActionMap().put("leftAction", leftAction);
        label.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        label.getActionMap().put("rightAction", rightAction);

        this.frame.add(label);
        this.frame.setVisible(true);
    }
    public class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(), label.getY()-10);
        }
    }
    public class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(), label.getY()+10);
        }
    }
    public class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()-10, label.getY());
        }
    }
    public class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()+10, label.getY());
        }
    }

}
