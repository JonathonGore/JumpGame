
package jumpgame;

/**
 * required imports
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * StartScreen.java - the starting GUI for the application
 * @version 1.0
 * @since 2014-01-14
 * @author Jack Gore
 */
public class StartScreen extends JFrame 
{
    
    private JLabel lblJump;
    private JButton btnPlay;
    private JButton btnQuit;
    private Container container;
    public boolean isPlay = false;
    
    /**
     * default constructor for the class
     */
    public StartScreen() {        
        Font labelFont = new Font("Consolas", Font.BOLD, 72);
        final int LABEL_WIDTH = 200;
        final int LABEL_HEIGHT = 100;
        final int BUTTON_WIDTH = 100;
        final int BUTTON_HEIGHT = 25;
        container = this.getContentPane();
        container.setLayout(null);        
        lblJump = new JLabel("JUMP");
        btnPlay = new JButton("Play");
        btnQuit = new JButton("Quit");        
        container.add(lblJump);
        container.add(btnPlay);
        container.add(btnQuit);        
        lblJump.setFont(labelFont);
        lblJump.setForeground(Color.BLUE);
        lblJump.setBounds(165, 75, LABEL_WIDTH, LABEL_HEIGHT);        
        btnPlay.setBounds(115, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnPlay.setForeground(Color.red);        
        btnQuit.setBounds(300, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        btnQuit.setForeground(Color.red);        
        ActionListener playButton = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                play();
            }         
        };
        btnPlay.addActionListener(playButton);        
        ActionListener quitButton = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {                 
                thxPlaying();
                System.exit(0);                
            }         
        };
        btnQuit.addActionListener(quitButton); 
        this.setUndecorated(true);
        this.setTitle("JUMP");
        this.setSize(500, 300);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
    /**
     * ending game message
     */
    private void thxPlaying(){
        Display.output("Thanks for coming!\nGood-Bye!");
        System.exit(0);
    }
    
    /**
     * moves the application to the game GUI
     */
    private void play() {
        GameScreen gameScreeen = new GameScreen();
        this.dispose();
    }
    
}
