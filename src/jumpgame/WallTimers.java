
package jumpgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class WallTimers {
    
    private Timer wallMoveTimer;
    public Coordinates coordinates = new Coordinates();
    
    
    public WallTimers(){
        
        final int wallDelay = 50;
        final int wallMoveAmount = 50;

        
        
        ActionListener timerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    coordinates.moveLeft();
            }
        };
        wallMoveTimer = new Timer(wallDelay,timerListener);
        
    }
    
}
