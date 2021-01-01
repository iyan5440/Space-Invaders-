
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class EnemyBullet extends MasterBullet {

    private float x,y;
    private Image img;
    private Rectangle hitbox;
    public EnemyBullet(float x, float y, Image imgb) throws SlickException {
        super(x, y, imgb);
        this.x =  x;
        this.y =  y;
        img =imgb;
        hitbox = new Rectangle(x,y,img.getWidth()*0.45f,img.getHeight()*0.85f);
    }
    
    public void tick(){
        //y obtains hitboxs original y location
        y = hitbox.getY();
        //y subtracts at a rate of one
        y+=0.8f;
        //hitbox continuously sets its y location to the new y
        hitbox.setY(y);
        //this set of code creates a continuoes upward movement

        
    }
    public float getY(){
        //refers to hitbox current y location
        return hitbox.getY();
    }

    public float getX(){
        //refers to hitbox current x location
        return hitbox.getX();
    }
    
    public void set(float x,float y){
        //sets hitbox x-y location
        hitbox.setX(x);
        hitbox.setY(y);
    }
    public void setX(float x){
        //sets hitbox x-y location
        hitbox.setX(x);
    }
    public void draw(float x, float y,float scalex,float scaley){
            //tells main game what to draw, where to draw and how big/small it should be
            img.draw(hitbox.getX(), hitbox.getY(), 100*scalex, 100*scaley);
            
        }
    public boolean isHitting(Player guy) {
        //keeping track of hitbox  in relation to gameobjects(items,enemies,etc)
        return hitbox.intersects(guy.getHitbox());
    }
    public boolean isHitting(Shield s){
        if(hitbox.intersects(s.getHitbox())){
            return true;
        }
        return false;
        }

    
    public void render(Graphics g){
        //draws image of bullet (found in constructor)
        g.drawImage(img,hitbox.getX(),hitbox.getY());
    }

    
    public void drawBullet(Graphics g){
        //sets color and draws outline of hitbox
        g.setColor(Color.red);
        g.draw(hitbox);
    }

    
}
