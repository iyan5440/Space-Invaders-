import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Alien3 extends Enemy {
    //declaration of instance variables
    //assignment of some instace variables
    static int xdir,ydir;
    int x,y;
    public final int DOWN=2, LEFT=-10, RIGHT=10, STOP=0;
    private int ycount=0;
    private int timer=0;
    private SpriteSheet gsprite;

    
    public Alien3(int x, int y,Image[] im, int v, boolean a) throws SlickException {
        //contructor
        super(x, y,im,v,a);
        //calling parent class constructor
        xdir = RIGHT;
        ydir = STOP;
        //makes each alien start moving right and not moving at all on the y-axis when game starts running
        hitbox.setWidth(img.getWidth()*.27f);
        hitbox.setHeight(img.getHeight()*.35f);
        //hitbox dimensions are set according to 35% of images original height
        //System.out.println(x);
        Enemy.count++;
    }
    public Alien3(int x, Enemy alien) throws SlickException{
        super(x,alien);
        xdir = RIGHT;
        ydir = STOP;
        //makes each alien start moving right and not moving at all on the y-axis when game starts running
        hitbox.setWidth(img.getWidth()*.35f);
        hitbox.setHeight(img.getHeight()*.35f);
        //hitbox dimensions are set according to 35% of images original height
        //System.out.println(x);
        Enemy.count++;
    }

    public void drawEnemy(Graphics g){
        //sets graphics color and draws object hitbox outline
        g.setColor(Color.red);
        g.draw(hitbox);
    }

    
    
    public void move(ArrayList<Rectangle> barriers) {   
        //while running timer will continue to grow in value
        timer++;
        //while timer is not at appointed value nothing happens
        //else when timer is at 830, timer restarts from 0
        if(timer < 50) return;
        else{
            timer=0;
        }
        //x-y are set to hit locations
        x = (int)hitbox.getX();
        y = (int)hitbox.getY();
        //System.out.println(x);
        //if x==barriers stop x go down y
        //System.out.println("start");
        //when alien is moving on x-axis and has hit leftmost barrier or
        //rightmost barrier alien will stop moving on x-axis and will move
        //downward on y-axis
        if(x>=820 || x<=160 ){ //x>=820 || x<=160
            if (x <= 160) {
                //when alien is on the left, move right
                //System.out.println("right");
                xdir = RIGHT;
                //ydir=DOWN;
            } else if (x >= 820) {
                //when alien is on the right, move left
                //System.out.println("left");
                xdir = LEFT;
            }
        }
        
        //x-y variables will add these dir changes
        x+=xdir;
        y+=ydir;  
        //System.out.println(x+","+y);
        //the hitbox will correct itself using these changes 
        //creating the movement motion
        hitbox.setX(x);
        hitbox.setY(y);
    }
    
    public boolean isout(){
        return true;
    }
    
    
    
    
    
}