
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Bullet extends MasterBullet {
    //declaration of instance variables
    private float x,y;
    private float ychange=0;
    private Image img;
    private Rectangle hitbox;
    //constructor
    public Bullet(float x, float y, Image imgb) throws SlickException{
        super(x,y,imgb);
        this.x =  x;
        this.y =  y;
        img =imgb;
        hitbox = new Rectangle(x,y,img.getWidth()*0.45f,img.getHeight()*0.85f);
    }
    public void tick(){
        //y obtains hitboxs original y location
        y = hitbox.getY();
        //y subtracts at a rate of one
        y--;
        //hitbox continuously sets its y location to the new y
        hitbox.setY(y);
        //this set of code creates a continuoes upward movement

    }
    public Rectangle getHitbox(){
        //class can refer to hitbox outside of class
        return hitbox;
    }
    public boolean isHitting(ArrayList<Rectangle> rect) {
        //keeping track of hitbox  in relation to rectangles(barriers)
        //class hitbox is in contact with any rectangles in the arraylist specified
        //return true
        //else return false
        for (Rectangle r : rect) {
            if(hitbox.intersects(r)){
                return true;
            }
        }
        return false;
    }
    public boolean isHitting(Shield s){
        if(hitbox.intersects(s.getHitbox())){
            return true;
        }
        return false;
        }
    
    public boolean isHitting(Enemy go) {
        //keeping track of hitbox  in relation to aliens
        return hitbox.intersects(go.getHitbox());
    }
    //public boolean isHitting(Alien2 go) {
        //keeping track of hitbox  in relation to aliens
      //  return hitbox.intersects(go.getHitbox());
    //}
    
    public void set(float x,float y){
        //sets hitbox x-y location
        hitbox.setX(x);
        hitbox.setY(y);
    }
    public float getY(){
        //refers to hitbox current y location
        return hitbox.getY();
    }

    public float getX(){
        //refers to hitbox current x location
        return hitbox.getX();
    }
    public void draw(float x, float y,float scalex,float scaley){
            //tells main game what to draw, where to draw and how big/small it should be
            img.draw(hitbox.getX(), hitbox.getY(), 100*scalex, 100*scaley);
            
        }
    
    public void render(Graphics g){
        //draws image of bullet (found in constructor)
        g.drawImage(img,hitbox.getX(),hitbox.getY());
        //g.drawRect(x, y-=ychange, 32, 55);
        //img.draw(hitbox.getX(), hitbox.getY());
        /*for (int i = 0; i < 2; i++) {
            g.drawImage(img,x,y-=0.05f);
        }*/
    }
    public void drawBullet(Graphics g){
        //sets color and draws outline of hitbox
        g.setColor(Color.red);
        g.draw(hitbox);
    }


 
}
