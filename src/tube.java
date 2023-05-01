import java.awt.*;
import java.awt.geom.*;

class tube extends Ground {
    Pair dimension;
    public tube(){
        super();
        dimension = new Pair(50,70); //can randomise height later
        position = new Pair(200, Main.HEIGHT - Ground.height - this.dimension.y);
    }
    public void draw(Graphics g){
        g.setColor(Color.white); //remove later
        g.fillRect((int)position.x, (int)position.y,(int)dimension.x,(int)dimension.y);
    }
    public void update(World w, double time){
        super.update(w,time);
        collisionDetection();
    }

    public void collisionDetection() { //detects whether player has hit an obstacle
        collision = World.mario.hitbox.intersects(this.position.x, this.position.y,this.dimension.x,this.dimension.y);
        if (collision) {
            World.mario.velocity.x = 0;
        }
    }
}
