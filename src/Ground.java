import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ground {
    final static int height = 100; //300 is a random placeholder for how tall the ground is
    final static int width = 8000; //*10 b/c ground should be much longer than width of screen
    Color color;
    int velocity; //dictates how fast character seems to run on screen
    Pair position;

    public Ground(){
        position = new Pair(0,Main.HEIGHT-height);
    }

    public void draw(Graphics g){
        //insert background image
        BufferedImage background = null;
        BufferedImage ground = null;
        try {
            background = ImageIO.read(getClass().getResourceAsStream("background.png"));
            ground = ImageIO.read(getClass().getResourceAsStream("ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(background, (int)position.x,0,10000,730, null);
        g.drawImage(ground, (int)position.x, (int)position.y,width,height, null);
    }

    public void update(World w, double time){
        position.x += velocity * time;
    }

}