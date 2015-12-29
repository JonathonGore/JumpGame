
package jumpgame;

/**
 * required imports
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Wall.java - represents a wall in this game
 * @version 1.0
 * @since 2014-01-14
 * @author Jack Gore
 */
public class Wall extends GameObject 
{
    public int lastYWall = 240;
    private int wallDelay = 50;
    private final int WALL_MOVE_AMOUNT = 10;
    ScoreKeeper scoreKeeper;
    private int newScore;
    private Timer          wallTimer;
    private ActionListener wallListener;    
    private GameScreen     gameScreen;
     
    /**
     * constructor for the class
     * @param image
     * @param gameScreen 
     */
    public Wall(JLabel image, GameScreen gameScreen) {
        
        super(image);
        scoreKeeper = new ScoreKeeper();
        wallListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wallAction(lastYWall);
                wallDelay = scoreKeeper.getSpeed();
                newScore = scoreKeeper.getScore();
                wallTimer.setDelay(wallDelay);
//                System.out.println(wallDelay);
            }
        };        
        wallTimer = new Timer(wallDelay,wallListener);
        this.gameScreen       = gameScreen;
        coordinates.direction = Directions.LEFT;
        coordinates.amount    = WALL_MOVE_AMOUNT;
    }
    
    /**
     * starts the wall moving
     */
    public void start() {
        wallTimer.start();
    }
    
    /**
     * the timer action for movement
     */
    private void wallAction(int lastYy) {
        update();
        coordinates.moveLeft();
        checkCollisions(lastYy);
        redraw();
    }

    /**
     * checks the wall to see if it collides with the screen edge
     */
    private void checkCollisions() {
        
        if (coordinates.right < 0) {
            coordinates.x = gameScreen.getWidth();
            // could recode if time ##################################
//            coordinates.y = RandomNumber.randomInt(100,gameScreen.getHeight()-coordinates.height-100);
            do {
            coordinates.y = RandomNumber.randomInt(coordinates.y - 100,gameScreen.getHeight()-coordinates.height-100);
            }while (coordinates.y < 50);
            // could recode if time ##################################
            coordinates.recalculate();
        }
    }
    private void checkCollisions(int lastYy){
        
            if (coordinates.right < 0) {
            coordinates.x = gameScreen.getWidth();
            // could recode if time ##################################
//            coordinates.y = RandomNumber.randomInt(100,gameScreen.getHeight()-coordinates.height-100);
            do {
            coordinates.y = RandomNumber.randomInt(lastYy - 100, gameScreen.getHeight()-coordinates.height-75);
            }while (coordinates.y < 50);
            // could recode if time ##################################
            coordinates.recalculate();
        }
    }
    
}
