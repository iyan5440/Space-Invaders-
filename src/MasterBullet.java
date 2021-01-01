
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


abstract public class MasterBullet {
    //declaration of instance variables
    protected float x1,y1;
    protected Image img;
    protected Rectangle hitbox;
    //constructor
    public MasterBullet(float x, float y, Image imgb) throws SlickException{
        x1 =  x;
        y1 =  y;
        img =imgb;
        hitbox = new Rectangle(x,y,img.getWidth()*0.45f,img.getHeight()*0.85f);
    }
    
    abstract public void render(Graphics g);
    abstract public void tick();
    
}
