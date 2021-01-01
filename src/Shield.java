
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;


public class Shield {
    //declaration & assignment
    private Image[] img = new Image[5];
    private SpriteSheet gsprite;
    private Rectangle hitbox;
    private int x,y;
    private int dmg=0;
    public boolean alive=true;
    public Shield(int x, int y)throws SlickException{
        //assignment
        this.x = x;
        this.y = y;
        gsprite = new SpriteSheet("ISUimages/spritesheet.png",32,32);
            gsprite.startUse();
            //img uses spritesheet to find shield images
            for (int i = 0; i < 1; i++) { //312,210 //372,210 //422,210 //472,210 // 472, 260
                img[0] = gsprite.getSubImage(312, 210, 50,35); //0 dmg
                img[1] = gsprite.getSubImage(472, 210, 50,35); //1 dmg
                img[2] = gsprite.getSubImage(372, 210, 50,35); 
                img[3] = gsprite.getSubImage(422, 210, 50,35);
                img[4] = gsprite.getSubImage(472, 260, 50,35);
            }
            gsprite.endUse();
            //creates a hitbox from parameter and image dimensions
            hitbox = new Rectangle(x,y,50*1.5f,35*1.5f);
    }
    public Rectangle getHitbox(){
        //class can refer to hitbox outside of class
        return hitbox;
    }
    public void draw(float scalex,float scaley){
            //tells main game what to draw and how big/small it should be
            //while damage has not equaled or exceeded 5 the images will draw
            //depending on the damage, img will draw a different image
            if(dmg<5){
                img[dmg].draw(x, y, 50*scalex, 35*scaley);
            }
            
        }
    public void drawShield(Graphics g){
        //sets color and draws player hitbox outline
            g.setColor(Color.red);
            g.draw(hitbox); 
        }
    
    public void inflict(){
        //adds damage to shield  under certain conditions
        //while damage has not equaled 5 and shield
        // is still alive
        if(dmg<5 && alive==true){
            dmg++;
        }
        else{
            //if damage is equal 5 set alive to false
            alive=false;
        }
    }
}
    

