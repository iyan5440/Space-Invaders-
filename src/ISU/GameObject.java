
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

abstract public class GameObject {
    
    protected Rectangle hitbox;
    protected Image img;
    
    public GameObject(int x, int y, String imgname) throws SlickException{
        img = new Image(imgname);
        hitbox = new Rectangle(x,y,img.getHeight(),img.getWidth());
    }
    
    abstract public void move(ArrayList<Rectangle> barriers);
    
    public void draw(){
        img.draw(hitbox.getX(),hitbox.getY());
        
    }
    public Rectangle getHitbox(){
        return hitbox;
    }
    
}
