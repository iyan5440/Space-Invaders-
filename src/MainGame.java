
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;



public class MainGame extends BasicGameState {
    //declaration of instance variables
    private Player guy;//player
    private Background arcade; //arcade backgound
    private Music song;
    private Alien1 test;
    private Alien2 test2;
    private Alien3 a3;
    private ArrayList<Alien1> t;
    private ArrayList<Alien2> t2;
    private TrueTypeFont font;
    private SpriteSheet gsprite;
    private Image[] imga,imga2,imga3;
    private Image img,imgb;
    private Bullet b;
    private EnemyBullet eb;
    private boolean fire;
    private int xloc,timer,count,score,sx,d,timer2;
    private int[] dead,living;
    private ArrayList<Shield> s;

    public void init(GameContainer gc,StateBasedGame sbg) throws SlickException {
        //assignment of instance variables
        
        song = new Music("ISUaudio/Voxel Revolution.wav");
        song.loop(1.0f,0.45f);
        font = new TrueTypeFont(new java.awt.Font("Agency FB",0,24),true);
        fire=false;
        imga = new Image[2];
        imga2 = new Image[2];
        imga3 = new Image[2];
        guy = new Player(480,597);
        arcade = new Background();
        gsprite = new SpriteSheet("ISUimages/spritesheet.png",48,48);
        //spritesheet start
        gsprite.startUse(); //498, 470, 70,80
        //hard coded exact img dimensions in spritesheet
        img = gsprite.getSubImage(392, 770, 188,100); //105, 220, 28,25 s1small //72, 220, 28,25 s2small
        imgb = gsprite.getSubImage(473, 889, 32,55); //473, 889, 32,55 lazer //477, 1134, 109,77
        
        for (int i = 0; i < 2; i++) { //(0+(40*i),0,40,40);
            imga[i] = gsprite.getSubImage(490-(103*i), 570, 95, 90); //387,570,95,90 sprite1big //490 sprite2big //490-(103*i), 570, 95, 90 //105-(33*i), 220, 28, 25
            imga2[i] = gsprite.getSubImage(398+(100*i), 470, 70, 80);
            imga3[i] = gsprite.getSubImage(392, 770, 188,100);
        }
        //spritesheet end
        gsprite.endUse();
        //creating bullet object by following constructor in class
        b = new Bullet(guy.getHitbox().getX(),guy.getHitbox().getY(),imgb);
        //assignment of Alien1 ArrayList
        t = new ArrayList<>();
        t2 = new ArrayList<>();
        s = new ArrayList<>();
        dead = new int[13];
        living = new int[13];
        //assignment of xloc
        xloc=170;
        sx=80;
        test = new Alien1(xloc,220,imga,1500,true);
        test2 = new Alien2(xloc,270,imga2,1000,true);
        a3 = new Alien3(xloc,170,imga3,5000,true);
        for (int i = 0; i < 13; i++) {
            //for every time the for loop is run add 45 to xloc
            xloc += 45;
            //this way xloc for each alien within the arraylist is unique
            t.add(new Alien1(xloc,test));
            t2.add(new Alien2(xloc,test2));
            living[i] =(1*i); //0-12
            //System.out.println(living[i]);
            //t.add(new Alien1(xloc+=45,test2));
            if(t.size()==10){
                pickAlien();
                for (Alien1 alien : t) {
                    if(alien.isChosen()){
                        eb = new EnemyBullet(alien.getHitbox().getX(),alien.getHitbox().getY(),imgb);
                    }
                }  
            }
        }
        
        for (int i = 0; i < 4; i++) {
            sx+=155;
            s.add(new Shield(sx,500));
        }
        count=Enemy.countAliens()+1;
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //timer for enemy shots
        timer++;
        
        //assigment of Input in
        //this game will register player inputs
        Input in = gc.getInput();
        //when player presses space, and fire boolean is not true
        if (in.isKeyPressed(Input.KEY_SPACE) && fire == false) {
            fire = true;
        }
        

        //activate guy move code and use player inputs from in
        //also have guy use arcades barriers for the methods that require an
        //Arraylist<rectangle>
        guy.move(in, arcade.getBarriers());

        //when fire is true, b will refer to tick code for movement
        if (fire) {
            b.tick();
        }
        //when b's ylocation is out of bounds/<-100 b will stop refering
        //to tick to move and will go back to player location
        if (b.getY() < 140 || b.isHitting(test)) {
            fire = false;
            b.set(guy.getHitbox().getX(), guy.getHitbox().getY());
        }
        a3.move(arcade.getBarriers());
        if(b.isHitting(a3) && a3.hasDead()==false){
            fire = false;
            b.set(guy.getHitbox().getX(), guy.getHitbox().getY());
            count=Enemy.countAliens()+1;
            score+=a3.getScore();
            a3.isDead();
        }
        for (Alien1 alien : t) {
            //
            //have alien move code run while refering to arcade barriers
            //for methods that require it
            alien.move(arcade.getBarriers());
            if (b.isHitting(alien) && alien.hasDead()==false) {
                fire = false;
                b.set(guy.getHitbox().getX(), guy.getHitbox().getY());
                alien.isUnworthy();
                dead[t.indexOf(alien)]=t.indexOf(alien); //at same location al num
                count=Enemy.countAliens()+1;
                score+=alien.getScore();
                alien.isDead();
            }
        }
        for (Alien2 alien : t2) {
            //
            //have alien move code run while refering to arcade barriers
            //for methods that require it
            alien.move(arcade.getBarriers());
            if (b.isHitting(alien) && alien.hasDead()==false) {
                fire = false;
                b.set(guy.getHitbox().getX(), guy.getHitbox().getY());
                count=Enemy.countAliens()+1;
                score+=alien.getScore();
                alien.isDead();
            }
        }
        
        for (Shield st : s) {
            if(b.isHitting(st) || eb.isHitting(st) && st.alive==true){
                st.inflict();
                if (b.isHitting(st) && st.alive == true) {
                    fire = false;
                    b.set(guy.getHitbox().getX(), guy.getHitbox().getY());
                }
                if (eb.isHitting(st)) {
                    d = pickAlien(); 
                    eb = new EnemyBullet(t.get(d).getHitbox().getX(), t.get(d).getHitbox().getY(), imgb);
                }
            }
        }
        
        if(eb.isHitting(guy) || count==0){
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }

        if (timer < 1) {
            return;
        } else {
            timer = 0;
                eb.tick();
                if (eb.getY() > 768  && count>0) {
                    d=pickAlien();
                    eb = new EnemyBullet(t.get(d).getHitbox().getX(), t.get(d).getHitbox().getY(), imgb);
                }
        }
    }

    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g) throws SlickException {
        //background and barrier hitbox drawn
        arcade.draw(-20,-5,4.0f,3.4f);
        arcade.drawbarriers(g);
        //img.draw(100, 570);
        font.drawString(162, 140, "Score:" + (score), Color.white);
        font.drawString(762, 140, "#ofAliens:" + (count), Color.white);
        //player drawn & hitbox
        guy.draw();
        guy.drawPlayer(g);
        for (Shield st : s) {
            st.draw(1.5f, 1.5f);
        }
        if(a3.hasDead()){
            
        }
        else{
            a3.draw(xloc,170,0.5f,0.3f);
            a3.drawEnemy(g);
        }
        
        //enemy
        for (Alien1 alien : t) {
            if(alien.hasDead()){
                
            }
            else{
                alien.draw(xloc, 170, 0.35f, 0.35f);
                //System.out.println(alien.xdir);
                alien.drawEnemy(g);
            }

        }
        for (Alien2 alien : t2) {
            if(alien.hasDead()){
                
            }
            else{
                alien.draw(xloc, 220, 0.35f, 0.35f);
                //System.out.println(alien.xdir);
                alien.drawEnemy(g);
            }

        }
        if(fire){
            //when fire is active
            //b will be drawn on screen
            //b will also refer to drawBullet method
            b.draw(guy.getHitbox().getX(),guy.getHitbox().getY(),0.15f,0.45f);
            b.drawBullet(g);
        }

            for (Alien1 alien : t) {
                eb.draw(alien.getHitbox().getX(),alien.getHitbox().getY(),0.15f,0.45f);
                eb.drawBullet(g);
            }
            
        
    }
    public int pickAlien(){
        int x;
        while(true){
            x = (int) (Math.random() * t.size()-1);
            //System.out.println(x);
            if(t.get(x).hasDead()==false && dead[x]!=living[x]){
                t.get(x).setChosen();
                break;
            }
        }
        return x;
    }
    
    public int getID(){
        return 1;
        
    }

    
}
