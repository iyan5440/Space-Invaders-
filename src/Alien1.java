import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Alien1 extends Enemy {
    //declaration of instance variables
    //assignment of some instace variables
    static int xdir,ydir;
    int x,y;
    public final int DOWN=2, LEFT=-10, RIGHT=10, STOP=0;
    private int ycount=0;
    private int timer=0;
    private SpriteSheet gsprite;
    public boolean isChosen=false;

    
    public Alien1(int x, int y,Image[] im, int v, boolean a) throws SlickException {
        //contructor
        super(x, y,im,v,a);
        //calling parent class constructor
        xdir = RIGHT;
        ydir = STOP;
        //makes each alien start moving right and not moving at all on the y-axis when game starts running
        hitbox.setWidth(img.getWidth()*.35f);
        hitbox.setHeight(img.getHeight()*.35f);
        //hitbox dimensions are set according to 35% of images original height
    }
    public Alien1(int x, Enemy alien) throws SlickException{
        super(x,alien);
        //calling second parent class constructor
        xdir = RIGHT;
        ydir = STOP;
        //makes each alien start moving right and not moving at all on the y-axis when game starts running
        hitbox.setWidth(img.getWidth()*.35f);
        hitbox.setHeight(img.getHeight()*.35f);
        //hitbox dimensions are set according to 35% of images original height
        Enemy.count++;
        //every time an Alien is made add to count from parent class
    }
    
    public boolean isChosen(){
        //returns a boolean
        return isChosen;
    }
    
    public void setChosen(){
        //setChosen is true
        isChosen=true;
    }
    public void isUnworthy(){
        //setChosen is false;
        isChosen=false;
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
        if(timer < 830) return;
        else{
            timer=0;
        }
        //x-y are set to hit locations
        x = (int)hitbox.getX();
        y = (int)hitbox.getY();
        //if x==barriers stop x go down y
        //when alien is moving on x-axis and has hit leftmost barrier or
        //rightmost barrier alien will stop moving on x-axis and will move
        //downward on y-axis
        if(xdir !=STOP && x>=820 || x<=160 ){ //x>=820 || x<=160
            xdir=STOP;
            ydir=DOWN;
        }
        
        //when alien is moving on y-axis
        //and is still by leftmost or rightmost barrier
        //and will move left or right on the x-axis depending on
        //the barrier they are currently touching
        if(ydir !=STOP && x>=820 || x<=160){       
            ycount+=1;
            //alien will continue to move downward while adding to ycount
            if(ycount==20){
                //after 20 seconds/ycount==20
                //alien will stop moving on the y-axis
                ydir=STOP;
                //ycount set to 0 so code can reuse this set of statements
                ycount=0;
                //depending on where alien is 
                //they will move accordingly
                if(x <= 160) {
                    //when alien is on the left, move right
                    xdir=RIGHT;
                    //ydir=DOWN;
                }
                else if(x>=820){
                    //when alien is on the right, move left
                    xdir=LEFT;
                }
            } 

        }
        //x-y variables will add these dir changes
        x+=xdir;
        y+=ydir;  
        //the hitbox will correct itself using these changes 
        //creating the movement motion
        hitbox.setX(x);
        hitbox.setY(y);
    }
    
    
    
}