
package jumpgame;

/**
 * required imports
 */
import javax.swing.JLabel;

/**
 * GameObject.java - represents an object in a game
 * @version 1.0
 * @since 2014-01-14
 * @author Jack Gore
 */
public class GameObject 
{
   
    public  Coordinates coordinates;
    public  JLabel      image;
    private boolean     isAlive;
    
    /**
     * default constructor for the class
     */
    public GameObject(){
        dispose();
        coordinates = new Coordinates();
        spawn();
    }
    
    /**
     * constructor for the class
     * @param image the image for this object
     */
    public GameObject(JLabel image){
        dispose();
        coordinates = new Coordinates();
        this.image = image;
        spawn();
    }
    
    /**
     * disposes of object resources
     */
    protected void dispose() {
        isAlive = false;
        if (coordinates != null) coordinates.dispose();
        image = null;
    }
    
    /**
     * spawns the object to be alive
     */
    protected void spawn(){
        isAlive = true;
        update();
    }
    
    /**
     * updates coordinates from the image location
     */
    public void update() {
        if (image != null) {
            coordinates.x = image.getX();
            coordinates.y = image.getY();
            coordinates.width = image.getWidth();
            coordinates.height = image.getHeight();            
        }
        coordinates.recalculate();
    }
    
    /**
     * determines if the object is colliding with another game object
     * vertically
     * @param target the game object to check against
     * @return it is colliding (true) or not (false)
     */
    public boolean isCollidingVertivally(GameObject target) {
        if (this.isAlive && target.isAlive){
            if (this.coordinates.top >= target.coordinates.top &&
                this.coordinates.top <= target.coordinates.bottom)
                return true;
            else if (target.coordinates.top >= this.coordinates.top &&
                     target.coordinates.top <= this.coordinates.bottom)
                return true;
            else if (this.coordinates.bottom >= target.coordinates.top &&
                     this.coordinates.bottom <= target.coordinates.bottom)
                return true;
            else if (target.coordinates.bottom >= this.coordinates.top &&
                     target.coordinates.bottom <= this.coordinates.bottom)
                return true;
        }        
        return false;
    }
    
    /**
     * determines if the object is colliding with another game object
     * horizontally
     * @param target the game object to check against
     * @return it is colliding (true) or not (false)
     */
    public boolean isCollidingHorizontally(GameObject target) {    
        if (this.isAlive && target.isAlive){
            if (this.coordinates.left >= target.coordinates.left &&
                this.coordinates.left <= target.coordinates.right)
                return true;
            else if (target.coordinates.left >= this.coordinates.left &&
                     target.coordinates.left <= this.coordinates.right)
                return true;
            else if (this.coordinates.right >= target.coordinates.left &&
                     this.coordinates.right <= target.coordinates.right)
                return true;
            else if (target.coordinates.right >= this.coordinates.left &&
                     target.coordinates.right <= this.coordinates.right)
                return true;
        }        
        return false;
    }
    
    /**
     * places the image at the stored coordinates
     */
    public void redraw() {
        image.setBounds(coordinates.x, coordinates.y, 
                coordinates.width, coordinates.height);
    }
    
}
