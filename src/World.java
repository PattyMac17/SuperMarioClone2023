import java.awt.*;
import java.util.ArrayList;

public class World {
    int height;
    int width;
    Ground level,platform;
    static Player mario; //can change name to mammo or something
    Eph eph; //will replace this with DS later
    //DS<Eph> ephs;
    static ArrayList<tube> tubes = new ArrayList<>();


    int gravity = 100; //changes velocity when characters jump
    public World(int initWidth, int initHeight){
        width = initWidth;
        height = initHeight;
        level = new Ground();
        mario = new Player();
        makeTubes();
        eph = new Eph();
        //ephs = new DS<Eph>();
    }

    public void makeTubes() {
        for (int i = 200; i < level.width; i += 500){
            tubes.add(new tube(i,50,70));
        }
    }

    public void drawObjects(Graphics g){
        //insert draw methods of various objects here
        level.draw(g);
        mario.draw(g);
        mario.drawHitbox(g);  //remove later once debugged
        eph.draw(g);
        for (tube tube: tubes){
            tube.draw(g);
            tube.drawHitbox(g); //remove later once debugged
        }
    }

    public void updateObjects(double time){
        //call update methods of various objects here
        mario.update(this,time);
        level.update(this, time);
        for (tube tube: tubes){
            tube.update(this,time);
        }
        eph.update(this,time);
    }
}