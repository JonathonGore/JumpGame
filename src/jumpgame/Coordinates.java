
package jumpgame;

/**
 * Coordinates.java - stores the coordinates of an object
 * @version 1.0
 * @since 2014-01-14
 * @author Jack Gore
 */
public class Coordinates 
{
    
    public int x, y, left, right, top, bottom, 
               width, height, direction, amount, rightLeftAmount;
    
    /**
     * default constructor for the class
     */
    public Coordinates() {
        dispose();
    }

    /**
     * disposes of object resources
     */
    public void dispose() {
        x = y = left = right = top = bottom = 
            width = height = direction = amount = 0;
    }
    
    /**
     * moves the coordinates up
     */
    public void moveUp(){
        direction = Directions.DOWN;
        y = y - amount;
        recalculate();
    }
    
    /**
     * moves the coordinates down
     */
    public void moveDown(){
        direction = Directions.DOWN;
        y = y + amount;
        recalculate();
    }
    
    /**
     * moves the coordinates left
     */
    public void moveLeft(){
        direction = Directions.LEFT;
        x = x - amount;
        recalculate();
    }
    public void squareMoveLeft(){
        direction = Directions.LEFT;
        x = x - rightLeftAmount;
        recalculate();
    }
    public void squareMoveRight(){
        direction = Directions.RIGHT;
        x = x + rightLeftAmount;
        recalculate();
    }           
    /**
     * moves the coordinates right
     */
    public void moveRight(){
        direction = Directions.RIGHT;
        x = x + amount;
        recalculate();
    }

    /**
     * moves the coordinates north
     */
    public void moveNorth(){
        moveUp();
    }
    
    /**
     * moves the coordinates south
     */
    public void moveSouth(){
        moveDown();
    }
    
    /**
     * moves the coordinates east
     */
    public void moveEast(){
        moveRight();
    }
    
    /**
     * moves the coordinates west
     */
    public void moveWest(){
        moveLeft();
    }   
    
    /**
     * recalculates all other coordinates
     */
    public void recalculate() {
        left = x;
        top = y;
        right = left + width;
        bottom = top + height;        
    }

}
