
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Title {
    private Image img;
    private SpriteSheet bkgd;
        public Title() throws SlickException{
            //assigning spritesheet to bkgd
            bkgd = new SpriteSheet("ISUimages/spritesheet.png",32,32);
            bkgd.startUse();
            //img uses spritesheet bkgd to find bkgd image
            img = bkgd.getSubImage(173, 0, 240, 200);
            bkgd.endUse();
        }
        
        public void draw(int x, int y){
            //tells main game what to draw and how big/small it should be
            img.draw(x, y);
            
        }
}
