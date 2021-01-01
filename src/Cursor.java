
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Cursor {
    //declaration and assignment of instance variables
    private Image img;
    private Rectangle hitbox;
    public int x=395,y=460;
    //image
    //move code that moves it to two specific locations
    //hitbox
    public Cursor() throws SlickException{
        //constructor
        img = new Image("ISUimages/cursor.png");
        hitbox = new Rectangle(x,y,img.getWidth()*.5f,img.getHeight()*.5f);
    }
    //choice selection
    //play
    //instructions
    //quit
    public void moveup(Input in){
        //when player presses up key
        //y will subtract 30
        //cursor hitbox will be set to the new y \
        //which will result in a upward movement by 30
        //y is only allowed to be subtracted when it is greater
        //than 460. When it subtracts and is less, y will be set to 520
        //between 520-460 there are 3 times that y can be subtracted
        //from. Which works for the three choice being implemented in the game
        if(in.isKeyPressed(Input.KEY_UP)) {
            y -= 30;
            if (y < 460) {
                y = 520;
            }
            hitbox.setY(y);
            System.out.println(y); 
        }
        
    }
    //when player presses down key
        //y will add 30
        //cursor hitbox will be set to the new y \
        //which will result in a downward movement by 30
        //y is only allowed to be added when it is less
        //than 520. When it adds and is greater, y will be set to 460
        //between 460-520 there are 3 times that y can be added
        //from. Which works for the three choices being implemented in the game
    public void movedown(Input in){
      if(in.isKeyPressed(Input.KEY_DOWN)) {
                y += 30;
                if (y > 520) {
                    y = 460;
                }
                hitbox.setY(y);
                System.out.println(y);
            }
      

    }
    public Rectangle getHitbox(){
        return hitbox;
    }
    public void setX(float x){
        //sets hitbox x-y location
        hitbox.setX(x);
    }
    public void set(int x,int y){
        //sets hitbox x-y location
        hitbox.setX(x);
        hitbox.setY(y);
    }
    public float getY(){
        //refers to hitbox current y location
        return hitbox.getY();
    }
    
    public void draw(float scalex, float scaley){
            //tells main game what to draw and how big/small it should be
            img.draw(hitbox.getX(), hitbox.getY(),50*scalex,50*scaley);
            
        }
}
