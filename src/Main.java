import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Main extends JPanel implements KeyListener {
    public static final int WIDTH = 1024; //feel free to change to reflect size of mario game screen
    public static final int HEIGHT = 650;
    public static final int FPS = 60;
    World world;

    public Main(){ //this is the constructor
        world = new World(WIDTH, HEIGHT);
        addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Mammoth Mario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main mainInstance = new Main();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.gray);
        g.fillRect(512, 0, 5, 768);

        world.drawObjects(g);
    }

    class Runner implements Runnable {
        public void run() {
            while (true) {
                //update whatever is in the world here
                world.updateObjects(1.0 / (double) FPS);
                repaint();
                try {
                    Thread.sleep(1000 / FPS);
                } catch (InterruptedException e) {
                }
            }

        }
    }
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
    }

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);

        /*switch(c){
            case 'w':
                //mario jumps
                if (world.mario.velocity.y == 0) { //to ensure no double jumping
                    //jump
                }
                break;
            case 'a':
                //move left
                world.mario.velocity.x = -250;
                break;
            case 'd':
                //move right
                world.mario.velocity.x = 250;
                break;
        }*/
        if ((e.getKeyCode() == KeyEvent.VK_A) && (world.mario.position.x >= 0)){
            //character moves left up until the left edge
            world.mario.velocity.x = -250;
            world.mario.direction = "left";
        }
        if ((e.getKeyCode() == KeyEvent.VK_D) && !(world.mario.position.x + 20 >= 512)){
            world.mario.velocity.x = 250;//the character can move right until the middle of the screen
            world.mario.direction = "right";
        }
        if ((e.getKeyCode() == KeyEvent.VK_D) && (world.mario.position.x + 20 >= 512)){
            world.level.velocity = -250; //once character passes the midpoint,
            // the ground should scroll with the character
            world.mario.direction = "right";
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            //character jumps
            //if on ground, jump up
            if (world.mario.onGround()) {
                world.mario.velocity.y = -250;
                world.mario.acceleration.y = 198;
            }
        }
    }


    public void keyReleased(KeyEvent e) { //edit this

        char c = e.getKeyChar();
        switch(c){
            case 'a':
                //stop moving left and stop changing the sprite
                world.mario.velocity.x = 0;
                break;
            case 'd':
                //stop move right
                world.mario.velocity.x = 0;
                world.level.velocity = 0;
                break;
        }
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}