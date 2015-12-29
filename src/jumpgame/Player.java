
package jumpgame;

/**
 * required imports
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Player.java - represents a game player in this game
 * @version 1.0
 * @since 2014-01-14
 * @author Jack Gore
 */
public class Player extends GameObject
{
        
    // global constants    
    private final int PLAYER_TIMER_DELAY  = 20;
    private final int GRAVITY_TIMER_DELAY = 20;
    private final int JUMP_TIMER_DELAY    = 10;
    private final int GRAVITY_MOVE_AMOUNT = 10;    
    private final int MOVE_AMOUNT         = 3;
    private final int RIGHT_LEFT_AMOUNT   = 100;
    private final int MAX_JUMP_HEIGHT     = 10; 
    
    private Timer          verticalMoveTimer;     
    private Timer          gravityTimer;
    private Timer          jumpTimer;
    private Timer          checkY;
    private ActionListener verticalMoveListener;
    private ActionListener gravityListener;
    private ActionListener jumpListener;
    private ActionListener yListener;
    private Wall[]         walls;
    private Wall           ground;
    private GameScreen     gameScreen;
    private int            jumpHeight;
    private boolean        isInJump;
    public int          lastY = 0;
    public boolean         isRightPressed = false;
    public boolean         isLeftPressed = false;
    private boolean        straightJump = false;
    private boolean        rightJump = false;
    private boolean        leftJump = false;
    private boolean        isAlive = true;
 
    /**
     * constructor for the class
     * @param image the image for this object
     * @param walls the walls for the player
     * @param ground the ground for the player
     * @param gameScreen the game screen GUI
     */
    public Player(JLabel image, Wall[] walls, Wall ground,
            GameScreen gameScreen){
        super(image);
        super.coordinates.direction = Directions.STOP;
        super.coordinates.amount    = MOVE_AMOUNT;
        super.coordinates.rightLeftAmount = RIGHT_LEFT_AMOUNT;
        setPlayerTimers();
        this.walls      = walls;
        this.ground     = ground;
        this.gameScreen = gameScreen;
    }
    
    /**
     * starts the game
     */
    public void start() {
        verticalMoveTimer.start();
        gravityTimer.start();
    }
    
    /**
     * sets up the timer objects for the game
     */
    private void setPlayerTimers() {
        
        yListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getLastWall();

            }
        };
        
        verticalMoveListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verticalMoveAction();
            }
        };        
        gravityListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gravityAction();
            }
        };     
        jumpListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jumpAction();
            }
        };     
        verticalMoveTimer  = new Timer(PLAYER_TIMER_DELAY,  verticalMoveListener);
        gravityTimer       = new Timer(GRAVITY_TIMER_DELAY, gravityListener);
        jumpTimer          = new Timer(JUMP_TIMER_DELAY,    jumpListener);
        checkY             = new Timer(JUMP_TIMER_DELAY,    yListener);
        checkY.start();
    }
    
    /**
     * the jump action the game player calls for
     */
    public void jump() {
        if (isInJump == false) {
            isInJump = true;
            straightJump = true;
            rightJump = false;
            leftJump = false;
            gravityTimer.stop();
            jumpTimer.start();
        }
    }
    public void rightJump(){
        if(isInJump == false){
            isInJump = true;
            straightJump = false;
            rightJump = true;
            leftJump = false;
            gravityTimer.stop();
            jumpTimer.start();
        }
    }
    public void leftJump(){
        if(isInJump == false){
            isInJump = true;
            straightJump = false;
            rightJump = false;
            leftJump = true;
            gravityTimer.stop();
            jumpTimer.start();
        }
    }
    
    /**
     * the move left action the game player calls for
     */
    public void moveRight() {
        if (isRightPressed){
        coordinates.squareMoveRight();
//        isRightPressed = true;
        }
    }
    
    /**
     * the move right action the game player calls for
     */
    public void moveLeft() {
        if(isLeftPressed){
            coordinates.squareMoveLeft();
//            isLeftPressed = true;
        }

    }
    
    /**
     * the vertical movement action for the vertical movement timer
     */
    private void verticalMoveAction() {
        update();
        checkVerticalDirection();
        checkSideCollisions();
        redraw();
    }
    
    /**
     * the gravity movement action for the gravity timer
     */
    private void gravityAction() {
        update();
        moveDown();
        checkDownwardCollisions();
        redraw();
    }
    
    /**
     * the jump movement action for the jump timer
     */
    private void jumpAction() {
        if (jumpHeight >= MAX_JUMP_HEIGHT) {
            gravityTimer.start();
            jumpTimer.stop();
            jumpHeight = 0;
        }
        else {
            
            if(straightJump){
                jumpHeight++;    
                update();
                coordinates.y = coordinates.y - GRAVITY_MOVE_AMOUNT;   
                coordinates.recalculate();
                redraw();
            }
            else if (rightJump){
                jumpHeight++;  
                update();
                int jumpingHeight = 1; //jumpHeight;
                int jumpLength = 10; //300
                int jumpAmount = MAX_JUMP_HEIGHT;
                int newY = coordinates.y - ((1/500) * ((jumpingHeight - (jumpLength / 2))*(jumpingHeight - (jumpLength / 2))) + jumpAmount);
                coordinates.y = newY - 5;
                coordinates.recalculate();
                redraw();           
            }
            else if (leftJump){
                jumpHeight++;  
                update();
                int jumpingHeight = 1; //jumpHeight;
                int jumpLength = 10; //300
                int jumpAmount = MAX_JUMP_HEIGHT;
                int newY = coordinates.y - ((1/500) * ((jumpingHeight - (jumpLength / 2))*(jumpingHeight - (jumpLength / 2))) + jumpAmount);
                coordinates.y = newY - 5;
                coordinates.recalculate();
                redraw(); 
            }
        }
    }    
    
    /**
     * checks the current vertical direction and moves
     */
    private void checkVerticalDirection() {
        if      (coordinates.direction == Directions.LEFT)  
            coordinates.moveLeft();
        else if (coordinates.direction == Directions.RIGHT) 
            coordinates.moveRight();
        else if (coordinates.direction == Directions.STOP)  
            coordinates.direction = Directions.STOP;
    }
    private void getLastWall(){
        for (int i = 0; i < walls.length; i++) {
            if (walls[i].coordinates.left < 200 ){
                if (i == 0){
                    lastY = walls[1].coordinates.y;
                    walls[2].lastYWall = lastY;
                }
                else if (i == 1){
                    lastY = walls[2].coordinates.y;
                    walls[0].lastYWall = lastY;
                }
                else if (i == 2){
                    lastY = walls[0].coordinates.y;
                    walls[1].lastYWall = lastY;
                }
            }
        }
//        System.out.println(lastY);
        
    }
    public int getLastY(){
//        walls[0].lastY = lastY;
        return lastY;
        
    }

    /**
     * checks for collisions against side objects
     */
    private void checkSideCollisions() {
        for (int i = 0; i < walls.length; i++) {
            if (isTouching(walls[i])) {
                stickTo(walls[i]);
            }
        }
        if (coordinates.right > gameScreen.getWidth() || coordinates.x < 0){
            stickToSide();
        }
        
    }

    /**
     * checks for collisions while moving down
     */
    private void checkDownwardCollisions() {
        if (isTouching(ground)) {
            verticalMoveTimer.stop();
            gravityTimer.stop();
            youDied();
                        
        }
        for (int i = 0; i < walls.length; i++) {
            if (isTouching(walls[i])) {
                landOn(walls[i]);
                i = walls.length;
            }
        } 
    } 
    
    /**
     * determines if the object is touching a wall
     * @param wall the wall object to check
     * @return is touching (true) or not (false)
     */
    private boolean isTouching(Wall wall) {
        if (isCollidingHorizontally(wall) &&
            isCollidingVertivally(wall)) {
            return true;
        }
        return false;
    }

    /**
     * sticks the coordinates to the wall object
     * @param wall the wall object to stick to
     */
    private void stickTo(Wall wall) {
        if (coordinates.direction == Directions.LEFT) 
            coordinates.x = wall.coordinates.right + 1;
        else if (coordinates.direction == Directions.RIGHT) 
            coordinates.x = wall.coordinates.left - 1;
        coordinates.recalculate();
    }

    /**
     * lands on the current wall object
     * @param wall the wall object to land on
     */
    private void landOn(Wall wall) {
        coordinates.y = wall.coordinates.top - coordinates.height - 1;
        coordinates.recalculate();
        isInJump = false;
    }
    
    /**
     * moves the objects down
     */
    private void moveDown() {
        coordinates.y = coordinates.y + GRAVITY_MOVE_AMOUNT;
        coordinates.recalculate();
    }

    private void stickToSide() {
        if(coordinates.direction == Directions.LEFT)
            coordinates.x = 1;
        else if (coordinates.direction == Directions.RIGHT)
            coordinates.x = gameScreen.getWidth() - gameScreen.squareWidth - 1;
    }

    private void youDied() {
        isAlive = false;
        Display.output("You hit the ground!" + "\n You scored " + gameScreen.scoreInt);
        gameScreen.dispose();
        StartScreen start = new StartScreen();
    }
    public boolean areAlive(){
        return isAlive;
    }
    
}
