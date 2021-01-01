import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;


public class StartScreen extends BasicGameState {

    //declaration of instance variables
    private Background arcade;
    private TrueTypeFont font;
    private Title title;
    private Cursor c;
    private int screen=0;
    private int timer;
    
    
   
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //assignment of instance variables
        font = new TrueTypeFont(new java.awt.Font("Agency FB",0,24),true);
        arcade = new Background();
        title = new Title();
        c = new Cursor();
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        timer++;
        //declaration and assignment of input variable
        Input in = gc.getInput();
        //when screen is 0 show base menu screen
        if(screen==0){

            //move cursor in place
            c.setX(395);
            //allow cursor to move up through options and only options
            c.moveup(in);
            //allow cursor to move down through options and only options
            c.movedown(in);
            //when player presses space
            //cursor y point is traced
            //the y point the cursor is at will affect the screen
            if(in.isKeyPressed(Input.KEY_SPACE)){
                c.getY();
                if(c.getY()==520){
                    screen=-1; //exit screen
                }
                else if(c.getY()==490){
                    screen=2; //instructions screen
                }
                else{
                    screen=1; //play screen
                }

            }
        }

        if(screen==1){ // play/ transition to main game state
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        if(screen==2){ 
            // move cursor in place 
            c.set(750, 588);
            //when space is pressed on screen 2/
            //screen reverts back to base menu screen
            if(in.isKeyPressed(Input.KEY_SPACE)){
                    screen=0;
            }
        }
        if(screen==-1){
            //when screen is -1 or cursor is at quit, game will quit
            System.exit(0);
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //arcade background image - simulates 90's feel
        arcade.draw(-20,-5,4.0f,3.4f);
        //arcade.drawbarriers(g);
        //draw title and options while screen is at 0/menu
        if(screen==0){
            title.draw(395,180);
            c.draw(0.5f,0.5f);
            font.drawString(445, 455, "Play", Color.white);
            font.drawString(445, 486, "Instructions", Color.white);
            font.drawString(445,517,"Quit",Color.white);
        }
        if(screen==2){ 
            //draw instructions while screen is on 2
            font.drawString(160, 145, "Oh No! Aliens are invading! Use the arrow keys to move and the spacebar to shoot!", Color.white);
            font.drawString(800,588,"Back",Color.white);
            c.draw(0.5f,0.5f);
        }
    }

    public int getID() {
        //id for states
        return 0;  
    } 
}
