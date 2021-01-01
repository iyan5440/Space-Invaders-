import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Background {
    //declaration and assignment of instance variables
    private Image img;
    private SpriteSheet bkgd;
    private ArrayList<Rectangle> barriers = new ArrayList<Rectangle>();
        public Background() throws SlickException{
            //assigning spritesheet to bkgd
            bkgd = new SpriteSheet("ISUimages/spritesheet.png",32,32);
            bkgd.startUse();
            //img uses spritesheet bkgd to find bkgd image
            img = bkgd.getSubImage(0, 990, 270, 230);
            bkgd.endUse();
            //creating barriers in relation to the background image so that objects will not go out of bounds
            //barrier 1:left vertical
            barriers.add(new Rectangle(0,0,160,768));
            //barrier 2:top horizontal
            barriers.add(new Rectangle(160,0,1024,140));
            //barrier 3:right vertical
            barriers.add(new Rectangle(860,140,1024,768));
            //barrier 4:bottom horizontal
            barriers.add(new Rectangle(160,628,700,768));
            //barrier 5:box in corner
            barriers.add(new Rectangle(835,588,25,40));
        }
        public void draw(int x, int y,float scalex,float scaley){
            //tells main game what to draw and how big/small it should be
            img.draw(x, y, 270*scalex, 230*scaley);
            
        }
        public ArrayList<Rectangle> getBarriers(){
            //for player so it can keep track of its location between it and barriers
            return barriers;
        }
        
        public void drawbarriers(Graphics g){
            //sets color and draws hitbox outlines for barriers
            g.setColor(Color.red);
            for (Rectangle barrier : barriers) {
                g.draw(barrier);
            }
            
        }
}
