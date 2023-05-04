import java.awt.*;
import java.awt.geom.*;

class tube extends Ground {
    Pair dimension;
    Rectangle hitbox;
    public tube(int x, int w, int h){ //no y here because it depends on the height of the tube
        super();
        dimension = new Pair(w,h); //can randomise height later
        position = new Pair(x, Main.HEIGHT - Ground.height - this.dimension.y);
        hitbox = new Rectangle(x, (int) position.y,w,h);
    }
    public void draw(Graphics g){
        g.setColor(Color.white); //remove later
        g.fillRect((int)position.x, (int)position.y,(int)dimension.x,(int)dimension.y);
    }

    public void drawHitbox(Graphics g){
        g.setColor(Color.red); //remove later
        g.drawRect((int)position.x, (int)position.y,(int)dimension.x,(int)dimension.y);
    }
    public void update(World w, double time){
        super.update(w,time);
    }

}
