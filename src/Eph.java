import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Eph extends Player{
    int charWidth = 40;
    int stepsCounter = 0; //this will be randomised to determine after how many frames does the eph change direction
    public Eph(){
        position = new Pair(850, 520);
        velocity = new Pair(-50,0);
        getImage();
        direction = "left";

    }
    public void draw(Graphics g){
        //draws character

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
        g.drawImage(image,(int)position.x,(int)position.y, charWidth, charWidth,null);

    }

    public void update(World w, double time) { //update is called 60 times per second
        //updates character's position and animation
        position.x += velocity.x * time;
        position.y += velocity.y * time;
        velocity.y += acceleration.y * time;

        spriteCounter++;
        if (velocity.x != 0 || w.level.velocity != 0){
            if (spriteCounter > 10) { //the image switches after this many frames
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0; //reset the counter
            }
        }

        for (tube tube: World.tubes){
            if (hitbox.intersects(tube.hitbox)) {
                hitbox.x -= velocity.x;
                while (!tube.hitbox.intersects(hitbox)) {
                    hitbox.x += Math.signum(velocity.x);
                }
                hitbox.x -= Math.signum(velocity.x);
                velocity.flipX();
                if (direction == "left"){
                    direction = "right";
                }
                position.x = hitbox.x;
            }
        }

        /*
        stepsCounter ++; //this is for making the cow turn
        int turn = //random number between 3 and 10 seconds (*60 frams/sec)
        if ()

         */
    }

    public void getImage(){ //pulls images needed for the character
        //we did this by referring to a YouTube tutorial

        try{
            right1 = ImageIO.read(getClass().getResourceAsStream("eph_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("eph_right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("eph_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("eph_left2.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
