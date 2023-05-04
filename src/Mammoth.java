import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Mammoth {
    Pair position;
    Pair velocity;
    String direction;
    BufferedImage left1,left2,right1,right2;
    Rectangle2D hitbox;
    int size;
    int spriteNum;
    public Mammoth(){
        position = new Pair(50, 500);
        velocity = new Pair(0,0);
        getImage();
        direction = "right";
        size = 66;
        hitbox = new Rectangle2D.Double(position.x, position.y - size, size, size);
    }
    public void getImage(){ //pulls images needed for the character
        //we did this by referring to a YouTube tutorial

        try{
            right1 = ImageIO.read(getClass().getResourceAsStream("bebu_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("bebu_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("bebu_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("bebu_left2.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void updateHitbox(){
        hitbox.setRect(position.x, position.y, size, size);
    }
    public void draw(Graphics g){
        //sets color and draws character

        BufferedImage image = null;
        switch (direction) {//connected to keyPressed in Main,
            //enables character's running animation to play when he's moving
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
        }
        g.drawImage(image,(int)position.x,(int)position.y, size, size,null);

    }
}
