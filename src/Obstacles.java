import java.awt.*;

abstract class Obstacles { //obstacles are objects that are a part of the level, such as platforms and stairs
Pair position;
Pair dimension;
Color color;
public Obstacles() {
    color = Color.white;
}

    public abstract void update(World w, double time);

    abstract public void draw(Graphics g);
}

class tube extends Obstacles {
    public tube(){
        super();
        dimension = new Pair(30,50); //can randomise height later
        position = new Pair(500, Main.HEIGHT - Ground.height - this.dimension.y);
    }
    public void draw(Graphics g){
        g.fillRect();
    }
    public void update(World w, double time){

    }
}
