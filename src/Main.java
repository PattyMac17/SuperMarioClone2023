import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
public class Main extends JPanel implements KeyListener {
    public static final int WIDTH = 1024; //feel free to change to reflect size of mario game screen
    public static final int HEIGHT = 650;
    public static final int FPS = 60;
    World world;
    boolean gameStarted;
    
    // variables for timer
    JFrame window;
    JLabel counterLabel;
    Font font = new Font("Helvetica", Font.PLAIN, 70);
    Timer timer;
    int second;

    public Main(){ //this is the constructor
        world = new World(WIDTH, HEIGHT);
        addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();

        /*
        //should display timer in the center of the screen
        counterLabel = new JLabel("");
        counterLabel.setBounds(300, 230, 200, 100);
        counterLabel.setHorizontalAlignment(JLabel.CENTER);
        counterLabel.setFont(font);

        window = new JFrame();
        window.add(counterLabel);
        window.setVisible(true);
        
        counterLabel.setText("400");
        second = 400; //sets timer to 400
        countdownTimer();
        timer.start(); //starts timer

         */
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
        world.level.drawMenu(g);
        g.drawString("Press enter to start", 450,400);
        if (gameStarted){
            world.drawObjects(g);
        }
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

    /*
    public void countdownTimer(){ //method for game timer
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e){
                second--; //timer counts down
                //timerSecond = dFormat.format(second);
                //counterLabel.setText(timerSecond);
                if(second == 0){ //stops once timer reaches 0
                    timer.stop();
                }
            }
        });
    }

     */
    
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
    }

    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        System.out.println("You pressed down: " + c);

        if ((e.getKeyCode() == KeyEvent.VK_A) && (world.mario.position.x >= 0) && gameStarted){
            //character moves left up until the left edge
            world.mario.velocity.x = -250;
            world.mario.direction = "left";
        }
        // PROBLEM HERE
        if ((e.getKeyCode() == KeyEvent.VK_D) && !(world.mario.position.x + 20 >= 512) && gameStarted){
            world.mario.velocity.x = 250;//the character can move right until the middle of the screen
            world.mario.direction = "right";
        }
        if ((e.getKeyCode() == KeyEvent.VK_D) && (world.mario.position.x + 20 >= 512) && gameStarted){
            world.level.velocity = -250; //once character passes the midpoint,
            // the ground should scroll with the character
            for (tube tube: world.tubes){
                tube.velocity = -250;
            }
            world.eph.velocity.x = -50 - 250; //-50 is the eph's initial speed
            world.mario.direction = "right";
            //ground stops scrolling when character reaches end of level:

        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameStarted){
            //character jumps
            //if on ground, jump up
            if (world.mario.onGround()) {
                world.mario.velocity.y = -300;
                world.mario.acceleration.y = 498;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            gameStarted = true;
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
                for (tube tube: world.tubes){
                    tube.velocity = 0;
                }
                world.eph.velocity.x = -50; //eph's default speed
                break;
        }
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}
