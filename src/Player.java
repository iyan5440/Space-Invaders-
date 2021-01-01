

import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;


public class Player {
    private boolean stopped;
    private Image img;
    private SpriteSheet gsprite;
    private Rectangle hitbox;
    private boolean isHitting;
    private boolean fire = false;
    
    
    //constructor need x-y coords
    public Player(int x, int y) throws SlickException {
        gsprite = new SpriteSheet("ISUimages/spritesheet.png",48,48);
        //spritesheet start
        gsprite.startUse();
        //hard coded exact img dimensions in spritesheet
        img = gsprite.getSubImage(273, 220, 34, 30);
        //spritesheet end
        gsprite.endUse();
        //hitbox location starting at player x-y coord to img dimensions
        hitbox = new Rectangle(x,y,34,30);
        stopped = true;

    }
    
    
    public void move(Input in, ArrayList<Rectangle> walls){
        stopped = false;
        //hitbox movement variables
        int rx = (int) hitbox.getX();
        int ry = (int) hitbox.getY();
        int origx = rx, origy = ry;
        //buttonpressed statements (if rb is pressed go right,etc)
        if(in.isKeyDown(Input.KEY_RIGHT)) {
            //rx will grow
            rx++;
        }
        else if(in.isKeyDown(Input.KEY_LEFT)) {
            //rx will shrink
            rx--;
        }

        else{
            //if not moving hitbox stops moving and stays at a stops
            stopped =true;
        }

        hitbox.setX(rx);
        hitbox.setY(ry);
        //if hitbox touches walls stop moving
        if(isHitting(walls)){
            hitbox.setX(origx);
            hitbox.setY(origy);
        }
    }

    
    public Rectangle getHitbox(){
        return hitbox;
    }

    public boolean isHitting(ArrayList<Rectangle> rect) {
        //keeping track of hitbox  in relation to rectangles(barriers)
        //class hitbox is in contact with any rectangles in the arraylist specified
        // return true
        //else return false
        for (Rectangle r : rect) {
            if(hitbox.intersects(r)){
                return true;
            }
        }
        return false;
    }
    public boolean isHitting(Rectangle rect) {
        //keeping track of hitbox  in relation to a rectangle
        //class hitbox is in contact with the rectangle in the arraylist specified
        //return true
        //else return false
            if(hitbox.intersects(rect)){
                return true;
            }
        
        return false;
    }

        
    public boolean isHitting(Alien1 go) {
        //keeping track of hitbox  in relation to gameobjects(items,enemies,etc)
        return hitbox.intersects(go.getHitbox());
    }
    public void draw(){
        //tells main game what to draw exactly when refering to player drawing
        img.draw(hitbox.getX(), hitbox.getY());
    }
    public void drawPlayer(Graphics g){
        //sets color and draws player hitbox outline
            g.setColor(Color.red);
            g.draw(hitbox); 
        }
}
