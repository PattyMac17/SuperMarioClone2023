import java.awt.*;

public class World {
    int height;
    int width;
    Ground level,platform;
    static Player mario; //can change name to mammo or something
    Ground tube;


    int gravity = 100; //changes velocity when characters jump
    public World(int initWidth, int initHeight){
        width = initWidth;
        height = initHeight;
        level = new Ground();
        mario = new Player();
        tube = new tube();
    }

    public void drawObjects(Graphics g){
        //insert draw methods of various objects here
        level.draw(g);
        mario.draw(g);
        tube.draw(g);
    }

    public void updateObjects(double time){
        //call update methods of various objects here
        mario.update(this,time);
        level.update(this, time);
        tube.update(this,time);
    }
}