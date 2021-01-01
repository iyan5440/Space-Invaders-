import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;


public class EndScreen extends BasicGameState {

    //declaration of instance variables
    private Background arcade;
    private Image img;
    
    
   
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //assignment of instance variables
        arcade = new Background();
        img = new Image("ISUimages/go.jpeg");
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
  
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //arcade background image - simulates 90's feel
        arcade.draw(-20,-5,4.0f,3.4f);
        img.draw(160,140,660,428);
        //arcade.drawbarriers(g);
        //draw title and options while screen is at 0/menu

    }

    public int getID() {
        //id for states
        return 2;  
    } 
}
