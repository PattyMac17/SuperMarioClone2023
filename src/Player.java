import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    int charHeight = 50; //not final in case we make mario grow in size
    int charWidth = 20;
    Pair position,velocity;
    Pair acceleration = new Pair(0,0); //the "gravity" pulling on the character when it jumps
    Color color;
    BufferedImage left1,left2,right1,right2;
    String direction;
    int spriteCounter = 0;
    int spriteNum = 1;


    public Player(){
        color = Color.white;
        position = new Pair(50, 520);
        velocity = new Pair(0,0);
        getImage();
        direction = "right";
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
        g.drawImage(image,(int)position.x,(int)position.y, charWidth*2, charWidth*2,null);

    }

    public void update(World w, double time){ //update is called 60 times per second
        //updates character's position and animation
        position.x +=velocity.x * time;
        position.y +=velocity.y * time; //may replace this line after implementing jumping
        velocity.y += acceleration.y * time;

        hitWall(w);
        spriteCounter++;
        if (onGround() && (velocity.x != 0 || w.level.velocity != 0)){
            if (spriteCounter > 10) { //the image switches after this many frames
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0; //reset the counter
            }
        }

    }
    public void hitWall(World w){
        if(position.x + 20 >= 512 || position.x < 0){
            velocity.x = 0;//position ends up being 495.8333333417
        }
        if (position.y >= 520){ //if character hits ground after jumping, set y vel to 0
            position.y = 520; //reposition character
            velocity.y = 0;
        }
    }
    public boolean onGround(){ //check if character is on the ground
        if(position.y == 520){
            return true;
        } else{
            return false;
        }
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

}