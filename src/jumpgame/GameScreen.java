
package jumpgame;

/**
 * required imports
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * GameScreen.java - the main game screen of the game
 * @version 1.0
 * @since 2014-01-14
 * @author Jack Gore
 */
public class GameScreen extends JFrame 
{
    
    private final int MAX_WALLS = 3;
    
    public  final int squareWidth = 42;
    ScoreKeeper score;
    public int scoreInt=0;
    private String scoreTxt = "";
    private Timer update;
    private boolean isAlive = true;
    
    private JLabel      lblSquare;          // GUI labels (images)
    private JLabel[]    lblWalls;
    private JLabel      lblGround;
    private JLabel      lblScore;
    private Container   container;          // GUI container
    private Player      player;             // Game objects
    private Wall[]      walls;
    private Wall        ground;
    private KeyListener keyListener;        // Action listener objects
    
    
    /**
     * default constructor for the class
     */    
    public GameScreen() {        
        setUpGUI();
        setGameObjects();
        setScoreKeeper();
        setKeyboardListening();
        startGame();
    }

    /**
     * keyboard press action 
     * @param e the registered key event
     */
    private void keyAction(KeyEvent e) {
        int key = e.getKeyCode();
        if      (key == KeyEvent.VK_SPACE && !player.isRightPressed && !player.isLeftPressed) player.jump();
        else if (key == KeyEvent.VK_SPACE && player.isRightPressed && !player.isLeftPressed)player.rightJump();
        else if (key == KeyEvent.VK_SPACE && player.isLeftPressed && !player.isRightPressed)player.leftJump();
//        else if (key == KeyEvent.VK_SPACE && player.isLeftPressed && player.isRightPressed)player.jump();
        else if (key == KeyEvent.VK_RIGHT) {
            player.isRightPressed = true;
            player.moveRight();
        }
        else if (key == KeyEvent.VK_LEFT) {
            player.isLeftPressed = true;
            player.moveLeft();
        }
        else if (key == KeyEvent.VK_Q)     thxPlaying();
    }
    
    /**
     * ending game message
     */
    private void thxPlaying(){
        Display.output("Thanks for coming!\nGood-Bye!");
        System.exit(0);
    }
    
    /**
     * set up the GUI for the game, placing all GUI components
     */
    private void setUpGUI() {
        // set container objecgt for GUI components        
        container = this.getContentPane();
        container.setLayout(null);
        // instantiate labels (images)
        Font scoreFont = new Font("Consolas", Font.BOLD, 38);
        startDelayTimer();
        lblGround = new JLabel();
        lblSquare = new JLabel();
        lblScore = new JLabel("0");
        lblWalls = new JLabel[MAX_WALLS];
        for (int i = 0; i < lblWalls.length; i++) {
            lblWalls[i] = new JLabel();
        }
        // create variables for positioning objects         
        final int wallSpacing = 285;
        final int frameWidth = 750;
        final int frameHeight = 500;
        final int wallWidth = 225;
        final int wallHeight = 20;
        final int squareHeight = 42;       

        final int wallStartX = (frameWidth / 2) - (wallWidth / 2);
        final int wallStartY = (frameHeight / 2) - (wallHeight / 2);
        final int wallTwoStartX = (wallStartX + wallSpacing + 50);
        final int wallThreeStartX = (wallTwoStartX + wallSpacing + 50);        
        final int groundHeight = 20;
        final int groundWidth = frameWidth;
        final int groundSrtX = 0;
        final int groundSrtY = (frameHeight - groundHeight);
        // add GUI components to container
        container.add(lblGround);
        container.add(lblSquare);
        container.add(lblScore);
        // set look and feel of walls
        for (int i = 0; i < lblWalls.length; i++) {
            container.add(lblWalls[i]);
            lblWalls[i].setOpaque(true);
            lblWalls[i].setBackground(Color.blue);
            lblWalls[i].setVisible(true);
        }
        lblWalls[0].setBounds(wallStartX, wallStartY, wallWidth, wallHeight);
        lblWalls[1].setBounds(wallTwoStartX, wallStartY, wallWidth, wallHeight);
        lblWalls[2].setBounds(wallThreeStartX, wallStartY, wallWidth, wallHeight);      
        // ground
        lblGround.setOpaque(true);
        lblGround.setBackground(Color.orange);
        lblGround.setVisible(true);
        lblGround.setBounds(groundSrtX, groundSrtY, groundWidth, groundHeight);
        // player
        lblSquare.setOpaque(true);
        lblSquare.setBackground(Color.red);
        lblSquare.setVisible(true);
        lblSquare.setBounds(wallStartX, (wallStartY - squareHeight - 1), squareWidth, squareHeight);
        
        lblScore.setOpaque(true);
        lblScore.setForeground(Color.red);
        lblScore.setFont(scoreFont);
        lblScore.setVisible(true);
        lblScore.setBounds(20, 20, 100, 50);
        // set look and feel of JFrame
        this.setSize(frameWidth, frameHeight);       
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * sets up the listening event for the keyboard
     */
    private void setKeyboardListening() {
        keyListener = new KeyListener() {
            public void keyTyped(KeyEvent e)    { keyAction(e); }
            public void keyPressed(KeyEvent e)  { keyAction(e); }
            public void keyReleased(KeyEvent e) { 

                releasedKey(e);
            }
        };
        container.addKeyListener(keyListener);
        this.addKeyListener(keyListener);
    }

    /**
     * creates the needed game objects
     */
    private void setGameObjects() {
        ground = new Wall(lblGround,this);
        walls = new Wall[MAX_WALLS];
        for (int i = 0; i < walls.length; i++) {
            walls[i]  = new Wall(lblWalls[i],this);
        }
        player = new Player(lblSquare,walls,ground,this);                
    }

    /**
     * starts the game
     */
    private void startGame() {
        player.start();
        for (int i = 0; i < walls.length; i++) {
            walls[i].start();
        } 
        this.setVisible(true);
    }
    private void releasedKey(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) player.isRightPressed = false;
        else if (key == KeyEvent.VK_LEFT) player.isLeftPressed = false;
    }

    private void setScoreKeeper() {
        score = new ScoreKeeper();
    }

    private void startDelayTimer() {
        int delay = 50;


        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                scoreInt = score.getScore();
                scoreTxt = Integer.toString(scoreInt);
                lblScore.setText(scoreTxt);
                isAlive = player.areAlive();
                if(!isAlive){
                    score.levelUpTimer.stop();
                }
                    
            }
        };
        update = new Timer(delay, actionListener);
        update.start();
    }
    
}

