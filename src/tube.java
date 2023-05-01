import java.awt.*;
import java.awt.geom.*;

class tube extends Ground {
    Pair dimension;
    public tube(){
        super();
        dimension = new Pair(50,70); //can randomise height later
        position = new Pair(700, Main.HEIGHT - Ground.height - this.dimension.y);
    }
    public void draw(Graphics g){
        g.setColor(Color.white); //remove later
        g.fillRect((int)position.x, (int)position.y,(int)dimension.x,(int)dimension.y);
    }
    public void update(World w, double time){
        super.update(w,time);

    }

    public void collisionDetection() { //detects whether player has hit an obstacle
        Player.hitbox.intersects(tube., double y, double w, double h)
    }
}
