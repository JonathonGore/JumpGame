
package jumpgame;

/**
 * required imports
 */
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Display.java - used for displaying messages
 * @version 1.0
 * @since 2014-01-14
 * @author Jack Gore
 */
public class Display 
{

    public static String title = "";
    public static JFrame gui   = null;
    
    /**
     * outputs the text in a dialog message box
     * @param text the text to display
     */
    public static void output(String text) {
        JOptionPane.showMessageDialog(gui,text,title,
                JOptionPane.PLAIN_MESSAGE,null);
    }

    /**
     * outputs the text to the system output
     * @param text the text to display 
     */
    static void print(String text) {
        System.out.println(text);
    }
    
}
