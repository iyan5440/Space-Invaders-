
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class ISUGL extends StateBasedGame {

   
    
    public ISUGL(String title) {
        super(title);  
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new ISUGL("Setup Test"));
        app.setDisplayMode(1024, 768, false);
         
        app.start();
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        //gamestates
        this.addState(new StartScreen());
        this.addState(new MainGame());
        this.addState(new EndScreen());
    }
    
}
