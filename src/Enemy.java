


import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;


abstract public class Enemy {
    //declaration of instance variables
    protected Rectangle hitbox;
    protected Animation img;
    protected int value;
    protected boolean alive;
    protected static boolean life;
    public static int count;
    public int score=1000;
    
    
    public Enemy(int x, int y,Image[] i,int v, boolean a) throws SlickException{
        //Enemy contructor - template that needs variables x,y,i,v and a to produce an object
        img = new Animation(i,830);
        hitbox = new Rectangle(x,y, img.getHeight(), img.getWidth());
        value = v;
        alive = a;
        life = alive;
    }
    public Enemy(int x, Enemy e) throws SlickException{
        //enemy constructor - template that uses an object and an x location to replicate another object
        img = e.img;
        hitbox = new Rectangle(x,e.getHitbox().getY(), img.getHeight(), img.getWidth());
        value = e.value;
        alive = e.alive;
    }
    
    
    
    
    abstract public void move(ArrayList<Rectangle> barriers);
    //each child class will implement their own movement code
    
    public final void draw(float x, float y,float scalex,float scaley){
            //tells main game what to draw, where to draw and how big/small it should be
            img.draw(hitbox.getX(), hitbox.getY(), 100*scalex, 100*scaley);
        }
    public final void draw(float x, float y){
            //tells main game what to draw, where to draw and how big/small it should be
            img.draw(hitbox.getX(), hitbox.getY());
        }
    public final Rectangle getHitbox(){
        //child class can refer to hitbox outside of class
        return hitbox;
    }
    public static int countAliens(){
        //while life is true subtract the number of Enemies on screen
        if(life==true){
            count--;
            return count;
        }
        return 0;
    }
    
    public void isDead(){
        //sets alive to false for an Enemy
        alive=false;
    }
    
    
    public boolean hasDead(){
            //checks whether or not alive is or not. returns a value based on the answer for that alien
            if(alive==false){
                
                return true;
            }
            else
                return false;
    }
    public int getScore(){
        //while alive is true
        //score is set to specific Enemy value and returned
        if (alive==true) {
            score=value;
            return score;
        }
        return 0;
    }
    
    

}
